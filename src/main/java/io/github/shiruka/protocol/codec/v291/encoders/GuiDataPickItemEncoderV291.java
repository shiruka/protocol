package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.GuiDataPickItem;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents gui data pick item packet encoders.
 */
@PacketId(54)
public final class GuiDataPickItemEncoderV291 extends PacketEncoder.Base<GuiDataPickItem> {

  @Override
  public void decode(@NotNull final GuiDataPickItem packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.description(buffer.readString());
    packet.itemEffects(buffer.readString());
    packet.hotBarSlot(buffer.readIntLE());
  }

  @Override
  public void encode(@NotNull final GuiDataPickItem packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeString(packet.description());
    buffer.writeString(packet.itemEffects());
    buffer.writeIntLE(packet.hotBarSlot());
  }
}
