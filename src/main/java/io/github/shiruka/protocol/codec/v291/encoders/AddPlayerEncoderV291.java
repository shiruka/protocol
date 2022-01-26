package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.AddPlayer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add player packet encoders.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddPlayerEncoderV291 implements PacketEncoder<AddPlayer> {

  /**
   * the instance.
   */
  public static final AddPlayerEncoderV291 INSTANCE = new AddPlayerEncoderV291();

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
    AdventureSettingsEncoderV291.INSTANCE.decode(packet.adventureSettings(), helper, buffer, session);
    buffer.readArray(packet.entityLinks(), b -> helper.readEntityLink(b, session));
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
    AdventureSettingsEncoderV291.INSTANCE.encode(packet.adventureSettings(), helper, buffer, session);
    buffer.writeArray(packet.entityLinks(), (b, link) -> helper.writeEntityLink(b, session, link));
    buffer.writeString(packet.deviceId());
  }
}