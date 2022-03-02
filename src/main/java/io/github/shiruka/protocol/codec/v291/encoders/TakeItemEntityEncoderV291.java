package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.TakeItemEntity;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents take item entity packet encoders.
 */
@PacketId(17)
public final class TakeItemEntityEncoderV291 extends PacketEncoder.Base<TakeItemEntity> {

  @Override
  public void decode(@NotNull final TakeItemEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.itemRuntimeEntityId(buffer.readUnsignedVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
  }

  @Override
  public void encode(@NotNull final TakeItemEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarLong(packet.itemRuntimeEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
  }
}
