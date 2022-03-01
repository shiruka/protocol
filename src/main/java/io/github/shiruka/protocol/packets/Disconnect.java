package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents disconnect packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class Disconnect extends MinecraftPacket {

  //@formatter:off
  private String kickMessage;
  private boolean messageSkipped;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
