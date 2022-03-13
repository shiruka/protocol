package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents tick sync packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class TickSync extends MinecraftPacket.Base {

  //@formatter:off
  private long requestTimestamp;
  private long responseTimestamp;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
