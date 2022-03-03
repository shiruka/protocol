package io.github.shiruka.protocol.codec.v291;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.api.nbt.CompoundTag;
import io.github.shiruka.api.nbt.Tag;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.IdentifierDefinitionRegistry;
import io.github.shiruka.protocol.codec.IntTypeMap;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.data.AdventureSetting;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.CommonLevelEvent;
import io.github.shiruka.protocol.data.EntityEventType;
import io.github.shiruka.protocol.data.GamePublishSetting;
import io.github.shiruka.protocol.data.GameRuleValue;
import io.github.shiruka.protocol.data.GameType;
import io.github.shiruka.protocol.data.ItemDefinition;
import io.github.shiruka.protocol.data.LevelEventType;
import io.github.shiruka.protocol.data.ParticleType;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.SoundEvent;
import io.github.shiruka.protocol.data.command.CommandData;
import io.github.shiruka.protocol.data.command.CommandEnumData;
import io.github.shiruka.protocol.data.command.CommandParam;
import io.github.shiruka.protocol.data.command.CommandParamData;
import io.github.shiruka.protocol.data.command.CommandParamOption;
import io.github.shiruka.protocol.data.command.CommandPermission;
import io.github.shiruka.protocol.data.command.CommandSymbolData;
import io.github.shiruka.protocol.data.entity.EntityData;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityDataType;
import io.github.shiruka.protocol.data.entity.EntityFlag;
import io.github.shiruka.protocol.data.entity.EntityFlags;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import io.github.shiruka.protocol.data.entity.EntityLinkDataType;
import io.github.shiruka.protocol.data.inventory.ItemData;
import io.github.shiruka.protocol.packets.AdventureSettings;
import io.github.shiruka.protocol.packets.BookEdit;
import io.github.shiruka.protocol.packets.ResourcePackInfo;
import io.github.shiruka.protocol.packets.ResourcePackStack;
import io.github.shiruka.protocol.packets.StartGame;
import io.netty.buffer.ByteBufInputStream;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents codec helpers for v291.
 */
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodecHelperV291 implements CodecHelper {

  /**
   * the level event block.
   */
  protected static final int LEVEL_EVENT_BLOCK = 3500;

  /**
   * the level event particle.
   */
  protected static final int LEVEL_EVENT_PARTICLE = 2000;

  /**
   * the leve levent particle type.
   */
  protected static final int LEVEL_EVENT_PARTICLE_TYPE = 0x4000;

  /**
   * the level event sound.
   */
  protected static final int LEVEL_EVENT_SOUND = 1000;

  /**
   * the level event world.
   */
  protected static final int LEVEL_EVENT_WORLD = 3000;

  /**
   * the read byte.
   */
  private static final ToIntFunction<PacketBuffer> READ_BYTE = PacketBuffer::readUnsignedByte;

  /**
   * the read int.
   */
  private static final ToIntFunction<PacketBuffer> READ_INT = PacketBuffer::readIntLE;

  /**
   * the read short.
   */
  private static final ToIntFunction<PacketBuffer> READ_SHORT = PacketBuffer::readUnsignedShortLE;

  /**
   * the reader supplier.
   */
  private static final IntFunction<ToIntFunction<PacketBuffer>> READER_FUNCTION = size -> {
    if (size < 0x100) {
      return CodecHelperV291.READ_BYTE;
    }
    if (size < 0x10000) {
      return CodecHelperV291.READ_SHORT;
    }
    return CodecHelperV291.READ_INT;
  };

  /**
   * the write byte.
   */
  private static final ObjIntConsumer<PacketBuffer> WRITE_BYTE = PacketBuffer::writeByte;

  /**
   * the write int.
   */
  private static final ObjIntConsumer<PacketBuffer> WRITE_INT = PacketBuffer::writeIntLE;

  /**
   * the write short.
   */
  private static final ObjIntConsumer<PacketBuffer> WRITE_SHORT = PacketBuffer::writeShortLE;

  /**
   * the writer supplier.
   */
  private static final IntFunction<ObjIntConsumer<PacketBuffer>> WRITER_FUNCTION = size -> {
    if (size < 0x100) {
      return CodecHelperV291.WRITE_BYTE;
    }
    if (size < 0x10000) {
      return CodecHelperV291.WRITE_SHORT;
    }
    return CodecHelperV291.WRITE_INT;
  };

  /**
   * the adventure setting flags 1.
   */
  @Getter
  protected final IntTypeMap<AdventureSetting> adventureSettingFlags1 = IntTypeMap.newBuilder(AdventureSetting.class)
    .insert(1, AdventureSetting.WORLD_IMMUTABLE)
    .insert(1 << 1, AdventureSetting.NO_PVM)
    .insert(1 << 2, AdventureSetting.NO_MVP)
    .insert(1 << 4, AdventureSetting.SHOW_NAME_TAGS)
    .insert(1 << 5, AdventureSetting.AUTO_JUMP)
    .insert(1 << 6, AdventureSetting.MAY_FLY)
    .insert(1 << 7, AdventureSetting.NO_CLIP)
    .insert(1 << 8, AdventureSetting.WORLD_BUILDER)
    .insert(1 << 9, AdventureSetting.FLYING)
    .insert(1 << 10, AdventureSetting.MUTED)
    .build();

  /**
   * the adventure setting flags 2.
   */
  @Getter
  protected final IntTypeMap<AdventureSetting> adventureSettingFlags2 = IntTypeMap.newBuilder(AdventureSetting.class)
    .insert(1, AdventureSetting.MINE)
    .insert(1 << 1, AdventureSetting.DOORS_AND_SWITCHES)
    .insert(1 << 2, AdventureSetting.OPEN_CONTAINERS)
    .insert(1 << 3, AdventureSetting.ATTACK_PLAYERS)
    .insert(1 << 4, AdventureSetting.ATTACK_MOBS)
    .insert(1 << 5, AdventureSetting.OPERATOR)
    .insert(1 << 7, AdventureSetting.TELEPORT)
    .insert(1 << 8, AdventureSetting.BUILD)
    .insert(1 << 9, AdventureSetting.DEFAULT_LEVEL_PERMISSIONS)
    .build();

  /**
   * the book edit type.
   */
  @Getter
  protected final IntTypeMap<BookEdit.Action> bookEditTypes = IntTypeMap.newBuilder(BookEdit.Action.class)
    .insert(0, BookEdit.Action.REPLACE_PAGE)
    .insert(1, BookEdit.Action.ADD_PAGE)
    .insert(2, BookEdit.Action.DELETE_PAGE)
    .insert(3, BookEdit.Action.SWAP_PAGES)
    .insert(4, BookEdit.Action.SIGN_BOOK)
    .build();

  /**
   * the command parameters.
   */
  @Getter
  protected final IntTypeMap<CommandParam> commandParameters = IntTypeMap.newBuilder(CommandParam.class)
    .insert(1, CommandParam.INT)
    .insert(2, CommandParam.FLOAT)
    .insert(3, CommandParam.VALUE)
    .insert(4, CommandParam.WILDCARD_INT)
    .insert(5, CommandParam.OPERATOR)
    .insert(6, CommandParam.TARGET)
    .insert(7, CommandParam.WILDCARD_TARGET)
    .insert(14, CommandParam.FILE_PATH)
    .insert(18, CommandParam.INT_RANGE)
    .insert(26, CommandParam.STRING)
    .insert(28, CommandParam.POSITION)
    .insert(31, CommandParam.MESSAGE)
    .insert(33, CommandParam.TEXT)
    .insert(36, CommandParam.JSON)
    .insert(43, CommandParam.COMMAND)
    .build();

  /**
   * the entity data.
   */
  @Getter
  protected final IntTypeMap<EntityData> entityData = IntTypeMap.newBuilder(EntityData.class)
    .insert(0, EntityData.FLAGS)
    .insert(1, EntityData.HEALTH)
    .insert(2, EntityData.VARIANT)
    .insert(3, EntityData.COLOR)
    .insert(4, EntityData.NAMETAG)
    .insert(5, EntityData.OWNER_EID)
    .insert(6, EntityData.TARGET_EID)
    .insert(7, EntityData.AIR_SUPPLY)
    .insert(8, EntityData.EFFECT_COLOR)
    .insert(9, EntityData.EFFECT_AMBIENT)
    .insert(10, EntityData.JUMP_DURATION)
    .insert(11, EntityData.HURT_TIME)
    .insert(12, EntityData.HURT_DIRECTION)
    .insert(13, EntityData.ROW_TIME_LEFT)
    .insert(14, EntityData.ROW_TIME_RIGHT)
    .insert(15, EntityData.EXPERIENCE_VALUE)
    .insert(16, EntityData.DISPLAY_ITEM)
    .insert(17, EntityData.DISPLAY_OFFSET)
    .insert(18, EntityData.CUSTOM_DISPLAY)
    .insert(19, EntityData.SWELL)
    .insert(20, EntityData.OLD_SWELL)
    .insert(21, EntityData.SWELL_DIRECTION)
    .insert(22, EntityData.CHARGE_AMOUNT)
    .insert(23, EntityData.CARRIED_BLOCK)
    .insert(24, EntityData.CLIENT_EVENT)
    .insert(25, EntityData.USING_ITEM)
    .insert(26, EntityData.PLAYER_FLAGS)
    .insert(27, EntityData.PLAYER_INDEX)
    .insert(28, EntityData.BED_POSITION)
    .insert(29, EntityData.X_POWER)
    .insert(30, EntityData.Y_POWER)
    .insert(31, EntityData.Z_POWER)
    .insert(32, EntityData.AUX_POWER)
    .insert(33, EntityData.FISH_X)
    .insert(34, EntityData.FISH_Z)
    .insert(35, EntityData.FISH_ANGLE)
    .insert(36, EntityData.POTION_AUX_VALUE)
    .insert(37, EntityData.LEASH_HOLDER_EID)
    .insert(38, EntityData.SCALE)
    .insert(39, EntityData.INTERACTIVE_TAG)
    .insert(40, EntityData.NPC_SKIN_ID)
    .insert(41, EntityData.URL_TAG)
    .insert(42, EntityData.MAX_AIR_SUPPLY)
    .insert(43, EntityData.MARK_VARIANT)
    .insert(44, EntityData.CONTAINER_TYPE)
    .insert(45, EntityData.CONTAINER_BASE_SIZE)
    .insert(46, EntityData.CONTAINER_STRENGTH_MODIFIER)
    .insert(47, EntityData.BLOCK_TARGET)
    .insert(48, EntityData.WITHER_INVULNERABLE_TICKS)
    .insert(49, EntityData.WITHER_TARGET_1)
    .insert(50, EntityData.WITHER_TARGET_2)
    .insert(51, EntityData.WITHER_TARGET_3)
    .insert(52, EntityData.WITHER_AERIAL_ATTACK)
    .insert(53, EntityData.BOUNDING_BOX_WIDTH)
    .insert(54, EntityData.BOUNDING_BOX_HEIGHT)
    .insert(55, EntityData.FUSE_LENGTH)
    .insert(56, EntityData.RIDER_SEAT_POSITION)
    .insert(57, EntityData.RIDER_ROTATION_LOCKED)
    .insert(58, EntityData.RIDER_MAX_ROTATION)
    .insert(59, EntityData.RIDER_MIN_ROTATION)
    .insert(60, EntityData.AREA_EFFECT_CLOUD_RADIUS)
    .insert(61, EntityData.AREA_EFFECT_CLOUD_WAITING)
    .insert(62, EntityData.AREA_EFFECT_CLOUD_PARTICLE_ID)
    .insert(63, EntityData.SHULKER_PEEK_ID)
    .insert(64, EntityData.SHULKER_ATTACH_FACE)
    .insert(66, EntityData.SHULKER_ATTACH_POS)
    .insert(67, EntityData.TRADE_TARGET_EID)
    .insert(69, EntityData.COMMAND_BLOCK_ENABLED) // Unsure
    .insert(70, EntityData.COMMAND_BLOCK_COMMAND)
    .insert(71, EntityData.COMMAND_BLOCK_LAST_OUTPUT)
    .insert(72, EntityData.COMMAND_BLOCK_TRACK_OUTPUT)
    .insert(73, EntityData.CONTROLLING_RIDER_SEAT_INDEX)
    .insert(74, EntityData.STRENGTH)
    .insert(75, EntityData.MAX_STRENGTH)
    .insert(76, EntityData.EVOKER_SPELL_COLOR)
    .insert(77, EntityData.LIMITED_LIFE)
    .insert(78, EntityData.ARMOR_STAND_POSE_INDEX)
    .insert(79, EntityData.ENDER_CRYSTAL_TIME_OFFSET)
    .insert(80, EntityData.NAMETAG_ALWAYS_SHOW)
    .insert(81, EntityData.COLOR_2)
    .insert(83, EntityData.SCORE_TAG)
    .insert(84, EntityData.BALLOON_ATTACHED_ENTITY)
    .insert(85, EntityData.PUFFERFISH_SIZE)
    .insert(86, EntityData.BOAT_BUBBLE_TIME)
    .insert(87, EntityData.AGENT_ID)
    .build();

  /**
   * the entity data type.
   */
  @Getter
  protected final IntTypeMap<EntityDataType> entityDataTypes = IntTypeMap.newBuilder(EntityDataType.class)
    .insert(0, EntityDataType.BYTE)
    .insert(1, EntityDataType.SHORT)
    .insert(2, EntityDataType.INT)
    .insert(3, EntityDataType.FLOAT)
    .insert(4, EntityDataType.STRING)
    .insert(5, EntityDataType.NBT)
    .insert(6, EntityDataType.VECTOR3I)
    .insert(7, EntityDataType.LONG)
    .insert(8, EntityDataType.VECTOR3F)
    .build();

  /**
   * the book edit type.
   */
  @Getter
  protected final IntTypeMap<EntityEventType> entityEventTypes = IntTypeMap.newBuilder(EntityEventType.class)
    .insert(0, EntityEventType.NONE)
    .insert(1, EntityEventType.JUMP)
    .insert(2, EntityEventType.HURT)
    .insert(3, EntityEventType.DEATH)
    .insert(4, EntityEventType.ATTACK_START)
    .insert(5, EntityEventType.ATTACK_STOP)
    .insert(6, EntityEventType.TAME_FAILED)
    .insert(7, EntityEventType.TAME_SUCCEEDED)
    .insert(8, EntityEventType.SHAKE_WETNESS)
    .insert(9, EntityEventType.USE_ITEM)
    .insert(10, EntityEventType.EAT_GRASS)
    .insert(11, EntityEventType.FISH_HOOK_BUBBLE)
    .insert(12, EntityEventType.FISH_HOOK_POSITION)
    .insert(13, EntityEventType.FISH_HOOK_TIME)
    .insert(14, EntityEventType.FISH_HOOK_TEASE)
    .insert(15, EntityEventType.SQUID_FLEEING)
    .insert(16, EntityEventType.ZOMBIE_VILLAGER_CURE)
    .insert(17, EntityEventType.PLAY_AMBIENT)
    .insert(18, EntityEventType.RESPAWN)
    .insert(19, EntityEventType.GOLEM_FLOWER_OFFER)
    .insert(20, EntityEventType.GOLEM_FLOWER_WITHDRAW)
    .insert(21, EntityEventType.LOVE_PARTICLES)
    .insert(22, EntityEventType.VILLAGER_ANGRY)
    .insert(23, EntityEventType.VILLAGER_HAPPY)
    .insert(24, EntityEventType.WITCH_HAT_MAGIC)
    .insert(25, EntityEventType.FIREWORK_EXPLODE)
    .insert(26, EntityEventType.IN_LOVE_HEARTS)
    .insert(27, EntityEventType.SILVERFISH_MERGE_WITH_STONE)
    .insert(28, EntityEventType.GUARDIAN_ATTACK_ANIMATION)
    .insert(29, EntityEventType.WITCH_DRINK_POTION)
    .insert(30, EntityEventType.WITCH_THROW_POTION)
    .insert(31, EntityEventType.PRIME_TNT_MINECART)
    .insert(32, EntityEventType.PRIME_CREEPER)
    .insert(33, EntityEventType.AIR_SUPPLY)
    .insert(34, EntityEventType.PLAYER_ADD_XP_LEVELS)
    .insert(35, EntityEventType.ELDER_GUARDIAN_CURSE)
    .insert(36, EntityEventType.AGENT_ARM_SWING)
    .insert(37, EntityEventType.ENDER_DRAGON_DEATH)
    .insert(38, EntityEventType.DUST_PARTICLES)
    .insert(39, EntityEventType.ARROW_SHAKE)
    .insert(57, EntityEventType.EATING_ITEM)
    .insert(60, EntityEventType.BABY_ANIMAL_FEED)
    .insert(61, EntityEventType.DEATH_SMOKE_CLOUD)
    .insert(62, EntityEventType.COMPLETE_TRADE)
    .insert(63, EntityEventType.REMOVE_LEASH)
    .insert(64, EntityEventType.CARAVAN)
    .insert(65, EntityEventType.CONSUME_TOTEM)
    .insert(66, EntityEventType.CHECK_TREASURE_HUNTER_ACHIEVEMENT)
    .insert(67, EntityEventType.ENTITY_SPAWN)
    .insert(68, EntityEventType.DRAGON_FLAMING)
    .insert(69, EntityEventType.UPDATE_ITEM_STACK_SIZE)
    .insert(70, EntityEventType.START_SWIMMING)
    .insert(71, EntityEventType.BALLOON_POP)
    .insert(72, EntityEventType.TREASURE_HUNT)
    .build();

  /**
   * the entity flags.
   */
  @Getter
  protected final IntTypeMap<EntityFlag> entityFlags = IntTypeMap.newBuilder(EntityFlag.class)
    .insert(0, EntityFlag.ON_FIRE)
    .insert(1, EntityFlag.SNEAKING)
    .insert(2, EntityFlag.RIDING)
    .insert(3, EntityFlag.SPRINTING)
    .insert(4, EntityFlag.USING_ITEM)
    .insert(5, EntityFlag.INVISIBLE)
    .insert(6, EntityFlag.TEMPTED)
    .insert(7, EntityFlag.IN_LOVE)
    .insert(8, EntityFlag.SADDLED)
    .insert(9, EntityFlag.POWERED)
    .insert(10, EntityFlag.IGNITED)
    .insert(11, EntityFlag.BABY)
    .insert(12, EntityFlag.CONVERTING)
    .insert(13, EntityFlag.CRITICAL)
    .insert(14, EntityFlag.CAN_SHOW_NAME)
    .insert(15, EntityFlag.ALWAYS_SHOW_NAME)
    .insert(16, EntityFlag.NO_AI)
    .insert(17, EntityFlag.SILENT)
    .insert(18, EntityFlag.WALL_CLIMBING)
    .insert(19, EntityFlag.CAN_CLIMB)
    .insert(20, EntityFlag.CAN_SWIM)
    .insert(21, EntityFlag.CAN_FLY)
    .insert(22, EntityFlag.CAN_WALK)
    .insert(23, EntityFlag.RESTING)
    .insert(24, EntityFlag.SITTING)
    .insert(25, EntityFlag.ANGRY)
    .insert(26, EntityFlag.INTERESTED)
    .insert(27, EntityFlag.CHARGED)
    .insert(28, EntityFlag.TAMED)
    .insert(29, EntityFlag.ORPHANED)
    .insert(30, EntityFlag.LEASHED)
    .insert(31, EntityFlag.SHEARED)
    .insert(32, EntityFlag.GLIDING)
    .insert(33, EntityFlag.ELDER)
    .insert(34, EntityFlag.MOVING)
    .insert(35, EntityFlag.BREATHING)
    .insert(36, EntityFlag.CHESTED)
    .insert(37, EntityFlag.STACKABLE)
    .insert(38, EntityFlag.SHOW_BOTTOM)
    .insert(39, EntityFlag.STANDING)
    .insert(40, EntityFlag.SHAKING)
    .insert(41, EntityFlag.IDLING)
    .insert(42, EntityFlag.CASTING)
    .insert(43, EntityFlag.CHARGING)
    .insert(44, EntityFlag.WASD_CONTROLLED)
    .insert(45, EntityFlag.CAN_POWER_JUMP)
    .insert(46, EntityFlag.LINGERING)
    .insert(47, EntityFlag.HAS_COLLISION)
    .insert(48, EntityFlag.HAS_GRAVITY)
    .insert(49, EntityFlag.FIRE_IMMUNE)
    .insert(50, EntityFlag.DANCING)
    .insert(51, EntityFlag.ENCHANTED)
    .insert(52, EntityFlag.RETURN_TRIDENT)
    .insert(53, EntityFlag.CONTAINER_IS_PRIVATE)
    .insert(54, EntityFlag.IS_TRANSFORMING)
    .insert(55, EntityFlag.DAMAGE_NEARBY_MOBS)
    .insert(56, EntityFlag.SWIMMING)
    .insert(57, EntityFlag.BRIBED)
    .insert(58, EntityFlag.IS_PREGNANT)
    .insert(59, EntityFlag.LAYING_EGG)
    .insert(60, EntityFlag.RIDER_CAN_PICK)
    .build();

  /**
   * the game rule types.
   */
  @Getter
  protected final IntTypeMap<Class<?>> gameRuleTypes = IntTypeMap.<Class<?>>newBuilder("GameRuleTypes")
    .insert(1, Boolean.class)
    .insert(2, Integer.class)
    .insert(3, Float.class)
    .build();

  /**
   * the item definitions.
   */
  @Getter
  protected final IdentifierDefinitionRegistry<ItemDefinition> itemDefinitions =
    IdentifierDefinitionRegistry.<ItemDefinition>newBuilder()
      .build();

  /**
   * the level events.
   */
  @Getter
  protected final IntTypeMap<CommonLevelEvent> levelEvents = IntTypeMap.newBuilder(CommonLevelEvent.class)
    .insert(0, LevelEventType.UNDEFINED)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND, LevelEventType.SOUND_CLICK)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 1, LevelEventType.SOUND_CLICK_FAIL)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 2, LevelEventType.SOUND_LAUNCH)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 3, LevelEventType.SOUND_DOOR_OPEN)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 4, LevelEventType.SOUND_FIZZ)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 5, LevelEventType.SOUND_FUSE)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 6, LevelEventType.SOUND_PLAY_RECORDING)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 7, LevelEventType.SOUND_GHAST_WARNING)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 8, LevelEventType.SOUND_GHAST_FIREBALL)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 9, LevelEventType.SOUND_BLAZE_FIREBALL)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 10, LevelEventType.SOUND_ZOMBIE_DOOR_BUMP)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 12, LevelEventType.SOUND_ZOMBIE_DOOR_CRASH)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 16, LevelEventType.SOUND_ZOMBIE_INFECTED)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 17, LevelEventType.SOUND_ZOMBIE_CONVERTED)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 18, LevelEventType.SOUND_ENDERMAN_TELEPORT)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 20, LevelEventType.SOUND_ANVIL_BROKEN)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 21, LevelEventType.SOUND_ANVIL_USED)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 22, LevelEventType.SOUND_ANVIL_LAND)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 30, LevelEventType.SOUND_INFINITY_ARROW_PICKUP)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 32, LevelEventType.SOUND_TELEPORT_ENDERPEARL)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 40, LevelEventType.SOUND_ITEMFRAME_ITEM_ADD)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 41, LevelEventType.SOUND_ITEMFRAME_BREAK)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 42, LevelEventType.SOUND_ITEMFRAME_PLACE)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 43, LevelEventType.SOUND_ITEMFRAME_ITEM_REMOVE)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 44, LevelEventType.SOUND_ITEMFRAME_ITEM_ROTATE)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 51, LevelEventType.SOUND_EXPERIENCE_ORB_PICKUP)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 52, LevelEventType.SOUND_TOTEM_USED)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 60, LevelEventType.SOUND_ARMOR_STAND_BREAK)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 61, LevelEventType.SOUND_ARMOR_STAND_HIT)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 62, LevelEventType.SOUND_ARMOR_STAND_LAND)
    .insert(CodecHelperV291.LEVEL_EVENT_SOUND + 63, LevelEventType.SOUND_ARMOR_STAND_PLACE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE, LevelEventType.PARTICLE_SHOOT)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 1, LevelEventType.PARTICLE_DESTROY_BLOCK)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 2, LevelEventType.PARTICLE_POTION_SPLASH)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 3, LevelEventType.PARTICLE_EYE_OF_ENDER_DEATH)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 4, LevelEventType.PARTICLE_MOB_BLOCK_SPAWN)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 5, LevelEventType.PARTICLE_CROP_GROWTH)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 6, LevelEventType.PARTICLE_SOUND_GUARDIAN_GHOST)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 7, LevelEventType.PARTICLE_DEATH_SMOKE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 8, LevelEventType.PARTICLE_DENY_BLOCK)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 9, LevelEventType.PARTICLE_GENERIC_SPAWN)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 10, LevelEventType.PARTICLE_DRAGON_EGG)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 11, LevelEventType.PARTICLE_CROP_EATEN)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 12, LevelEventType.PARTICLE_CRIT)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 13, LevelEventType.PARTICLE_TELEPORT)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 14, LevelEventType.PARTICLE_CRACK_BLOCK)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 15, LevelEventType.PARTICLE_BUBBLES)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 16, LevelEventType.PARTICLE_EVAPORATE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 17, LevelEventType.PARTICLE_DESTROY_ARMOR_STAND)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 18, LevelEventType.PARTICLE_BREAKING_EGG)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 19, LevelEventType.PARTICLE_DESTROY_EGG)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 20, LevelEventType.PARTICLE_EVAPORATE_WATER)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE + 21, LevelEventType.PARTICLE_DESTROY_BLOCK_NO_SOUND)
    .insert(CodecHelperV291.LEVEL_EVENT_WORLD + 1, LevelEventType.START_RAINING)
    .insert(CodecHelperV291.LEVEL_EVENT_WORLD + 2, LevelEventType.START_THUNDERSTORM)
    .insert(CodecHelperV291.LEVEL_EVENT_WORLD + 3, LevelEventType.STOP_RAINING)
    .insert(CodecHelperV291.LEVEL_EVENT_WORLD + 4, LevelEventType.STOP_THUNDERSTORM)
    .insert(CodecHelperV291.LEVEL_EVENT_WORLD + 5, LevelEventType.GLOBAL_PAUSE)
    .insert(CodecHelperV291.LEVEL_EVENT_WORLD + 6, LevelEventType.SIM_TIME_STEP)
    .insert(CodecHelperV291.LEVEL_EVENT_WORLD + 7, LevelEventType.SIM_TIME_SCALE)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK, LevelEventType.ACTIVATE_BLOCK)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 1, LevelEventType.CAULDRON_EXPLODE)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 2, LevelEventType.CAULDRON_DYE_ARMOR)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 3, LevelEventType.CAULDRON_CLEAN_ARMOR)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 4, LevelEventType.CAULDRON_FILL_POTION)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 5, LevelEventType.CAULDRON_TAKE_POTION)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 6, LevelEventType.CAULDRON_FILL_WATER)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 7, LevelEventType.CAULDRON_TAKE_WATER)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 8, LevelEventType.CAULDRON_ADD_DYE)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 9, LevelEventType.CAULDRON_CLEAN_BANNER)
    .insert(CodecHelperV291.LEVEL_EVENT_BLOCK + 10, LevelEventType.CAULDRON_FLUSH)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE, ParticleType.UNDEFINED)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 1, ParticleType.BUBBLE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 2, ParticleType.CRIT)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 3, ParticleType.BLOCK_FORCE_FIELD)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 4, ParticleType.SMOKE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 5, ParticleType.EXPLODE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 6, ParticleType.EVAPORATION)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 7, ParticleType.FLAME)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 8, ParticleType.LAVA)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 9, ParticleType.LARGE_SMOKE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 10, ParticleType.RED_DUST)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 11, ParticleType.RISING_BORDER_DUST)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 12, ParticleType.ICON_CRACK)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 13, ParticleType.SNOWBALL_POOF)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 14, ParticleType.LARGE_EXPLODE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 15, ParticleType.HUGE_EXPLOSION)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 16, ParticleType.MOB_FLAME)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 17, ParticleType.HEART)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 18, ParticleType.TERRAIN)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 19, ParticleType.TOWN_AURA)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 20, ParticleType.PORTAL)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 21, ParticleType.WATER_SPLASH)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 22, ParticleType.WATER_WAKE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 23, ParticleType.DRIP_WATER)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 24, ParticleType.DRIP_LAVA)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 25, ParticleType.FALLING_DUST)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 26, ParticleType.MOB_SPELL)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 27, ParticleType.MOB_SPELL_AMBIENT)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 28, ParticleType.MOB_SPELL_INSTANTANEOUS)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 29, ParticleType.INK)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 30, ParticleType.SLIME)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 31, ParticleType.RAIN_SPLASH)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 32, ParticleType.VILLAGER_ANGRY)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 33, ParticleType.VILLAGER_HAPPY)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 34, ParticleType.ENCHANTING_TABLE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 35, ParticleType.TRACKER_EMITTER)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 36, ParticleType.NOTE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 37, ParticleType.WITCH_SPELL)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 38, ParticleType.CARROT_BOOST)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 39, ParticleType.MOB_APPEARANCE)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 40, ParticleType.END_ROD)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 41, ParticleType.DRAGON_BREATH)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 42, ParticleType.SPIT)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 43, ParticleType.TOTEM)
    .insert(CodecHelperV291.LEVEL_EVENT_PARTICLE_TYPE + 44, ParticleType.FOOD)
    .build();

  /**
   * the sound events.
   */
  @Getter
  protected final IntTypeMap<SoundEvent> soundEvents = IntTypeMap.newBuilder(SoundEvent.class)
    .insert(0, SoundEvent.ITEM_USE_ON)
    .insert(1, SoundEvent.HIT)
    .insert(2, SoundEvent.STEP)
    .insert(3, SoundEvent.FLY)
    .insert(4, SoundEvent.JUMP)
    .insert(5, SoundEvent.BREAK)
    .insert(6, SoundEvent.PLACE)
    .insert(7, SoundEvent.HEAVY_STEP)
    .insert(8, SoundEvent.GALLOP)
    .insert(9, SoundEvent.FALL)
    .insert(10, SoundEvent.AMBIENT)
    .insert(11, SoundEvent.AMBIENT_BABY)
    .insert(12, SoundEvent.AMBIENT_IN_WATER)
    .insert(13, SoundEvent.BREATHE)
    .insert(14, SoundEvent.DEATH)
    .insert(15, SoundEvent.DEATH_IN_WATER)
    .insert(16, SoundEvent.DEATH_TO_ZOMBIE)
    .insert(17, SoundEvent.HURT)
    .insert(18, SoundEvent.HURT_IN_WATER)
    .insert(19, SoundEvent.MAD)
    .insert(20, SoundEvent.BOOST)
    .insert(21, SoundEvent.BOW)
    .insert(22, SoundEvent.SQUISH_BIG)
    .insert(23, SoundEvent.SQUISH_SMALL)
    .insert(24, SoundEvent.FALL_BIG)
    .insert(25, SoundEvent.FALL_SMALL)
    .insert(26, SoundEvent.SPLASH)
    .insert(27, SoundEvent.FIZZ)
    .insert(28, SoundEvent.FLAP)
    .insert(29, SoundEvent.SWIM)
    .insert(30, SoundEvent.DRINK)
    .insert(31, SoundEvent.EAT)
    .insert(32, SoundEvent.TAKEOFF)
    .insert(33, SoundEvent.SHAKE)
    .insert(34, SoundEvent.PLOP)
    .insert(35, SoundEvent.LAND)
    .insert(36, SoundEvent.SADDLE)
    .insert(37, SoundEvent.ARMOR)
    .insert(38, SoundEvent.MOB_ARMOR_STAND_PLACE)
    .insert(39, SoundEvent.ADD_CHEST)
    .insert(40, SoundEvent.THROW)
    .insert(41, SoundEvent.ATTACK)
    .insert(42, SoundEvent.ATTACK_NODAMAGE)
    .insert(43, SoundEvent.ATTACK_STRONG)
    .insert(44, SoundEvent.WARN)
    .insert(45, SoundEvent.SHEAR)
    .insert(46, SoundEvent.MILK)
    .insert(47, SoundEvent.THUNDER)
    .insert(48, SoundEvent.EXPLODE)
    .insert(49, SoundEvent.FIRE)
    .insert(50, SoundEvent.IGNITE)
    .insert(51, SoundEvent.FUSE)
    .insert(52, SoundEvent.STARE)
    .insert(53, SoundEvent.SPAWN)
    .insert(54, SoundEvent.SHOOT)
    .insert(55, SoundEvent.BREAK_BLOCK)
    .insert(56, SoundEvent.LAUNCH)
    .insert(57, SoundEvent.BLAST)
    .insert(58, SoundEvent.LARGE_BLAST)
    .insert(59, SoundEvent.TWINKLE)
    .insert(60, SoundEvent.REMEDY)
    .insert(61, SoundEvent.UNFECT)
    .insert(62, SoundEvent.LEVELUP)
    .insert(63, SoundEvent.BOW_HIT)
    .insert(64, SoundEvent.BULLET_HIT)
    .insert(65, SoundEvent.EXTINGUISH_FIRE)
    .insert(66, SoundEvent.ITEM_FIZZ)
    .insert(67, SoundEvent.CHEST_OPEN)
    .insert(68, SoundEvent.CHEST_CLOSED)
    .insert(69, SoundEvent.SHULKERBOX_OPEN)
    .insert(70, SoundEvent.SHULKERBOX_CLOSED)
    .insert(71, SoundEvent.ENDERCHEST_OPEN)
    .insert(72, SoundEvent.ENDERCHEST_CLOSED)
    .insert(73, SoundEvent.POWER_ON)
    .insert(74, SoundEvent.POWER_OFF)
    .insert(75, SoundEvent.ATTACH)
    .insert(76, SoundEvent.DETACH)
    .insert(77, SoundEvent.DENY)
    .insert(78, SoundEvent.TRIPOD)
    .insert(79, SoundEvent.POP)
    .insert(80, SoundEvent.DROP_SLOT)
    .insert(81, SoundEvent.NOTE)
    .insert(82, SoundEvent.THORNS)
    .insert(83, SoundEvent.PISTON_IN)
    .insert(84, SoundEvent.PISTON_OUT)
    .insert(85, SoundEvent.PORTAL)
    .insert(86, SoundEvent.WATER)
    .insert(87, SoundEvent.LAVA_POP)
    .insert(88, SoundEvent.LAVA)
    .insert(89, SoundEvent.BURP)
    .insert(90, SoundEvent.BUCKET_FILL_WATER)
    .insert(91, SoundEvent.BUCKET_FILL_LAVA)
    .insert(92, SoundEvent.BUCKET_EMPTY_WATER)
    .insert(93, SoundEvent.BUCKET_EMPTY_LAVA)
    .insert(94, SoundEvent.ARMOR_EQUIP_CHAIN)
    .insert(95, SoundEvent.ARMOR_EQUIP_DIAMOND)
    .insert(96, SoundEvent.ARMOR_EQUIP_GENERIC)
    .insert(97, SoundEvent.ARMOR_EQUIP_GOLD)
    .insert(98, SoundEvent.ARMOR_EQUIP_IRON)
    .insert(99, SoundEvent.ARMOR_EQUIP_LEATHER)
    .insert(100, SoundEvent.ARMOR_EQUIP_ELYTRA)
    .insert(101, SoundEvent.RECORD_13)
    .insert(102, SoundEvent.RECORD_CAT)
    .insert(103, SoundEvent.RECORD_BLOCKS)
    .insert(104, SoundEvent.RECORD_CHIRP)
    .insert(105, SoundEvent.RECORD_FAR)
    .insert(106, SoundEvent.RECORD_MALL)
    .insert(107, SoundEvent.RECORD_MELLOHI)
    .insert(108, SoundEvent.RECORD_STAL)
    .insert(109, SoundEvent.RECORD_STRAD)
    .insert(110, SoundEvent.RECORD_WARD)
    .insert(111, SoundEvent.RECORD_11)
    .insert(112, SoundEvent.RECORD_WAIT)
    .insert(113, SoundEvent.STOP_RECORD)
    .insert(114, SoundEvent.FLOP)
    .insert(115, SoundEvent.ELDERGUARDIAN_CURSE)
    .insert(116, SoundEvent.MOB_WARNING)
    .insert(117, SoundEvent.MOB_WARNING_BABY)
    .insert(118, SoundEvent.TELEPORT)
    .insert(119, SoundEvent.SHULKER_OPEN)
    .insert(120, SoundEvent.SHULKER_CLOSE)
    .insert(121, SoundEvent.HAGGLE)
    .insert(122, SoundEvent.HAGGLE_YES)
    .insert(123, SoundEvent.HAGGLE_NO)
    .insert(124, SoundEvent.HAGGLE_IDLE)
    .insert(125, SoundEvent.CHORUS_GROW)
    .insert(126, SoundEvent.CHORUS_DEATH)
    .insert(127, SoundEvent.GLASS)
    .insert(128, SoundEvent.POTION_BREWED)
    .insert(129, SoundEvent.CAST_SPELL)
    .insert(130, SoundEvent.PREPARE_ATTACK)
    .insert(131, SoundEvent.PREPARE_SUMMON)
    .insert(132, SoundEvent.PREPARE_WOLOLO)
    .insert(133, SoundEvent.FANG)
    .insert(134, SoundEvent.CHARGE)
    .insert(135, SoundEvent.CAMERA_TAKE_PICTURE)
    .insert(136, SoundEvent.LEASHKNOT_PLACE)
    .insert(137, SoundEvent.LEASHKNOT_BREAK)
    .insert(138, SoundEvent.GROWL)
    .insert(139, SoundEvent.WHINE)
    .insert(140, SoundEvent.PANT)
    .insert(141, SoundEvent.PURR)
    .insert(142, SoundEvent.PURREOW)
    .insert(143, SoundEvent.DEATH_MIN_VOLUME)
    .insert(144, SoundEvent.DEATH_MID_VOLUME)
    .insert(145, SoundEvent.IMITATE_BLAZE)
    .insert(146, SoundEvent.IMITATE_CAVE_SPIDER)
    .insert(147, SoundEvent.IMITATE_CREEPER)
    .insert(148, SoundEvent.IMITATE_ELDER_GUARDIAN)
    .insert(149, SoundEvent.IMITATE_ENDER_DRAGON)
    .insert(150, SoundEvent.IMITATE_ENDERMAN)
    .insert(152, SoundEvent.IMITATE_EVOCATION_ILLAGER)
    .insert(153, SoundEvent.IMITATE_GHAST)
    .insert(154, SoundEvent.IMITATE_HUSK)
    .insert(155, SoundEvent.IMITATE_ILLUSION_ILLAGER)
    .insert(156, SoundEvent.IMITATE_MAGMA_CUBE)
    .insert(157, SoundEvent.IMITATE_POLAR_BEAR)
    .insert(158, SoundEvent.IMITATE_SHULKER)
    .insert(159, SoundEvent.IMITATE_SILVERFISH)
    .insert(160, SoundEvent.IMITATE_SKELETON)
    .insert(161, SoundEvent.IMITATE_SLIME)
    .insert(162, SoundEvent.IMITATE_SPIDER)
    .insert(163, SoundEvent.IMITATE_STRAY)
    .insert(164, SoundEvent.IMITATE_VEX)
    .insert(165, SoundEvent.IMITATE_VINDICATION_ILLAGER)
    .insert(166, SoundEvent.IMITATE_WITCH)
    .insert(167, SoundEvent.IMITATE_WITHER)
    .insert(168, SoundEvent.IMITATE_WITHER_SKELETON)
    .insert(169, SoundEvent.IMITATE_WOLF)
    .insert(170, SoundEvent.IMITATE_ZOMBIE)
    .insert(171, SoundEvent.IMITATE_ZOMBIE_PIGMAN)
    .insert(172, SoundEvent.IMITATE_ZOMBIE_VILLAGER)
    .insert(173, SoundEvent.BLOCK_END_PORTAL_FRAME_FILL)
    .insert(174, SoundEvent.BLOCK_END_PORTAL_SPAWN)
    .insert(175, SoundEvent.RANDOM_ANVIL_USE)
    .insert(176, SoundEvent.BOTTLE_DRAGONBREATH)
    .insert(177, SoundEvent.PORTAL_TRAVEL)
    .insert(178, SoundEvent.ITEM_TRIDENT_HIT)
    .insert(179, SoundEvent.ITEM_TRIDENT_RETURN)
    .insert(180, SoundEvent.ITEM_TRIDENT_RIPTIDE_1)
    .insert(181, SoundEvent.ITEM_TRIDENT_RIPTIDE_2)
    .insert(182, SoundEvent.ITEM_TRIDENT_RIPTIDE_3)
    .insert(183, SoundEvent.ITEM_TRIDENT_THROW)
    .insert(184, SoundEvent.ITEM_TRIDENT_THUNDER)
    .insert(185, SoundEvent.ITEM_TRIDENT_HIT_GROUND)
    .insert(186, SoundEvent.DEFAULT)
    .insert(188, SoundEvent.ELEMENT_CONSTRUCTOR_OPEN)
    .insert(189, SoundEvent.ICE_BOMB_HIT)
    .insert(190, SoundEvent.BALLOON_POP)
    .insert(191, SoundEvent.LT_REACTION_ICE_BOMB)
    .insert(192, SoundEvent.LT_REACTION_BLEACH)
    .insert(193, SoundEvent.LT_REACTION_E_PASTE)
    .insert(194, SoundEvent.LT_REACTION_E_PASTE2)
    .insert(199, SoundEvent.LT_REACTION_FERTILIZER)
    .insert(200, SoundEvent.LT_REACTION_FIREBALL)
    .insert(201, SoundEvent.LT_REACTION_MG_SALT)
    .insert(202, SoundEvent.LT_REACTION_MISC_FIRE)
    .insert(203, SoundEvent.LT_REACTION_FIRE)
    .insert(204, SoundEvent.LT_REACTION_MISC_EXPLOSION)
    .insert(205, SoundEvent.LT_REACTION_MISC_MYSTICAL)
    .insert(206, SoundEvent.LT_REACTION_MISC_MYSTICAL2)
    .insert(207, SoundEvent.LT_REACTION_PRODUCT)
    .insert(208, SoundEvent.SPARKLER_USE)
    .insert(209, SoundEvent.GLOWSTICK_USE)
    .insert(210, SoundEvent.SPARKLER_ACTIVE)
    .insert(211, SoundEvent.CONVERT_TO_DROWNED)
    .insert(212, SoundEvent.BUCKET_FILL_FISH)
    .insert(213, SoundEvent.BUCKET_EMPTY_FISH)
    .insert(214, SoundEvent.BUBBLE_UP)
    .insert(215, SoundEvent.BUBBLE_DOWN)
    .insert(216, SoundEvent.BUBBLE_POP)
    .insert(217, SoundEvent.BUBBLE_UP_INSIDE)
    .insert(218, SoundEvent.BUBBLE_DOWN_INSIDE)
    .insert(219, SoundEvent.BABY_HURT)
    .insert(220, SoundEvent.BABY_DEATH)
    .insert(221, SoundEvent.BABY_STEP)
    .insert(222, SoundEvent.BABY_SPAWN)
    .insert(223, SoundEvent.BORN)
    .insert(224, SoundEvent.BLOCK_TURTLE_EGG_BREAK)
    .insert(225, SoundEvent.BLOCK_TURTLE_EGG_CRACK)
    .insert(226, SoundEvent.BLOCK_TURTLE_EGG_HATCH)
    .insert(227, SoundEvent.TURTLE_LAY_EGG)
    .insert(228, SoundEvent.BLOCK_TURTLE_EGG_ATTACK)
    .insert(229, SoundEvent.BEACON_ACTIVATE)
    .insert(230, SoundEvent.BEACON_AMBIENT)
    .insert(231, SoundEvent.BEACON_DEACTIVATE)
    .insert(232, SoundEvent.BEACON_POWER)
    .insert(233, SoundEvent.CONDUIT_ACTIVATE)
    .insert(234, SoundEvent.CONDUIT_AMBIENT)
    .insert(235, SoundEvent.CONDUIT_ATTACK)
    .insert(236, SoundEvent.CONDUIT_DEACTIVATE)
    .insert(237, SoundEvent.CONDUIT_SHORT)
    .insert(238, SoundEvent.SWOOP)
    .insert(239, SoundEvent.UNDEFINED)
    .build();

  /**
   * reads the flags.
   *
   * @param flags the flags to read.
   * @param mappings the mappings to read.
   * @param settings the settings to read.
   */
  private static void readFlags(final int flags, @NotNull final Collection<AdventureSetting> mappings,
                                @NotNull final Set<AdventureSetting> settings) {
    final var array = mappings.toArray(new AdventureSetting[0]);
    for (var index = 0; index < array.length; index++) {
      final var setting = array[index];
      if ((flags & 1 << index) != 0) {
        settings.add(setting);
      }
    }
  }

  @Override
  public void readAdventureSettings(@NotNull final AdventureSettings packet, @NotNull final PacketBuffer buffer) {
    final var flags1 = buffer.readUnsignedVarInt();
    packet.commandPermission(CommandPermission.byOrdinal(buffer.readUnsignedVarInt()));
    final var flags2 = buffer.readUnsignedVarInt();
    packet.playerPermission(PlayerPermission.byOrdinal(buffer.readUnsignedVarInt()));
    buffer.readUnsignedVarInt();
    packet.uniqueEntityId(buffer.readLongLE());
    final var settings = packet.settings();
    CodecHelperV291.readFlags(flags1, this.adventureSettingFlags1.values(), settings);
    CodecHelperV291.readFlags(flags2, this.adventureSettingFlags2.values(), settings);
  }

  @NotNull
  @Override
  public AttributeData readAttribute(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var name = buffer.readString();
    final var min = buffer.readFloatLE();
    final var max = buffer.readFloatLE();
    final var val = buffer.readFloatLE();
    return new AttributeData(name, min, max, val);
  }

  @NotNull
  @Override
  public CommandEnumData readCommandEnum(@NotNull final PacketBuffer buffer, final boolean soft) {
    final var name = buffer.readString();
    final var values = new String[buffer.readUnsignedVarInt()];
    for (var index = 0; index < values.length; index++) {
      values[index] = buffer.readString();
    }
    return new CommandEnumData(soft, name, values);
  }

  @NotNull
  @Override
  public List<CommandData> readCommands(@NotNull final PacketBuffer buffer) {
    final var enumValues = buffer.readArrayUnsignedInt(buffer::readString);
    final var postFixes = buffer.readArrayUnsignedInt(buffer::readString);
    final var enums = this.readCommandEnums(buffer, enumValues);
    final var softEnums = buffer.readArrayUnsignedInt(() -> this.readCommandEnum(buffer, true));
    return buffer.readArrayUnsignedInt(() -> this.readCommand(buffer, enums, softEnums, postFixes));
  }

  @Override
  public void readEntityData(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session,
                             @NotNull final EntityDataMap map) {
    final var length = buffer.readUnsignedVarInt();
    for (var index = 0; index < length; index++) {
      final var metadataInt = buffer.readUnsignedVarInt();
      final var data = this.entityData.type(metadataInt);
      var type = this.entityDataTypes.type(buffer.readUnsignedVarInt());
      if (data != null && data.isFlags()) {
        if (type != EntityDataType.LONG) {
          throw new IllegalArgumentException("Expected long value for flags, got " + type.name());
        }
        type = EntityDataType.FLAGS;
      }
      final Object object;
      switch (type) {
        case BYTE -> object = buffer.readByte();
        case SHORT -> object = buffer.readShortLE();
        case INT -> object = buffer.readVarInt();
        case FLOAT -> object = buffer.readFloatLE();
        case STRING -> object = buffer.readString();
        case NBT -> object = this.readItem(buffer, session);
        case VECTOR3I -> object = buffer.readVector3i();
        case FLAGS -> {
          map.getOrCreateFlags().set(
            buffer.readVarLong(),
            data == EntityData.FLAGS_2 ? 1 : 0,
            this.entityFlags
          );
          continue;
        }
        case LONG -> object = buffer.readVarLong();
        case VECTOR3F -> object = buffer.readVector3f();
        default -> throw new IllegalArgumentException("Unknown entity data type received!");
      }
      if (data != null) {
        map.put(data, object);
      } else {
        CodecHelper.LOG.debug("Unknown entity data: {} type {} value {}", metadataInt, type, object);
      }
    }
  }

  @NotNull
  @Override
  public EntityLinkData readEntityLink(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var from = buffer.readVarLong();
    final var to = buffer.readVarLong();
    final var type = buffer.readUnsignedByte();
    final var immediate = buffer.readBoolean();
    return new EntityLinkData(from, to, EntityLinkDataType.byOrdinal(type), immediate);
  }

  @Override
  public GameRuleValue readGameRule(@NotNull final PacketBuffer buffer) {
    final var name = buffer.readString();
    final var type = buffer.readUnsignedVarInt();
    return switch (type) {
      case 1 -> new GameRuleValue(name, buffer.readBoolean());
      case 2 -> new GameRuleValue(name, buffer.readUnsignedVarInt());
      case 3 -> new GameRuleValue(name, buffer.readFloatLE());
      default -> throw new IllegalStateException("Invalid game rule type received!");
    };
  }

  @NotNull
  @Override
  public ItemData readItem(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var runtimeId = buffer.readVarInt();
    if (runtimeId == 0) {
      return ItemData.AIR;
    }
    final var definition = this.itemDefinitions.byId(runtimeId);
    final var aux = buffer.readVarInt();
    var damage = (short) (aux >> 8);
    if (damage == Short.MAX_VALUE) {
      damage = -1;
    }
    final var count = aux & 0xff;
    final var nbtSize = buffer.readShortLE();
    CompoundTag compoundTag = null;
    if (nbtSize > 0) {
      try (final var reader = Tag.createNetworkReader(new ByteBufInputStream(buffer.buffer().readSlice(nbtSize)))) {
        compoundTag = reader.readCompoundTag();
      } catch (final IOException e) {
        throw new IllegalStateException("Unable to load NBT data!", e);
      }
    }
    final var canPlace = buffer.readArrayUnsignedInt(buffer::readString);
    final var canBreak = buffer.readArrayUnsignedInt(buffer::readString);
    return ItemData.newBuilder()
      .definition(definition)
      .damage(damage)
      .count(count)
      .tag(compoundTag)
      .canPlace(canPlace.toArray(new String[0]))
      .canBreak(canBreak.toArray(new String[0]))
      .build();
  }

  @Override
  public void readLevelSettings(@NotNull final StartGame packet, @NotNull final PacketBuffer buffer) {
    packet.seed(buffer.readVarInt());
    packet.dimensionId(buffer.readVarInt());
    packet.generatorId(buffer.readVarInt());
    packet.levelGameType(GameType.byOrdinal(buffer.readVarInt()));
    packet.difficulty(buffer.readVarInt());
    packet.defaultSpawn(buffer.readVector3i());
    packet.achievementsDisabled(buffer.readBoolean());
    packet.dayCycleStopTime(buffer.readVarInt());
    packet.eduEditionOffers(buffer.readBoolean() ? 1 : 0);
    packet.eduFeaturesEnabled(buffer.readBoolean());
    packet.rainLevel(buffer.readFloatLE());
    packet.lightningLevel(buffer.readFloatLE());
    packet.multiplayerGame(buffer.readBoolean());
    packet.broadcastingToLan(buffer.readBoolean());
    buffer.readBoolean();
    packet.commandsEnabled(buffer.readBoolean());
    packet.texturePacksRequired(buffer.readBoolean());
    packet.gameRules(buffer.readArrayUnsignedInt(() -> this.readGameRule(buffer)));
    packet.bonusChestEnabled(buffer.readBoolean());
    packet.startingWithMap(buffer.readBoolean());
    packet.trustingPlayers(buffer.readBoolean());
    packet.defaultPlayerPermission(PlayerPermission.byOrdinal(buffer.readVarInt()));
    packet.xblBroadcastMode(GamePublishSetting.byOrdinal(buffer.readVarInt()));
    packet.serverChunkTickRange(buffer.readIntLE());
    buffer.readBoolean();
    packet.platformBroadcastMode(GamePublishSetting.byOrdinal(buffer.readVarInt()));
    buffer.readBoolean();
    packet.behaviorPackLocked(buffer.readBoolean());
    packet.resourcePackLocked(buffer.readBoolean());
    packet.fromLockedWorldTemplate(buffer.readBoolean());
    packet.usingMsaGamerTagsOnly(buffer.readBoolean());
  }

  @NotNull
  @Override
  public ResourcePackInfo.Entry readResourcePackInfoEntry(@NotNull final PacketBuffer buffer) {
    final var packId = buffer.readString();
    final var packVersion = buffer.readString();
    final var packSize = buffer.readLongLE();
    final var contentKey = buffer.readString();
    final var subPackName = buffer.readString();
    final var contentId = buffer.readString();
    return new ResourcePackInfo.Entry(packId, packVersion, packSize, contentKey, subPackName, contentId,
      false, false);
  }

  @NotNull
  @Override
  public ResourcePackStack.Entry readResourcePackStackEntry(@NotNull final PacketBuffer buffer) {
    final var packId = buffer.readString();
    final var packVersion = buffer.readString();
    final var subPackName = buffer.readString();
    return new ResourcePackStack.Entry(packId, packVersion, subPackName);
  }

  @Override
  public void writeAdventureSettings(@NotNull final AdventureSettings packet, @NotNull final PacketBuffer buffer) {
    var flags1 = 0;
    var flags2 = 0;
    for (final var setting : packet.settings()) {
      if (this.adventureSettingFlags1.containsValue(setting)) {
        flags1 |= this.adventureSettingFlags1.id(setting);
      } else if (this.adventureSettingFlags2.containsValue(setting)) {
        flags2 |= this.adventureSettingFlags2.id(setting);
      }
    }
    buffer.writeUnsignedVarInt(flags1);
    buffer.writeUnsignedVarInt(packet.commandPermission().ordinal());
    buffer.writeUnsignedVarInt(flags2);
    buffer.writeUnsignedVarInt(packet.playerPermission().ordinal());
    buffer.writeUnsignedVarInt(0);
    buffer.writeLongLE(packet.uniqueEntityId());
  }

  @Override
  public void writeAttribute(@NotNull final PacketBuffer buffer,
                             @NotNull final AttributeData data) {
    buffer.writeString(data.name());
    buffer.writeFloatLE(data.minimum());
    buffer.writeFloatLE(data.maximum());
    buffer.writeFloatLE(data.value());
  }

  @Override
  public void writeCommandEnum(@NotNull final PacketBuffer buffer,
                               @NotNull final CommandEnumData data) {
    buffer.writeString(data.name());
    final var values = data.values();
    buffer.writeUnsignedVarInt(values.length);
    for (final var value : values) {
      buffer.writeString(value);
    }
  }

  @Override
  public void writeCommands(@NotNull final PacketBuffer buffer, @NotNull final List<CommandData> commands) {
    final var enumValuesSet = new ObjectOpenHashSet<String>();
    final var postFixesSet = new ObjectOpenHashSet<String>();
    final var enumsSet = new ObjectOpenHashSet<CommandEnumData>();
    final var softEnumsSet = new ObjectOpenHashSet<CommandEnumData>();
    for (final var data : commands) {
      final var aliases = data.aliases();
      if (aliases != null) {
        Collections.addAll(enumValuesSet, aliases.values());
        enumsSet.add(aliases);
      }
      for (final var overload : data.overloads()) {
        for (final var parameter : overload) {
          final var commandEnumData = parameter.enumData();
          if (commandEnumData != null) {
            if (commandEnumData.isSoft()) {
              softEnumsSet.add(commandEnumData);
            } else {
              Collections.addAll(enumValuesSet, commandEnumData.values());
              enumsSet.add(commandEnumData);
            }
          }
          final var postfix = parameter.postfix();
          if (postfix != null) {
            postFixesSet.add(postfix);
          }
        }
      }
    }
    final var enumValues = new ObjectArrayList<>(enumValuesSet);
    final var postFixes = new ObjectArrayList<>(postFixesSet);
    final var enums = new ObjectArrayList<>(enumsSet);
    final var softEnums = new ObjectArrayList<>(softEnumsSet);
    buffer.writeArrayUnsignedInt(enumValues, buffer::writeString);
    buffer.writeArrayUnsignedInt(postFixes, buffer::writeString);
    this.writeCommandEnums(buffer, enumValues, enums);
    buffer.writeArrayUnsignedInt(commands, command ->
      this.writeCommand(buffer, command, enums, softEnums, postFixes));
    buffer.writeArrayUnsignedInt(softEnums, data -> this.writeCommandEnum(buffer, data));
  }

  @Override
  public void writeEntityData(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session,
                              @NotNull final EntityDataMap map) {
    buffer.writeUnsignedVarInt(map.size());
    for (final var entry : map.entrySet()) {
      final var size = buffer.size();
      buffer.writeUnsignedVarInt(this.entityData.id(entry.getKey()));
      var object = entry.getValue();
      final var type = EntityDataType.byObject(object);
      final int typeId;
      if (type == EntityDataType.FLAGS) {
        typeId = this.entityDataTypes.id(EntityDataType.LONG);
      } else {
        typeId = this.entityDataTypes.id(type);
      }
      buffer.writeUnsignedVarInt(typeId);
      switch (type) {
        case BYTE:
          buffer.writeByte((byte) object);
          break;
        case SHORT:
          buffer.writeShortLE((short) object);
          break;
        case INT:
          buffer.writeVarInt((int) object);
          break;
        case FLOAT:
          buffer.writeFloatLE((float) object);
          break;
        case STRING:
          buffer.writeString((String) object);
          break;
        case NBT:
          final ItemData item;
          if (object instanceof CompoundTag compoundTag) {
            item = ItemData.newBuilder()
              .definition(ItemDefinition.LEGACY_FIREWORK)
              .damage(0)
              .count(1)
              .tag(compoundTag)
              .build();
          } else {
            item = (ItemData) object;
          }
          this.writeItem(buffer, session, item);
          break;
        case VECTOR3I:
          buffer.writeVector3i((Vector3i) object);
          break;
        case FLAGS:
          final var flagsIndex = entry.getKey() == EntityData.FLAGS_2 ? 1 : 0;
          object = ((EntityFlags) object).get(flagsIndex, this.entityFlags);
        case LONG:
          buffer.writeVarLong((long) object);
          break;
        case VECTOR3F:
          buffer.writeVector3f((Vector3f) object);
          break;
        default:
          buffer.buffer().writerIndex(size);
          break;
      }
    }
  }

  @Override
  public void writeEntityLink(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session,
                              @NotNull final EntityLinkData link) {
    buffer.writeVarLong(link.from());
    buffer.writeVarLong(link.to());
    buffer.writeByte(link.type().ordinal());
    buffer.writeBoolean(link.immediate());
  }

  @Override
  public void writeGameRule(@NotNull final PacketBuffer buffer, @NotNull final GameRuleValue gameRule) {
    final var value = gameRule.value();
    final var type = this.gameRuleTypes.id(value.getClass());
    buffer.writeString(gameRule.name());
    buffer.writeUnsignedVarInt(type);
    switch (type) {
      case 1 -> buffer.writeBoolean((boolean) value);
      case 2 -> buffer.writeUnsignedVarInt((int) value);
      case 3 -> buffer.writeFloatLE((float) value);
      default -> throw new IllegalStateException("Invalid game rule type received!");
    }
  }

  @Override
  public void writeItem(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session,
                        @NotNull final ItemData item) {
    final var definition = item.definition();
    if (this.isAir(definition)) {
      buffer.writeByte(0);
      return;
    }
    buffer.writeVarInt(definition.id());
    var damage = item.damage();
    if (damage == -1) {
      damage = Short.MAX_VALUE;
    }
    buffer.writeVarInt(damage << 8 | item.count() & 0xff);
    final var sizeIndex = buffer.size();
    buffer.writeShortLE(0);
    final var tag = item.tag();
    if (tag != null) {
      final var afterSizeIndex = buffer.size();
      try (final var stream = Tag.createWriterLE(buffer.buffer())) {
        stream.write(tag);
      } catch (final IOException e) {
        throw new IllegalStateException("Unable to save NBT data", e);
      }
      buffer.setShortLE(sizeIndex, buffer.size() - afterSizeIndex);
    }
    buffer.writeArrayUnsignedInt(item.canPlace(), buffer::writeString);
    buffer.writeArrayUnsignedInt(item.canBreak(), buffer::writeString);
  }

  @Override
  public void writeLevelSettings(@NotNull final StartGame packet, @NotNull final PacketBuffer buffer) {
    buffer.writeVarInt(packet.seed());
    buffer.writeVarInt(packet.dimensionId());
    buffer.writeVarInt(packet.generatorId());
    buffer.writeVarInt(packet.levelGameType().ordinal());
    buffer.writeVarInt(packet.difficulty());
    buffer.writeVector3i(packet.defaultSpawn());
    buffer.writeBoolean(packet.achievementsDisabled());
    buffer.writeVarInt(packet.dayCycleStopTime());
    buffer.writeBoolean(packet.eduEditionOffers() != 0);
    buffer.writeBoolean(packet.eduFeaturesEnabled());
    buffer.writeFloatLE(packet.rainLevel());
    buffer.writeFloatLE(packet.lightningLevel());
    buffer.writeBoolean(packet.multiplayerGame());
    buffer.writeBoolean(packet.broadcastingToLan());
    buffer.writeBoolean(packet.xblBroadcastMode() != GamePublishSetting.NO_MULTI_PLAY);
    buffer.writeBoolean(packet.commandsEnabled());
    buffer.writeBoolean(packet.texturePacksRequired());
    buffer.writeArrayUnsignedInt(packet.gameRules(), rule -> this.writeGameRule(buffer, rule));
    buffer.writeBoolean(packet.bonusChestEnabled());
    buffer.writeBoolean(packet.startingWithMap());
    buffer.writeBoolean(packet.trustingPlayers());
    buffer.writeVarInt(packet.defaultPlayerPermission().ordinal());
    buffer.writeVarInt(packet.xblBroadcastMode().ordinal());
    buffer.writeIntLE(packet.serverChunkTickRange());
    buffer.writeBoolean(packet.platformBroadcastMode() != GamePublishSetting.NO_MULTI_PLAY);
    buffer.writeVarInt(packet.platformBroadcastMode().ordinal());
    buffer.writeBoolean(packet.xblBroadcastMode() != GamePublishSetting.NO_MULTI_PLAY);
    buffer.writeBoolean(packet.behaviorPackLocked());
    buffer.writeBoolean(packet.resourcePackLocked());
    buffer.writeBoolean(packet.fromLockedWorldTemplate());
    buffer.writeBoolean(packet.usingMsaGamerTagsOnly());
  }

  @Override
  public void writeResourcePackEntry(@NotNull final PacketBuffer buffer, @NotNull final ResourcePackInfo.Entry entry) {
    buffer.writeString(entry.packId());
    buffer.writeString(entry.packVersion());
    buffer.writeLongLE(entry.packSize());
    buffer.writeString(entry.contentKey());
    buffer.writeString(entry.subPackName());
    buffer.writeString(entry.contentId());
  }

  @Override
  public void writeResourcePackStackEntry(@NotNull final PacketBuffer buffer, @NotNull final ResourcePackStack.Entry entry) {
    buffer.writeString(entry.packId());
    buffer.writeString(entry.packVersion());
    buffer.writeString(entry.subPackName());
  }

  /**
   * checks if the definition is air.
   *
   * @param definition the definition to check.
   *
   * @return {@code true} if the definition is an air.
   */
  protected final boolean isAir(@NotNull final ItemDefinition definition) {
    return definition.id() == 0;
  }

  /**
   * reads the command.
   *
   * @param buffer the buffer to read.
   * @param enums the enums read.
   * @param softEnums the soft enums.
   * @param postFixes the post fixes to read.
   *
   * @return command.
   */
  @NotNull
  private CommandData readCommand(@NotNull final PacketBuffer buffer, @NotNull final List<CommandEnumData> enums,
                                  @NotNull final List<CommandEnumData> softEnums,
                                  @NotNull final List<String> postFixes) {
    final var name = buffer.readString();
    final var description = buffer.readString();
    final var flags = buffer.readByte();
    final var permissions = buffer.readByte();
    final var aliasesIndex = buffer.readIntLE();
    final var overloads = new CommandParamData[buffer.readUnsignedVarInt()][];
    for (var index = 0; index < overloads.length; index++) {
      overloads[index] = new CommandParamData[buffer.readUnsignedVarInt()];
      Arrays.setAll(overloads[index], index2 ->
        this.readParameter(buffer, enums, softEnums, postFixes));
    }
    final var flagList = IntStream.range(0, 6)
      .filter(index -> (flags & 1 << index) != 0)
      .mapToObj(index -> CommandData.Flag.VALUES[index])
      .collect(Collectors.toCollection(ObjectArrayList::new));
    final var aliases = aliasesIndex == -1 ? null : enums.get(aliasesIndex);
    return new CommandData(name, description, flagList, permissions, aliases, overloads);
  }

  /**
   * reads the command enums.
   *
   * @param buffer the buffer to read.
   * @param values the value to read.
   *
   * @return command enum data.
   */
  @NotNull
  private List<CommandEnumData> readCommandEnums(@NotNull final PacketBuffer buffer,
                                                 @NotNull final List<String> values) {
    final var indexReader = CodecHelperV291.READER_FUNCTION.apply(values.size());
    return buffer.readArrayUnsignedInt(() -> {
      final var name = buffer.readString();
      final var length = buffer.readUnsignedVarInt();
      final var enumValues = new String[length];
      for (var index = 0; index < length; index++) {
        enumValues[index] = values.get(indexReader.applyAsInt(buffer));
      }
      return new CommandEnumData(false, name, enumValues);
    });
  }

  /**
   * reads the parameter.
   *
   * @param buffer the buffer to read.
   * @param enums the enums to read.
   * @param softEnums the soft enums to read.
   * @param postFixes the post fixes to read.
   *
   * @return command parameter data.
   */
  @NotNull
  private CommandParamData readParameter(@NotNull final PacketBuffer buffer, @NotNull final List<CommandEnumData> enums,
                                         @NotNull final List<CommandEnumData> softEnums,
                                         @NotNull final List<String> postFixes) {
    final var name = buffer.readString();
    final var type = CommandSymbolData.deserialize(buffer.readIntLE());
    final var optional = buffer.readBoolean();
    final var optionsByte = buffer.readByte();
    String postfix = null;
    CommandEnumData enumData = null;
    CommandParam commandParam = null;
    if (type.postfix()) {
      postfix = postFixes.get(type.value());
    } else if (type.commandEnum()) {
      enumData = enums.get(type.value());
    } else if (type.softEnum()) {
      enumData = softEnums.get(type.value());
    } else {
      commandParam = this.commandParam(type.value());
    }
    final var options = IntStream.range(0, 8)
      .filter(index -> (optionsByte & 1 << index) != 0)
      .mapToObj(index -> CommandParamOption.VALUES[index])
      .collect(Collectors.toCollection(ObjectArrayList::new));
    return new CommandParamData(name, optional, enumData, commandParam, postfix, options);
  }

  /**
   * writes the command.
   *
   * @param buffer the buffer
   * @param commandData the command data to read.
   * @param enums the enums to read.
   * @param softEnums the soft enums to read.
   * @param postFixes the post fixes to read.
   */
  private void writeCommand(@NotNull final PacketBuffer buffer, @NotNull final CommandData commandData,
                            @NotNull final List<CommandEnumData> enums, @NotNull final List<CommandEnumData> softEnums,
                            @NotNull final List<String> postFixes) {
    buffer.writeString(commandData.name());
    buffer.writeString(commandData.description());
    final var flags = commandData.flags().stream()
      .mapToInt(flag -> 1 << flag.ordinal())
      .reduce(0, (a, b) -> a | b);
    buffer.writeByte(flags);
    buffer.writeByte(commandData.permission());
    final var aliases = commandData.aliases();
    buffer.writeIntLE(enums.indexOf(aliases));
    final var overloads = commandData.overloads();
    buffer.writeUnsignedVarInt(overloads.length);
    for (final var overload : overloads) {
      buffer.writeUnsignedVarInt(overload.length);
      for (final var param : overload) {
        this.writeParameter(buffer, param, enums, softEnums, postFixes);
      }
    }
  }

  /**
   * writes the command enums.
   *
   * @param buffer the buffer to write.
   * @param values the values to write.
   * @param enums the enums to write.
   */
  private void writeCommandEnums(@NotNull final PacketBuffer buffer, @NotNull final List<String> values,
                                 @NotNull final Collection<CommandEnumData> enums) {
    final var indexWriter = CodecHelperV291.WRITER_FUNCTION.apply(values.size());
    buffer.writeArrayUnsignedInt(enums, commandEnum -> {
      buffer.writeString(commandEnum.name());
      buffer.writeUnsignedVarInt(commandEnum.values().length);
      for (final var value : commandEnum.values()) {
        final var index = values.indexOf(value);
        Preconditions.checkArgument(index > -1, "Invalid enum value detected: %s", value);
        indexWriter.accept(buffer, index);
      }
    });
  }

  /**
   * writes the parameter.
   *
   * @param buffer the buffer to write.
   * @param param the param to write.
   * @param enums the enums to write.
   * @param softEnums the soft enums to write.
   * @param postFixes the post fixes to write.
   */
  private void writeParameter(@NotNull final PacketBuffer buffer, @NotNull final CommandParamData param,
                              @NotNull final List<CommandEnumData> enums,
                              @NotNull final List<CommandEnumData> softEnums, @NotNull final List<String> postFixes) {
    buffer.writeString(param.name());
    final int index;
    var postfix = false;
    var enumData = false;
    var softEnum = false;
    final var postfixString = param.postfix();
    if (postfixString != null) {
      postfix = true;
      index = postFixes.indexOf(postfixString);
    } else {
      final var commandEnumData = param.enumData();
      if (commandEnumData != null) {
        if (commandEnumData.isSoft()) {
          softEnum = true;
          index = softEnums.indexOf(commandEnumData);
        } else {
          enumData = true;
          index = enums.indexOf(commandEnumData);
        }
      } else {
        final var type = param.type();
        Preconditions.checkState(type != null, "No param type specified: %s", param);
        index = type.value(this);
      }
    }
    final var type = new CommandSymbolData(index, enumData, softEnum, postfix);
    buffer.writeIntLE(type.serialize());
    buffer.writeBoolean(param.optional());
  }
}
