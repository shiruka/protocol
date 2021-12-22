package io.github.shiruka.protocol;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.common.vectors.Vector2f;
import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.api.nbt.CompoundTag;
import io.github.shiruka.api.nbt.Tag;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.AuthoritativeMovementMode;
import io.github.shiruka.protocol.data.EduSharedUriResource;
import io.github.shiruka.protocol.data.ExperimentData;
import io.github.shiruka.protocol.data.GamePublishSetting;
import io.github.shiruka.protocol.data.GameRuleValue;
import io.github.shiruka.protocol.data.GameType;
import io.github.shiruka.protocol.data.PlayStatusStatus;
import io.github.shiruka.protocol.data.PlayerActionType;
import io.github.shiruka.protocol.data.PlayerBlockActionData;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.ResourcePackClientResponseStatus;
import io.github.shiruka.protocol.data.ResourcePackInfoEntry;
import io.github.shiruka.protocol.data.ResourcePackStackEntry;
import io.github.shiruka.protocol.data.SpawnBiomeType;
import io.github.shiruka.protocol.data.SyncedPlayerMovementSettings;
import io.github.shiruka.protocol.data.TextType;
import io.github.shiruka.protocol.data.command.CommandPermission;
import io.github.shiruka.protocol.data.entity.EntityData;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityDataType;
import io.github.shiruka.protocol.data.entity.EntityFlags;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import io.github.shiruka.protocol.data.entity.EntityLinkDataType;
import io.github.shiruka.protocol.data.inventory.InventoryActionData;
import io.github.shiruka.protocol.data.inventory.InventorySource;
import io.github.shiruka.protocol.data.inventory.ItemData;
import io.github.shiruka.protocol.data.inventory.ItemStackRequest;
import io.github.shiruka.protocol.data.inventory.ItemUseTransaction;
import io.github.shiruka.protocol.data.inventory.StackRequestSlotInfoData;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADBeaconPayment;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADConsume;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADCraftCreative;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADCraftRecipeOptional;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADCraftResultsDeprecated;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADCreate;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADDestroy;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADDrop;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADRecipe;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADSwap;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.SRADTransfer;
import io.github.shiruka.protocol.data.inventory.stackrequestactions.StackRequestActionData;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufUtil;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that delegates {@link PacketBuffer} and adds more helpful methods.
 */
@Log4j2
@RequiredArgsConstructor
public final class MinecraftPacketBuffer {

  /**
   * the buffer.
   */
  @NotNull
  @Delegate
  private final PacketBuffer buffer;

  /**
   * reads the array.
   *
   * @param array the array to read.
   * @param supplier the supplier to read.
   * @param <T> type of the array.
   */
  public <T> void readArray(@NotNull final Collection<T> array, @NotNull final Supplier<T> supplier) {
    final var length = this.readUnsignedInt();
    IntStream.iterate(0, i -> i < length, i -> i + 1)
      .mapToObj(i -> supplier.get())
      .forEach(array::add);
  }

  /**
   * reads the array shor le.
   *
   * @param array the array to read.
   * @param supplier the supplier to read.
   * @param <T> type of the array.
   */
  public <T> void readArrayShortLE(@NotNull final Collection<T> array, final Supplier<T> supplier) {
    final var length = this.readUnsignedShortLE();
    IntStream.range(0, length)
      .mapToObj(i -> supplier.get())
      .forEach(array::add);
  }

  /**
   * reads the attribute.
   *
   * @return attribute.
   */
  @NotNull
  public AttributeData readAttribute() {
    final var name = this.readString();
    final var min = this.readFloatLE();
    final var max = this.readFloatLE();
    final var val = this.readFloatLE();
    return new AttributeData(name, min, max, val);
  }

  /**
   * reads the command permission.
   *
   * @return command permission.
   */
  @NotNull
  public CommandPermission readCommandPermission() {
    return CommandPermission.byOrdinal(this.readUnsignedVarInt());
  }

