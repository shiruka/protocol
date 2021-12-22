package io.github.shiruka.protocol;

import io.github.shiruka.api.common.Int2ObjectBiMap;
import io.github.shiruka.protocol.data.AdventureSetting;
import io.github.shiruka.protocol.data.entity.EntityData;
import io.github.shiruka.protocol.data.entity.EntityDataType;
import io.github.shiruka.protocol.data.entity.EntityFlag;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.StackRequestActionType;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents constants.
 */
public final class Constants {

  /**
   * the blocking item identifier.
   */
  public static final String BLOCKING_ITEM_IDENTIFIER = "minecraft:shield";

  /**
   * the entity data.
   */
  public static final Int2ObjectBiMap<EntityData> ENTITY_DATA = new Int2ObjectBiMap<>();

  /**
   * the entity data types.
   */
  public static final Int2ObjectBiMap<EntityDataType> ENTITY_DATA_TYPES = new Int2ObjectBiMap<>();

  /**
   * the entity flags.
   */
  public static final Int2ObjectBiMap<EntityFlag> ENTITY_FLAGS = new Int2ObjectBiMap<>();

  /**
   * the flags 1.
   */
  public static final AdventureSetting @Nullable [] FLAGS_1 = {
    AdventureSetting.WORLD_IMMUTABLE, AdventureSetting.NO_PVM, AdventureSetting.NO_MVP, null,
    AdventureSetting.SHOW_NAME_TAGS, AdventureSetting.AUTO_JUMP, AdventureSetting.MAY_FLY, AdventureSetting.NO_CLIP,
    AdventureSetting.WORLD_BUILDER, AdventureSetting.FLYING, AdventureSetting.MUTED};

  /**
   * the flags 2.
   */
  public static final AdventureSetting @Nullable [] FLAGS_2 = {
    AdventureSetting.MINE, AdventureSetting.DOORS_AND_SWITCHES, AdventureSetting.OPEN_CONTAINERS,
    AdventureSetting.ATTACK_PLAYERS, AdventureSetting.ATTACK_MOBS, AdventureSetting.OPERATOR, null,
    AdventureSetting.TELEPORT, AdventureSetting.BUILD, AdventureSetting.DEFAULT_LEVEL_PERMISSIONS};

  /**
   * the flags to bit 1.
   */
  public static final Object2IntMap<AdventureSetting> FLAGS_TO_BIT_1 = new Object2IntOpenHashMap<>();

  /**
   * the flags to bit 2.
   */
  public static final Object2IntMap<AdventureSetting> FLAGS_TO_BIT_2 = new Object2IntOpenHashMap<>();

  /**
   * the game rule types.
   */
  public static final Object2IntMap<Class<?>> GAME_RULE_TYPES = new Object2IntOpenHashMap<>(3, 0.5f);

  /**
   * ctor.
   */
  private Constants() {
  }

  /**
   * checks if the id is the blocking item.
   *
   * @param id the id to check.
   * @param session the session to check.
   *
   * @return {@code true} if the id is blocking item of the session.
   */
  public static boolean isBlockingItem(final int id, @NotNull final MinecraftChildChannel session) {
    return id == session.dynamicBlockingId().get();
  }

  /**
   * registers the constants.
   */
  public static void register() {
    Constants.registerFlagsToBit();
    Constants.registerEntityDataTypes();
    Constants.registerEntityData();
    Constants.registerEntityFlags();
    Constants.registerGameRules();
  }

  /**
   * adds the entity data.
   *
   * @param index the index to add.
   * @param entityData the entity data to add.
   */
  private static void addEntityData(final int index, @NotNull final EntityData entityData) {
    Constants.ENTITY_DATA.put(index, entityData);
  }

  /**
   * adds the entity data type.
   *
   * @param index the index to add.
   * @param type the type to add.
   */
  private static void addEntityDataType(final int index, @NotNull final EntityDataType type) {
    Constants.ENTITY_DATA_TYPES.put(index, type);
  }

