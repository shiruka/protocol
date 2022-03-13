package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents boss event packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class BossEvent extends MinecraftPacket.Base {

  //@formatter:off
  private Action action;
  private long bossUniqueEntityId;
  private int color;
  private int darkenSky;
  private float healthPercentage;
  private int overlay;
  private long playerUniqueEntityId;
  private String title;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains boss event actions.
   */
  public enum Action {
    /**
     * the create.
     */
    CREATE,
    /**
     * the register player.
     */
    REGISTER_PLAYER,
    /**
     * the remove.
     */
    REMOVE,
    /**
     * the unregister player.
     */
    UNREGISTER_PLAYER,
    /**
     * the update percentage.
     */
    UPDATE_PERCENTAGE,
    /**
     * the update name.
     */
    UPDATE_NAME,
    /**
     * the update properties.
     */
    UPDATE_PROPERTIES,
    /**
     * the update style.
     */
    UPDATE_STYLE,
    /**
     * the query.
     */
    QUERY;

    /**
     * the values.
     */
    public static final Action[] VALUES = Action.values();
  }
}
