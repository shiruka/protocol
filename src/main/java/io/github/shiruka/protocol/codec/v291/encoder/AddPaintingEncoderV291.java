package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.AddPainting;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add painting packet encoders.
 */
@PacketId(22)
public final class AddPaintingEncoderV291 extends PacketEncoder.Base<AddPainting> {

  @Override
  public void decode(@NotNull final AddPainting packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.position(buffer.readVector3i().toFloat());
    packet.direction(buffer.readInt());
    packet.motive(buffer.readString());
  }

  @Override
  public void encode(@NotNull final AddPainting packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeVector3i(packet.position().toInt());
    buffer.writeVarInt(packet.direction());
    buffer.writeString(packet.motive());
  }
}
