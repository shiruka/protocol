package io.github.shiruka.protocol.codec.v291;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.api.nbt.CompoundTag;
import io.github.shiruka.api.nbt.Tag;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.IdentifierDefinitionRegistry;
import io.github.shiruka.protocol.codec.IntTypeMap;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.ItemDefinition;
import io.github.shiruka.protocol.data.command.CommandEnumData;
import io.github.shiruka.protocol.data.command.CommandParam;
import io.github.shiruka.protocol.data.entity.EntityData;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityDataType;
import io.github.shiruka.protocol.data.entity.EntityFlag;
import io.github.shiruka.protocol.data.entity.EntityFlags;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import io.github.shiruka.protocol.data.entity.EntityLinkDataType;
import io.github.shiruka.protocol.data.inventory.ItemData;
import io.netty.buffer.ByteBufInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
   * teh command parameters.
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
  @NotNull
  protected final IdentifierDefinitionRegistry<ItemDefinition> itemDefinitions =
    IdentifierDefinitionRegistry.<ItemDefinition>newBuilder()
      .build();

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
    final var canPlace = new ArrayList<String>();
    final var canBreak = new ArrayList<String>();
    buffer.readArray(canPlace, PacketBuffer::readString);
    buffer.readArray(canBreak, PacketBuffer::readString);
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
  public void writeAttribute(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session,
                             @NotNull final AttributeData data) {
    buffer.writeString(data.name());
    buffer.writeFloatLE(data.minimum());
    buffer.writeFloatLE(data.maximum());
    buffer.writeFloatLE(data.value());
  }

  @Override
  public void writeCommandEnum(@NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session,
                               @NotNull final CommandEnumData data) {
    buffer.writeString(data.name());
    final var values = data.values();
    buffer.writeUnsignedVarInt(values.length);
    for (final var value : values) {
      buffer.writeString(value);
    }
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
    buffer.writeArray(item.canPlace(), PacketBuffer::writeString);
    buffer.writeArray(item.canBreak(), PacketBuffer::writeString);
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
}
