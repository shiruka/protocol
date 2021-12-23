package io.github.shiruka.protocol;

import io.github.shiruka.api.common.Int2ObjectBiMap;
import io.github.shiruka.protocol.data.AdventureSetting;
import io.github.shiruka.protocol.data.LevelEventType;
import io.github.shiruka.protocol.data.SoundEvent;
import io.github.shiruka.protocol.data.entity.EntityData;
import io.github.shiruka.protocol.data.entity.EntityDataType;
import io.github.shiruka.protocol.data.entity.EntityFlag;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.jetbrains.annotations.NotNull;

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
   * the level events.
   */
  public static final Int2ObjectBiMap<LevelEventType> LEVEL_EVENTS = new Int2ObjectBiMap<>();

  /**
   * the minecraft version.
   */
  public static final String MINECRAFT_VERSION = "1.18.2";

  /**
   * the protocol version.
   */
  public static final int PROTOCOL_VERSION = 475;

  /**
   * the protocol version as string.
   */
  public static final String PROTOCOL_VERSION_AS_STRING = String.valueOf(Constants.PROTOCOL_VERSION);

  /**
   * the sound events.
   */
  public static final Int2ObjectBiMap<SoundEvent> SOUND_EVENTS = new Int2ObjectBiMap<>();

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
    Constants.registerSoundEvents();
    Constants.registerLevelEvents();
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

  /**
   * registers the level events.
   */
  private static void registerLevelEvents() {
    final var sound = 1000;
    final var particle = 2000;
    final var world = 3000;
    final var block = 3500;
    final var legacy = 16384;
    Constants.LEVEL_EVENTS.put(0 + sound, LevelEventType.SOUND_CLICK);
    Constants.LEVEL_EVENTS.put(1 + sound, LevelEventType.SOUND_CLICK_FAIL);
    Constants.LEVEL_EVENTS.put(2 + sound, LevelEventType.SOUND_LAUNCH);
    Constants.LEVEL_EVENTS.put(3 + sound, LevelEventType.SOUND_DOOR_OPEN);
    Constants.LEVEL_EVENTS.put(4 + sound, LevelEventType.SOUND_FIZZ);
    Constants.LEVEL_EVENTS.put(5 + sound, LevelEventType.SOUND_FUSE);
    Constants.LEVEL_EVENTS.put(6 + sound, LevelEventType.SOUND_PLAY_RECORDING);
    Constants.LEVEL_EVENTS.put(7 + sound, LevelEventType.SOUND_GHAST_WARNING);
    Constants.LEVEL_EVENTS.put(8 + sound, LevelEventType.SOUND_GHAST_FIREBALL);
    Constants.LEVEL_EVENTS.put(9 + sound, LevelEventType.SOUND_BLAZE_FIREBALL);
    Constants.LEVEL_EVENTS.put(10 + sound, LevelEventType.SOUND_ZOMBIE_DOOR_BUMP);
    Constants.LEVEL_EVENTS.put(12 + sound, LevelEventType.SOUND_ZOMBIE_DOOR_CRASH);
    Constants.LEVEL_EVENTS.put(16 + sound, LevelEventType.SOUND_ZOMBIE_INFECTED);
    Constants.LEVEL_EVENTS.put(17 + sound, LevelEventType.SOUND_ZOMBIE_CONVERTED);
    Constants.LEVEL_EVENTS.put(18 + sound, LevelEventType.SOUND_ENDERMAN_TELEPORT);
    Constants.LEVEL_EVENTS.put(20 + sound, LevelEventType.SOUND_ANVIL_BROKEN);
    Constants.LEVEL_EVENTS.put(21 + sound, LevelEventType.SOUND_ANVIL_USED);
    Constants.LEVEL_EVENTS.put(22 + sound, LevelEventType.SOUND_ANVIL_LAND);
    Constants.LEVEL_EVENTS.put(30 + sound, LevelEventType.SOUND_INFINITY_ARROW_PICKUP);
    Constants.LEVEL_EVENTS.put(32 + sound, LevelEventType.SOUND_TELEPORT_ENDERPEARL);
    Constants.LEVEL_EVENTS.put(40 + sound, LevelEventType.SOUND_ITEMFRAME_ITEM_ADD);
    Constants.LEVEL_EVENTS.put(41 + sound, LevelEventType.SOUND_ITEMFRAME_BREAK);
    Constants.LEVEL_EVENTS.put(42 + sound, LevelEventType.SOUND_ITEMFRAME_PLACE);
    Constants.LEVEL_EVENTS.put(43 + sound, LevelEventType.SOUND_ITEMFRAME_ITEM_REMOVE);
    Constants.LEVEL_EVENTS.put(44 + sound, LevelEventType.SOUND_ITEMFRAME_ITEM_ROTATE);
    Constants.LEVEL_EVENTS.put(51 + sound, LevelEventType.SOUND_EXPERIENCE_ORB_PICKUP);
    Constants.LEVEL_EVENTS.put(52 + sound, LevelEventType.SOUND_TOTEM_USED);
    Constants.LEVEL_EVENTS.put(60 + sound, LevelEventType.SOUND_ARMOR_STAND_BREAK);
    Constants.LEVEL_EVENTS.put(61 + sound, LevelEventType.SOUND_ARMOR_STAND_HIT);
    Constants.LEVEL_EVENTS.put(62 + sound, LevelEventType.SOUND_ARMOR_STAND_LAND);
    Constants.LEVEL_EVENTS.put(63 + sound, LevelEventType.SOUND_ARMOR_STAND_PLACE);
    Constants.LEVEL_EVENTS.put(0 + particle, LevelEventType.PARTICLE_SHOOT);
    Constants.LEVEL_EVENTS.put(1 + particle, LevelEventType.PARTICLE_DESTROY_BLOCK);
    Constants.LEVEL_EVENTS.put(2 + particle, LevelEventType.PARTICLE_POTION_SPLASH);
    Constants.LEVEL_EVENTS.put(3 + particle, LevelEventType.PARTICLE_EYE_OF_ENDER_DEATH);
    Constants.LEVEL_EVENTS.put(4 + particle, LevelEventType.PARTICLE_MOB_BLOCK_SPAWN);
    Constants.LEVEL_EVENTS.put(5 + particle, LevelEventType.PARTICLE_CROP_GROWTH);
    Constants.LEVEL_EVENTS.put(6 + particle, LevelEventType.PARTICLE_SOUND_GUARDIAN_GHOST);
    Constants.LEVEL_EVENTS.put(7 + particle, LevelEventType.PARTICLE_DEATH_SMOKE);
    Constants.LEVEL_EVENTS.put(8 + particle, LevelEventType.PARTICLE_DENY_BLOCK);
    Constants.LEVEL_EVENTS.put(9 + particle, LevelEventType.PARTICLE_GENERIC_SPAWN);
    Constants.LEVEL_EVENTS.put(10 + particle, LevelEventType.PARTICLE_DRAGON_EGG);
    Constants.LEVEL_EVENTS.put(11 + particle, LevelEventType.PARTICLE_CROP_EATEN);
    Constants.LEVEL_EVENTS.put(12 + particle, LevelEventType.PARTICLE_CRIT);
    Constants.LEVEL_EVENTS.put(13 + particle, LevelEventType.PARTICLE_TELEPORT);
    Constants.LEVEL_EVENTS.put(14 + particle, LevelEventType.PARTICLE_CRACK_BLOCK);
    Constants.LEVEL_EVENTS.put(15 + particle, LevelEventType.PARTICLE_BUBBLES);
    Constants.LEVEL_EVENTS.put(16 + particle, LevelEventType.PARTICLE_EVAPORATE);
    Constants.LEVEL_EVENTS.put(17 + particle, LevelEventType.PARTICLE_DESTROY_ARMOR_STAND);
    Constants.LEVEL_EVENTS.put(18 + particle, LevelEventType.PARTICLE_BREAKING_EGG);
    Constants.LEVEL_EVENTS.put(19 + particle, LevelEventType.PARTICLE_DESTROY_EGG);
    Constants.LEVEL_EVENTS.put(20 + particle, LevelEventType.PARTICLE_EVAPORATE_WATER);
    Constants.LEVEL_EVENTS.put(21 + particle, LevelEventType.PARTICLE_DESTROY_BLOCK_NO_SOUND);
    Constants.LEVEL_EVENTS.put(1 + world, LevelEventType.START_RAINING);
    Constants.LEVEL_EVENTS.put(2 + world, LevelEventType.START_THUNDERSTORM);
    Constants.LEVEL_EVENTS.put(3 + world, LevelEventType.STOP_RAINING);
    Constants.LEVEL_EVENTS.put(4 + world, LevelEventType.STOP_THUNDERSTORM);
    Constants.LEVEL_EVENTS.put(5 + world, LevelEventType.GLOBAL_PAUSE);
    Constants.LEVEL_EVENTS.put(6 + world, LevelEventType.SIM_TIME_STEP);
    Constants.LEVEL_EVENTS.put(7 + world, LevelEventType.SIM_TIME_SCALE);
    Constants.LEVEL_EVENTS.put(0 + block, LevelEventType.ACTIVATE_BLOCK);
    Constants.LEVEL_EVENTS.put(1 + block, LevelEventType.CAULDRON_EXPLODE);
    Constants.LEVEL_EVENTS.put(2 + block, LevelEventType.CAULDRON_DYE_ARMOR);
    Constants.LEVEL_EVENTS.put(3 + block, LevelEventType.CAULDRON_CLEAN_ARMOR);
    Constants.LEVEL_EVENTS.put(4 + block, LevelEventType.CAULDRON_FILL_POTION);
    Constants.LEVEL_EVENTS.put(5 + block, LevelEventType.CAULDRON_TAKE_POTION);
    Constants.LEVEL_EVENTS.put(6 + block, LevelEventType.CAULDRON_FILL_WATER);
    Constants.LEVEL_EVENTS.put(7 + block, LevelEventType.CAULDRON_TAKE_WATER);
    Constants.LEVEL_EVENTS.put(8 + block, LevelEventType.CAULDRON_ADD_DYE);
    Constants.LEVEL_EVENTS.put(9 + block, LevelEventType.CAULDRON_CLEAN_BANNER);
    Constants.LEVEL_EVENTS.put(10 + block, LevelEventType.CAULDRON_FLUSH);
    Constants.LEVEL_EVENTS.put(1 + legacy, LevelEventType.PARTICLE_BUBBLE);
    Constants.LEVEL_EVENTS.put(2 + legacy, LevelEventType.PARTICLE_CRITICAL);
    Constants.LEVEL_EVENTS.put(3 + legacy, LevelEventType.PARTICLE_BLOCK_FORCE_FIELD);
    Constants.LEVEL_EVENTS.put(4 + legacy, LevelEventType.PARTICLE_SMOKE);
    Constants.LEVEL_EVENTS.put(5 + legacy, LevelEventType.PARTICLE_EXPLODE);
    Constants.LEVEL_EVENTS.put(6 + legacy, LevelEventType.PARTICLE_EVAPORATION);
    Constants.LEVEL_EVENTS.put(7 + legacy, LevelEventType.PARTICLE_FLAME);
    Constants.LEVEL_EVENTS.put(8 + legacy, LevelEventType.PARTICLE_LAVA);
    Constants.LEVEL_EVENTS.put(9 + legacy, LevelEventType.PARTICLE_LARGE_SMOKE);
    Constants.LEVEL_EVENTS.put(10 + legacy, LevelEventType.PARTICLE_REDSTONE);
    Constants.LEVEL_EVENTS.put(11 + legacy, LevelEventType.PARTICLE_RISING_RED_DUST);
    Constants.LEVEL_EVENTS.put(12 + legacy, LevelEventType.PARTICLE_ITEM_BREAK);
    Constants.LEVEL_EVENTS.put(13 + legacy, LevelEventType.PARTICLE_SNOWBALL_POOF);
    Constants.LEVEL_EVENTS.put(14 + legacy, LevelEventType.PARTICLE_HUGE_EXPLODE);
    Constants.LEVEL_EVENTS.put(15 + legacy, LevelEventType.PARTICLE_HUGE_EXPLODE_SEED);
    Constants.LEVEL_EVENTS.put(16 + legacy, LevelEventType.PARTICLE_MOB_FLAME);
    Constants.LEVEL_EVENTS.put(17 + legacy, LevelEventType.PARTICLE_HEART);
    Constants.LEVEL_EVENTS.put(18 + legacy, LevelEventType.PARTICLE_TERRAIN);
    Constants.LEVEL_EVENTS.put(19 + legacy, LevelEventType.PARTICLE_TOWN_AURA);
    Constants.LEVEL_EVENTS.put(20 + legacy, LevelEventType.PARTICLE_PORTAL);
    Constants.LEVEL_EVENTS.put(21 + legacy, LevelEventType.PARTICLE_SPLASH);
    Constants.LEVEL_EVENTS.put(22 + legacy, LevelEventType.PARTICLE_WATER_WAKE);
    Constants.LEVEL_EVENTS.put(23 + legacy, LevelEventType.PARTICLE_DRIP_WATER);
    Constants.LEVEL_EVENTS.put(24 + legacy, LevelEventType.PARTICLE_DRIP_LAVA);
    Constants.LEVEL_EVENTS.put(25 + legacy, LevelEventType.PARTICLE_FALLING_DUST);
    Constants.LEVEL_EVENTS.put(26 + legacy, LevelEventType.PARTICLE_MOB_SPELL);
    Constants.LEVEL_EVENTS.put(27 + legacy, LevelEventType.PARTICLE_MOB_SPELL_AMBIENT);
    Constants.LEVEL_EVENTS.put(28 + legacy, LevelEventType.PARTICLE_MOB_SPELL_INSTANTANEOUS);
    Constants.LEVEL_EVENTS.put(29 + legacy, LevelEventType.PARTICLE_INK);
    Constants.LEVEL_EVENTS.put(30 + legacy, LevelEventType.PARTICLE_SLIME);
    Constants.LEVEL_EVENTS.put(31 + legacy, LevelEventType.PARTICLE_RAIN_SPLASH);
    Constants.LEVEL_EVENTS.put(32 + legacy, LevelEventType.PARTICLE_VILLAGER_ANGRY);
    Constants.LEVEL_EVENTS.put(33 + legacy, LevelEventType.PARTICLE_VILLAGER_HAPPY);
    Constants.LEVEL_EVENTS.put(34 + legacy, LevelEventType.PARTICLE_ENCHANTMENT_TABLE);
    Constants.LEVEL_EVENTS.put(35 + legacy, LevelEventType.PARTICLE_TRACKING_EMITTER);
    Constants.LEVEL_EVENTS.put(36 + legacy, LevelEventType.PARTICLE_NOTE);
    Constants.LEVEL_EVENTS.put(37 + legacy, LevelEventType.PARTICLE_WITCH_SPELL);
    Constants.LEVEL_EVENTS.put(38 + legacy, LevelEventType.PARTICLE_CARROT);
    Constants.LEVEL_EVENTS.put(39 + legacy, LevelEventType.PARTICLE_MOB_APPEARANCE);
    Constants.LEVEL_EVENTS.put(40 + legacy, LevelEventType.PARTICLE_END_ROD);
    Constants.LEVEL_EVENTS.put(41 + legacy, LevelEventType.PARTICLE_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(42 + legacy, LevelEventType.PARTICLE_SPIT);
    Constants.LEVEL_EVENTS.put(43 + legacy, LevelEventType.PARTICLE_TOTEM);
    Constants.LEVEL_EVENTS.put(44 + legacy, LevelEventType.PARTICLE_FOOD);
    Constants.LEVEL_EVENTS.put(11 + 3500, LevelEventType.AGENT_SPAWN_EFFECT);
    Constants.LEVEL_EVENTS.put(45 + legacy, LevelEventType.PARTICLE_FIREWORKS_STARTER);
    Constants.LEVEL_EVENTS.put(46 + legacy, LevelEventType.PARTICLE_FIREWORKS_SPARK);
    Constants.LEVEL_EVENTS.put(47 + legacy, LevelEventType.PARTICLE_FIREWORKS_OVERLAY);
    Constants.LEVEL_EVENTS.put(48 + legacy, LevelEventType.PARTICLE_BALLOON_GAS);
    Constants.LEVEL_EVENTS.put(49 + legacy, LevelEventType.PARTICLE_COLORED_FLAME);
    Constants.LEVEL_EVENTS.put(50 + legacy, LevelEventType.PARTICLE_SPARKLER);
    Constants.LEVEL_EVENTS.put(51 + legacy, LevelEventType.PARTICLE_CONDUIT);
    Constants.LEVEL_EVENTS.put(52 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_UP);
    Constants.LEVEL_EVENTS.put(53 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_DOWN);
    Constants.LEVEL_EVENTS.put(54 + legacy, LevelEventType.PARTICLE_SNEEZE);
    Constants.LEVEL_EVENTS.put(12 + block, LevelEventType.CAULDRON_FILL_LAVA);
    Constants.LEVEL_EVENTS.put(13 + block, LevelEventType.CAULDRON_TAKE_LAVA);
    Constants.LEVEL_EVENTS.put(22 + 2000, LevelEventType.PARTICLE_KNOCKBACK_ROAR);
    Constants.LEVEL_EVENTS.put(23 + 2000, LevelEventType.PARTICLE_TELEPORT_TRAIL);
    Constants.LEVEL_EVENTS.put(1 + legacy, LevelEventType.PARTICLE_BUBBLE);
    Constants.LEVEL_EVENTS.put(2 + legacy, LevelEventType.PARTICLE_BUBBLE_MANUAL);
    Constants.LEVEL_EVENTS.put(3 + legacy, LevelEventType.PARTICLE_CRITICAL);
    Constants.LEVEL_EVENTS.put(4 + legacy, LevelEventType.PARTICLE_BLOCK_FORCE_FIELD);
    Constants.LEVEL_EVENTS.put(5 + legacy, LevelEventType.PARTICLE_SMOKE);
    Constants.LEVEL_EVENTS.put(6 + legacy, LevelEventType.PARTICLE_EXPLODE);
    Constants.LEVEL_EVENTS.put(7 + legacy, LevelEventType.PARTICLE_EVAPORATION);
    Constants.LEVEL_EVENTS.put(8 + legacy, LevelEventType.PARTICLE_FLAME);
    Constants.LEVEL_EVENTS.put(9 + legacy, LevelEventType.PARTICLE_LAVA);
    Constants.LEVEL_EVENTS.put(10 + legacy, LevelEventType.PARTICLE_LARGE_SMOKE);
    Constants.LEVEL_EVENTS.put(11 + legacy, LevelEventType.PARTICLE_REDSTONE);
    Constants.LEVEL_EVENTS.put(12 + legacy, LevelEventType.PARTICLE_RISING_RED_DUST);
    Constants.LEVEL_EVENTS.put(13 + legacy, LevelEventType.PARTICLE_ITEM_BREAK);
    Constants.LEVEL_EVENTS.put(14 + legacy, LevelEventType.PARTICLE_SNOWBALL_POOF);
    Constants.LEVEL_EVENTS.put(15 + legacy, LevelEventType.PARTICLE_HUGE_EXPLODE);
    Constants.LEVEL_EVENTS.put(16 + legacy, LevelEventType.PARTICLE_HUGE_EXPLODE_SEED);
    Constants.LEVEL_EVENTS.put(17 + legacy, LevelEventType.PARTICLE_MOB_FLAME);
    Constants.LEVEL_EVENTS.put(18 + legacy, LevelEventType.PARTICLE_HEART);
    Constants.LEVEL_EVENTS.put(19 + legacy, LevelEventType.PARTICLE_TERRAIN);
    Constants.LEVEL_EVENTS.put(20 + legacy, LevelEventType.PARTICLE_TOWN_AURA);
    Constants.LEVEL_EVENTS.put(21 + legacy, LevelEventType.PARTICLE_PORTAL);
    Constants.LEVEL_EVENTS.put(22 + legacy, LevelEventType.PARTICLE_MOB_PORTAL);
    Constants.LEVEL_EVENTS.put(23 + legacy, LevelEventType.PARTICLE_SPLASH);
    Constants.LEVEL_EVENTS.put(24 + legacy, LevelEventType.PARTICLE_SPLASH_MANUAL);
    Constants.LEVEL_EVENTS.put(25 + legacy, LevelEventType.PARTICLE_WATER_WAKE);
    Constants.LEVEL_EVENTS.put(26 + legacy, LevelEventType.PARTICLE_DRIP_WATER);
    Constants.LEVEL_EVENTS.put(27 + legacy, LevelEventType.PARTICLE_DRIP_LAVA);
    Constants.LEVEL_EVENTS.put(28 + legacy, LevelEventType.PARTICLE_FALLING_DUST);
    Constants.LEVEL_EVENTS.put(29 + legacy, LevelEventType.PARTICLE_MOB_SPELL);
    Constants.LEVEL_EVENTS.put(30 + legacy, LevelEventType.PARTICLE_MOB_SPELL_AMBIENT);
    Constants.LEVEL_EVENTS.put(31 + legacy, LevelEventType.PARTICLE_MOB_SPELL_INSTANTANEOUS);
    Constants.LEVEL_EVENTS.put(32 + legacy, LevelEventType.PARTICLE_INK);
    Constants.LEVEL_EVENTS.put(33 + legacy, LevelEventType.PARTICLE_SLIME);
    Constants.LEVEL_EVENTS.put(34 + legacy, LevelEventType.PARTICLE_RAIN_SPLASH);
    Constants.LEVEL_EVENTS.put(35 + legacy, LevelEventType.PARTICLE_VILLAGER_ANGRY);
    Constants.LEVEL_EVENTS.put(36 + legacy, LevelEventType.PARTICLE_VILLAGER_HAPPY);
    Constants.LEVEL_EVENTS.put(37 + legacy, LevelEventType.PARTICLE_ENCHANTMENT_TABLE);
    Constants.LEVEL_EVENTS.put(38 + legacy, LevelEventType.PARTICLE_TRACKING_EMITTER);
    Constants.LEVEL_EVENTS.put(39 + legacy, LevelEventType.PARTICLE_NOTE);
    Constants.LEVEL_EVENTS.put(40 + legacy, LevelEventType.PARTICLE_WITCH_SPELL);
    Constants.LEVEL_EVENTS.put(41 + legacy, LevelEventType.PARTICLE_CARROT);
    Constants.LEVEL_EVENTS.put(42 + legacy, LevelEventType.PARTICLE_MOB_APPEARANCE);
    Constants.LEVEL_EVENTS.put(43 + legacy, LevelEventType.PARTICLE_END_ROD);
    Constants.LEVEL_EVENTS.put(44 + legacy, LevelEventType.PARTICLE_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(45 + legacy, LevelEventType.PARTICLE_SPIT);
    Constants.LEVEL_EVENTS.put(46 + legacy, LevelEventType.PARTICLE_TOTEM);
    Constants.LEVEL_EVENTS.put(47 + legacy, LevelEventType.PARTICLE_FOOD);
    Constants.LEVEL_EVENTS.put(48 + legacy, LevelEventType.PARTICLE_FIREWORKS_STARTER);
    Constants.LEVEL_EVENTS.put(49 + legacy, LevelEventType.PARTICLE_FIREWORKS_SPARK);
    Constants.LEVEL_EVENTS.put(50 + legacy, LevelEventType.PARTICLE_FIREWORKS_OVERLAY);
    Constants.LEVEL_EVENTS.put(51 + legacy, LevelEventType.PARTICLE_BALLOON_GAS);
    Constants.LEVEL_EVENTS.put(52 + legacy, LevelEventType.PARTICLE_COLORED_FLAME);
    Constants.LEVEL_EVENTS.put(53 + legacy, LevelEventType.PARTICLE_SPARKLER);
    Constants.LEVEL_EVENTS.put(54 + legacy, LevelEventType.PARTICLE_CONDUIT);
    Constants.LEVEL_EVENTS.put(55 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_UP);
    Constants.LEVEL_EVENTS.put(56 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_DOWN);
    Constants.LEVEL_EVENTS.put(57 + legacy, LevelEventType.PARTICLE_SNEEZE);
    Constants.LEVEL_EVENTS.put(24 + particle, LevelEventType.PARTICLE_POINT_CLOUD);
    Constants.LEVEL_EVENTS.put(25 + particle, LevelEventType.PARTICLE_EXPLOSION);
    Constants.LEVEL_EVENTS.put(26 + particle, LevelEventType.PARTICLE_BLOCK_EXPLOSION);
    Constants.LEVEL_EVENTS.put(23 + 2000, LevelEventType.PARTICLE_TELEPORT_TRAIL);
    Constants.LEVEL_EVENTS.put(9810, LevelEventType.JUMP_PREVENTED);
    Constants.LEVEL_EVENTS.put(28 + legacy, LevelEventType.PARTICLE_DRIP_HONEY);
    Constants.LEVEL_EVENTS.put(29 + legacy, LevelEventType.PARTICLE_FALLING_DUST);
    Constants.LEVEL_EVENTS.put(30 + legacy, LevelEventType.PARTICLE_MOB_SPELL);
    Constants.LEVEL_EVENTS.put(31 + legacy, LevelEventType.PARTICLE_MOB_SPELL_AMBIENT);
    Constants.LEVEL_EVENTS.put(32 + legacy, LevelEventType.PARTICLE_MOB_SPELL_INSTANTANEOUS);
    Constants.LEVEL_EVENTS.put(33 + legacy, LevelEventType.PARTICLE_INK);
    Constants.LEVEL_EVENTS.put(34 + legacy, LevelEventType.PARTICLE_SLIME);
    Constants.LEVEL_EVENTS.put(35 + legacy, LevelEventType.PARTICLE_RAIN_SPLASH);
    Constants.LEVEL_EVENTS.put(36 + legacy, LevelEventType.PARTICLE_VILLAGER_ANGRY);
    Constants.LEVEL_EVENTS.put(37 + legacy, LevelEventType.PARTICLE_VILLAGER_HAPPY);
    Constants.LEVEL_EVENTS.put(38 + legacy, LevelEventType.PARTICLE_ENCHANTMENT_TABLE);
    Constants.LEVEL_EVENTS.put(39 + legacy, LevelEventType.PARTICLE_TRACKING_EMITTER);
    Constants.LEVEL_EVENTS.put(40 + legacy, LevelEventType.PARTICLE_NOTE);
    Constants.LEVEL_EVENTS.put(41 + legacy, LevelEventType.PARTICLE_WITCH_SPELL);
    Constants.LEVEL_EVENTS.put(42 + legacy, LevelEventType.PARTICLE_CARROT);
    Constants.LEVEL_EVENTS.put(43 + legacy, LevelEventType.PARTICLE_MOB_APPEARANCE);
    Constants.LEVEL_EVENTS.put(44 + legacy, LevelEventType.PARTICLE_END_ROD);
    Constants.LEVEL_EVENTS.put(45 + legacy, LevelEventType.PARTICLE_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(46 + legacy, LevelEventType.PARTICLE_SPIT);
    Constants.LEVEL_EVENTS.put(47 + legacy, LevelEventType.PARTICLE_TOTEM);
    Constants.LEVEL_EVENTS.put(48 + legacy, LevelEventType.PARTICLE_FOOD);
    Constants.LEVEL_EVENTS.put(49 + legacy, LevelEventType.PARTICLE_FIREWORKS_STARTER);
    Constants.LEVEL_EVENTS.put(50 + legacy, LevelEventType.PARTICLE_FIREWORKS_SPARK);
    Constants.LEVEL_EVENTS.put(51 + legacy, LevelEventType.PARTICLE_FIREWORKS_OVERLAY);
    Constants.LEVEL_EVENTS.put(52 + legacy, LevelEventType.PARTICLE_BALLOON_GAS);
    Constants.LEVEL_EVENTS.put(53 + legacy, LevelEventType.PARTICLE_COLORED_FLAME);
    Constants.LEVEL_EVENTS.put(54 + legacy, LevelEventType.PARTICLE_SPARKLER);
    Constants.LEVEL_EVENTS.put(55 + legacy, LevelEventType.PARTICLE_CONDUIT);
    Constants.LEVEL_EVENTS.put(56 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_UP);
    Constants.LEVEL_EVENTS.put(57 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_DOWN);
    Constants.LEVEL_EVENTS.put(58 + legacy, LevelEventType.PARTICLE_SNEEZE);
    Constants.LEVEL_EVENTS.put(59 + legacy, LevelEventType.PARTICLE_SHULKER_BULLET);
    Constants.LEVEL_EVENTS.put(60 + legacy, LevelEventType.PARTICLE_BLEACH);
    Constants.LEVEL_EVENTS.put(61 + legacy, LevelEventType.PARTICLE_DRAGON_DESTROY_BLOCK);
    Constants.LEVEL_EVENTS.put(62 + legacy, LevelEventType.PARTICLE_MYCELIUM_DUST);
    Constants.LEVEL_EVENTS.put(63 + legacy, LevelEventType.PARTICLE_FALLING_RED_DUST);
    Constants.LEVEL_EVENTS.put(64 + legacy, LevelEventType.PARTICLE_CAMPFIRE_SMOKE);
    Constants.LEVEL_EVENTS.put(65 + legacy, LevelEventType.PARTICLE_TALL_CAMPFIRE_SMOKE);
    Constants.LEVEL_EVENTS.put(66 + legacy, LevelEventType.PARTICLE_RISING_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(67 + legacy, LevelEventType.PARTICLE_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(1050, LevelEventType.SOUND_CAMERA);
    Constants.LEVEL_EVENTS.put(3600, LevelEventType.BLOCK_START_BREAK);
    Constants.LEVEL_EVENTS.put(3601, LevelEventType.BLOCK_STOP_BREAK);
    Constants.LEVEL_EVENTS.put(3602, LevelEventType.BLOCK_UPDATE_BREAK);
    Constants.LEVEL_EVENTS.put(4000, LevelEventType.SET_DATA);
    Constants.LEVEL_EVENTS.put(9800, LevelEventType.ALL_PLAYERS_SLEEPING);
    Constants.LEVEL_EVENTS.put(68 + legacy, LevelEventType.PARTICLE_BLUE_FLAME);
    Constants.LEVEL_EVENTS.put(69 + legacy, LevelEventType.PARTICLE_SOUL);
    Constants.LEVEL_EVENTS.put(70 + legacy, LevelEventType.PARTICLE_OBSIDIAN_TEAR);
    Constants.LEVEL_EVENTS.put(2027, LevelEventType.PARTICLE_VIBRATION_SIGNAL);
    Constants.LEVEL_EVENTS.put(3514, LevelEventType.CAULDRON_FILL_POWDER_SNOW);
    Constants.LEVEL_EVENTS.put(3515, LevelEventType.CAULDRON_TAKE_POWDER_SNOW);
    Constants.LEVEL_EVENTS.put(1064, LevelEventType.SOUND_POINTED_DRIPSTONE_LAND);
    Constants.LEVEL_EVENTS.put(1065, LevelEventType.SOUND_DYE_USED);
    Constants.LEVEL_EVENTS.put(1066, LevelEventType.SOUND_INK_SACE_USED);
    Constants.LEVEL_EVENTS.put(2028, LevelEventType.PARTICLE_DRIPSTONE_DRIP);
    Constants.LEVEL_EVENTS.put(2029, LevelEventType.PARTICLE_FIZZ_EFFECT);
    Constants.LEVEL_EVENTS.put(2030, LevelEventType.PARTICLE_WAX_ON);
    Constants.LEVEL_EVENTS.put(2031, LevelEventType.PARTICLE_WAX_OFF);
    Constants.LEVEL_EVENTS.put(2032, LevelEventType.PARTICLE_SCRAPE);
    Constants.LEVEL_EVENTS.put(2033, LevelEventType.PARTICLE_ELECTRIC_SPARK);
    Constants.LEVEL_EVENTS.put(29 + legacy, LevelEventType.PARTICLE_STALACTITE_DRIP_WATER);
    Constants.LEVEL_EVENTS.put(30 + legacy, LevelEventType.PARTICLE_STALACTITE_DRIP_LAVA);
    Constants.LEVEL_EVENTS.put(31 + legacy, LevelEventType.PARTICLE_FALLING_DUST);
    Constants.LEVEL_EVENTS.put(32 + legacy, LevelEventType.PARTICLE_MOB_SPELL);
    Constants.LEVEL_EVENTS.put(33 + legacy, LevelEventType.PARTICLE_MOB_SPELL_AMBIENT);
    Constants.LEVEL_EVENTS.put(34 + legacy, LevelEventType.PARTICLE_MOB_SPELL_INSTANTANEOUS);
    Constants.LEVEL_EVENTS.put(35 + legacy, LevelEventType.PARTICLE_INK);
    Constants.LEVEL_EVENTS.put(36 + legacy, LevelEventType.PARTICLE_SLIME);
    Constants.LEVEL_EVENTS.put(37 + legacy, LevelEventType.PARTICLE_RAIN_SPLASH);
    Constants.LEVEL_EVENTS.put(38 + legacy, LevelEventType.PARTICLE_VILLAGER_ANGRY);
    Constants.LEVEL_EVENTS.put(39 + legacy, LevelEventType.PARTICLE_VILLAGER_HAPPY);
    Constants.LEVEL_EVENTS.put(40 + legacy, LevelEventType.PARTICLE_ENCHANTMENT_TABLE);
    Constants.LEVEL_EVENTS.put(41 + legacy, LevelEventType.PARTICLE_TRACKING_EMITTER);
    Constants.LEVEL_EVENTS.put(42 + legacy, LevelEventType.PARTICLE_NOTE);
    Constants.LEVEL_EVENTS.put(43 + legacy, LevelEventType.PARTICLE_WITCH_SPELL);
    Constants.LEVEL_EVENTS.put(44 + legacy, LevelEventType.PARTICLE_CARROT);
    Constants.LEVEL_EVENTS.put(45 + legacy, LevelEventType.PARTICLE_MOB_APPEARANCE);
    Constants.LEVEL_EVENTS.put(46 + legacy, LevelEventType.PARTICLE_END_ROD);
    Constants.LEVEL_EVENTS.put(47 + legacy, LevelEventType.PARTICLE_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(48 + legacy, LevelEventType.PARTICLE_SPIT);
    Constants.LEVEL_EVENTS.put(49 + legacy, LevelEventType.PARTICLE_TOTEM);
    Constants.LEVEL_EVENTS.put(50 + legacy, LevelEventType.PARTICLE_FOOD);
    Constants.LEVEL_EVENTS.put(51 + legacy, LevelEventType.PARTICLE_FIREWORKS_STARTER);
    Constants.LEVEL_EVENTS.put(52 + legacy, LevelEventType.PARTICLE_FIREWORKS_SPARK);
    Constants.LEVEL_EVENTS.put(53 + legacy, LevelEventType.PARTICLE_FIREWORKS_OVERLAY);
    Constants.LEVEL_EVENTS.put(54 + legacy, LevelEventType.PARTICLE_BALLOON_GAS);
    Constants.LEVEL_EVENTS.put(55 + legacy, LevelEventType.PARTICLE_COLORED_FLAME);
    Constants.LEVEL_EVENTS.put(56 + legacy, LevelEventType.PARTICLE_SPARKLER);
    Constants.LEVEL_EVENTS.put(57 + legacy, LevelEventType.PARTICLE_CONDUIT);
    Constants.LEVEL_EVENTS.put(58 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_UP);
    Constants.LEVEL_EVENTS.put(59 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_DOWN);
    Constants.LEVEL_EVENTS.put(60 + legacy, LevelEventType.PARTICLE_SNEEZE);
    Constants.LEVEL_EVENTS.put(61 + legacy, LevelEventType.PARTICLE_SHULKER_BULLET);
    Constants.LEVEL_EVENTS.put(62 + legacy, LevelEventType.PARTICLE_BLEACH);
    Constants.LEVEL_EVENTS.put(63 + legacy, LevelEventType.PARTICLE_DRAGON_DESTROY_BLOCK);
    Constants.LEVEL_EVENTS.put(64 + legacy, LevelEventType.PARTICLE_MYCELIUM_DUST);
    Constants.LEVEL_EVENTS.put(65 + legacy, LevelEventType.PARTICLE_FALLING_RED_DUST);
    Constants.LEVEL_EVENTS.put(66 + legacy, LevelEventType.PARTICLE_CAMPFIRE_SMOKE);
    Constants.LEVEL_EVENTS.put(67 + legacy, LevelEventType.PARTICLE_TALL_CAMPFIRE_SMOKE);
    Constants.LEVEL_EVENTS.put(68 + legacy, LevelEventType.PARTICLE_RISING_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(69 + legacy, LevelEventType.PARTICLE_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(70 + legacy, LevelEventType.PARTICLE_BLUE_FLAME);
    Constants.LEVEL_EVENTS.put(71 + legacy, LevelEventType.PARTICLE_SOUL);
    Constants.LEVEL_EVENTS.put(72 + legacy, LevelEventType.PARTICLE_OBSIDIAN_TEAR);
    Constants.LEVEL_EVENTS.put(9 + legacy, LevelEventType.PARTICLE_CANDLE_FLAME);
    Constants.LEVEL_EVENTS.put(10 + legacy, LevelEventType.PARTICLE_LAVA);
    Constants.LEVEL_EVENTS.put(11 + legacy, LevelEventType.PARTICLE_LARGE_SMOKE);
    Constants.LEVEL_EVENTS.put(12 + legacy, LevelEventType.PARTICLE_REDSTONE);
    Constants.LEVEL_EVENTS.put(13 + legacy, LevelEventType.PARTICLE_RISING_RED_DUST);
    Constants.LEVEL_EVENTS.put(14 + legacy, LevelEventType.PARTICLE_ITEM_BREAK);
    Constants.LEVEL_EVENTS.put(15 + legacy, LevelEventType.PARTICLE_SNOWBALL_POOF);
    Constants.LEVEL_EVENTS.put(16 + legacy, LevelEventType.PARTICLE_HUGE_EXPLODE);
    Constants.LEVEL_EVENTS.put(17 + legacy, LevelEventType.PARTICLE_HUGE_EXPLODE_SEED);
    Constants.LEVEL_EVENTS.put(18 + legacy, LevelEventType.PARTICLE_MOB_FLAME);
    Constants.LEVEL_EVENTS.put(19 + legacy, LevelEventType.PARTICLE_HEART);
    Constants.LEVEL_EVENTS.put(20 + legacy, LevelEventType.PARTICLE_TERRAIN);
    Constants.LEVEL_EVENTS.put(21 + legacy, LevelEventType.PARTICLE_TOWN_AURA);
    Constants.LEVEL_EVENTS.put(22 + legacy, LevelEventType.PARTICLE_PORTAL);
    Constants.LEVEL_EVENTS.put(23 + legacy, LevelEventType.PARTICLE_MOB_PORTAL);
    Constants.LEVEL_EVENTS.put(24 + legacy, LevelEventType.PARTICLE_SPLASH);
    Constants.LEVEL_EVENTS.put(25 + legacy, LevelEventType.PARTICLE_SPLASH_MANUAL);
    Constants.LEVEL_EVENTS.put(26 + legacy, LevelEventType.PARTICLE_WATER_WAKE);
    Constants.LEVEL_EVENTS.put(27 + legacy, LevelEventType.PARTICLE_DRIP_WATER);
    Constants.LEVEL_EVENTS.put(28 + legacy, LevelEventType.PARTICLE_DRIP_LAVA);
    Constants.LEVEL_EVENTS.put(29 + legacy, LevelEventType.PARTICLE_DRIP_HONEY);
    Constants.LEVEL_EVENTS.put(30 + legacy, LevelEventType.PARTICLE_STALACTITE_DRIP_WATER);
    Constants.LEVEL_EVENTS.put(31 + legacy, LevelEventType.PARTICLE_STALACTITE_DRIP_LAVA);
    Constants.LEVEL_EVENTS.put(32 + legacy, LevelEventType.PARTICLE_FALLING_DUST);
    Constants.LEVEL_EVENTS.put(33 + legacy, LevelEventType.PARTICLE_MOB_SPELL);
    Constants.LEVEL_EVENTS.put(34 + legacy, LevelEventType.PARTICLE_MOB_SPELL_AMBIENT);
    Constants.LEVEL_EVENTS.put(35 + legacy, LevelEventType.PARTICLE_MOB_SPELL_INSTANTANEOUS);
    Constants.LEVEL_EVENTS.put(36 + legacy, LevelEventType.PARTICLE_INK);
    Constants.LEVEL_EVENTS.put(37 + legacy, LevelEventType.PARTICLE_SLIME);
    Constants.LEVEL_EVENTS.put(38 + legacy, LevelEventType.PARTICLE_RAIN_SPLASH);
    Constants.LEVEL_EVENTS.put(39 + legacy, LevelEventType.PARTICLE_VILLAGER_ANGRY);
    Constants.LEVEL_EVENTS.put(40 + legacy, LevelEventType.PARTICLE_VILLAGER_HAPPY);
    Constants.LEVEL_EVENTS.put(41 + legacy, LevelEventType.PARTICLE_ENCHANTMENT_TABLE);
    Constants.LEVEL_EVENTS.put(42 + legacy, LevelEventType.PARTICLE_TRACKING_EMITTER);
    Constants.LEVEL_EVENTS.put(43 + legacy, LevelEventType.PARTICLE_NOTE);
    Constants.LEVEL_EVENTS.put(44 + legacy, LevelEventType.PARTICLE_WITCH_SPELL);
    Constants.LEVEL_EVENTS.put(45 + legacy, LevelEventType.PARTICLE_CARROT);
    Constants.LEVEL_EVENTS.put(46 + legacy, LevelEventType.PARTICLE_MOB_APPEARANCE);
    Constants.LEVEL_EVENTS.put(47 + legacy, LevelEventType.PARTICLE_END_ROD);
    Constants.LEVEL_EVENTS.put(48 + legacy, LevelEventType.PARTICLE_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(49 + legacy, LevelEventType.PARTICLE_SPIT);
    Constants.LEVEL_EVENTS.put(50 + legacy, LevelEventType.PARTICLE_TOTEM);
    Constants.LEVEL_EVENTS.put(51 + legacy, LevelEventType.PARTICLE_FOOD);
    Constants.LEVEL_EVENTS.put(52 + legacy, LevelEventType.PARTICLE_FIREWORKS_STARTER);
    Constants.LEVEL_EVENTS.put(53 + legacy, LevelEventType.PARTICLE_FIREWORKS_SPARK);
    Constants.LEVEL_EVENTS.put(54 + legacy, LevelEventType.PARTICLE_FIREWORKS_OVERLAY);
    Constants.LEVEL_EVENTS.put(55 + legacy, LevelEventType.PARTICLE_BALLOON_GAS);
    Constants.LEVEL_EVENTS.put(56 + legacy, LevelEventType.PARTICLE_COLORED_FLAME);
    Constants.LEVEL_EVENTS.put(57 + legacy, LevelEventType.PARTICLE_SPARKLER);
    Constants.LEVEL_EVENTS.put(58 + legacy, LevelEventType.PARTICLE_CONDUIT);
    Constants.LEVEL_EVENTS.put(59 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_UP);
    Constants.LEVEL_EVENTS.put(60 + legacy, LevelEventType.PARTICLE_BUBBLE_COLUMN_DOWN);
    Constants.LEVEL_EVENTS.put(61 + legacy, LevelEventType.PARTICLE_SNEEZE);
    Constants.LEVEL_EVENTS.put(62 + legacy, LevelEventType.PARTICLE_SHULKER_BULLET);
    Constants.LEVEL_EVENTS.put(63 + legacy, LevelEventType.PARTICLE_BLEACH);
    Constants.LEVEL_EVENTS.put(64 + legacy, LevelEventType.PARTICLE_DRAGON_DESTROY_BLOCK);
    Constants.LEVEL_EVENTS.put(65 + legacy, LevelEventType.PARTICLE_MYCELIUM_DUST);
    Constants.LEVEL_EVENTS.put(66 + legacy, LevelEventType.PARTICLE_FALLING_RED_DUST);
    Constants.LEVEL_EVENTS.put(67 + legacy, LevelEventType.PARTICLE_CAMPFIRE_SMOKE);
    Constants.LEVEL_EVENTS.put(68 + legacy, LevelEventType.PARTICLE_TALL_CAMPFIRE_SMOKE);
    Constants.LEVEL_EVENTS.put(69 + legacy, LevelEventType.PARTICLE_RISING_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(70 + legacy, LevelEventType.PARTICLE_DRAGONS_BREATH);
    Constants.LEVEL_EVENTS.put(71 + legacy, LevelEventType.PARTICLE_BLUE_FLAME);
    Constants.LEVEL_EVENTS.put(72 + legacy, LevelEventType.PARTICLE_SOUL);
    Constants.LEVEL_EVENTS.put(73 + legacy, LevelEventType.PARTICLE_OBSIDIAN_TEAR);
    Constants.LEVEL_EVENTS.put(74 + legacy, LevelEventType.PARTICLE_PORTAL_REVERSE);
    Constants.LEVEL_EVENTS.put(75 + legacy, LevelEventType.PARTICLE_SNOWFLAKE);
    Constants.LEVEL_EVENTS.put(76 + legacy, LevelEventType.PARTICLE_VIBRATION_SIGNAL);
    Constants.LEVEL_EVENTS.put(77 + legacy, LevelEventType.PARTICLE_SCULK_SENSOR_REDSTONE);
    Constants.LEVEL_EVENTS.put(78 + legacy, LevelEventType.PARTICLE_SPORE_BLOSSOM_SHOWER);
    Constants.LEVEL_EVENTS.put(79 + legacy, LevelEventType.PARTICLE_SPORE_BLOSSOM_AMBIENT);
    Constants.LEVEL_EVENTS.put(80 + legacy, LevelEventType.PARTICLE_WAX);
    Constants.LEVEL_EVENTS.put(81 + legacy, LevelEventType.PARTICLE_ELECTRIC_SPARK);
    Constants.LEVEL_EVENTS.put(2034, LevelEventType.PARTICLE_TURTLE_EGG);
    Constants.LEVEL_EVENTS.put(2035, LevelEventType.PARTICLE_SCULK_SHRIEK);
    Constants.LEVEL_EVENTS.put(82 + legacy, LevelEventType.PARTICLE_SHRIEK);
    Constants.LEVEL_EVENTS.put(2036, LevelEventType.SCULK_CATALYST_BLOOM);
    Constants.LEVEL_EVENTS.put(83 + legacy, LevelEventType.PARTICLE_SCULK_SOUL);
    Constants.LEVEL_EVENTS.put(9801, LevelEventType.SLEEPING_PLAYERS);
  }

  /**
   * registers sound events.
   */
  private static void registerSoundEvents() {
    Constants.SOUND_EVENTS.put(0, SoundEvent.ITEM_USE_ON);
    Constants.SOUND_EVENTS.put(1, SoundEvent.HIT);
    Constants.SOUND_EVENTS.put(2, SoundEvent.STEP);
    Constants.SOUND_EVENTS.put(3, SoundEvent.FLY);
    Constants.SOUND_EVENTS.put(4, SoundEvent.JUMP);
    Constants.SOUND_EVENTS.put(5, SoundEvent.BREAK);
    Constants.SOUND_EVENTS.put(6, SoundEvent.PLACE);
    Constants.SOUND_EVENTS.put(7, SoundEvent.HEAVY_STEP);
    Constants.SOUND_EVENTS.put(8, SoundEvent.GALLOP);
    Constants.SOUND_EVENTS.put(9, SoundEvent.FALL);
    Constants.SOUND_EVENTS.put(10, SoundEvent.AMBIENT);
    Constants.SOUND_EVENTS.put(11, SoundEvent.AMBIENT_BABY);
    Constants.SOUND_EVENTS.put(12, SoundEvent.AMBIENT_IN_WATER);
    Constants.SOUND_EVENTS.put(13, SoundEvent.BREATHE);
    Constants.SOUND_EVENTS.put(14, SoundEvent.DEATH);
    Constants.SOUND_EVENTS.put(15, SoundEvent.DEATH_IN_WATER);
    Constants.SOUND_EVENTS.put(16, SoundEvent.DEATH_TO_ZOMBIE);
    Constants.SOUND_EVENTS.put(17, SoundEvent.HURT);
    Constants.SOUND_EVENTS.put(18, SoundEvent.HURT_IN_WATER);
    Constants.SOUND_EVENTS.put(19, SoundEvent.MAD);
    Constants.SOUND_EVENTS.put(20, SoundEvent.BOOST);
    Constants.SOUND_EVENTS.put(21, SoundEvent.BOW);
    Constants.SOUND_EVENTS.put(22, SoundEvent.SQUISH_BIG);
    Constants.SOUND_EVENTS.put(23, SoundEvent.SQUISH_SMALL);
    Constants.SOUND_EVENTS.put(24, SoundEvent.FALL_BIG);
    Constants.SOUND_EVENTS.put(25, SoundEvent.FALL_SMALL);
    Constants.SOUND_EVENTS.put(26, SoundEvent.SPLASH);
    Constants.SOUND_EVENTS.put(27, SoundEvent.FIZZ);
    Constants.SOUND_EVENTS.put(28, SoundEvent.FLAP);
    Constants.SOUND_EVENTS.put(29, SoundEvent.SWIM);
    Constants.SOUND_EVENTS.put(30, SoundEvent.DRINK);
    Constants.SOUND_EVENTS.put(31, SoundEvent.EAT);
    Constants.SOUND_EVENTS.put(32, SoundEvent.TAKEOFF);
    Constants.SOUND_EVENTS.put(33, SoundEvent.SHAKE);
    Constants.SOUND_EVENTS.put(34, SoundEvent.PLOP);
    Constants.SOUND_EVENTS.put(35, SoundEvent.LAND);
    Constants.SOUND_EVENTS.put(36, SoundEvent.SADDLE);
    Constants.SOUND_EVENTS.put(37, SoundEvent.ARMOR);
    Constants.SOUND_EVENTS.put(38, SoundEvent.MOB_ARMOR_STAND_PLACE);
    Constants.SOUND_EVENTS.put(39, SoundEvent.ADD_CHEST);
    Constants.SOUND_EVENTS.put(40, SoundEvent.THROW);
    Constants.SOUND_EVENTS.put(41, SoundEvent.ATTACK);
    Constants.SOUND_EVENTS.put(42, SoundEvent.ATTACK_NODAMAGE);
    Constants.SOUND_EVENTS.put(43, SoundEvent.ATTACK_STRONG);
    Constants.SOUND_EVENTS.put(44, SoundEvent.WARN);
    Constants.SOUND_EVENTS.put(45, SoundEvent.SHEAR);
    Constants.SOUND_EVENTS.put(46, SoundEvent.MILK);
    Constants.SOUND_EVENTS.put(47, SoundEvent.THUNDER);
    Constants.SOUND_EVENTS.put(48, SoundEvent.EXPLODE);
    Constants.SOUND_EVENTS.put(49, SoundEvent.FIRE);
    Constants.SOUND_EVENTS.put(50, SoundEvent.IGNITE);
    Constants.SOUND_EVENTS.put(51, SoundEvent.FUSE);
    Constants.SOUND_EVENTS.put(52, SoundEvent.STARE);
    Constants.SOUND_EVENTS.put(53, SoundEvent.SPAWN);
    Constants.SOUND_EVENTS.put(54, SoundEvent.SHOOT);
    Constants.SOUND_EVENTS.put(55, SoundEvent.BREAK_BLOCK);
    Constants.SOUND_EVENTS.put(56, SoundEvent.LAUNCH);
    Constants.SOUND_EVENTS.put(57, SoundEvent.BLAST);
    Constants.SOUND_EVENTS.put(58, SoundEvent.LARGE_BLAST);
    Constants.SOUND_EVENTS.put(59, SoundEvent.TWINKLE);
    Constants.SOUND_EVENTS.put(60, SoundEvent.REMEDY);
    Constants.SOUND_EVENTS.put(61, SoundEvent.UNFECT);
    Constants.SOUND_EVENTS.put(62, SoundEvent.LEVELUP);
    Constants.SOUND_EVENTS.put(63, SoundEvent.BOW_HIT);
    Constants.SOUND_EVENTS.put(64, SoundEvent.BULLET_HIT);
    Constants.SOUND_EVENTS.put(65, SoundEvent.EXTINGUISH_FIRE);
    Constants.SOUND_EVENTS.put(66, SoundEvent.ITEM_FIZZ);
    Constants.SOUND_EVENTS.put(67, SoundEvent.CHEST_OPEN);
    Constants.SOUND_EVENTS.put(68, SoundEvent.CHEST_CLOSED);
    Constants.SOUND_EVENTS.put(69, SoundEvent.SHULKERBOX_OPEN);
    Constants.SOUND_EVENTS.put(70, SoundEvent.SHULKERBOX_CLOSED);
    Constants.SOUND_EVENTS.put(71, SoundEvent.ENDERCHEST_OPEN);
    Constants.SOUND_EVENTS.put(72, SoundEvent.ENDERCHEST_CLOSED);
    Constants.SOUND_EVENTS.put(73, SoundEvent.POWER_ON);
    Constants.SOUND_EVENTS.put(74, SoundEvent.POWER_OFF);
    Constants.SOUND_EVENTS.put(75, SoundEvent.ATTACH);
    Constants.SOUND_EVENTS.put(76, SoundEvent.DETACH);
    Constants.SOUND_EVENTS.put(77, SoundEvent.DENY);
    Constants.SOUND_EVENTS.put(78, SoundEvent.TRIPOD);
    Constants.SOUND_EVENTS.put(79, SoundEvent.POP);
    Constants.SOUND_EVENTS.put(80, SoundEvent.DROP_SLOT);
    Constants.SOUND_EVENTS.put(81, SoundEvent.NOTE);
    Constants.SOUND_EVENTS.put(82, SoundEvent.THORNS);
    Constants.SOUND_EVENTS.put(83, SoundEvent.PISTON_IN);
    Constants.SOUND_EVENTS.put(84, SoundEvent.PISTON_OUT);
    Constants.SOUND_EVENTS.put(85, SoundEvent.PORTAL);
    Constants.SOUND_EVENTS.put(86, SoundEvent.WATER);
    Constants.SOUND_EVENTS.put(87, SoundEvent.LAVA_POP);
    Constants.SOUND_EVENTS.put(88, SoundEvent.LAVA);
    Constants.SOUND_EVENTS.put(89, SoundEvent.BURP);
    Constants.SOUND_EVENTS.put(90, SoundEvent.BUCKET_FILL_WATER);
    Constants.SOUND_EVENTS.put(91, SoundEvent.BUCKET_FILL_LAVA);
    Constants.SOUND_EVENTS.put(92, SoundEvent.BUCKET_EMPTY_WATER);
    Constants.SOUND_EVENTS.put(93, SoundEvent.BUCKET_EMPTY_LAVA);
    Constants.SOUND_EVENTS.put(94, SoundEvent.ARMOR_EQUIP_CHAIN);
    Constants.SOUND_EVENTS.put(95, SoundEvent.ARMOR_EQUIP_DIAMOND);
    Constants.SOUND_EVENTS.put(96, SoundEvent.ARMOR_EQUIP_GENERIC);
    Constants.SOUND_EVENTS.put(97, SoundEvent.ARMOR_EQUIP_GOLD);
    Constants.SOUND_EVENTS.put(98, SoundEvent.ARMOR_EQUIP_IRON);
    Constants.SOUND_EVENTS.put(99, SoundEvent.ARMOR_EQUIP_LEATHER);
    Constants.SOUND_EVENTS.put(100, SoundEvent.ARMOR_EQUIP_ELYTRA);
    Constants.SOUND_EVENTS.put(101, SoundEvent.RECORD_13);
    Constants.SOUND_EVENTS.put(102, SoundEvent.RECORD_CAT);
    Constants.SOUND_EVENTS.put(103, SoundEvent.RECORD_BLOCKS);
    Constants.SOUND_EVENTS.put(104, SoundEvent.RECORD_CHIRP);
    Constants.SOUND_EVENTS.put(105, SoundEvent.RECORD_FAR);
    Constants.SOUND_EVENTS.put(106, SoundEvent.RECORD_MALL);
    Constants.SOUND_EVENTS.put(107, SoundEvent.RECORD_MELLOHI);
    Constants.SOUND_EVENTS.put(108, SoundEvent.RECORD_STAL);
    Constants.SOUND_EVENTS.put(109, SoundEvent.RECORD_STRAD);
    Constants.SOUND_EVENTS.put(110, SoundEvent.RECORD_WARD);
    Constants.SOUND_EVENTS.put(111, SoundEvent.RECORD_11);
    Constants.SOUND_EVENTS.put(112, SoundEvent.RECORD_WAIT);
    Constants.SOUND_EVENTS.put(113, SoundEvent.STOP_RECORD);
    Constants.SOUND_EVENTS.put(114, SoundEvent.FLOP);
    Constants.SOUND_EVENTS.put(115, SoundEvent.ELDERGUARDIAN_CURSE);
    Constants.SOUND_EVENTS.put(116, SoundEvent.MOB_WARNING);
    Constants.SOUND_EVENTS.put(117, SoundEvent.MOB_WARNING_BABY);
    Constants.SOUND_EVENTS.put(118, SoundEvent.TELEPORT);
    Constants.SOUND_EVENTS.put(119, SoundEvent.SHULKER_OPEN);
    Constants.SOUND_EVENTS.put(120, SoundEvent.SHULKER_CLOSE);
    Constants.SOUND_EVENTS.put(121, SoundEvent.HAGGLE);
    Constants.SOUND_EVENTS.put(122, SoundEvent.HAGGLE_YES);
    Constants.SOUND_EVENTS.put(123, SoundEvent.HAGGLE_NO);
    Constants.SOUND_EVENTS.put(124, SoundEvent.HAGGLE_IDLE);
    Constants.SOUND_EVENTS.put(125, SoundEvent.CHORUS_GROW);
    Constants.SOUND_EVENTS.put(126, SoundEvent.CHORUS_DEATH);
    Constants.SOUND_EVENTS.put(127, SoundEvent.GLASS);
    Constants.SOUND_EVENTS.put(128, SoundEvent.POTION_BREWED);
    Constants.SOUND_EVENTS.put(129, SoundEvent.CAST_SPELL);
    Constants.SOUND_EVENTS.put(130, SoundEvent.PREPARE_ATTACK);
    Constants.SOUND_EVENTS.put(131, SoundEvent.PREPARE_SUMMON);
    Constants.SOUND_EVENTS.put(132, SoundEvent.PREPARE_WOLOLO);
    Constants.SOUND_EVENTS.put(133, SoundEvent.FANG);
    Constants.SOUND_EVENTS.put(134, SoundEvent.CHARGE);
    Constants.SOUND_EVENTS.put(135, SoundEvent.CAMERA_TAKE_PICTURE);
    Constants.SOUND_EVENTS.put(136, SoundEvent.LEASHKNOT_PLACE);
    Constants.SOUND_EVENTS.put(137, SoundEvent.LEASHKNOT_BREAK);
    Constants.SOUND_EVENTS.put(138, SoundEvent.GROWL);
    Constants.SOUND_EVENTS.put(139, SoundEvent.WHINE);
    Constants.SOUND_EVENTS.put(140, SoundEvent.PANT);
    Constants.SOUND_EVENTS.put(141, SoundEvent.PURR);
    Constants.SOUND_EVENTS.put(142, SoundEvent.PURREOW);
    Constants.SOUND_EVENTS.put(143, SoundEvent.DEATH_MIN_VOLUME);
    Constants.SOUND_EVENTS.put(144, SoundEvent.DEATH_MID_VOLUME);
    Constants.SOUND_EVENTS.put(145, SoundEvent.IMITATE_BLAZE);
    Constants.SOUND_EVENTS.put(146, SoundEvent.IMITATE_CAVE_SPIDER);
    Constants.SOUND_EVENTS.put(147, SoundEvent.IMITATE_CREEPER);
    Constants.SOUND_EVENTS.put(148, SoundEvent.IMITATE_ELDER_GUARDIAN);
    Constants.SOUND_EVENTS.put(149, SoundEvent.IMITATE_ENDER_DRAGON);
    Constants.SOUND_EVENTS.put(150, SoundEvent.IMITATE_ENDERMAN);
    Constants.SOUND_EVENTS.put(152, SoundEvent.IMITATE_EVOCATION_ILLAGER);
    Constants.SOUND_EVENTS.put(153, SoundEvent.IMITATE_GHAST);
    Constants.SOUND_EVENTS.put(154, SoundEvent.IMITATE_HUSK);
    Constants.SOUND_EVENTS.put(155, SoundEvent.IMITATE_ILLUSION_ILLAGER);
    Constants.SOUND_EVENTS.put(156, SoundEvent.IMITATE_MAGMA_CUBE);
    Constants.SOUND_EVENTS.put(157, SoundEvent.IMITATE_POLAR_BEAR);
    Constants.SOUND_EVENTS.put(158, SoundEvent.IMITATE_SHULKER);
    Constants.SOUND_EVENTS.put(159, SoundEvent.IMITATE_SILVERFISH);
    Constants.SOUND_EVENTS.put(160, SoundEvent.IMITATE_SKELETON);
    Constants.SOUND_EVENTS.put(161, SoundEvent.IMITATE_SLIME);
    Constants.SOUND_EVENTS.put(162, SoundEvent.IMITATE_SPIDER);
    Constants.SOUND_EVENTS.put(163, SoundEvent.IMITATE_STRAY);
    Constants.SOUND_EVENTS.put(164, SoundEvent.IMITATE_VEX);
    Constants.SOUND_EVENTS.put(165, SoundEvent.IMITATE_VINDICATION_ILLAGER);
    Constants.SOUND_EVENTS.put(166, SoundEvent.IMITATE_WITCH);
    Constants.SOUND_EVENTS.put(167, SoundEvent.IMITATE_WITHER);
    Constants.SOUND_EVENTS.put(168, SoundEvent.IMITATE_WITHER_SKELETON);
    Constants.SOUND_EVENTS.put(169, SoundEvent.IMITATE_WOLF);
    Constants.SOUND_EVENTS.put(170, SoundEvent.IMITATE_ZOMBIE);
    Constants.SOUND_EVENTS.put(171, SoundEvent.IMITATE_ZOMBIE_PIGMAN);
    Constants.SOUND_EVENTS.put(172, SoundEvent.IMITATE_ZOMBIE_VILLAGER);
    Constants.SOUND_EVENTS.put(173, SoundEvent.BLOCK_END_PORTAL_FRAME_FILL);
    Constants.SOUND_EVENTS.put(174, SoundEvent.BLOCK_END_PORTAL_SPAWN);
    Constants.SOUND_EVENTS.put(175, SoundEvent.RANDOM_ANVIL_USE);
    Constants.SOUND_EVENTS.put(176, SoundEvent.BOTTLE_DRAGONBREATH);
    Constants.SOUND_EVENTS.put(177, SoundEvent.PORTAL_TRAVEL);
    Constants.SOUND_EVENTS.put(178, SoundEvent.ITEM_TRIDENT_HIT);
    Constants.SOUND_EVENTS.put(179, SoundEvent.ITEM_TRIDENT_RETURN);
    Constants.SOUND_EVENTS.put(180, SoundEvent.ITEM_TRIDENT_RIPTIDE_1);
    Constants.SOUND_EVENTS.put(181, SoundEvent.ITEM_TRIDENT_RIPTIDE_2);
    Constants.SOUND_EVENTS.put(182, SoundEvent.ITEM_TRIDENT_RIPTIDE_3);
    Constants.SOUND_EVENTS.put(183, SoundEvent.ITEM_TRIDENT_THROW);
    Constants.SOUND_EVENTS.put(184, SoundEvent.ITEM_TRIDENT_THUNDER);
    Constants.SOUND_EVENTS.put(185, SoundEvent.ITEM_TRIDENT_HIT_GROUND);
    Constants.SOUND_EVENTS.put(186, SoundEvent.DEFAULT);
    Constants.SOUND_EVENTS.put(188, SoundEvent.ELEMENT_CONSTRUCTOR_OPEN);
    Constants.SOUND_EVENTS.put(189, SoundEvent.ICE_BOMB_HIT);
    Constants.SOUND_EVENTS.put(190, SoundEvent.BALLOON_POP);
    Constants.SOUND_EVENTS.put(191, SoundEvent.LT_REACTION_ICE_BOMB);
    Constants.SOUND_EVENTS.put(192, SoundEvent.LT_REACTION_BLEACH);
    Constants.SOUND_EVENTS.put(193, SoundEvent.LT_REACTION_E_PASTE);
    Constants.SOUND_EVENTS.put(194, SoundEvent.LT_REACTION_E_PASTE2);
    Constants.SOUND_EVENTS.put(199, SoundEvent.LT_REACTION_FERTILIZER);
    Constants.SOUND_EVENTS.put(200, SoundEvent.LT_REACTION_FIREBALL);
    Constants.SOUND_EVENTS.put(201, SoundEvent.LT_REACTION_MG_SALT);
    Constants.SOUND_EVENTS.put(202, SoundEvent.LT_REACTION_MISC_FIRE);
    Constants.SOUND_EVENTS.put(203, SoundEvent.LT_REACTION_FIRE);
    Constants.SOUND_EVENTS.put(204, SoundEvent.LT_REACTION_MISC_EXPLOSION);
    Constants.SOUND_EVENTS.put(205, SoundEvent.LT_REACTION_MISC_MYSTICAL);
    Constants.SOUND_EVENTS.put(206, SoundEvent.LT_REACTION_MISC_MYSTICAL2);
    Constants.SOUND_EVENTS.put(207, SoundEvent.LT_REACTION_PRODUCT);
    Constants.SOUND_EVENTS.put(208, SoundEvent.SPARKLER_USE);
    Constants.SOUND_EVENTS.put(209, SoundEvent.GLOWSTICK_USE);
    Constants.SOUND_EVENTS.put(210, SoundEvent.SPARKLER_ACTIVE);
    Constants.SOUND_EVENTS.put(211, SoundEvent.CONVERT_TO_DROWNED);
    Constants.SOUND_EVENTS.put(212, SoundEvent.BUCKET_FILL_FISH);
    Constants.SOUND_EVENTS.put(213, SoundEvent.BUCKET_EMPTY_FISH);
    Constants.SOUND_EVENTS.put(214, SoundEvent.BUBBLE_UP);
    Constants.SOUND_EVENTS.put(215, SoundEvent.BUBBLE_DOWN);
    Constants.SOUND_EVENTS.put(216, SoundEvent.BUBBLE_POP);
    Constants.SOUND_EVENTS.put(217, SoundEvent.BUBBLE_UP_INSIDE);
    Constants.SOUND_EVENTS.put(218, SoundEvent.BUBBLE_DOWN_INSIDE);
    Constants.SOUND_EVENTS.put(219, SoundEvent.BABY_HURT);
    Constants.SOUND_EVENTS.put(220, SoundEvent.BABY_DEATH);
    Constants.SOUND_EVENTS.put(221, SoundEvent.BABY_STEP);
    Constants.SOUND_EVENTS.put(222, SoundEvent.BABY_SPAWN);
    Constants.SOUND_EVENTS.put(223, SoundEvent.BORN);
    Constants.SOUND_EVENTS.put(224, SoundEvent.BLOCK_TURTLE_EGG_BREAK);
    Constants.SOUND_EVENTS.put(225, SoundEvent.BLOCK_TURTLE_EGG_CRACK);
    Constants.SOUND_EVENTS.put(226, SoundEvent.BLOCK_TURTLE_EGG_HATCH);
    Constants.SOUND_EVENTS.put(227, SoundEvent.TURTLE_LAY_EGG);
    Constants.SOUND_EVENTS.put(228, SoundEvent.BLOCK_TURTLE_EGG_ATTACK);
    Constants.SOUND_EVENTS.put(229, SoundEvent.BEACON_ACTIVATE);
    Constants.SOUND_EVENTS.put(230, SoundEvent.BEACON_AMBIENT);
    Constants.SOUND_EVENTS.put(231, SoundEvent.BEACON_DEACTIVATE);
    Constants.SOUND_EVENTS.put(232, SoundEvent.BEACON_POWER);
    Constants.SOUND_EVENTS.put(233, SoundEvent.CONDUIT_ACTIVATE);
    Constants.SOUND_EVENTS.put(234, SoundEvent.CONDUIT_AMBIENT);
    Constants.SOUND_EVENTS.put(235, SoundEvent.CONDUIT_ATTACK);
    Constants.SOUND_EVENTS.put(236, SoundEvent.CONDUIT_DEACTIVATE);
    Constants.SOUND_EVENTS.put(237, SoundEvent.CONDUIT_SHORT);
    Constants.SOUND_EVENTS.put(238, SoundEvent.SWOOP);
    Constants.SOUND_EVENTS.put(239, SoundEvent.BLOCK_BAMBOO_SAPLING_PLACE);
    Constants.SOUND_EVENTS.put(240, SoundEvent.PRE_SNEEZE);
    Constants.SOUND_EVENTS.put(241, SoundEvent.SNEEZE);
    Constants.SOUND_EVENTS.put(242, SoundEvent.AMBIENT_TAME);
    Constants.SOUND_EVENTS.put(243, SoundEvent.SCARED);
    Constants.SOUND_EVENTS.put(244, SoundEvent.BLOCK_SCAFFOLDING_CLIMB);
    Constants.SOUND_EVENTS.put(245, SoundEvent.CROSSBOW_LOADING_START);
    Constants.SOUND_EVENTS.put(246, SoundEvent.CROSSBOW_LOADING_MIDDLE);
    Constants.SOUND_EVENTS.put(247, SoundEvent.CROSSBOW_LOADING_END);
    Constants.SOUND_EVENTS.put(248, SoundEvent.CROSSBOW_SHOOT);
    Constants.SOUND_EVENTS.put(249, SoundEvent.CROSSBOW_QUICK_CHARGE_START);
    Constants.SOUND_EVENTS.put(250, SoundEvent.CROSSBOW_QUICK_CHARGE_MIDDLE);
    Constants.SOUND_EVENTS.put(251, SoundEvent.CROSSBOW_QUICK_CHARGE_END);
    Constants.SOUND_EVENTS.put(252, SoundEvent.AMBIENT_AGGRESSIVE);
    Constants.SOUND_EVENTS.put(253, SoundEvent.AMBIENT_WORRIED);
    Constants.SOUND_EVENTS.put(254, SoundEvent.CANT_BREED);
    Constants.SOUND_EVENTS.put(255, SoundEvent.SHIELD_BLOCK);
    Constants.SOUND_EVENTS.put(256, SoundEvent.LECTERN_BOOK_PLACE);
    Constants.SOUND_EVENTS.put(187, SoundEvent.FLETCHING_TABLE_USE);
    Constants.SOUND_EVENTS.put(257, SoundEvent.GRINDSTONE_USE);
    Constants.SOUND_EVENTS.put(258, SoundEvent.BELL);
    Constants.SOUND_EVENTS.put(259, SoundEvent.CAMPFIRE_CRACKLE);
    Constants.SOUND_EVENTS.put(262, SoundEvent.SWEET_BERRY_BUSH_HURT);
    Constants.SOUND_EVENTS.put(263, SoundEvent.SWEET_BERRY_BUSH_PICK);
    Constants.SOUND_EVENTS.put(260, SoundEvent.ROAR);
    Constants.SOUND_EVENTS.put(261, SoundEvent.STUN);
    Constants.SOUND_EVENTS.put(264, SoundEvent.CARTOGRAPHY_TABLE_USE);
    Constants.SOUND_EVENTS.put(265, SoundEvent.STONECUTTER_USE);
    Constants.SOUND_EVENTS.put(266, SoundEvent.COMPOSTER_EMPTY);
    Constants.SOUND_EVENTS.put(267, SoundEvent.COMPOSTER_FILL);
    Constants.SOUND_EVENTS.put(268, SoundEvent.COMPOSTER_FILL_LAYER);
    Constants.SOUND_EVENTS.put(269, SoundEvent.COMPOSTER_READY);
    Constants.SOUND_EVENTS.put(270, SoundEvent.BARREL_OPEN);
    Constants.SOUND_EVENTS.put(271, SoundEvent.BARREL_CLOSE);
    Constants.SOUND_EVENTS.put(272, SoundEvent.RAID_HORN);
    Constants.SOUND_EVENTS.put(273, SoundEvent.LOOM_USE);
    Constants.SOUND_EVENTS.put(274, SoundEvent.AMBIENT_IN_RAID);
    Constants.SOUND_EVENTS.put(275, SoundEvent.UI_CARTOGRAPHY_TABLE_USE);
    Constants.SOUND_EVENTS.put(276, SoundEvent.UI_STONECUTTER_USE);
    Constants.SOUND_EVENTS.put(277, SoundEvent.UI_LOOM_USE);
    Constants.SOUND_EVENTS.put(278, SoundEvent.SMOKER_USE);
    Constants.SOUND_EVENTS.put(279, SoundEvent.BLAST_FURNACE_USE);
    Constants.SOUND_EVENTS.put(280, SoundEvent.SMITHING_TABLE_USE);
    Constants.SOUND_EVENTS.put(281, SoundEvent.SCREECH);
    Constants.SOUND_EVENTS.put(282, SoundEvent.SLEEP);
    Constants.SOUND_EVENTS.put(283, SoundEvent.FURNACE_USE);
    Constants.SOUND_EVENTS.put(284, SoundEvent.MOOSHROOM_CONVERT);
    Constants.SOUND_EVENTS.put(285, SoundEvent.MILK_SUSPICIOUSLY);
    Constants.SOUND_EVENTS.put(286, SoundEvent.CELEBRATE);
    Constants.SOUND_EVENTS.put(287, SoundEvent.JUMP_PREVENT);
    Constants.SOUND_EVENTS.put(288, SoundEvent.AMBIENT_POLLINATE);
    Constants.SOUND_EVENTS.put(289, SoundEvent.BEEHIVE_DRIP);
    Constants.SOUND_EVENTS.put(290, SoundEvent.BEEHIVE_ENTER);
    Constants.SOUND_EVENTS.put(291, SoundEvent.BEEHIVE_EXIT);
    Constants.SOUND_EVENTS.put(292, SoundEvent.BEEHIVE_WORK);
    Constants.SOUND_EVENTS.put(293, SoundEvent.BEEHIVE_SHEAR);
    Constants.SOUND_EVENTS.put(294, SoundEvent.HONEYBOTTLE_DRINK);
    Constants.SOUND_EVENTS.put(295, SoundEvent.AMBIENT_CAVE);
    Constants.SOUND_EVENTS.put(296, SoundEvent.RETREAT);
    Constants.SOUND_EVENTS.put(297, SoundEvent.CONVERT_TO_ZOMBIFIED);
    Constants.SOUND_EVENTS.put(298, SoundEvent.ADMIRE);
    Constants.SOUND_EVENTS.put(299, SoundEvent.STEP_LAVA);
    Constants.SOUND_EVENTS.put(300, SoundEvent.TEMPT);
    Constants.SOUND_EVENTS.put(301, SoundEvent.PANIC);
    Constants.SOUND_EVENTS.put(302, SoundEvent.ANGRY);
    Constants.SOUND_EVENTS.put(303, SoundEvent.AMBIENT_WARPED_FOREST);
    Constants.SOUND_EVENTS.put(304, SoundEvent.AMBIENT_SOULSAND_VALLEY);
    Constants.SOUND_EVENTS.put(305, SoundEvent.AMBIENT_NETHER_WASTES);
    Constants.SOUND_EVENTS.put(306, SoundEvent.AMBIENT_BASALT_DELTAS);
    Constants.SOUND_EVENTS.put(307, SoundEvent.AMBIENT_CRIMSON_FOREST);
    Constants.SOUND_EVENTS.put(308, SoundEvent.RESPAWN_ANCHOR_CHARGE);
    Constants.SOUND_EVENTS.put(309, SoundEvent.RESPAWN_ANCHOR_DEPLETE);
    Constants.SOUND_EVENTS.put(310, SoundEvent.RESPAWN_ANCHOR_SET_SPAWN);
    Constants.SOUND_EVENTS.put(311, SoundEvent.RESPAWN_ANCHOR_AMBIENT);
    Constants.SOUND_EVENTS.put(312, SoundEvent.SOUL_ESCAPE_QUIET);
    Constants.SOUND_EVENTS.put(313, SoundEvent.SOUL_ESCAPE_LOUD);
    Constants.SOUND_EVENTS.put(314, SoundEvent.RECORD_PIGSTEP);
    Constants.SOUND_EVENTS.put(315, SoundEvent.LINK_COMPASS_TO_LODESTONE);
    Constants.SOUND_EVENTS.put(316, SoundEvent.USE_SMITHING_TABLE);
    Constants.SOUND_EVENTS.put(317, SoundEvent.EQUIP_NETHERITE);
    Constants.SOUND_EVENTS.put(318, SoundEvent.AMBIENT_LOOP_WARPED_FOREST);
    Constants.SOUND_EVENTS.put(319, SoundEvent.AMBIENT_LOOP_SOULSAND_VALLEY);
    Constants.SOUND_EVENTS.put(320, SoundEvent.AMBIENT_LOOP_NETHER_WASTES);
    Constants.SOUND_EVENTS.put(321, SoundEvent.AMBIENT_LOOP_BASALT_DELTAS);
    Constants.SOUND_EVENTS.put(322, SoundEvent.AMBIENT_LOOP_CRIMSON_FOREST);
    Constants.SOUND_EVENTS.put(323, SoundEvent.AMBIENT_ADDITION_WARPED_FOREST);
    Constants.SOUND_EVENTS.put(324, SoundEvent.AMBIENT_ADDITION_SOULSAND_VALLEY);
    Constants.SOUND_EVENTS.put(325, SoundEvent.AMBIENT_ADDITION_NETHER_WASTES);
    Constants.SOUND_EVENTS.put(326, SoundEvent.AMBIENT_ADDITION_BASALT_DELTAS);
    Constants.SOUND_EVENTS.put(327, SoundEvent.AMBIENT_ADDITION_CRIMSON_FOREST);
    Constants.SOUND_EVENTS.put(328, SoundEvent.SCULK_SENSOR_POWER_ON);
    Constants.SOUND_EVENTS.put(329, SoundEvent.SCULK_SENSOR_POWER_OFF);
    Constants.SOUND_EVENTS.put(330, SoundEvent.BUCKET_FILL_POWDER_SNOW);
    Constants.SOUND_EVENTS.put(331, SoundEvent.BUCKET_EMPTY_POWDER_SNOW);
    Constants.SOUND_EVENTS.put(332, SoundEvent.POINTED_DRIPSTONE_CAULDRON_DRIP_LAVA);
    Constants.SOUND_EVENTS.put(333, SoundEvent.POINTED_DRIPSTONE_CAULDRON_DRIP_WATER);
    Constants.SOUND_EVENTS.put(334, SoundEvent.POINTED_DRIPSTONE_DRIP_LAVA);
    Constants.SOUND_EVENTS.put(335, SoundEvent.POINTED_DRIPSTONE_DRIP_WATER);
    Constants.SOUND_EVENTS.put(336, SoundEvent.CAVE_VINES_PICK_BERRIES);
    Constants.SOUND_EVENTS.put(337, SoundEvent.BIG_DRIPLEAF_TILT_DOWN);
    Constants.SOUND_EVENTS.put(338, SoundEvent.BIG_DRIPLEAF_TILT_UP);
    Constants.SOUND_EVENTS.put(339, SoundEvent.COPPER_WAX_ON);
    Constants.SOUND_EVENTS.put(340, SoundEvent.COPPER_WAX_OFF);
    Constants.SOUND_EVENTS.put(341, SoundEvent.SCRAPE);
    Constants.SOUND_EVENTS.put(342, SoundEvent.PLAYER_HURT_DROWN);
    Constants.SOUND_EVENTS.put(343, SoundEvent.PLAYER_HURT_ON_FIRE);
    Constants.SOUND_EVENTS.put(344, SoundEvent.PLAYER_HURT_FREEZE);
    Constants.SOUND_EVENTS.put(345, SoundEvent.USE_SPYGLASS);
    Constants.SOUND_EVENTS.put(346, SoundEvent.STOP_USING_SPYGLASS);
    Constants.SOUND_EVENTS.put(347, SoundEvent.AMETHYST_BLOCK_CHIME);
    Constants.SOUND_EVENTS.put(348, SoundEvent.AMBIENT_SCREAMER);
    Constants.SOUND_EVENTS.put(349, SoundEvent.HURT_SCREAMER);
    Constants.SOUND_EVENTS.put(350, SoundEvent.DEATH_SCREAMER);
    Constants.SOUND_EVENTS.put(351, SoundEvent.MILK_SCREAMER);
    Constants.SOUND_EVENTS.put(352, SoundEvent.JUMP_TO_BLOCK);
    Constants.SOUND_EVENTS.put(353, SoundEvent.PRE_RAM);
    Constants.SOUND_EVENTS.put(354, SoundEvent.PRE_RAM_SCREAMER);
    Constants.SOUND_EVENTS.put(355, SoundEvent.RAM_IMPACT);
    Constants.SOUND_EVENTS.put(356, SoundEvent.RAM_IMPACT_SCREAMER);
    Constants.SOUND_EVENTS.put(357, SoundEvent.SQUID_INK_SQUIRT);
    Constants.SOUND_EVENTS.put(358, SoundEvent.GLOW_SQUID_INK_SQUIRT);
    Constants.SOUND_EVENTS.put(359, SoundEvent.CONVERT_TO_STRAY);
    Constants.SOUND_EVENTS.put(360, SoundEvent.CAKE_ADD_CANDLE);
    Constants.SOUND_EVENTS.put(361, SoundEvent.EXTINGUISH_CANDLE);
    Constants.SOUND_EVENTS.put(362, SoundEvent.AMBIENT_CANDLE);
    Constants.SOUND_EVENTS.put(363, SoundEvent.BLOCK_CLICK);
    Constants.SOUND_EVENTS.put(364, SoundEvent.BLOCK_CLICK_FAIL);
    Constants.SOUND_EVENTS.put(366, SoundEvent.SCULK_SHRIEKER_SHRIEK);
    Constants.SOUND_EVENTS.put(367, SoundEvent.WARDEN_NEARBY_CLOSE);
    Constants.SOUND_EVENTS.put(368, SoundEvent.WARDEN_NEARBY_CLOSER);
    Constants.SOUND_EVENTS.put(369, SoundEvent.WARDEN_NEARBY_CLOSEST);
    Constants.SOUND_EVENTS.put(370, SoundEvent.WARDEN_SLIGHTLY_ANGRY);
    Constants.SOUND_EVENTS.put(365, SoundEvent.SCULK_CATALYST_BLOOM);
    Constants.SOUND_EVENTS.put(371, SoundEvent.RECORD_OTHERSIDE);
    Constants.SOUND_EVENTS.put(372, SoundEvent.UNDEFINED);
  }
}
