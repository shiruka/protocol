package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents rider jump packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class RiderJump extends MinecraftPacket.Base {

  //@formatter:off
  private int jumpStrength;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
