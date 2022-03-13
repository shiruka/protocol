package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents gui data pick item packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class GuiDataPickItem extends MinecraftPacket.Base {

  //@formatter:off
  private String description;
  private int hotBarSlot;
  private String itemEffects;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
