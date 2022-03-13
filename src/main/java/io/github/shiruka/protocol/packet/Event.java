package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
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
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class Event extends MinecraftPacket.Base {

  //@formatter:off
  Data data;
  long uniqueEntityId;
  byte usePlayerId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains event types.
   */
  public enum Type {
    /**
     * the achievement awarded.
     */
    ACHIEVEMENT_AWARDED,
    /**
     * the entity interact.
     */
    ENTITY_INTERACT,
    /**
     * the portal built.
     */
    PORTAL_BUILT,
    /**
     * the portal used.
     */
    PORTAL_USED,
    /**
     * the mob killed.
     */
    MOB_KILLED,
    /**
     * the cauldron used.
     */
    CAULDRON_USED,
    /**
     * the player death.
     */
    PLAYER_DEATH,
    /**
     * the boss killed.
     */
    BOSS_KILLED,
    /**
     * the agent command.
     */
    AGENT_COMMAND,
    /**
     * the agent created.
     */
    AGENT_CREATED,
    /**
     * the pattern removed.
     */
    PATTERN_REMOVED,
    /**
     * the slash command executed.
     */
    SLASH_COMMAND_EXECUTED,
    /**
     * the fish bucketed.
     */
    FISH_BUCKETED,
    /**
     * the mob born.
     */
    MOB_BORN,
    /**
     * the pet died.
     */
    PET_DIED,
    /**
     * the cauldron block used.
     */
    CAULDRON_BLOCK_USED,
    /**
     * the composter block used.
     */
    COMPOSTER_BLOCK_USED,
    /**
     * the bell block used.
     */
    BELL_BLOCK_USED;

    /**
     * the values.
     */
    public static final Type[] VALUES = Type.values();
  }

  /**
   * an interface to determine event data.
   */
  public interface Data {

    /**
     * obtains the type.
     *
     * @return type.
     */
    @NotNull
    Type type();
  }
}
