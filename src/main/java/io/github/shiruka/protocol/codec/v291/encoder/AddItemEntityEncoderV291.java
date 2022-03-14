package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.AddItemEntity;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add item entity packet encoders.
 */
@PacketId(15)
public final class AddItemEntityEncoderV291 extends PacketEncoder.Base<AddItemEntity> {

  @Override
  public void decode(@NotNull final AddItemEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.itemInHand(helper.readItem(buffer));
    packet.position(buffer.readVector3f());
    packet.motion(buffer.readVector3f());
    helper.readEntityData(buffer, packet.metadata());
    packet.fromFishing(buffer.readBoolean());
  }

  @Override
  public void encode(@NotNull final AddItemEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    helper.writeItem(buffer, packet.itemInHand());
    buffer.writeVector3f(packet.position());
    buffer.writeVector3f(packet.motion());
    helper.writeEntityData(buffer, packet.metadata());
    buffer.writeBoolean(packet.fromFishing());
  }
}
