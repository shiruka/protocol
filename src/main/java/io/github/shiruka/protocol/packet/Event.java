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
public final class Event extends MinecraftPacket.Base {

  Data data;

  long uniqueEntityId;

  byte usePlayerId;

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
     * the player died.
     */
    PLAYER_DIED,
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
     * the cauldron interact.
     */
    CAULDRON_INTERACT,
    /**
     * the composter interact.
     */
    COMPOSTER_INTERACT,
    /**
     * the bell used.
     */
    BELL_USED,
    /**
     * the entity definition trigger.
     */
    ENTITY_DEFINITION_TRIGGER,
    /**
     * the raid update.
     */
    RAID_UPDATE,
    /**
     * the movement anomaly.
     */
    MOVEMENT_ANOMALY,
    /**
     * the movement corrected.
     */
    MOVEMENT_CORRECTED,
    /**
     * the extract honey.
     */
    EXTRACT_HONEY,
    /**
     * the target block hit.
     */
    TARGET_BLOCK_HIT,
    /**
     * the piglin barter.
     */
    PIGLIN_BARTER,
    /**
     * the copper waxed or unwaxed.
     */
    COPPER_WAXED_OR_UNWAXED,
    /**
     * the code builder action.
     */
    CODE_BUILDER_ACTION,
    /**
     * the code builder scoreboard.
     */
    CODE_BUILDER_SCOREBOARD,
    /**
     * the strider ridden in lava in overworld.
     */
    STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD,
    /**
     * the sneak close to sculk sensor.
     */
    SNEAK_CLOSE_TO_SCULK_SENSOR;

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
