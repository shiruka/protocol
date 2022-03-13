package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.EntityEventType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents explode packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class EntityEvent extends MinecraftPacket.Base {

  //@formatter:off
  private int data;
  private long runtimeEntityId;
  private EntityEventType type;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
