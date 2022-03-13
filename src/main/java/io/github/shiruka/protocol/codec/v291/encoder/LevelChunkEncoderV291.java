package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packet.LevelChunk;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents level chunk packet encoders.
 */
@PacketId(58)
public final class LevelChunkEncoderV291 extends PacketEncoder.Base<LevelChunk> {

  @Override
  public void decode(@NotNull final LevelChunk packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.chunkX(buffer.readVarInt());
    packet.chunkZ(buffer.readVarInt());
    final var length = buffer.readUnsignedVarInt();
    packet.data(buffer.readRetainedSlice(length).buffer());
  }

  @Override
  public void encode(@NotNull final LevelChunk packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarInt(packet.chunkX());
    buffer.writeVarInt(packet.chunkZ());
    final var data = new PacketBuffer(packet.data());
    buffer.writeUnsignedVarInt(data.remaining());
    buffer.writeBytes(data, data.readerIndex(), data.size());
  }
}