  /**
   * reads the compound tag.
   *
   * @return compound tag
   */
  @SneakyThrows
  @NotNull
  public CompoundTag readCompoundTag() {
    try (final var reader = Tag.createNetworkReader(new ByteBufInputStream(this.buffer()))) {
      return reader.readCompoundTag();
    }
  }

  /**
   * reads the edu shared uri resource.
   *
   * @return edu shared uri resource.
   */
  @NotNull
  public EduSharedUriResource readEduSharedUriResource() {
    return new EduSharedUriResource(this.readString(), this.readString());
  }

  /**
   * reads the entity data.
   *
   * @param metadata the metadata to read.
   */
  public void readEntityData(@NotNull final EntityDataMap metadata) {
    final var length = this.readUnsignedVarInt();
    for (var i = 0; i < length; i++) {
      final var metadataInt = this.readUnsignedVarInt();
      final var entityData = Constants.ENTITY_DATA.get(metadataInt);
      var type = Constants.ENTITY_DATA_TYPES.get(this.readUnsignedVarInt());
      if (entityData != null && entityData.isFlags()) {
        Preconditions.checkArgument(type == EntityDataType.LONG, "Expected long value for flags, got %s", type.name());
        type = EntityDataType.FLAGS;
      }
      final Object object;
      switch (type) {
        case BYTE -> object = this.readByte();
        case SHORT -> object = this.readShortLE();
        case INT -> object = this.readVarInt();
        case FLOAT -> object = this.readFloatLE();
        case STRING -> object = this.readString();
        case NBT -> object = this.readCompoundTag();
        case VECTOR3I -> object = this.readVector3i();
        case FLAGS -> {
          final var index = entityData == EntityData.FLAGS_2 ? 1 : 0;
          metadata.getOrCreateFlags().set(this.readVarLong(), index, Constants.ENTITY_FLAGS);
          continue;
        }
        case LONG -> object = this.readVarLong();
        case VECTOR3F -> object = this.readVector3f();
        default -> throw new IllegalArgumentException("Unknown entity data type received");
      }
      if (entityData != null) {
        metadata.put(entityData, object);
      } else {
        MinecraftPacketBuffer.log.debug("Unknown entity data: {} type {} value {}", metadataInt, type, object);
      }
    }
  }

  /**
   * reads the entity link.
   *
   * @return entity link.
   */
  @NotNull
  public EntityLinkData readEntityLink() {
    return new EntityLinkData(
      this.readVarLong(),
      this.readVarLong(),
      EntityLinkDataType.byOrdinal(this.readUnsignedByte()),
      this.readBoolean(),
      this.readBoolean()
    );
  }

  /**
   * reads the expiriments.
   *
   * @param experiments the experiments to read.
   */
  public void readExperiments(@NotNull final List<ExperimentData> experiments) {
    final var count = this.readIntLE();
    IntStream.range(0, count)
      .mapToObj(i -> new ExperimentData(this.readString(), this.readBoolean()))
      .forEach(experiments::add);
  }

  /**
   * reads the game publish setting.
   *
   * @return game publish setting.
   */
  @NotNull
  public GamePublishSetting readGamePublishSetting() {
    return GamePublishSetting.byOrdinal(this.readVarInt());
  }

  /**
   * reads game rule.
   *
   * @return game rule.
   */
  @NotNull
  public GameRuleValue readGameRule() {
    final var name = this.readString();
    final var editable = this.readBoolean();
    final var type = this.readUnsignedVarInt();
    return switch (type) {
      case 1 -> new GameRuleValue(name, editable, this.readBoolean());
      case 2 -> new GameRuleValue(name, editable, this.readUnsignedIntLE());
      case 3 -> new GameRuleValue(name, editable, this.readFloatLE());
      default -> throw new IllegalStateException("Invalid game rule type received!");
    };
  }

  /**
   * reads the game type.
   *
   * @return game type.
   */
  @NotNull
  public GameType readGameType() {
    return GameType.byOrdinal(this.readVarInt());
  }

