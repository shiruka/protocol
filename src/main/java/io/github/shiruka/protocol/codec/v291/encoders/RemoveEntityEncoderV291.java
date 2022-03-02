package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.RemoveEntity;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents remove entity packet encoders.
 */
@PacketId(14)
public final class RemoveEntityEncoderV291 extends PacketEncoder.Base<RemoveEntity> {

  @Override
  public void decode(@NotNull final RemoveEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
  }

  @Override
  public void encode(@NotNull final RemoveEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
  }
}
