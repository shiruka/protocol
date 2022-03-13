package io.github.shiruka.protocol.packet;

import io.github.shiruka.api.common.vectors.Vector3i;
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
 * a class that represents lab table packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class LabTable extends MinecraftPacket.Base {

  //@formatter:off
  Vector3i position;
  ReactionType reactionType;
  Type type;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains lab table reaction types.
   */
  public enum ReactionType {
    /**
     * the none.
     */
    NONE,
    /**
     * the ice bomb.
     */
    ICE_BOMB,
    /**
     * the bleach.
     */
    BLEACH,
    /**
     * the elephant toothpaste.
     */
    ELEPHANT_TOOTHPASTE,
    /**
     * the fertilizer.
     */
    FERTILIZER,
    /**
     * the heat block.
     */
    HEAT_BLOCK,
    /**
     * the magnesium salts.
     */
    MAGNESIUM_SALTS,
    /**
     * the misc fire.
     */
    MISC_FIRE,
    /**
     * the misc explosion.
     */
    MISC_EXPLOSION,
    /**
     * the misc laval.
     */
    MISC_LAVAL,
    /**
     * the misc mystical.
     */
    MISC_MYSTICAL,
    /**
     * the misc smoke.
     */
    MISC_SMOKE,
    /**
     * the misc large smoke.
     */
    MISC_LARGE_SMOKE;

    /**
     * the values.
     */
    public static final ReactionType[] VALUES = ReactionType.values();
  }

  /**
   * an enum class that contains lab table types.
   */
  public enum Type {
    /**
     * the start combine.
     */
    START_COMBINE,
    /**
     * the start reaction.
     */
    START_REACTION,
    /**
     * the reset.
     */
    RESET;

    /**
     * the values.
     */
    public static final Type[] VALUES = Type.values();
  }
}
