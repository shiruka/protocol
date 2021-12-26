package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.api.nbt.CompoundTag;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents block entity data packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class BlockEntityData extends MinecraftPacket {

  /**
   * the block position.
   */
  private Vector3i blockPosition;

  /**
   * the data.
   */
  private CompoundTag data;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