  /**
   * reads the item.
   *
   * @param session the session to read.
   *
   * @return item data.
   */
  @NotNull
  public ItemData readItem(final MinecraftChildChannel session) {
    final var id = this.readVarInt();
    if (id == 0) {
      return ItemData.AIR;
    }
    final var count = this.readUnsignedShortLE();
    final var damage = this.readUnsignedVarInt();
    final var hasNetId = this.readBoolean();
    var netId = 0;
    if (hasNetId) {
      netId = this.readVarInt();
    }
    final var blockRuntimeId = this.readVarInt();
    CompoundTag compoundTag = null;
    var blockingTicks = 0L;
    final String[] canPlace;
    final String[] canBreak;
    final var buf = this.readSlice();
    try (final var stream = Tag.createReaderLE(buf)) {
      final var nbtSize = stream.readShort().shortValue();
      if (nbtSize > 0) {
        compoundTag = stream.readCompoundTag();
      } else if (nbtSize == -1) {
        final var tagCount = stream.input().readUnsignedByte();
        Preconditions.checkArgument(tagCount == 1, "Expected 1 tag but got %s", tagCount);
        compoundTag = stream.readCompoundTag();
      }
      canPlace = new String[stream.readInt().intValue()];
      for (var i = 0; i < canPlace.length; i++) {
        canPlace[i] = stream.readString().value();
      }
      canBreak = new String[stream.readInt().intValue()];
      for (var i = 0; i < canBreak.length; i++) {
        canBreak[i] = stream.readString().value();
      }
      if (Constants.isBlockingItem(id, session)) {
        blockingTicks = stream.readLong().longValue();
      }
    } catch (final IOException e) {
      throw new IllegalStateException("Unable to read item user data", e);
    }
    if (buf.isReadable()) {
      MinecraftPacketBuffer.log.info("Item user data has {} readable bytes left\n{}",
        buf.readableBytes(), ByteBufUtil.prettyHexDump(buf.readerIndex(0)));
    }
    return ItemData.newBuilder()
      .id(id)
      .damage(damage)
      .count(count)
      .tag(compoundTag)
      .canPlace(canPlace)
      .canBreak(canBreak)
      .blockingTicks(blockingTicks)
      .blockRuntimeId(blockRuntimeId)
      .usingNetId(hasNetId)
      .netId(netId)
      .build();
  }

  /**
   * reads the play status status.
   *
   * @return play status status.
   */
  @NotNull
  public PlayStatusStatus readPlayStatusStatus() {
    return PlayStatusStatus.byOrdinal(this.readInt());
  }

  /**
   * reads the player permission.
   *
   * @return player permission.
   */
  @NotNull
  public PlayerPermission readPlayerPermission() {
    return PlayerPermission.byOrdinal(this.readVarInt());
  }

  /**
   * reads the resource pack client response status.
   *
   * @return resource pack client response status.
   */
  @NotNull
  public ResourcePackClientResponseStatus readResourcePackClientResponseStatus() {
    return ResourcePackClientResponseStatus.byOrdinal(this.readUnsignedByte());
  }

  /**
   * reads the entry.
   *
   * @return entry.
   */
  @NotNull
  public ResourcePackInfoEntry readResourcePackEntry() {
    final var packId = this.readString();
    final var packVersion = this.readString();
    final var packSize = this.readLongLE();
    final var contentKey = this.readString();
    final var subPackName = this.readString();
    final var contentId = this.readString();
    final var isScripting = this.readBoolean();
    return new ResourcePackInfoEntry(packId, packVersion, packSize, contentKey, subPackName, contentId, isScripting,
      false);
  }

  /**
   * reads the resource pack entry.
   *
   * @return entry.
   */
  @NotNull
  public ResourcePackInfoEntry readResourcePackEntryWithRaytracing() {
    final var entry = this.readResourcePackEntry();
    final var raytracingCapable = this.readBoolean();
    return entry.raytracingCapable(raytracingCapable);
  }

