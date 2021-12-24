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
 * a class that represents add hanging entity packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class AddHangingEntity extends MinecraftPacket {

  /**
   * the direction.
   */
  private int direction;

  /**
   * the position.
   */
  private Vector3f position;

  /**
   * the runtime entity id.
   */
  private long runtimeEntityId;

  /**
   * the unique entity id.
   */
  private long uniqueEntityId;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
