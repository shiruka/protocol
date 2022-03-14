package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.AddHangingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add hanging entity packet encoders.
 */
@PacketId(16)
public final class AddHangingEntityEncoderV291 extends PacketEncoder.Base<AddHangingEntity> {

  @Override
  public void decode(@NotNull final AddHangingEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.position(buffer.readVector3i().toFloat());
    packet.direction(buffer.readVarInt());
  }

  @Override
  public void encode(@NotNull final AddHangingEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeVector3i(packet.position().toInt());
    buffer.writeVarInt(packet.direction());
  }
}
