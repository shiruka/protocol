package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.Explode;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents explode packet encoders.
 */
@PacketId(23)
public final class ExplodeEncoderV291 extends PacketEncoder.Base<Explode> {

  @Override
  public void decode(
    @NotNull final Explode packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.position(buffer.readVector3f());
    packet.radius(buffer.readVarInt() / 32f);
    packet.records(buffer.readArrayUnsignedInt(buffer::readVector3i));
  }

  @Override
  public void encode(
    @NotNull final Explode packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeVector3f(packet.position());
    buffer.writeVarInt((int) (packet.radius() * 32));
    buffer.writeArrayUnsignedInt(packet.records(), buffer::writeVector3i);
  }
}
