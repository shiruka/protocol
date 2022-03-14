package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.InventoryContent;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents inventory content packet encoders.
 */
@PacketId(49)
public final class InventoryContentEncoderV291 extends PacketEncoder.Base<InventoryContent> {

  @Override
  public void decode(@NotNull final InventoryContent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.containerId(buffer.readUnsignedVarInt());
    packet.contents(buffer.readArrayUnsignedInt(() ->
      helper.readItem(buffer)));
  }

  @Override
  public void encode(@NotNull final InventoryContent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarInt(packet.containerId());
    buffer.writeArrayUnsignedInt(packet.contents(), item ->
      helper.writeItem(buffer, item));
  }
}
