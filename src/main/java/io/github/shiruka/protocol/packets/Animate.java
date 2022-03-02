package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents animate packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class Animate extends MinecraftPacket {

  //@formatter:off
  private Action action;
  private float rowingTime;
  private long runtimeEntityId;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains animate actions.
   */
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
    @Getter
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
      return Arrays.stream(Action.VALUES)
        .filter(action1 -> action1.id == id)
        .findFirst()
        .orElseThrow();
    }
  }
}
