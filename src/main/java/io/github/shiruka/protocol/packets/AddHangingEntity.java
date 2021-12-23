package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents add hanging entity packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public class AddHangingEntity extends MinecraftPacket {

  /**
   * the direction.
   */
  @Getter
  private int direction;

  /**
   * the position.
   */
  @Nullable
  private Vector3f position;

  /**
   * the runtime entity id.
   */
  @Getter
  private long runtimeEntityId;

  /**
   * the unique entity id.
   */
  @Getter
  private long uniqueEntityId;

  /**
   * obtains the position.
   *
   * @return position.
   */
  @NotNull
  public final Vector3f position() {
    return Objects.requireNonNull(this.position, "position");
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
