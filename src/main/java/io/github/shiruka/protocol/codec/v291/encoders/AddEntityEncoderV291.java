package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.AddEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add entity packet encoders.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddEntityEncoderV291 implements PacketEncoder<AddEntity> {

  /**
   * the instance.
   */
  public static final AddEntityEncoderV291 INSTANCE = new AddEntityEncoderV291();

  @Override
  public void decode(@NotNull final AddEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.identifier(buffer.readString());
    packet.position(buffer.readVector3f());
    packet.motion(buffer.readVector3f());
    packet.rotation(buffer.readVector3f());
    buffer.readArray(packet.attributes(), b -> helper.readAttribute(buffer, session));
    helper.readEntityData(buffer, session, packet.metadata());
    buffer.readArray(packet.entityLinks(), b -> helper.readEntityLink(buffer, session));
  }

  @Override
  public void encode(@NotNull final AddEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedLong(packet.runtimeEntityId());
    buffer.writeString(packet.identifier());
    buffer.writeVector3f(packet.position());
    buffer.writeVector3f(packet.motion());
    buffer.writeVector3f(packet.rotation());
    buffer.writeArray(packet.attributes(), (b, data) -> helper.writeAttribute(b, session, data));
    helper.writeEntityData(buffer, session, packet.metadata());
    buffer.writeArray(packet.entityLinks(), (b, link) -> helper.writeEntityLink(b, session, link));
  }
}