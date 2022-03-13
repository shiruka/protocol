package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.InventorySlot;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents inventory slot packet encoders.
 */
@PacketId(50)
public final class InventorySlotEncoderV291 extends PacketEncoder.Base<InventorySlot> {

  @Override
  public void decode(@NotNull final InventorySlot packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.containerId(buffer.readUnsignedVarInt());
    packet.slot(buffer.readUnsignedVarInt());
    packet.item(helper.readItem(buffer));
  }

  @Override
  public void encode(@NotNull final InventorySlot packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarInt(packet.containerId());
    buffer.writeUnsignedVarInt(packet.slot());
    helper.writeItem(buffer, packet.item());
  }
}
