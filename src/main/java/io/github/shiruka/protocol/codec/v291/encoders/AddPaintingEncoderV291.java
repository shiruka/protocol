package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.AddPainting;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add painting packet encoders.
 */
@PacketId(22)
public final class AddPaintingEncoderV291 extends PacketEncoder.Base<AddPainting> {

  @Override
  public void decode(@NotNull final AddPainting packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var entity = packet.entity();
    entity.uniqueEntityId(buffer.readVarLong());
    entity.runtimeEntityId(buffer.readUnsignedVarLong());
    entity.position(buffer.readVector3i().toFloat());
    entity.direction(buffer.readInt());
    packet.motive(buffer.readString());
  }

  @Override
  public void encode(@NotNull final AddPainting packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var entity = packet.entity();
    buffer.writeVarLong(entity.uniqueEntityId());
    buffer.writeUnsignedVarLong(entity.runtimeEntityId());
    buffer.writeVector3i(entity.position().toInt());
    buffer.writeVarInt(entity.direction());
    buffer.writeString(packet.motive());
  }
}
