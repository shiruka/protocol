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

  /**
   * the flag on ground.
   */
  private static final int FLAG_ON_GROUND = 1;

  /**
   * the flag teleported.
   */
  private static final int FLAG_TELEPORTED = 2;

  /**
   * the on ground.
   */
  private boolean onGround;

  /**
   * the position.
   */
  private Vector3f position;

  /**
   * the rotation.
   */
  private Vector3f rotation;

  /**
   * the runtime entity id.
   */
  private long runtimeEntityId;

  /**
   * the teleported.
   */
  private boolean teleported;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
