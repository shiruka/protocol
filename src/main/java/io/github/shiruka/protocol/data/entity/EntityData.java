package io.github.shiruka.protocol.data.entity;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains entity data.
 */
public enum EntityData {
  FLAGS(true, EntityDataType.FLAGS),
  HEALTH(EntityDataType.INT),
  VARIANT(EntityDataType.INT),
  COLOR(EntityDataType.BYTE),
  NAMETAG(EntityDataType.STRING),
  OWNER_EID(EntityDataType.LONG),
  TARGET_EID(EntityDataType.LONG),
  AIR_SUPPLY(EntityDataType.SHORT),
  EFFECT_COLOR(EntityDataType.INT),
  EFFECT_AMBIENT(EntityDataType.BYTE),
  JUMP_DURATION(EntityDataType.BYTE),
  HURT_TIME(EntityDataType.INT),
  HURT_DIRECTION(EntityDataType.INT),
  ROW_TIME_LEFT(EntityDataType.FLOAT),
  ROW_TIME_RIGHT(EntityDataType.FLOAT),
  EXPERIENCE_VALUE(EntityDataType.INT),
  DISPLAY_ITEM(EntityDataType.INT),
  DISPLAY_OFFSET(EntityDataType.LONG, EntityDataType.INT),
  CUSTOM_DISPLAY(EntityDataType.BYTE),
  SWELL(EntityDataType.INT),
  OLD_SWELL(EntityDataType.INT),
  SWELL_DIRECTION(EntityDataType.INT),
  CHARGE_AMOUNT(EntityDataType.BYTE),
  CARRIED_BLOCK(EntityDataType.INT),
  CLIENT_EVENT(EntityDataType.BYTE),
  USING_ITEM(EntityDataType.BYTE),
  PLAYER_FLAGS(EntityDataType.BYTE),
  PLAYER_INDEX(EntityDataType.INT),
  BED_POSITION(EntityDataType.VECTOR3I),
  X_POWER(EntityDataType.FLOAT),
  Y_POWER(EntityDataType.FLOAT),
  Z_POWER(EntityDataType.FLOAT),
  AUX_POWER,
  FISH_X,
  FISH_Z,
  FISH_ANGLE,
  POTION_AUX_VALUE(EntityDataType.SHORT),
  LEASH_HOLDER_EID(EntityDataType.LONG),
  SCALE(EntityDataType.FLOAT),
  HAS_NPC_COMPONENT(EntityDataType.BYTE),
  SKIN_ID(EntityDataType.STRING),
  NPC_SKIN_ID(EntityDataType.STRING),
  NPC_DATA,
  URL_TAG(EntityDataType.STRING),
  MAX_AIR_SUPPLY(EntityDataType.SHORT),
  MARK_VARIANT(EntityDataType.INT),
  CONTAINER_TYPE(EntityDataType.BYTE),
  CONTAINER_BASE_SIZE(EntityDataType.INT),
  CONTAINER_STRENGTH_MODIFIER(EntityDataType.INT),
  BLOCK_TARGET(EntityDataType.VECTOR3I),
  WITHER_INVULNERABLE_TICKS(EntityDataType.INT),
  WITHER_TARGET_1(EntityDataType.LONG),
  WITHER_TARGET_2(EntityDataType.LONG),
  WITHER_TARGET_3(EntityDataType.LONG),
  WITHER_AERIAL_ATTACK(EntityDataType.SHORT),
  BOUNDING_BOX_WIDTH(EntityDataType.FLOAT),
  BOUNDING_BOX_HEIGHT(EntityDataType.FLOAT),
  FUSE_LENGTH(EntityDataType.INT),
  RIDER_SEAT_POSITION(EntityDataType.VECTOR3F),
  RIDER_ROTATION_LOCKED(EntityDataType.BYTE),
  RIDER_MAX_ROTATION(EntityDataType.FLOAT),
  RIDER_MIN_ROTATION(EntityDataType.FLOAT),
  RIDER_ROTATION_OFFSET,
  AREA_EFFECT_CLOUD_RADIUS(EntityDataType.FLOAT),
  AREA_EFFECT_CLOUD_WAITING(EntityDataType.INT),
  AREA_EFFECT_CLOUD_PARTICLE_ID(EntityDataType.INT),
  SHULKER_PEAK_HEIGHT(EntityDataType.INT),
  SHULKER_PEEK_ID,
  SHULKER_ATTACH_FACE(EntityDataType.BYTE),
  SHULKER_ATTACH_POS(EntityDataType.VECTOR3I),
  TRADE_TARGET_EID(EntityDataType.LONG),
  COMMAND_BLOCK_ENABLED(EntityDataType.BYTE),
  COMMAND_BLOCK_COMMAND(EntityDataType.STRING),
  COMMAND_BLOCK_LAST_OUTPUT(EntityDataType.STRING),
  COMMAND_BLOCK_TRACK_OUTPUT(EntityDataType.BYTE),
  CONTROLLING_RIDER_SEAT_INDEX(EntityDataType.BYTE),
  STRENGTH(EntityDataType.INT),
  MAX_STRENGTH(EntityDataType.INT),
  EVOKER_SPELL_COLOR(EntityDataType.INT),
  LIMITED_LIFE(EntityDataType.INT),
  ARMOR_STAND_POSE_INDEX(EntityDataType.INT),
  ENDER_CRYSTAL_TIME_OFFSET(EntityDataType.INT),
  NAMETAG_ALWAYS_SHOW(EntityDataType.BYTE),
  COLOR_2(EntityDataType.BYTE),
  SCORE_TAG(EntityDataType.STRING),
  BALLOON_ATTACHED_ENTITY(EntityDataType.LONG),
  PUFFERFISH_SIZE(EntityDataType.BYTE),
  BOAT_BUBBLE_TIME(EntityDataType.INT),
  AGENT_ID(EntityDataType.LONG),
  SITTING_AMOUNT,
  SITTING_AMOUNT_PREVIOUS,
  EATING_COUNTER(EntityDataType.INT),
  FLAGS_2(true, EntityDataType.FLAGS),
  LAYING_AMOUNT,
  LAYING_AMOUNT_PREVIOUS,
  AREA_EFFECT_CLOUD_DURATION(EntityDataType.INT),
  AREA_EFFECT_CLOUD_SPAWN_TIME(EntityDataType.INT),
  AREA_EFFECT_CLOUD_CHANGE_RATE(EntityDataType.FLOAT),
  AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP(EntityDataType.FLOAT),
  AREA_EFFECT_CLOUD_COUNT(EntityDataType.INT),
  INTERACTIVE_TAG(EntityDataType.STRING),
  TRADE_TIER(EntityDataType.INT),
  MAX_TRADE_TIER(EntityDataType.INT),
  TRADE_XP(EntityDataType.INT),
  SPAWNING_FRAMES,
  COMMAND_BLOCK_TICK_DELAY(EntityDataType.INT),
  COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK(EntityDataType.BYTE),
  AMBIENT_SOUND_INTERVAL(EntityDataType.FLOAT),
  AMBIENT_SOUND_INTERVAL_RANGE(EntityDataType.FLOAT),
  AMBIENT_SOUND_EVENT_NAME(EntityDataType.STRING),
  FALL_DAMAGE_MULTIPLIER(EntityDataType.FLOAT),
  NAME_RAW_TEXT,
  CAN_RIDE_TARGET(EntityDataType.BYTE),
  LOW_TIER_CURED_TRADE_DISCOUNT(EntityDataType.INT),
  HIGH_TIER_CURED_TRADE_DISCOUNT(EntityDataType.INT),
  NEARBY_CURED_TRADE_DISCOUNT(EntityDataType.INT),
  NEARBY_CURED_DISCOUNT_TIME_STAMP(EntityDataType.INT),
  HITBOX(EntityDataType.NBT),
  IS_BUOYANT(EntityDataType.BYTE),
  BUOYANCY_DATA(EntityDataType.STRING),
  FREEZING_EFFECT_STRENGTH,
  GOAT_HORN_COUNT,
  BASE_RUNTIME_ID,
  DEFINE_PROPERTIES,
  UPDATE_PROPERTIES;

  /**
   * the flags.
   */
  private final boolean flags;

  /**
   * the type.
   */
  @NotNull
  private final EntityDataType[] type;

  /**
   * ctor.
   *
   * @param flags the flags.
   * @param type the type.
   */
  EntityData(final boolean flags, @NotNull final EntityDataType... type) {
    this.flags = flags;
    this.type = type;
  }

  /**
   * ctor.
   *
   * @param type the type.
   */
  EntityData(@NotNull final EntityDataType... type) {
    this(false, type);
  }
}
