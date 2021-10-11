package io.github.shiruka.protocol.data.entity;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains entity data.
 */
public enum EntityData {
  /**
   * the flags.
   */
  FLAGS(true, EntityDataType.FLAGS),
  /**
   * the health.
   */
  HEALTH(EntityDataType.INT),
  /**
   * the variant.
   */
  VARIANT(EntityDataType.INT),
  /**
   * the color.
   */
  COLOR(EntityDataType.BYTE),
  /**
   * the nametag.
   */
  NAMETAG(EntityDataType.STRING),
  /**
   * the owner eid.
   */
  OWNER_EID(EntityDataType.LONG),
  /**
   * the target eid.
   */
  TARGET_EID(EntityDataType.LONG),
  /**
   * the air supply.
   */
  AIR_SUPPLY(EntityDataType.SHORT),
  /**
   * the effect color.
   */
  EFFECT_COLOR(EntityDataType.INT),
  /**
   * the effect ambient.
   */
  EFFECT_AMBIENT(EntityDataType.BYTE),
  /**
   * the jump duration.
   */
  JUMP_DURATION(EntityDataType.BYTE),
  /**
   * the hurt time.
   */
  HURT_TIME(EntityDataType.INT),
  /**
   * the hurt direction.
   */
  HURT_DIRECTION(EntityDataType.INT),
  /**
   * the row time left.
   */
  ROW_TIME_LEFT(EntityDataType.FLOAT),
  /**
   * the row time right.
   */
  ROW_TIME_RIGHT(EntityDataType.FLOAT),
  /**
   * the experience value.
   */
  EXPERIENCE_VALUE(EntityDataType.INT),
  /**
   * the display item.
   */
  DISPLAY_ITEM(EntityDataType.INT),
  /**
   * the display offset.
   */
  DISPLAY_OFFSET(EntityDataType.LONG, EntityDataType.INT),
  /**
   * the custom display.
   */
  CUSTOM_DISPLAY(EntityDataType.BYTE),
  /**
   * the swell.
   */
  SWELL(EntityDataType.INT),
  /**
   * the old swell.
   */
  OLD_SWELL(EntityDataType.INT),
  /**
   * the swell direction.
   */
  SWELL_DIRECTION(EntityDataType.INT),
  /**
   * the charge amount.
   */
  CHARGE_AMOUNT(EntityDataType.BYTE),
  /**
   * the carried block.
   */
  CARRIED_BLOCK(EntityDataType.INT),
  /**
   * the client event.
   */
  CLIENT_EVENT(EntityDataType.BYTE),
  /**
   * the using item.
   */
  USING_ITEM(EntityDataType.BYTE),
  /**
   * the player flags.
   */
  PLAYER_FLAGS(EntityDataType.BYTE),
  /**
   * the player index.
   */
  PLAYER_INDEX(EntityDataType.INT),
  /**
   * the bed position.
   */
  BED_POSITION(EntityDataType.VECTOR3I),
  /**
   * the x power.
   */
  X_POWER(EntityDataType.FLOAT),
  /**
   * the y power.
   */
  Y_POWER(EntityDataType.FLOAT),
  /**
   * the z power.
   */
  Z_POWER(EntityDataType.FLOAT),
  /**
   * the aux power.
   */
  AUX_POWER,
  /**
   * the fish x.
   */
  FISH_X,
  /**
   * the fish z.
   */
  FISH_Z,
  /**
   * the fish angle.
   */
  FISH_ANGLE,
  /**
   * the potion aux value.
   */
  POTION_AUX_VALUE(EntityDataType.SHORT),
  /**
   * the leash holder eid.
   */
  LEASH_HOLDER_EID(EntityDataType.LONG),
  /**
   * the scale.
   */
  SCALE(EntityDataType.FLOAT),
  /**
   * the has npc component.
   */
  HAS_NPC_COMPONENT(EntityDataType.BYTE),
  /**
   * the skin id.
   */
  SKIN_ID(EntityDataType.STRING),
  /**
   * the npc skin id.
   */
  NPC_SKIN_ID(EntityDataType.STRING),
  /**
   * the npc data.
   */
  NPC_DATA,
  /**
   * the url tag.
   */
  URL_TAG(EntityDataType.STRING),
  /**
   * the max air supply.
   */
  MAX_AIR_SUPPLY(EntityDataType.SHORT),
  /**
   * the mark variant.
   */
  MARK_VARIANT(EntityDataType.INT),
  /**
   * the container type.
   */
  CONTAINER_TYPE(EntityDataType.BYTE),
  /**
   * the container base size.
   */
  CONTAINER_BASE_SIZE(EntityDataType.INT),
  /**
   * the container strength modifier.
   */
  CONTAINER_STRENGTH_MODIFIER(EntityDataType.INT),
  /**
   * the block target.
   */
  BLOCK_TARGET(EntityDataType.VECTOR3I),
  /**
   * the wither invulnerable ticks.
   */
  WITHER_INVULNERABLE_TICKS(EntityDataType.INT),
  /**
   * the wither target 1.
   */
  WITHER_TARGET_1(EntityDataType.LONG),
  /**
   * the wither target 2.
   */
  WITHER_TARGET_2(EntityDataType.LONG),
  /**
   * the wither target 3.
   */
  WITHER_TARGET_3(EntityDataType.LONG),
  /**
   * the wither aerial attack.
   */
  WITHER_AERIAL_ATTACK(EntityDataType.SHORT),
  /**
   * the bounding box width.
   */
  BOUNDING_BOX_WIDTH(EntityDataType.FLOAT),
  /**
   * the bounding box height.
   */
  BOUNDING_BOX_HEIGHT(EntityDataType.FLOAT),
  /**
   * the fuse length.
   */
  FUSE_LENGTH(EntityDataType.INT),
  /**
   * the rider seat position.
   */
  RIDER_SEAT_POSITION(EntityDataType.VECTOR3F),
  /**
   * the rider rotation locked.
   */
  RIDER_ROTATION_LOCKED(EntityDataType.BYTE),
  /**
   * the rider max rotation.
   */
  RIDER_MAX_ROTATION(EntityDataType.FLOAT),
  /**
   * the rider min rotation.
   */
  RIDER_MIN_ROTATION(EntityDataType.FLOAT),
  /**
   * the rider rotation offset.
   */
  RIDER_ROTATION_OFFSET,
  /**
   * the area effect cloud radius.
   */
  AREA_EFFECT_CLOUD_RADIUS(EntityDataType.FLOAT),
  /**
   * the area effect cloud waiting.
   */
  AREA_EFFECT_CLOUD_WAITING(EntityDataType.INT),
  /**
   * the area effect cloud particle id.
   */
  AREA_EFFECT_CLOUD_PARTICLE_ID(EntityDataType.INT),
  /**
   * the shulker peak height.
   */
  SHULKER_PEAK_HEIGHT(EntityDataType.INT),
  /**
   * the shulker peek id.
   */
  SHULKER_PEEK_ID,
  /**
   * the shulker attach face.
   */
  SHULKER_ATTACH_FACE(EntityDataType.BYTE),
  /**
   * the shulker attach pos.
   */
  SHULKER_ATTACH_POS(EntityDataType.VECTOR3I),
  /**
   * the trade target eid.
   */
  TRADE_TARGET_EID(EntityDataType.LONG),
  /**
   * the command block enabled.
   */
  COMMAND_BLOCK_ENABLED(EntityDataType.BYTE),
  /**
   * the command block command.
   */
  COMMAND_BLOCK_COMMAND(EntityDataType.STRING),
  /**
   * the command block last output.
   */
  COMMAND_BLOCK_LAST_OUTPUT(EntityDataType.STRING),
  /**
   * the command block track output.
   */
  COMMAND_BLOCK_TRACK_OUTPUT(EntityDataType.BYTE),
  /**
   * the controlling rider seat index.
   */
  CONTROLLING_RIDER_SEAT_INDEX(EntityDataType.BYTE),
  /**
   * the strength.
   */
  STRENGTH(EntityDataType.INT),
  /**
   * the max strength.
   */
  MAX_STRENGTH(EntityDataType.INT),
  /**
   * the evoker spell color.
   */
  EVOKER_SPELL_COLOR(EntityDataType.INT),
  /**
   * the limited life.
   */
  LIMITED_LIFE(EntityDataType.INT),
  /**
   * the armor stand pose index.
   */
  ARMOR_STAND_POSE_INDEX(EntityDataType.INT),
  /**
   * the ender crystal time offset.
   */
  ENDER_CRYSTAL_TIME_OFFSET(EntityDataType.INT),
  /**
   * the nametag always show.
   */
  NAMETAG_ALWAYS_SHOW(EntityDataType.BYTE),
  /**
   * the color 2.
   */
  COLOR_2(EntityDataType.BYTE),
  /**
   * the score tag.
   */
  SCORE_TAG(EntityDataType.STRING),
  /**
   * the balloon attached entity.
   */
  BALLOON_ATTACHED_ENTITY(EntityDataType.LONG),
  /**
   * the pufferfish size.
   */
  PUFFERFISH_SIZE(EntityDataType.BYTE),
  /**
   * the boat bubble time.
   */
  BOAT_BUBBLE_TIME(EntityDataType.INT),
  /**
   * the agent id.
   */
  AGENT_ID(EntityDataType.LONG),
  /**
   * the sitting amount.
   */
  SITTING_AMOUNT,
  /**
   * the sitting amount previous.
   */
  SITTING_AMOUNT_PREVIOUS,
  /**
   * the eating counter.
   */
  EATING_COUNTER(EntityDataType.INT),
  /**
   * the flags 2.
   */
  FLAGS_2(true, EntityDataType.FLAGS),
  /**
   * the laying amount.
   */
  LAYING_AMOUNT,
  /**
   * the laying amount previous.
   */
  LAYING_AMOUNT_PREVIOUS,
  /**
   * the area effect cloud duration.
   */
  AREA_EFFECT_CLOUD_DURATION(EntityDataType.INT),
  /**
   * the area effect cloud spawn time.
   */
  AREA_EFFECT_CLOUD_SPAWN_TIME(EntityDataType.INT),
  /**
   * the area effect cloud change rate.
   */
  AREA_EFFECT_CLOUD_CHANGE_RATE(EntityDataType.FLOAT),
  /**
   * the area effect cloud change on pickup.
   */
  AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP(EntityDataType.FLOAT),
  /**
   * the area effect cloud count.
   */
  AREA_EFFECT_CLOUD_COUNT(EntityDataType.INT),
  /**
   * the interactive tag.
   */
  INTERACTIVE_TAG(EntityDataType.STRING),
  /**
   * the trade tier.
   */
  TRADE_TIER(EntityDataType.INT),
  /**
   * the max trade tier.
   */
  MAX_TRADE_TIER(EntityDataType.INT),
  /**
   * the trade xp.
   */
  TRADE_XP(EntityDataType.INT),
  /**
   * the spawning frames.
   */
  SPAWNING_FRAMES,
  /**
   * the command block tick delay.
   */
  COMMAND_BLOCK_TICK_DELAY(EntityDataType.INT),
  /**
   * the command block execute on first tick.
   */
  COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK(EntityDataType.BYTE),
  /**
   * the ambient sound interval.
   */
  AMBIENT_SOUND_INTERVAL(EntityDataType.FLOAT),
  /**
   * the ambient sound interval range.
   */
  AMBIENT_SOUND_INTERVAL_RANGE(EntityDataType.FLOAT),
  /**
   * the ambient sound event name.
   */
  AMBIENT_SOUND_EVENT_NAME(EntityDataType.STRING),
  /**
   * the fall damage multiplier.
   */
  FALL_DAMAGE_MULTIPLIER(EntityDataType.FLOAT),
  /**
   * the name raw text.
   */
  NAME_RAW_TEXT,
  /**
   * the can ride target.
   */
  CAN_RIDE_TARGET(EntityDataType.BYTE),
  /**
   * the low tier cured trade discount.
   */
  LOW_TIER_CURED_TRADE_DISCOUNT(EntityDataType.INT),
  /**
   * the high tier cured trade discount.
   */
  HIGH_TIER_CURED_TRADE_DISCOUNT(EntityDataType.INT),
  /**
   * the nearby cured trade discount.
   */
  NEARBY_CURED_TRADE_DISCOUNT(EntityDataType.INT),
  /**
   * the nearby cured discount time stamp.
   */
  NEARBY_CURED_DISCOUNT_TIME_STAMP(EntityDataType.INT),
  /**
   * the hitbox.
   */
  HITBOX(EntityDataType.NBT),
  /**
   * the is buoyant.
   */
  IS_BUOYANT(EntityDataType.BYTE),
  /**
   * the buoyancy data.
   */
  BUOYANCY_DATA(EntityDataType.STRING),
  /**
   * the freezing effect strength.
   */
  FREEZING_EFFECT_STRENGTH,
  /**
   * the goat horn count.
   */
  GOAT_HORN_COUNT,
  /**
   * the base runtime id.
   */
  BASE_RUNTIME_ID,
  /**
   * the define properties.
   */
  DEFINE_PROPERTIES,
  /**
   * the update properties.
   */
  UPDATE_PROPERTIES;

  /**
   * the cache.
   */
  private static final EntityData[] CACHE = EntityData.values();

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
