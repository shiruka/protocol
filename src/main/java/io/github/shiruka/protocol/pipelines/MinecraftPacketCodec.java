package io.github.shiruka.protocol.pipelines;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * a class that represents Minecraft packet codec.
 */
@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MinecraftPacketCodec extends MessageToMessageCodec<ByteBuf, List<MinecraftPacket>> {

  /**
   * the instance.
   */
  public static final MinecraftPacketCodec INSTANCE = new MinecraftPacketCodec();

  @Override
  protected void encode(final ChannelHandlerContext ctx, final List<MinecraftPacket> msg,
                        final List<Object> out) {
  }

  @Override
  protected void decode(final ChannelHandlerContext ctx, final ByteBuf msg, final List<Object> out) {
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
          packet.decode(packetBuffer);
          packets.add(packet);
        } catch (final Exception e) {
          MinecraftPacketCodec.log.debug("Error occurred whilst decoding packet!", e);
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
