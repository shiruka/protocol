package io.github.shiruka.protocol.packet;

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
 * a class that represents boss event packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class BossEvent extends MinecraftPacket.Base {

  //@formatter:off
  Action action;
  long bossUniqueEntityId;
  int color;
  int darkenSky;
  float healthPercentage;
  int overlay;
  long playerUniqueEntityId;
  String title;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
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
