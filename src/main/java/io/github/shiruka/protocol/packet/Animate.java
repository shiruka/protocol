package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents animate packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class Animate extends MinecraftPacket.Base {

  Action action;

  float rowingTime;

  long runtimeEntityId;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains animate actions.
   */
  @Getter
  @Accessors(fluent = true)
  @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
  public enum Action {
    /**
     * the no action.
     */
    NO_ACTION(0),
    /**
     * the swing arm.
     */
    SWING_ARM(1),
    /**
     * the wake up.
     */
    WAKE_UP(3),
    /**
     * the critical hit.
     */
    CRITICAL_HIT(4),
    /**
     * the magic critical hit.
     */
    MAGIC_CRITICAL_HIT(5),
    /**
     * the row right.
     */
    ROW_RIGHT(128),
    /**
     * the row left.
     */
    ROW_LEFT(129);

    /**
     * the values.
     */
    public static final Action[] VALUES = Action.values();

    /**
     * the id.
     */
    private final int id;

    /**
     * gets action by id.
     *
     * @param id the id to get.
     *
     * @return action.
     */
    @NotNull
    public static Action byId(final int id) {
      return Arrays
        .stream(Action.VALUES)
        .filter(action1 -> action1.id == id)
        .findFirst()
        .orElseThrow();
    }
  }
}
