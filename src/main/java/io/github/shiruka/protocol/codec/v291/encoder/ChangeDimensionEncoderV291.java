package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.ChangeDimension;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents change dimension packet encoders.
 */
@PacketId(61)
public final class ChangeDimensionEncoderV291
  extends PacketEncoder.Base<ChangeDimension> {

  @Override
  public void decode(
    @NotNull final ChangeDimension packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.dimension(buffer.readVarInt());
    packet.position(buffer.readVector3f());
    packet.respawn(buffer.readBoolean());
  }

  @Override
  public void encode(
    @NotNull final ChangeDimension packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeVarInt(packet.dimension());
    buffer.writeVector3f(packet.position());
    buffer.writeBoolean(packet.respawn());
  }
}
