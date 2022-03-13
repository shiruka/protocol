package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents change dimension packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class ChangeDimension extends MinecraftPacket.Base {

  //@formatter:off
  private int dimension;
  private Vector3f position;
  private boolean respawn;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