  /**
   * reads the entry.
   *
   * @return entry.
   */
  @NotNull
  public ResourcePackStackEntry readResourcePackStackEntry() {
    final var packId = this.readString();
    final var packVersion = this.readString();
    final var subPackName = this.readString();
    return new ResourcePackStackEntry(packId, packVersion, subPackName);
  }

  /**
   * reads the spawn biome type.
   *
   * @return spawn biome type.
   */
  @NotNull
  public SpawnBiomeType readSpawnBiomeType() {
    return SpawnBiomeType.byId(this.readShortLE());
  }

  /**
   * reads the synced player movement settings.
   *
   * @return synced player movement settings.
   */
  @NotNull
  public SyncedPlayerMovementSettings readSyncedPlayerMovementSettings() {
    return new SyncedPlayerMovementSettings(
      this.readBoolean(),
      AuthoritativeMovementMode.byOrdinal(this.readUnsignedVarInt()),
      this.readVarInt()
    );
  }

  /**
   * reads the text type.
   *
   * @return text type.
   */
  @NotNull
  public TextType readTextType() {
    return TextType.values()[this.readUnsignedByte()];
  }

  /**
   * reads the vector 2f.
   *
   * @return vector 2f.
   */
  @NotNull
  public Vector2f readVector2f() {
    final var x = this.readFloatLE();
    final var y = this.readFloatLE();
    return Vector2f.of(x, y);
  }

  /**
   * reads vector 3f.
   *
   * @return vector 3f.
   */
  @NotNull
  public Vector3f readVector3f() {
    final var x = this.readFloatLE();
    final var y = this.readFloatLE();
    final var z = this.readFloatLE();
    return Vector3f.of(x, y, z);
  }

  /**
   * reads the vector 3i.
   *
   * @return vector 3i.
   */
  @NotNull
  public Vector3i readVector3i() {
    final var x = this.readVarInt();
    final var y = this.readUnsignedVarInt();
    final var z = this.readVarInt();
    return Vector3i.of(x, y, z);
  }

  /**
   * writes the array.
   *
   * @param array the array to write.
   * @param consumer the consumer to write.
   * @param <T> type of the array.
   */
  public <T> void writeArray(@NotNull final Collection<T> array, @NotNull final Consumer<T> consumer) {
    this.writeUnsignedInt(array.size());
    array.forEach(consumer);
  }

  /**
   * writes the array.
   *
   * @param array the array to write.
   * @param consumer the consumer to write.
   * @param <T> type of the array.
   */
  public <T> void writeArray(@NotNull final T[] array, @NotNull final Consumer<T> consumer) {
    this.writeUnsignedInt(array.length);
    Arrays.stream(array).forEach(consumer);
  }

  /**
   * writes the array short le.
   *
   * @param array the array to write.
   * @param consumer the consumer to write.
   * @param <T> type of the array.
   */
  public <T> void writeArrayShortLE(@NotNull final Collection<T> array, @NotNull final Consumer<T> consumer) {
    this.writeShortLE(array.size());
    array.forEach(consumer);
  }

  /**
   * writes the attribute.
   *
   * @param attribute the attribute to write.
   */
  public void writeAttribute(@NotNull final AttributeData attribute) {
    this.writeString(attribute.name());
    this.writeFloatLE(attribute.minimum());
    this.writeFloatLE(attribute.maximum());
    this.writeFloatLE(attribute.value());
  }

  /**
   * writes the block position.
   *
   * @param blockPosition the block position to write.
   */
  public void writeBlockPosition(@NotNull final Vector3i blockPosition) {
    this.writeVarInt(blockPosition.x());
    this.writeUnsignedVarInt(blockPosition.y());
    this.writeVarInt(blockPosition.z());
  }

  /**
   * writes byte array.
   *
   * @param bytes the bytes to write.
   */
  public void writeByteArray(final byte[] bytes) {
    this.writeUnsignedVarInt(bytes.length);
    this.writeBytes(bytes);
  }

