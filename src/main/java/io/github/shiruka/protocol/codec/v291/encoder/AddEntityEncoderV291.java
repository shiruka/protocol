package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.AddEntity;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add entity packet encoders.
 */
@PacketId(13)
public final class AddEntityEncoderV291 extends PacketEncoder.Base<AddEntity> {

  @Override
  public void decode(
    @NotNull final AddEntity packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.identifier(buffer.readString());
    packet.position(buffer.readVector3f());
    packet.motion(buffer.readVector3f());
    packet.rotation(buffer.readVector3f());
    packet.attributes(
      buffer.readArrayUnsignedInt(() -> helper.readAttribute(buffer))
    );
    helper.readEntityData(buffer, packet.metadata());
    packet.entityLinks(
      buffer.readArrayUnsignedInt(() -> helper.readEntityLink(buffer))
    );
  }

  @Override
  public void encode(
    @NotNull final AddEntity packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedLong(packet.runtimeEntityId());
    buffer.writeString(packet.identifier());
    buffer.writeVector3f(packet.position());
    buffer.writeVector3f(packet.motion());
    buffer.writeVector3f(packet.rotation());
    buffer.writeArrayUnsignedInt(
      packet.attributes(),
      data -> helper.writeAttribute(buffer, data)
    );
    helper.writeEntityData(buffer, packet.metadata());
    buffer.writeArrayUnsignedInt(
      packet.entityLinks(),
      link -> helper.writeEntityLink(buffer, link)
    );
  }
}
