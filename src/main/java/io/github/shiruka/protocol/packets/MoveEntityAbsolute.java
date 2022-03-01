package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents move entity absolute packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class MoveEntityAbsolute extends MinecraftPacket {

  //@formatter:off
  private static final int FLAG_ON_GROUND = 1;
  private static final int FLAG_TELEPORTED = 2;
  private boolean onGround;
  private Vector3f position;
  private Vector3f rotation;
  private long runtimeEntityId;
  private boolean teleported;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
