package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.ItemFrameDropItem;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents item frame drop itme packet encoders.
 */
@PacketId(71)
public final class ItemFrameDropItemEncoderV291 extends PacketEncoder.Base<ItemFrameDropItem> {

  @Override
  public void decode(@NotNull final ItemFrameDropItem packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.blockPosition(buffer.readVector3i());
  }

  @Override
  public void encode(@NotNull final ItemFrameDropItem packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVector3i(packet.blockPosition());
  }
}
