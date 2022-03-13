package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents block pick request packet.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class BlockPickRequest extends MinecraftPacket.Base {

  //@formatter:off
  private boolean addUserData;
  private Vector3i blockPosition;
  private int hotBarSlot;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
