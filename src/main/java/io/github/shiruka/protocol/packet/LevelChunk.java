package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents camera packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class LevelChunk extends MinecraftPacket.ReferencedCount {

  LongList blobIds;

  boolean cachingEnabled;

  int chunkX;

  int chunkZ;

  ByteBuf data;

  boolean requestSubChunks;

  int subChunkLimit;

  int subChunksLength;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  @Override
  public LevelChunk touch(final Object hint) {
    this.data.touch(hint);
    return this;
  }

  @Override
  protected void deallocate() {
    this.data.release();
  }
}
