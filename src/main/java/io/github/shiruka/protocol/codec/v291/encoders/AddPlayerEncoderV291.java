package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.AddPlayer;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add player packet encoders.
 */
@PacketId(12)
public final class AddPlayerEncoderV291 extends PacketEncoder.Base<AddPlayer> {

  @Override
  public void decode(@NotNull final AddPlayer packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uuid(buffer.readUUID());
    packet.username(buffer.readString());
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.platformChatId(buffer.readString());
    packet.position(buffer.readVector3f());
    packet.motion(buffer.readVector3f());
    packet.rotation(buffer.readVector3f());
    packet.hand(helper.readItem(buffer, session));
    helper.readEntityData(buffer, session, packet.metadata());
    helper.readAdventureSettings(packet.adventureSettings(), buffer);
    packet.entityLinks(buffer.readArrayUnsignedInt(() -> helper.readEntityLink(buffer, session)));
    packet.deviceId(buffer.readString());
  }

  @Override
  public void encode(@NotNull final AddPlayer packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUUID(packet.uuid());
    buffer.writeString(packet.username());
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeString(packet.platformChatId());
    buffer.writeVector3f(packet.position());
    buffer.writeVector3f(packet.motion());
    buffer.writeVector3f(packet.rotation());
    helper.writeItem(buffer, session, packet.hand());
    helper.writeEntityData(buffer, session, packet.metadata());
    helper.writeAdventureSettings(packet.adventureSettings(), buffer);
    buffer.writeArrayUnsignedInt(packet.entityLinks(), link -> helper.writeEntityLink(buffer, session, link));
    buffer.writeString(packet.deviceId());
  }
}