  /**
   * writes the compound tag.
   *
   * @param tag the tag to write.
   */
  @SneakyThrows
  public void writeCompoundTag(@NotNull final CompoundTag tag) {
    try (final var writer = Tag.createNetworkWriter(new ByteBufOutputStream(this.buffer()))) {
      writer.writeCompoundTag(tag);
    }
  }

  /**
   * writes the entity data.
   *
   * @param metadata the metadata to write.
   */
  public void writeEntityData(@NotNull final EntityDataMap metadata) {
    this.writeUnsignedVarInt(metadata.size());
    for (final var entry : metadata.entrySet()) {
      final var index = this.size();
      this.writeUnsignedVarInt(Constants.ENTITY_DATA.get(entry.getKey()));
      var object = entry.getValue();
      final var type = EntityDataType.byObject(object);
      this.writeUnsignedVarInt(Constants.ENTITY_DATA_TYPES.get(type));
      switch (type) {
        case BYTE:
          this.writeByte((byte) object);
          break;
        case SHORT:
          this.writeShortLE((short) object);
          break;
        case INT:
          this.writeVarInt((int) object);
          break;
        case FLOAT:
          this.writeFloatLE((float) object);
          break;
        case STRING:
          this.writeString((String) object);
          break;
        case NBT:
          CompoundTag tag;
          if (object instanceof CompoundTag) {
            tag = (CompoundTag) object;
          } else {
            final var item = (ItemData) object;
            tag = item.tag();
            if (tag == null) {
              tag = Tag.createCompound();
            }
          }
          this.writeCompoundTag(tag);
          break;
        case VECTOR3I:
          this.writeVector3i((Vector3i) object);
          break;
        case FLAGS:
          final var flagsIndex = entry.getKey() == EntityData.FLAGS_2 ? 1 : 0;
          object = ((EntityFlags) object).get(flagsIndex, Constants.ENTITY_FLAGS);
        case LONG:
          this.writeVarLong((long) object);
          break;
        case VECTOR3F:
          this.writeVector3f((Vector3f) object);
          break;
        default:
          this.buffer().writerIndex(index);
          break;
      }
    }
  }

  /**
   * writes the entity link.
   *
   * @param entityLink the entity link to write.
   */
  public void writeEntityLink(@NotNull final EntityLinkData entityLink) {
    this.writeVarLong(entityLink.from());
    this.writeVarLong(entityLink.to());
    this.writeByte(entityLink.type().ordinal());
    this.writeBoolean(entityLink.immediate());
    this.writeBoolean(entityLink.riderInitiated());
  }

  /**
   * writes the experiments.
   *
   * @param experiments the experiments to write.
   */
  public void writeExperiments(@NotNull final List<ExperimentData> experiments) {
    this.writeIntLE(experiments.size());
    for (final var experiment : experiments) {
      this.writeString(experiment.name());
      this.writeBoolean(experiment.enabled());
    }
  }

  /**
   * writes the game publish setting.
   *
   * @param setting the setting to write.
   */
  public void writeGamePublishSetting(@NotNull final GamePublishSetting setting) {
    this.writeVarInt(setting.ordinal());
  }

  /**
   * writes the game rule value.
   *
   * @param gameRule the game rule to write.
   */
  public void writeGameRule(@NotNull final GameRuleValue gameRule) {
    final var value = gameRule.value();
    this.writeString(gameRule.name());
    this.writeBoolean(gameRule.editable());
    final var type = Constants.GAME_RULE_TYPES.getInt(value.getClass());
    this.writeUnsignedVarInt(type);
    switch (type) {
      case 1 -> this.writeBoolean((boolean) value);
      case 2 -> this.writeUnsignedVarInt((int) value);
      case 3 -> this.writeFloatLE((float) value);
      default -> throw new IllegalStateException("Invalid game rule type received!");
    }
  }

  /**
   * writes the game type.
   *
   * @param type the type to write.
   */
  public void writeGameType(@NotNull final GameType type) {
    this.writeVarInt(type.ordinal());
  }

