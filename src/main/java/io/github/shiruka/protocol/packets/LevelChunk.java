package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents camera packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class LevelChunk extends MinecraftPacket.ReferencedCount {

  //@formatter:off
  private LongList blobIds;
  private boolean cachingEnabled;
  private int chunkX;
  private int chunkZ;
  private ByteBuf data;
  private boolean requestSubChunks;
  private int subChunkLimit;
  private int subChunksLength;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
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
