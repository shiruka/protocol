package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.BlockEvent;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents block event packet encoders.
 */
@PacketId(26)
public final class BlockEventEncoderV291
  extends PacketEncoder.Base<BlockEvent> {

  @Override
  public void decode(
    @NotNull final BlockEvent packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.blockPosition(buffer.readVector3i());
    packet.eventType(buffer.readVarInt());
    packet.eventData(buffer.readVarInt());
  }

  @Override
  public void encode(
    @NotNull final BlockEvent packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeVector3i(packet.blockPosition());
    buffer.writeVarInt(packet.eventType());
    buffer.writeVarInt(packet.eventData());
  }
}
