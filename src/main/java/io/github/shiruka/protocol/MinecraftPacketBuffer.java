package io.github.shiruka.protocol;

import io.github.shiruka.api.math.vectors.Vector2f;
import io.github.shiruka.api.math.vectors.Vector3f;
import io.github.shiruka.api.math.vectors.Vector3i;
import io.github.shiruka.api.nbt.CompoundTag;
import io.github.shiruka.api.nbt.Tag;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.data.AuthoritativeMovementMode;
import io.github.shiruka.protocol.data.EduSharedUriResource;
import io.github.shiruka.protocol.data.ExperimentData;
import io.github.shiruka.protocol.data.GamePublishSetting;
import io.github.shiruka.protocol.data.GameRuleValue;
import io.github.shiruka.protocol.data.GameType;
import io.github.shiruka.protocol.data.PlayStatusStatus;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.ResourcePackClientResponseStatus;
import io.github.shiruka.protocol.data.ResourcePackInfoEntry;
import io.github.shiruka.protocol.data.ResourcePackStackEntry;
import io.github.shiruka.protocol.data.SpawnBiomeType;
import io.github.shiruka.protocol.data.SyncedPlayerMovementSettings;
import io.github.shiruka.protocol.data.TextType;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that delegates {@link PacketBuffer} and adds more helpful methods.
 */
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
   * reads the compound tag.
   *
   * @return compound tag
   */
  @SneakyThrows
  @NotNull
  public CompoundTag readCompoundTag() {
    try (final var reader = Tag.createNetworkReader(new ByteBufInputStream(this.buffer.buffer()))) {
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
    switch (type) {
      case 1:
        return new GameRuleValue(name, editable, this.readBoolean());
      case 2:
        return new GameRuleValue(name, editable, this.readUnsignedIntLE());
      case 3:
        return new GameRuleValue(name, editable, this.readFloatLE());
    }
    throw new IllegalStateException("Invalid game rule type received!");
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
    return new ResourcePackInfoEntry(packId, packVersion, packSize, contentKey, subPackName, contentId,
      isScripting, false);
  }

  /**
   * reads the resource pack entry.
   *
   * @return entry.
   */
  @NotNull
  public ResourcePackInfoEntry readResourcePackEntryWithRaytracing() {
    final var packId = this.readString();
    final var packVersion = this.readString();
    final var packSize = this.readLongLE();
    final var contentKey = this.readString();
    final var subPackName = this.readString();
    final var contentId = this.readString();
    final var isScripting = this.readBoolean();
    final var raytracingCapable = this.readBoolean();
    return new ResourcePackInfoEntry(packId, packVersion, packSize, contentKey, subPackName, contentId,
      isScripting, raytracingCapable);
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
  public <T> void writeArray(@NotNull final Collection<T> array,
                             @NotNull final Consumer<T> consumer) {
    this.writeUnsignedInt(array.size());
    array.forEach(consumer);
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
   * writes the compound tag.
   *
   * @param tag the tag to write.
   */
  @SneakyThrows
  public void writeCompoundTag(@NotNull final CompoundTag tag) {
    try (final var writer = Tag.createNetworkWriter(new ByteBufOutputStream(this.buffer.buffer()))) {
      writer.writeCompoundTag(tag);
    }
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
    final var type = Constants.GAME_RULE_TYPES.getInt(value.getClass());
    this.writeString(gameRule.name());
    this.writeBoolean(gameRule.editable());
    this.writeUnsignedVarInt(type);
    switch (type) {
      case 1 -> this.writeBoolean((boolean) value);
      case 2 -> this.writeUnsignedVarInt((int) value);
      case 3 -> this.writeFloatLE((float) value);
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
   * writes the play status status.
   *
   * @param status the status to write.
   */
  public void writePlayStatusStatus(@NotNull final PlayStatusStatus status) {
    this.writeInt(status.ordinal());
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
   * writes the spawn biome type.
   *
   * @param type the type to write.
   */
  public void writeSpawnBiomeType(@NotNull final SpawnBiomeType type) {
    this.buffer.writeShortLE(type.ordinal());
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
}
