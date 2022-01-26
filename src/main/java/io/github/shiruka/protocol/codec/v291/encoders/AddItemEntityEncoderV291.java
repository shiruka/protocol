package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.AddItemEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add item entity packet encoders.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddItemEntityEncoderV291 implements PacketEncoder<AddItemEntity> {

  /**
   * the instance.
   */
  public static final AddItemEntityEncoderV291 INSTANCE = new AddItemEntityEncoderV291();

  @Override
  public void decode(@NotNull final AddItemEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.itemInHand(helper.readItem(buffer, session));
    packet.position(buffer.readVector3f());
    packet.motion(buffer.readVector3f());
    helper.readEntityData(buffer, session, packet.metadata());
    packet.fromFishing(buffer.readBoolean());
  }

  @Override
  public void encode(@NotNull final AddItemEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    helper.writeItem(buffer, session, packet.itemInHand());
    buffer.writeVector3f(packet.position());
    buffer.writeVector3f(packet.motion());
    helper.writeEntityData(buffer, session, packet.metadata());
    buffer.writeBoolean(packet.fromFishing());
  }
}