  /**
   * adds the entity flags.
   *
   * @param index the index to add.
   * @param flag the flag to add.
   */
  private static void addEntityFlag(final int index, @NotNull final EntityFlag flag) {
    Constants.ENTITY_FLAGS.put(index, flag);
  }

  /**
   * registers the entity data.
   */
  private static void registerEntityData() {
    // 291
    Constants.addEntityData(0, EntityData.FLAGS);
    Constants.addEntityData(1, EntityData.HEALTH);
    Constants.addEntityData(2, EntityData.VARIANT);
    Constants.addEntityData(3, EntityData.COLOR);
    Constants.addEntityData(4, EntityData.NAMETAG);
    Constants.addEntityData(5, EntityData.OWNER_EID);
    Constants.addEntityData(6, EntityData.TARGET_EID);
    Constants.addEntityData(7, EntityData.AIR_SUPPLY);
    Constants.addEntityData(8, EntityData.EFFECT_COLOR);
    Constants.addEntityData(9, EntityData.EFFECT_AMBIENT);
    Constants.addEntityData(10, EntityData.JUMP_DURATION);
    Constants.addEntityData(11, EntityData.HURT_TIME);
    Constants.addEntityData(12, EntityData.HURT_DIRECTION);
    Constants.addEntityData(13, EntityData.ROW_TIME_LEFT);
    Constants.addEntityData(14, EntityData.ROW_TIME_RIGHT);
    Constants.addEntityData(15, EntityData.EXPERIENCE_VALUE);
    Constants.addEntityData(16, EntityData.DISPLAY_ITEM);
    Constants.addEntityData(17, EntityData.DISPLAY_OFFSET);
    Constants.addEntityData(18, EntityData.CUSTOM_DISPLAY);
    Constants.addEntityData(19, EntityData.SWELL);
    Constants.addEntityData(20, EntityData.OLD_SWELL);
    Constants.addEntityData(21, EntityData.SWELL_DIRECTION);
    Constants.addEntityData(22, EntityData.CHARGE_AMOUNT);
    Constants.addEntityData(23, EntityData.CARRIED_BLOCK);
    Constants.addEntityData(24, EntityData.CLIENT_EVENT);
    Constants.addEntityData(25, EntityData.USING_ITEM);
    Constants.addEntityData(26, EntityData.PLAYER_FLAGS);
    Constants.addEntityData(27, EntityData.PLAYER_INDEX);
    Constants.addEntityData(28, EntityData.BED_POSITION);
    Constants.addEntityData(29, EntityData.X_POWER);
    Constants.addEntityData(30, EntityData.Y_POWER);
    Constants.addEntityData(31, EntityData.Z_POWER);
    Constants.addEntityData(32, EntityData.AUX_POWER);
    Constants.addEntityData(33, EntityData.FISH_X);
    Constants.addEntityData(34, EntityData.FISH_Z);
    Constants.addEntityData(35, EntityData.FISH_ANGLE);
    Constants.addEntityData(36, EntityData.POTION_AUX_VALUE);
    Constants.addEntityData(37, EntityData.LEASH_HOLDER_EID);
    Constants.addEntityData(38, EntityData.SCALE);
    Constants.addEntityData(39, EntityData.INTERACTIVE_TAG);
    Constants.addEntityData(40, EntityData.NPC_SKIN_ID);
    Constants.addEntityData(41, EntityData.URL_TAG);
    Constants.addEntityData(42, EntityData.MAX_AIR_SUPPLY);
    Constants.addEntityData(43, EntityData.MARK_VARIANT);
    Constants.addEntityData(44, EntityData.CONTAINER_TYPE);
    Constants.addEntityData(45, EntityData.CONTAINER_BASE_SIZE);
    Constants.addEntityData(46, EntityData.CONTAINER_STRENGTH_MODIFIER);
    Constants.addEntityData(47, EntityData.BLOCK_TARGET);
    Constants.addEntityData(48, EntityData.WITHER_INVULNERABLE_TICKS);
    Constants.addEntityData(49, EntityData.WITHER_TARGET_1);
    Constants.addEntityData(50, EntityData.WITHER_TARGET_2);
    Constants.addEntityData(51, EntityData.WITHER_TARGET_3);
    Constants.addEntityData(52, EntityData.WITHER_AERIAL_ATTACK);
    Constants.addEntityData(53, EntityData.BOUNDING_BOX_WIDTH);
    Constants.addEntityData(54, EntityData.BOUNDING_BOX_HEIGHT);
    Constants.addEntityData(55, EntityData.FUSE_LENGTH);
    Constants.addEntityData(56, EntityData.RIDER_SEAT_POSITION);
    Constants.addEntityData(57, EntityData.RIDER_ROTATION_LOCKED);
    Constants.addEntityData(58, EntityData.RIDER_MAX_ROTATION);
    Constants.addEntityData(59, EntityData.RIDER_MIN_ROTATION);
    Constants.addEntityData(60, EntityData.AREA_EFFECT_CLOUD_RADIUS);
    Constants.addEntityData(61, EntityData.AREA_EFFECT_CLOUD_WAITING);
    Constants.addEntityData(62, EntityData.AREA_EFFECT_CLOUD_PARTICLE_ID);
    Constants.addEntityData(63, EntityData.SHULKER_PEEK_ID);
    Constants.addEntityData(64, EntityData.SHULKER_ATTACH_FACE);
    Constants.addEntityData(66, EntityData.SHULKER_ATTACH_POS);
    Constants.addEntityData(67, EntityData.TRADE_TARGET_EID);
    Constants.addEntityData(69, EntityData.COMMAND_BLOCK_ENABLED);
    Constants.addEntityData(70, EntityData.COMMAND_BLOCK_COMMAND);
    Constants.addEntityData(71, EntityData.COMMAND_BLOCK_LAST_OUTPUT);
    Constants.addEntityData(72, EntityData.COMMAND_BLOCK_TRACK_OUTPUT);
    Constants.addEntityData(73, EntityData.CONTROLLING_RIDER_SEAT_INDEX);
    Constants.addEntityData(74, EntityData.STRENGTH);
    Constants.addEntityData(75, EntityData.MAX_STRENGTH);
    Constants.addEntityData(76, EntityData.EVOKER_SPELL_COLOR);
    Constants.addEntityData(77, EntityData.LIMITED_LIFE);
    Constants.addEntityData(78, EntityData.ARMOR_STAND_POSE_INDEX);
    Constants.addEntityData(79, EntityData.ENDER_CRYSTAL_TIME_OFFSET);
    Constants.addEntityData(80, EntityData.NAMETAG_ALWAYS_SHOW);
    Constants.addEntityData(81, EntityData.COLOR_2);
    Constants.addEntityData(83, EntityData.SCORE_TAG);
    Constants.addEntityData(84, EntityData.BALLOON_ATTACHED_ENTITY);
    Constants.addEntityData(85, EntityData.PUFFERFISH_SIZE);
    Constants.addEntityData(86, EntityData.BOAT_BUBBLE_TIME);
    Constants.addEntityData(87, EntityData.AGENT_ID);
    // 313
    Constants.addEntityData(88, EntityData.SITTING_AMOUNT);
    Constants.addEntityData(89, EntityData.SITTING_AMOUNT_PREVIOUS);
    Constants.addEntityData(90, EntityData.EATING_COUNTER);
    Constants.addEntityData(91, EntityData.FLAGS_2);
    Constants.addEntityData(92, EntityData.LAYING_AMOUNT);
    Constants.addEntityData(93, EntityData.LAYING_AMOUNT_PREVIOUS);
    // 332
    Constants.addEntityData(94, EntityData.AREA_EFFECT_CLOUD_DURATION);
    Constants.addEntityData(95, EntityData.AREA_EFFECT_CLOUD_SPAWN_TIME);
    Constants.addEntityData(96, EntityData.AREA_EFFECT_CLOUD_CHANGE_RATE);
    Constants.addEntityData(97, EntityData.AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP);
    Constants.addEntityData(98, EntityData.AREA_EFFECT_CLOUD_COUNT);
    // 340
    Constants.addEntityData(39, EntityData.HAS_NPC_COMPONENT);
    Constants.addEntityData(99, EntityData.INTERACTIVE_TAG);
    Constants.addEntityData(100, EntityData.TRADE_TIER);
    Constants.addEntityData(101, EntityData.MAX_TRADE_TIER);
    // 354
    Constants.addEntityData(102, EntityData.TRADE_XP);
    // 361
    Constants.addEntityData(40, EntityData.NPC_DATA);
    Constants.addEntityData(103, EntityData.SKIN_ID);
    Constants.addEntityData(104, EntityData.SPAWNING_FRAMES);
    Constants.addEntityData(105, EntityData.COMMAND_BLOCK_TICK_DELAY);
    Constants.addEntityData(106, EntityData.COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK);
    // 388
    Constants.addEntityData(107, EntityData.AMBIENT_SOUND_INTERVAL);
    Constants.addEntityData(108, EntityData.AMBIENT_SOUND_INTERVAL_RANGE);
    Constants.addEntityData(109, EntityData.AMBIENT_SOUND_EVENT_NAME);
    Constants.addEntityData(110, EntityData.FALL_DAMAGE_MULTIPLIER);
    Constants.addEntityData(111, EntityData.NAME_RAW_TEXT);
    Constants.addEntityData(112, EntityData.CAN_RIDE_TARGET);
    // 407
    Constants.addEntityData(113, EntityData.LOW_TIER_CURED_TRADE_DISCOUNT);
    Constants.addEntityData(114, EntityData.HIGH_TIER_CURED_TRADE_DISCOUNT);
    Constants.addEntityData(115, EntityData.NEARBY_CURED_TRADE_DISCOUNT);
    Constants.addEntityData(116, EntityData.NEARBY_CURED_DISCOUNT_TIME_STAMP);
    Constants.addEntityData(117, EntityData.HITBOX);
    Constants.addEntityData(118, EntityData.IS_BUOYANT);
    Constants.addEntityData(119, EntityData.BUOYANCY_DATA);
    // 428
    Constants.addEntityData(60, EntityData.RIDER_ROTATION_OFFSET);
    Constants.addEntityData(61, EntityData.AREA_EFFECT_CLOUD_RADIUS);
    Constants.addEntityData(62, EntityData.AREA_EFFECT_CLOUD_WAITING);
    Constants.addEntityData(63, EntityData.AREA_EFFECT_CLOUD_PARTICLE_ID);
    Constants.addEntityData(64, EntityData.SHULKER_PEEK_ID);
    Constants.addEntityData(65, EntityData.SHULKER_ATTACH_FACE);
    Constants.addEntityData(67, EntityData.SHULKER_ATTACH_POS);
    Constants.addEntityData(68, EntityData.TRADE_TARGET_EID);
    Constants.addEntityData(70, EntityData.COMMAND_BLOCK_ENABLED);
    Constants.addEntityData(71, EntityData.COMMAND_BLOCK_COMMAND);
    Constants.addEntityData(72, EntityData.COMMAND_BLOCK_LAST_OUTPUT);
    Constants.addEntityData(73, EntityData.COMMAND_BLOCK_TRACK_OUTPUT);
    Constants.addEntityData(74, EntityData.CONTROLLING_RIDER_SEAT_INDEX);
    Constants.addEntityData(75, EntityData.STRENGTH);
    Constants.addEntityData(76, EntityData.MAX_STRENGTH);
    Constants.addEntityData(77, EntityData.EVOKER_SPELL_COLOR);
    Constants.addEntityData(78, EntityData.LIMITED_LIFE);
    Constants.addEntityData(79, EntityData.ARMOR_STAND_POSE_INDEX);
    Constants.addEntityData(80, EntityData.ENDER_CRYSTAL_TIME_OFFSET);
    Constants.addEntityData(81, EntityData.NAMETAG_ALWAYS_SHOW);
    Constants.addEntityData(82, EntityData.COLOR_2);
    Constants.addEntityData(84, EntityData.SCORE_TAG);
    Constants.addEntityData(85, EntityData.BALLOON_ATTACHED_ENTITY);
    Constants.addEntityData(86, EntityData.PUFFERFISH_SIZE);
    Constants.addEntityData(87, EntityData.BOAT_BUBBLE_TIME);
    Constants.addEntityData(88, EntityData.AGENT_ID);
    Constants.addEntityData(89, EntityData.SITTING_AMOUNT);
    Constants.addEntityData(90, EntityData.SITTING_AMOUNT_PREVIOUS);
    Constants.addEntityData(91, EntityData.EATING_COUNTER);
    Constants.addEntityData(92, EntityData.FLAGS_2);
    Constants.addEntityData(93, EntityData.LAYING_AMOUNT);
    Constants.addEntityData(94, EntityData.LAYING_AMOUNT_PREVIOUS);
    Constants.addEntityData(95, EntityData.AREA_EFFECT_CLOUD_DURATION);
    Constants.addEntityData(96, EntityData.AREA_EFFECT_CLOUD_SPAWN_TIME);
    Constants.addEntityData(97, EntityData.AREA_EFFECT_CLOUD_CHANGE_RATE);
    Constants.addEntityData(98, EntityData.AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP);
    Constants.addEntityData(99, EntityData.AREA_EFFECT_CLOUD_COUNT);
    Constants.addEntityData(100, EntityData.INTERACTIVE_TAG);
    Constants.addEntityData(101, EntityData.TRADE_TIER);
    Constants.addEntityData(102, EntityData.MAX_TRADE_TIER);
    Constants.addEntityData(103, EntityData.TRADE_XP);
    Constants.addEntityData(104, EntityData.SKIN_ID);
    Constants.addEntityData(105, EntityData.SPAWNING_FRAMES);
    Constants.addEntityData(106, EntityData.COMMAND_BLOCK_TICK_DELAY);
    Constants.addEntityData(107, EntityData.COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK);
    Constants.addEntityData(108, EntityData.AMBIENT_SOUND_INTERVAL);
    Constants.addEntityData(109, EntityData.AMBIENT_SOUND_INTERVAL_RANGE);
    Constants.addEntityData(110, EntityData.AMBIENT_SOUND_EVENT_NAME);
    Constants.addEntityData(111, EntityData.FALL_DAMAGE_MULTIPLIER);
    Constants.addEntityData(112, EntityData.NAME_RAW_TEXT);
    Constants.addEntityData(113, EntityData.CAN_RIDE_TARGET);
    Constants.addEntityData(114, EntityData.LOW_TIER_CURED_TRADE_DISCOUNT);
    Constants.addEntityData(115, EntityData.HIGH_TIER_CURED_TRADE_DISCOUNT);
    Constants.addEntityData(116, EntityData.NEARBY_CURED_TRADE_DISCOUNT);
    Constants.addEntityData(117, EntityData.NEARBY_CURED_DISCOUNT_TIME_STAMP);
    Constants.addEntityData(118, EntityData.HITBOX);
    Constants.addEntityData(119, EntityData.IS_BUOYANT);
    Constants.addEntityData(120, EntityData.FREEZING_EFFECT_STRENGTH);
    Constants.addEntityData(121, EntityData.BUOYANCY_DATA);
    Constants.addEntityData(122, EntityData.GOAT_HORN_COUNT);
    Constants.addEntityData(123, EntityData.BASE_RUNTIME_ID);
    Constants.addEntityData(124, EntityData.DEFINE_PROPERTIES);
    Constants.addEntityData(125, EntityData.UPDATE_PROPERTIES);
    // 440
    Constants.addEntityData(120, EntityData.BASE_RUNTIME_ID);
    Constants.addEntityData(121, EntityData.FREEZING_EFFECT_STRENGTH);
    Constants.addEntityData(122, EntityData.BUOYANCY_DATA);
    Constants.addEntityData(123, EntityData.GOAT_HORN_COUNT);
    Constants.addEntityData(124, EntityData.UPDATE_PROPERTIES);
  }

