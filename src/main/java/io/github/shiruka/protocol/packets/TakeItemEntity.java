package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents take item entity packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class TakeItemEntity extends MinecraftPacket {

  /**
   * the item runtime entity id.
   */
  private long itemRuntimeEntityId;

  /**
   * the runtime entity id.
   */
  private long runtimeEntityId;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
