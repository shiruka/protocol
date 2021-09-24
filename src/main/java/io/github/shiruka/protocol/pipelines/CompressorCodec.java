package io.github.shiruka.protocol.pipelines;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents compressor pipelines.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CompressorCodec extends MessageToMessageCodec<ByteBuf, ByteBuf> {

  /**
   * the instance.
   */
  public static final CompressorCodec INSTANCE = new CompressorCodec();

  /**
   * the name.
   */
  public static final String NAME = "rn-mc-compressor";

  /**
   * the chunk.
   */
  private static final int CHUNK = 8192;

  /**
   * the max size.
   */
  private static final int MAX_SIZE = 12 * 1024 * 1024;

  /**
   * the deflater.
   */
  private final ThreadLocal<Deflater> deflater = ThreadLocal.withInitial(() -> new Deflater(7, true));

  /**
   * the inflater.
   */
  private final ThreadLocal<Inflater> inflater = ThreadLocal.withInitial(() -> new Inflater(true));

  @Override
  protected void encode(final ChannelHandlerContext ctx, final ByteBuf msg, final List<Object> out) throws Exception {
    final var decompressed = ctx.alloc().ioBuffer();
    @Nullable ByteBuf source = null;
    try {
      if (msg.isDirect()) {
        source = msg;
      } else {
        final var temp = ctx.alloc().ioBuffer();
        temp.writeBytes(msg);
        source = temp;
      }
      final var inflater = this.inflater.get();
      inflater.reset();
      inflater.setInput(source.internalNioBuffer(source.readerIndex(), source.readableBytes()));
      inflater.finished();
      while (!inflater.finished()) {
        decompressed.ensureWritable(CompressorCodec.CHUNK);
        final var index = decompressed.writerIndex();
        final var written = inflater.inflate(decompressed.internalNioBuffer(index, CompressorCodec.CHUNK));
        if (written < 1) {
          break;
        }
        decompressed.writerIndex(index + written);
        if (CompressorCodec.MAX_SIZE > 0 && decompressed.writerIndex() >= CompressorCodec.MAX_SIZE) {
          throw new DataFormatException("Inflated data exceeds maximum size!");
        }
      }
      out.add(decompressed);
    } catch (final DataFormatException e) {
      decompressed.release();
      throw e;
    } finally {
      if (source != null && source != msg) {
        source.release();
      }
    }
  }

  @Override
  protected void decode(final ChannelHandlerContext ctx, final ByteBuf msg, final List<Object> out) {
    final var compressed = ctx.alloc().ioBuffer();
    @Nullable ByteBuf source = null;
    try {
      if (msg.isDirect()) {
        source = msg;
      } else {
        source = ctx.alloc().ioBuffer();
        source.writeBytes(msg);
      }
      final var deflater = this.deflater.get();
      deflater.reset();
      deflater.setLevel(Deflater.DEFAULT_COMPRESSION);
      deflater.setInput(source.internalNioBuffer(source.readerIndex(), source.readableBytes()));
      while (!deflater.finished()) {
        final var index = compressed.writerIndex();
        compressed.ensureWritable(CompressorCodec.CHUNK);
        final var written = deflater.deflate(compressed.internalNioBuffer(index, CompressorCodec.CHUNK));
        compressed.writerIndex(index + written);
      }
      out.add(compressed);
    } finally {
      if (source != null && source != msg) {
        source.release();
      }
    }
  }
}
