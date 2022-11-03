package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.AddPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add player packet encoders.
 */
@PacketId(12)
public final class AddPlayerEncoderV291 extends PacketEncoder.Base<AddPlayer> {

  @Override
  public void decode(
    @NotNull final AddPlayer packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.uuid(buffer.readUUID());
    packet.username(buffer.readString());
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.platformChatId(buffer.readString());
    packet.position(buffer.readVector3f());
    packet.motion(buffer.readVector3f());
    packet.rotation(buffer.readVector3f());
    packet.hand(helper.readItem(buffer));
    helper.readEntityData(buffer, packet.metadata());
    helper.readAdventureSettings(packet.adventureSettings(), buffer);
    packet.entityLinks(
      buffer.readArrayUnsignedInt(() -> helper.readEntityLink(buffer))
    );
    packet.deviceId(buffer.readString());
  }

  @Override
  public void encode(
    @NotNull final AddPlayer packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeUUID(packet.uuid());
    buffer.writeString(packet.username());
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeString(packet.platformChatId());
    buffer.writeVector3f(packet.position());
    buffer.writeVector3f(packet.motion());
    buffer.writeVector3f(packet.rotation());
    helper.writeItem(buffer, packet.hand());
    helper.writeEntityData(buffer, packet.metadata());
    helper.writeAdventureSettings(packet.adventureSettings(), buffer);
    buffer.writeArrayUnsignedInt(
      packet.entityLinks(),
      link -> helper.writeEntityLink(buffer, link)
    );
    buffer.writeString(packet.deviceId());
  }
}
