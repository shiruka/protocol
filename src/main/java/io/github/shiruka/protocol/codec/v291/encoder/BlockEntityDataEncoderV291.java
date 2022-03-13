package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packet.BlockEntityData;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents block entity data packet encoders.
 */
@PacketId(56)
public final class BlockEntityDataEncoderV291 extends PacketEncoder.Base<BlockEntityData> {

  @Override
  public void decode(@NotNull final BlockEntityData packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.blockPosition(buffer.readVector3i());
    packet.data(buffer.readCompoundTag());
  }

  @Override
  public void encode(@NotNull final BlockEntityData packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVector3i(packet.blockPosition());
    buffer.writeCompoundTag(packet.data());
  }
}
