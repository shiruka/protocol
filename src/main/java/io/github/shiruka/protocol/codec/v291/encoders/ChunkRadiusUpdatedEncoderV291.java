package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.ChunkRadiusUpdated;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents chunk radius updated packet encoders.
 */
@PacketId(70)
public final class ChunkRadiusUpdatedEncoderV291 extends PacketEncoder.Base<ChunkRadiusUpdated> {

  @Override
  public void decode(@NotNull final ChunkRadiusUpdated packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.radius(buffer.readVarInt());
  }

  @Override
  public void encode(@NotNull final ChunkRadiusUpdated packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarInt(packet.radius());
  }
}
