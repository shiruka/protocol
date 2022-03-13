package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
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
public final class TakeItemEntity extends MinecraftPacket.Base {

  //@formatter:off
  private long itemRuntimeEntityId;
  private long runtimeEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
