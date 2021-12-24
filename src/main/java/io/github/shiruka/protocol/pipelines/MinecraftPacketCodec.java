package io.github.shiruka.protocol.pipelines;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketRegistry;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents Minecraft packet codec pipelines.
 */
@Log4j2
@RequiredArgsConstructor
public final class MinecraftPacketCodec extends MessageToMessageCodec<ByteBuf, List<MinecraftPacket>> {

  /**
   * the name.
   */
  public static final String NAME = "rn-mc-codec";

  /**
   * the server.
   */
  @NotNull
  private final MinecraftServer server;

  @Override
  protected void encode(final ChannelHandlerContext ctx, final List<MinecraftPacket> msg,
                        final List<Object> out) {
    final var session = MinecraftChildChannel.cast(ctx);
    final var uncompressed = new PacketBuffer(ctx.alloc().ioBuffer());
    final var uncompressedBuffer = uncompressed.buffer();
    try {
      for (final var packet : msg) {
        final var packetBuffer = new PacketBuffer(ctx.alloc().ioBuffer());
        final var packetBufferBuffer = packetBuffer.buffer();
        try {
          final var id = packet.packetId();
          var header = 0;
          header |= id & 0x3ff;
          header |= (packet.senderId() & 3) << 10;
          header |= (packet.clientId() & 3) << 12;
          packetBuffer.writeUnsignedVarInt(header);
          packet.encode(packetBuffer, session);
          uncompressed.writeUnsignedVarInt(packetBuffer.remaining());
          uncompressedBuffer.writeBytes(packetBufferBuffer);
        } catch (final Exception e) {
          MinecraftPacketCodec.log.error("Error occurred whilst decoding packet!", e);
          if (MinecraftPacketCodec.log.isTraceEnabled()) {
            MinecraftPacketCodec.log.trace("Packet contents\n{}", ByteBufUtil.prettyHexDump(packetBufferBuffer.readerIndex(0)));
          }
        } finally {
          packetBuffer.release();
        }
      }
    } finally {
      out.add(uncompressedBuffer);
    }
  }

  @Override
  protected void decode(final ChannelHandlerContext ctx, final ByteBuf msg, final List<Object> out) {
    final var session = MinecraftChildChannel.cast(ctx);
    final var packets = new ArrayList<MinecraftPacket>();
    try {
      final var buffer = new PacketBuffer(msg);
      while (buffer.isReadable()) {
        final var packetBuffer = new PacketBuffer(buffer.readSlice());
        if (!packetBuffer.isReadable()) {
          throw new DataFormatException("Packet cannot be empty");
        }
        try {
          final var header = packetBuffer.readUnsignedVarInt();
          final var packetId = header & 0x3ff;
          final var packet = PacketRegistry.get(packetId);
          packet.packetId(packetId);
          packet.senderId(header >>> 10 & 3);
          packet.clientId(header >>> 12 & 3);
          packet.decode(packetBuffer, session);
          packets.add(packet);
        } catch (final Exception e) {
          MinecraftPacketCodec.log.error("Error occurred whilst decoding packet!", e);
          if (MinecraftPacketCodec.log.isTraceEnabled()) {
            MinecraftPacketCodec.log.trace("Packet contents\n{}", ByteBufUtil.prettyHexDump(packetBuffer.buffer().readerIndex(0)));
          }
        }
      }
    } catch (final Exception e) {
      throw new RuntimeException(e);
    } finally {
      out.add(packets);
    }
  }
}