  /**
   * writes the inventory actions.
   *
   * @param session the session to write.
   * @param actions the actions to write.
   * @param hasNetworkIds the has network ids to write.
   */
  public void writeInventoryActions(@NotNull final MinecraftChildChannel session,
                                    @NotNull final List<InventoryActionData> actions,
                                    final boolean hasNetworkIds) {
    this.writeArray(actions, action -> {
      this.writeSource(action.source());
      this.writeUnsignedVarInt(action.slot());
      this.writeItem(action.fromItem(), session);
      this.writeItem(action.toItem(), session);
    });
  }

  /**
   * writes the item.
   *
   * @param item the item to write.
   * @param session the session to write.
   */
  public void writeItem(@NotNull final ItemData item, @NotNull final MinecraftChildChannel session) {
    final var id = item.id();
    if (id == 0) {
      this.writeByte(0);
      return;
    }
    this.writeVarInt(id);
    this.writeShortLE(item.count());
    this.writeUnsignedVarInt(item.damage());
    this.writeBoolean(item.usingNetId());
    if (item.usingNetId()) {
      this.writeVarInt(item.netId());
    }
    this.writeVarInt(item.blockRuntimeId());
    final var userDataBuf = ByteBufAllocator.DEFAULT.ioBuffer();
    try (final var stream = Tag.createWriterLE(userDataBuf)) {
      final var tag = item.tag();
      if (tag != null) {
        stream.writeShort(Tag.createShort(-1));
        stream.writeByte(Tag.createByte(1));
        stream.writeCompoundTag(tag);
      } else {
        userDataBuf.writeShortLE(0);
      }
      final var canPlace = item.canPlace();
      stream.writeInt(Tag.createInt(canPlace.length));
      for (final var aCanPlace : canPlace) {
        stream.writeString(Tag.createString(aCanPlace));
      }
      final var canBreak = item.canBreak();
      stream.writeInt(Tag.createInt(canBreak.length));
      for (final var aCanBreak : canBreak) {
        stream.writeString(Tag.createString(aCanBreak));
      }
      if (Constants.isBlockingItem(id, session)) {
        stream.writeLong(Tag.createLong(item.blockingTicks()));
      }
      this.writeUnsignedVarInt(userDataBuf.readableBytes());
      this.buffer().writeBytes(userDataBuf);
    } catch (final IOException e) {
      throw new IllegalStateException("Unable to write item user data", e);
    } finally {
      userDataBuf.release();
    }
  }

  /**
   * writes item stack request.
   *
   * @param session the session to write.
   * @param request the request to write.
   */
  public void writeItemStackRequest(@NotNull final MinecraftChildChannel session,
                                    @NotNull final ItemStackRequest request) {
    this.writeVarInt(request.requestId());
    this.writeArray(request.actions(), action -> {
      final var type = action.type();
      this.writeByte(type.id());
      this.writeRequestActionData(session, action);
    });
    this.writeArray(request.filterStrings(), this::writeString);
  }

  /**
   * writes the item use transaction.
   *
   * @param session the session to write.
   * @param transaction the transaction to write.
   */
  public void writeItemUseTransaction(@NotNull final MinecraftChildChannel session,
                                      @NotNull final ItemUseTransaction transaction) {
    final var legacyRequestId = transaction.legacyRequestId();
    this.writeVarInt(legacyRequestId);
    if (legacyRequestId < -1 && (legacyRequestId & 1) == 0) {
      this.writeArray(transaction.legacySlots(), element -> {
        this.writeByte(element.containerId());
        this.writeByteArray(element.slots());
      });
    }
    this.writeInventoryActions(session, transaction.actions(), transaction.usingNetIds());
    this.writeUnsignedVarInt(transaction.actionType());
    this.writeBlockPosition(transaction.blockPosition());
    this.writeVarInt(transaction.blockFace());
    this.writeVarInt(transaction.hotbarSlot());
    this.writeItem(transaction.itemInHand(), session);
    this.writeVector3f(transaction.playerPosition());
    this.writeVector3f(transaction.clickPosition());
    this.writeUnsignedVarInt(transaction.blockRuntimeId());
  }

