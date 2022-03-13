package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents move player packets.
 */
@Log4j2
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class MovePlayer extends MinecraftPacket.Base {

  //@formatter:off
  int entityType;
  Mode mode;
  boolean onGround;
  Vector3f position;
  long ridingRuntimeEntityId;
  Vector3f rotation;
  long runtimeEntityId;
  TeleportationCause teleportationCause;
  long tick;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that represents modes.
   */
  public enum Mode {
    /**
     * the normal.
     */
    NORMAL,
    /**
     * the respawn.
     */
    RESPAWN,
    /**
     * the teleport.
     */
    TELEPORT,
    /**
     * the head rotation.
     */
    HEAD_ROTATION;

    /**
     * the values.
     */
    public static final Mode[] VALUES = Mode.values();
  }

  /**
   * an enum class that contains teleportation causes.
   */
  public enum TeleportationCause {
    /**
     * the unknown.
     */
    UNKNOWN,
    /**
     * the projectile.
     */
    PROJECTILE,
    /**
     * the chorus fruit.
     */
    CHORUS_FRUIT,
    /**
     * the command.
     */
    COMMAND,
    /**
     * the behavior.
     */
    BEHAVIOR;

    /**
     * the values.
     */
    private static final TeleportationCause[] VALUES = TeleportationCause.values();

    /**
     * gets teleportation cause by ordinal.
     *
     * @param ordinal the ordinal to get.
     *
     * @return teleportation cause.
     */
    @NotNull
    public static TeleportationCause byOrdinal(final int ordinal) {
      return Optional.of(ordinal)
        .filter(id -> id >= 0 && id < TeleportationCause.VALUES.length)
        .map(id -> TeleportationCause.VALUES[id])
        .orElse(TeleportationCause.UNKNOWN);
    }
  }
}
