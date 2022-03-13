package io.github.shiruka.protocol.packet;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents camera packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class Interact extends MinecraftPacket.Base {

  //@formatter:off
  Action action;
  Vector3f mousePosition;
  long runtimeEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains interact actions.
   */
  public enum Action {
    /**
     * the none.
     */
    NONE,
    /**
     * the interact.
     */
    INTERACT,
    /**
     * the damage.
     */
    DAMAGE,
    /**
     * the leave vehicle.
     */
    LEAVE_VEHICLE,
    /**
     * the mouseover.
     */
    MOUSEOVER,
    /**
     * the npc open.
     */
    NPC_OPEN,
    /**
     * the open inventory.
     */
    OPEN_INVENTORY;

    /**
     * the values.
     */
    public static final Action[] VALUES = Action.values();
  }
}