  /**
   * registers the entity data types.
   */
  private static void registerEntityDataTypes() {
    // 291
    Constants.addEntityDataType(7, EntityDataType.FLAGS);
    Constants.addEntityDataType(0, EntityDataType.BYTE);
    Constants.addEntityDataType(1, EntityDataType.SHORT);
    Constants.addEntityDataType(2, EntityDataType.INT);
    Constants.addEntityDataType(3, EntityDataType.FLOAT);
    Constants.addEntityDataType(4, EntityDataType.STRING);
    Constants.addEntityDataType(5, EntityDataType.NBT);
    Constants.addEntityDataType(6, EntityDataType.VECTOR3I);
    Constants.addEntityDataType(7, EntityDataType.LONG);
    Constants.addEntityDataType(8, EntityDataType.VECTOR3F);
  }

  /**
   * registers the entity flags.
   */
  private static void registerEntityFlags() {
    // 291
    Constants.addEntityFlag(0, EntityFlag.ON_FIRE);
    Constants.addEntityFlag(1, EntityFlag.SNEAKING);
    Constants.addEntityFlag(2, EntityFlag.RIDING);
    Constants.addEntityFlag(3, EntityFlag.SPRINTING);
    Constants.addEntityFlag(4, EntityFlag.USING_ITEM);
    Constants.addEntityFlag(5, EntityFlag.INVISIBLE);
    Constants.addEntityFlag(6, EntityFlag.TEMPTED);
    Constants.addEntityFlag(7, EntityFlag.IN_LOVE);
    Constants.addEntityFlag(8, EntityFlag.SADDLED);
    Constants.addEntityFlag(9, EntityFlag.POWERED);
    Constants.addEntityFlag(10, EntityFlag.IGNITED);
    Constants.addEntityFlag(11, EntityFlag.BABY);
    Constants.addEntityFlag(12, EntityFlag.CONVERTING);
    Constants.addEntityFlag(13, EntityFlag.CRITICAL);
    Constants.addEntityFlag(14, EntityFlag.CAN_SHOW_NAME);
    Constants.addEntityFlag(15, EntityFlag.ALWAYS_SHOW_NAME);
    Constants.addEntityFlag(16, EntityFlag.NO_AI);
    Constants.addEntityFlag(17, EntityFlag.SILENT);
    Constants.addEntityFlag(18, EntityFlag.WALL_CLIMBING);
    Constants.addEntityFlag(19, EntityFlag.CAN_CLIMB);
    Constants.addEntityFlag(20, EntityFlag.CAN_SWIM);
    Constants.addEntityFlag(21, EntityFlag.CAN_FLY);
    Constants.addEntityFlag(22, EntityFlag.CAN_WALK);
    Constants.addEntityFlag(23, EntityFlag.RESTING);
    Constants.addEntityFlag(24, EntityFlag.SITTING);
    Constants.addEntityFlag(25, EntityFlag.ANGRY);
    Constants.addEntityFlag(26, EntityFlag.INTERESTED);
    Constants.addEntityFlag(27, EntityFlag.CHARGED);
    Constants.addEntityFlag(28, EntityFlag.TAMED);
    Constants.addEntityFlag(29, EntityFlag.ORPHANED);
    Constants.addEntityFlag(30, EntityFlag.LEASHED);
    Constants.addEntityFlag(31, EntityFlag.SHEARED);
    Constants.addEntityFlag(32, EntityFlag.GLIDING);
    Constants.addEntityFlag(33, EntityFlag.ELDER);
    Constants.addEntityFlag(34, EntityFlag.MOVING);
    Constants.addEntityFlag(35, EntityFlag.BREATHING);
    Constants.addEntityFlag(36, EntityFlag.CHESTED);
    Constants.addEntityFlag(37, EntityFlag.STACKABLE);
    Constants.addEntityFlag(38, EntityFlag.SHOW_BOTTOM);
    Constants.addEntityFlag(39, EntityFlag.STANDING);
    Constants.addEntityFlag(40, EntityFlag.SHAKING);
    Constants.addEntityFlag(41, EntityFlag.IDLING);
    Constants.addEntityFlag(42, EntityFlag.CASTING);
    Constants.addEntityFlag(43, EntityFlag.CHARGING);
    Constants.addEntityFlag(44, EntityFlag.WASD_CONTROLLED);
    Constants.addEntityFlag(45, EntityFlag.CAN_POWER_JUMP);
    Constants.addEntityFlag(46, EntityFlag.LINGERING);
    Constants.addEntityFlag(47, EntityFlag.HAS_COLLISION);
    Constants.addEntityFlag(48, EntityFlag.HAS_GRAVITY);
    Constants.addEntityFlag(49, EntityFlag.FIRE_IMMUNE);
    Constants.addEntityFlag(50, EntityFlag.DANCING);
    Constants.addEntityFlag(51, EntityFlag.ENCHANTED);
    Constants.addEntityFlag(52, EntityFlag.RETURN_TRIDENT);
    Constants.addEntityFlag(53, EntityFlag.CONTAINER_IS_PRIVATE);
    Constants.addEntityFlag(54, EntityFlag.IS_TRANSFORMING);
    Constants.addEntityFlag(55, EntityFlag.DAMAGE_NEARBY_MOBS);
    Constants.addEntityFlag(56, EntityFlag.SWIMMING);
    Constants.addEntityFlag(57, EntityFlag.BRIBED);
    Constants.addEntityFlag(58, EntityFlag.IS_PREGNANT);
    Constants.addEntityFlag(59, EntityFlag.LAYING_EGG);
    Constants.addEntityFlag(60, EntityFlag.RIDER_CAN_PICK);
    // 313
    Constants.addEntityFlag(61, EntityFlag.TRANSITION_SITTING);
    Constants.addEntityFlag(62, EntityFlag.EATING);
    Constants.addEntityFlag(63, EntityFlag.LAYING_DOWN);
    Constants.addEntityFlag(64, EntityFlag.SNEEZING);
    Constants.addEntityFlag(65, EntityFlag.TRUSTING);
    Constants.addEntityFlag(66, EntityFlag.ROLLING);
    Constants.addEntityFlag(67, EntityFlag.SCARED);
    Constants.addEntityFlag(68, EntityFlag.IN_SCAFFOLDING);
    Constants.addEntityFlag(69, EntityFlag.OVER_SCAFFOLDING);
    Constants.addEntityFlag(70, EntityFlag.FALL_THROUGH_SCAFFOLDING);
    // 340
    Constants.addEntityFlag(71, EntityFlag.BLOCKING);
    Constants.addEntityFlag(72, EntityFlag.TRANSITION_BLOCKING);
    Constants.addEntityFlag(73, EntityFlag.BLOCKED_USING_SHIELD);
    Constants.addEntityFlag(74, EntityFlag.SLEEPING);
    Constants.addEntityFlag(75, EntityFlag.WANTS_TO_WAKE);
    Constants.addEntityFlag(76, EntityFlag.TRADE_INTEREST);
    Constants.addEntityFlag(77, EntityFlag.DOOR_BREAKER);
    Constants.addEntityFlag(78, EntityFlag.BREAKING_OBSTRUCTION);
    Constants.addEntityFlag(79, EntityFlag.DOOR_OPENER);
    // 354
    Constants.addEntityFlag(74, EntityFlag.BLOCKED_USING_DAMAGED_SHIELD);
    Constants.addEntityFlag(75, EntityFlag.SLEEPING);
    Constants.addEntityFlag(76, EntityFlag.WANTS_TO_WAKE);
    Constants.addEntityFlag(77, EntityFlag.TRADE_INTEREST);
    Constants.addEntityFlag(78, EntityFlag.DOOR_BREAKER);
    Constants.addEntityFlag(79, EntityFlag.BREAKING_OBSTRUCTION);
    Constants.addEntityFlag(80, EntityFlag.DOOR_OPENER);
    Constants.addEntityFlag(81, EntityFlag.IS_ILLAGER_CAPTAIN);
    Constants.addEntityFlag(82, EntityFlag.STUNNED);
    Constants.addEntityFlag(83, EntityFlag.ROARING);
    Constants.addEntityFlag(84, EntityFlag.DELAYED_ATTACK);
    Constants.addEntityFlag(85, EntityFlag.IS_AVOIDING_MOBS);
    Constants.addEntityFlag(86, EntityFlag.FACING_TARGET_TO_RANGE_ATTACK);
    // 361
    Constants.addEntityFlag(87, EntityFlag.HIDDEN_WHEN_INVISIBLE);
    // 388
    Constants.addEntityFlag(88, EntityFlag.IS_IN_UI);
    Constants.addEntityFlag(89, EntityFlag.STALKING);
    Constants.addEntityFlag(90, EntityFlag.EMOTING);
    Constants.addEntityFlag(91, EntityFlag.CELEBRATING);
    // 407
    Constants.addEntityFlag(86, EntityFlag.IS_AVOIDING_BLOCK);
    Constants.addEntityFlag(87, EntityFlag.FACING_TARGET_TO_RANGE_ATTACK);
    Constants.addEntityFlag(88, EntityFlag.HIDDEN_WHEN_INVISIBLE);
    Constants.addEntityFlag(89, EntityFlag.IS_IN_UI);
    Constants.addEntityFlag(90, EntityFlag.STALKING);
    Constants.addEntityFlag(91, EntityFlag.EMOTING);
    Constants.addEntityFlag(92, EntityFlag.CELEBRATING);
    Constants.addEntityFlag(93, EntityFlag.ADMIRING);
    Constants.addEntityFlag(94, EntityFlag.CELEBRATING_SPECIAL);
    // 428
    Constants.addEntityFlag(96, EntityFlag.RAM_ATTACK);
    // 440
    Constants.addEntityFlag(97, EntityFlag.PLAYING_DEAD);
    // 448
    Constants.addEntityFlag(98, EntityFlag.IN_ASCENDABLE_BLOCK);
    Constants.addEntityFlag(99, EntityFlag.OVER_DESCENDABLE_BLOCK);
  }