  /**
   * writes the play status status.
   *
   * @param status the status to write.
   */
  public void writePlayStatusStatus(@NotNull final PlayStatusStatus status) {
    this.writeInt(status.ordinal());
  }

  /**
   * writes the player block action data.
   *
   * @param data the data to write.
   */
  public void writePlayerBlockActionData(@NotNull final PlayerBlockActionData data) {
    final var action = data.action();
    final var face = data.face();
    final var blockPosition = data.blockPosition();
    this.writeVarInt(action.ordinal());
    switch (action) {
      case START_BREAK, ABORT_BREAK, CONTINUE_BREAK, BLOCK_PREDICT_DESTROY, BLOCK_CONTINUE_DESTROY -> {
        this.writeVector3i(blockPosition);
        this.writeVarInt(face);
      }
    }
    if (action == PlayerActionType.STOP_BREAK) {
      this.writeVector3i(blockPosition);
      this.writeVarInt(face);
    }
  }

  /**
   * writes the player permission.
   *
   * @param permission the permission to write.
   */
  public void writePlayerPermission(@NotNull final PlayerPermission permission) {
    this.writeVarInt(permission.ordinal());
  }

  /**
   * writes the resource pack client response status.
   *
   * @param status the status to write.
   */
  public void writeResourcePackClientResponseStatus(@NotNull final ResourcePackClientResponseStatus status) {
    this.writeByte(status.ordinal());
  }

  /**
   * writes the resource pack entry.
   *
   * @param entry the entry to write.
   */
  public void writeResourcePackEntry(@NotNull final ResourcePackInfoEntry entry) {
    this.writeString(entry.packId());
    this.writeString(entry.packVersion());
    this.writeLongLE(entry.packSize());
    this.writeString(entry.contentKey());
    this.writeString(entry.subPackName());
    this.writeString(entry.contentId());
    this.writeBoolean(entry.scripting());
  }

  /**
   * writes the resource pack entry.
   *
   * @param entry the entry to write.
   */
  public void writeResourcePackEntryWithRaytracing(@NotNull final ResourcePackInfoEntry entry) {
    this.writeResourcePackEntry(entry);
    this.writeBoolean(entry.raytracingCapable());
  }

  /**
   * writes the entry.
   *
   * @param entry the entry to write.
   */
  public void writeResourcePackStackEntry(@NotNull final ResourcePackStackEntry entry) {
    this.writeString(entry.packId());
    this.writeString(entry.packVersion());
    this.writeString(entry.subPackName());
  }

  /**
   * writes the inventory source.
   *
   * @param inventorySource the inventory source to write.
   */
  public void writeSource(@NotNull final InventorySource inventorySource) {
    final var type = inventorySource.type();
    this.writeUnsignedVarInt(type.id());
    switch (type) {
      case CONTAINER, UNTRACKED_INTERACTION_UI, NON_IMPLEMENTED_TODO -> this.writeVarInt(inventorySource.containerId());
      case WORLD_INTERACTION -> this.writeUnsignedVarInt(inventorySource.flag().ordinal());
    }
  }

  /**
   * writes the spawn biome type.
   *
   * @param type the type to write.
   */
  public void writeSpawnBiomeType(@NotNull final SpawnBiomeType type) {
    this.writeShortLE(type.ordinal());
  }

  /**
   * writes the synced player movement settings.
   *
   * @param playerMovementSettings the player movement settings to write.
   */
  public void writeSyncedPlayerMovementSettings(@NotNull final SyncedPlayerMovementSettings playerMovementSettings) {
    this.writeUnsignedVarInt(playerMovementSettings.movementMode().ordinal());
    this.writeVarInt(playerMovementSettings.rewindHistorySize());
    this.writeBoolean(playerMovementSettings.serverAuthoritativeBlockBreaking());
  }

