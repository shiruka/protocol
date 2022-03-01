package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.BlockEntityData;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents block entity data packet encoders.
 */
public final class BlockEntityDataEncoderV291 extends PacketEncoder.Base<BlockEntityData> {

  /**
   * ctor.
   */
  private BlockEntityDataEncoderV291() {
    super(56);
  }

  @Override
  public void decode(@NotNull final BlockEntityData packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.blockPosition(buffer.readBlockPosition());
    packet.data(buffer.readCompoundTag());
  }

  @Override
  public void encode(@NotNull final BlockEntityData packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeBlockPosition(packet.blockPosition());
    buffer.writeCompoundTag(packet.data());
  }
}
