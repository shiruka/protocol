package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents automation client connect packets.
 */
@Getter
@Setter
@ToString
@Accessors(fluent = true)
public class AutomationClientConnect extends MinecraftPacket {

  //@formatter:off
  private String address;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