  /**
   * writes the text type.
   *
   * @param type the type to write.
   */
  public void writeTextType(@NotNull final TextType type) {
    this.writeByte(type.ordinal());
  }

  /**
   * writes the vector 2f.
   *
   * @param vector the vector to write.
   */
  public void writeVector2f(@NotNull final Vector2f vector) {
    this.writeFloatLE(vector.x());
    this.writeFloatLE(vector.y());
  }

  /**
   * writes the vector 3f.
   *
   * @param vector the vector to write.
   */
  public void writeVector3f(@NotNull final Vector3f vector) {
    this.writeFloatLE(vector.x());
    this.writeFloatLE(vector.y());
    this.writeFloatLE(vector.z());
  }

  /**
   * writes the vector 31.
   *
   * @param vector the vector to write.
   */
  public void writeVector3i(@NotNull final Vector3i vector) {
    this.writeVarInt(vector.x());
    this.writeUnsignedVarInt(vector.y());
    this.writeVarInt(vector.z());
  }

  /**
   * writes the request action data.
   *
   * @param session the session to write.
   * @param action the action to write.
   */
  private void writeRequestActionData(@NotNull final MinecraftChildChannel session,
                                      @NotNull final StackRequestActionData action) {
    final var type = action.type();
    switch (type) {
      case TAKE:
      case PLACE:
        this.writeByte(((SRADTransfer) action).count());
        this.writeStackRequestSlotInfo(((SRADTransfer) action).source());
        this.writeStackRequestSlotInfo(((SRADTransfer) action).destination());
        break;
      case SWAP:
        this.writeStackRequestSlotInfo(((SRADSwap) action).source());
        this.writeStackRequestSlotInfo(((SRADSwap) action).destination());
        break;
      case DROP:
        this.writeByte(((SRADDrop) action).count());
        this.writeStackRequestSlotInfo(((SRADDrop) action).source());
        this.writeBoolean(((SRADDrop) action).randomly());
        break;
      case DESTROY:
        this.writeByte(((SRADDestroy) action).count());
        this.writeStackRequestSlotInfo(((SRADDestroy) action).source());
        break;
      case CONSUME:
        this.writeByte(((SRADConsume) action).count());
        this.writeStackRequestSlotInfo(((SRADConsume) action).source());
        break;
      case CREATE:
        this.writeByte(((SRADCreate) action).slot());
        break;
      case BEACON_PAYMENT:
        this.writeVarInt(((SRADBeaconPayment) action).primaryEffect());
        this.writeVarInt(((SRADBeaconPayment) action).secondaryEffect());
        break;
      case CRAFT_RECIPE:
      case CRAFT_RECIPE_AUTO:
        this.writeUnsignedVarInt(((SRADRecipe) action).recipeNetworkId());
        break;
      case CRAFT_CREATIVE:
        this.writeUnsignedVarInt(((SRADCraftCreative) action).creativeItemNetworkId());
        break;
      case CRAFT_RECIPE_OPTIONAL:
        this.writeUnsignedVarInt(((SRADCraftRecipeOptional) action).recipeNetworkId());
        this.writeIntLE(((SRADCraftRecipeOptional) action).filteredStringIndex());
        break;
      case CRAFT_RESULTS_DEPRECATED:
        this.writeArray(((SRADCraftResultsDeprecated) action).resultItems(), item -> this.writeItem(item, session));
        this.writeByte(((SRADCraftResultsDeprecated) action).timesCrafted());
        break;
      case LAB_TABLE_COMBINE:
      case CRAFT_NON_IMPLEMENTED_DEPRECATED:
        break;
      default:
        throw new UnsupportedOperationException("Unhandled stack request action type: " + type);
    }
  }

  /**
   * writes stack request slot info.
   *
   * @param data the data to write.
   */
  private void writeStackRequestSlotInfo(@NotNull final StackRequestSlotInfoData data) {
    this.writeByte(data.container().ordinal());
    this.writeByte(data.slot());
    this.writeVarInt(data.stackNetworkId());
  }
}
