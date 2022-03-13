package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents network stack latency packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class NetworkStackLatency extends MinecraftPacket.Base {

  //@formatter:off
  private boolean fromServer;
  private long timestamp;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