  /**
   * registers the flags to bit.
   */
  private static void registerFlagsToBit() {
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.WORLD_IMMUTABLE, 1);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.NO_PVM, 1 << 1);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.NO_MVP, 1 << 2);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.SHOW_NAME_TAGS, 1 << 4);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.AUTO_JUMP, 1 << 5);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.MAY_FLY, 1 << 6);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.NO_CLIP, 1 << 7);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.WORLD_BUILDER, 1 << 8);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.FLYING, 1 << 9);
    Constants.FLAGS_TO_BIT_1.put(AdventureSetting.MUTED, 1 << 10);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.MINE, 1);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.DOORS_AND_SWITCHES, 1 << 1);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.OPEN_CONTAINERS, 1 << 2);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.ATTACK_PLAYERS, 1 << 3);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.ATTACK_MOBS, 1 << 4);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.OPERATOR, 1 << 5);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.TELEPORT, 1 << 7);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.BUILD, 1 << 8);
    Constants.FLAGS_TO_BIT_2.put(AdventureSetting.DEFAULT_LEVEL_PERMISSIONS, 1 << 9);
  }

  /**
   * registers the game rules.
   */
  private static void registerGameRules() {
    Constants.GAME_RULE_TYPES.put(Boolean.class, 1);
    Constants.GAME_RULE_TYPES.put(Integer.class, 2);
    Constants.GAME_RULE_TYPES.put(Float.class, 3);
    Constants.GAME_RULE_TYPES.defaultReturnValue(-1);
  }
}
