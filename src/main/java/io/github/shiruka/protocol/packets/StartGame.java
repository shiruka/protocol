package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector2f;
import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.api.nbt.ListTag;
import io.github.shiruka.protocol.Constants;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.BlockPropertyData;
import io.github.shiruka.protocol.data.EduSharedUriResource;
import io.github.shiruka.protocol.data.ExperimentData;
import io.github.shiruka.protocol.data.GamePublishSetting;
import io.github.shiruka.protocol.data.GameRuleValue;
import io.github.shiruka.protocol.data.GameType;
import io.github.shiruka.protocol.data.ItemEntry;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.SpawnBiomeType;
import io.github.shiruka.protocol.data.SyncedPlayerMovementSettings;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents play status packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class StartGame extends MinecraftPacket {

  /**
   * the block properties.
   */
  private final List<BlockPropertyData> blockProperties = new ObjectArrayList<>();

  /**
   * the experiments.
   */
  private final List<ExperimentData> experiments = new ObjectArrayList<>();

  /**
   * the game rules.
   */
  private final List<GameRuleValue> gameRules = new ObjectArrayList<>();

  /**
   * achievement disabled.
   */
  @Getter
  private boolean achievementsDisabled;

  /**
   * the behavior pack locked.
   */
  @Getter
  private boolean behaviorPackLocked;

  /**
   * the block palette.
   */
  @Nullable
  private ListTag blockPalette;

  /**
   * the bonus chest enabled.
   */
  @Getter
  private boolean bonusChestEnabled;

  /**
   * the broadcasting to lon.
   */
  @Getter
  private boolean broadcastingToLan;

  /**
   * teh commands enabled.
   */
  @Getter
  private boolean commandsEnabled;

  /**
   * the current tick.
   */
  @Getter
  private long currentTick;

  /**
   * the custom biome name.
   */
  @NotNull
  @Getter
  private String customBiomeName = "";

  /**
   * the day cycle stop time.
   */
  @Getter
  private int dayCycleStopTime;

  /**
   * the default player permission.
   */
  @Nullable
  private PlayerPermission defaultPlayerPermission;

  /**
   * the default spawn.
   */
  @Nullable
  private Vector3i defaultSpawn;

  /**
   * the difficulty.
   */
  @Getter
  private int difficulty;

  /**
   * the dimension id.
   */
  @Getter
  private int dimensionId;

  /**
   * the edu edition offers.
   */
  @Getter
  private int eduEditionOffers;

  /**
   * the edu features enabled.
   */
  @Getter
  private boolean eduFeaturesEnabled;

  /**
   * the edu shared uri resource.
   */
  @NotNull
  @Getter
  private EduSharedUriResource eduSharedUriResource = EduSharedUriResource.EMPTY;

  /**
   * the education production id.
   */
  @NotNull
  @Getter
  private String educationProductionId = "";

  /**
   * the enchantment seed.
   */
  @Getter
  private int enchantmentSeed;

  /**
   * the experiments previously toggled.
   */
  @Getter
  private boolean experimentsPreviouslyToggled;

  /**
   * the force experimental gameplay.
   */
  @Getter
  private boolean forceExperimentalGameplay;

  /**
   * the from locked world template.
   */
  @Getter
  private boolean fromLockedWorldTemplate;

  /**
   * the from world template.
   */
  @Getter
  private boolean fromWorldTemplate;

  /**
   * the generator id.
   */
  @Getter
  private int generatorId;

  /**
   * the inventories server authoritative.
   */
  @Getter
  private boolean inventoriesServerAuthoritative;

  /**
   * the item entries.
   */
  @NotNull
  private List<ItemEntry> itemEntries = new ObjectArrayList<>();

  /**
   * the level game type.
   */
  @Nullable
  private GameType levelGameType;

  /**
   * the level id.
   */
  @Nullable
  private String levelId;

  /***
   * the level name.
   */
  @Nullable
  private String levelName;

  /**
   * the lightning level.
   */
  @Getter
  private float lightningLevel;

  /**
   * the limited world height.
   */
  @Getter
  private int limitedWorldHeight;

  /**
   * the limited world width.
   */
  @Getter
  private int limitedWorldWidth;

  /**
   * the multiplayer correlation id.
   */
  @Nullable
  private String multiplayerCorrelationId;

  /**
   * the multiplayer game.
   */
  @Getter
  private boolean multiplayerGame;

  /**
   * the nether type.
   */
  @Getter
  private boolean netherType;

  /**
   * the only spawning v1 villagers.
   */
  @Getter
  private boolean onlySpawningV1Villagers;

  /**
   * the platform broadcast mode.
   */
  @Nullable
  private GamePublishSetting platformBroadcastMode;

  /**
   * the platform locked content confirmed.
   */
  @Getter
  private boolean platformLockedContentConfirmed;

  /**
   * the player game type.
   */
  @Nullable
  private GameType playerGameType;

  /**
   * the player movement settings.
   */
  @Nullable
  private SyncedPlayerMovementSettings playerMovementSettings;

  /**
   * the player position.
   */
  @Nullable
  private Vector3f playerPosition;

  /**
   * the premium world template id.
   */
  @Nullable
  private String premiumWorldTemplateId;

  /**
   * the rain level.
   */
  @Getter
  private float rainLevel;

  /**
   * the resource pack locked.
   */
  @Getter
  private boolean resourcePackLocked;

  /**
   * the rotation.
   */
  @Nullable
  private Vector2f rotation;

  /**
   * the runtime entity id.
   */
  @Getter
  private long runtimeEntityId;

  /**
   * the seed.
   */
  @Getter
  private int seed;

  /**
   * the server chunk tick range.
   */
  @Getter
  private int serverChunkTickRange;

  /**
   * the server engine.
   */
  @Nullable
  private String serverEngine;

  /**
   * the spawn biome type.
   */
  @NotNull
  @Getter
  private SpawnBiomeType spawnBiomeType = SpawnBiomeType.DEFAULT;

  /**
   * the starting with map.
   */
  @Getter
  private boolean startingWithMap;

  /**
   * the texture packs required.
   */
  @Getter
  private boolean texturePacksRequired;

  /**
   * the trial.
   */
  @Getter
  private boolean trial;

  /**
   * the trusting players.
   */
  @Getter
  private boolean trustingPlayers;

  /**
   * the unique entity id.
   */
  @Getter
  private long uniqueEntityId;

  /**
   * the using msa gamer tags only.
   */
  @Getter
  private boolean usingMsaGamerTagsOnly;

  /**
   * the vanilla version.
   */
  @Nullable
  private String vanillaVersion;

  /**
   * the world template option locked.
   */
  @Getter
  private boolean worldTemplateOptionLocked;

  /**
   * the xbl broadcast mode.
   */
  @Nullable
  private GamePublishSetting xblBroadcastMode;

  /**
   * obtains the block palette.
   *
   * @return block palette.
   */
  @NotNull
  public ListTag blockPalette() {
    return Objects.requireNonNull(this.blockPalette, "block palette");
  }

  /**
   * obtains the block properties.
   *
   * @return block properties.
   */
  @NotNull
  public List<BlockPropertyData> blockProperties() {
    return Collections.unmodifiableList(this.blockProperties);
  }

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    this.uniqueEntityId = buffer.readVarLong();
    this.runtimeEntityId = buffer.readUnsignedVarLong();
    this.playerGameType = buffer.readGameType();
    this.playerPosition = buffer.readVector3f();
    this.rotation = buffer.readVector2f();
    this.readLevelSettings(buffer);
    this.levelId = buffer.readString();
    this.levelName = buffer.readString();
    this.premiumWorldTemplateId = buffer.readString();
    this.trial = buffer.readBoolean();
    this.playerMovementSettings = buffer.readSyncedPlayerMovementSettings();
    this.currentTick = buffer.readLongLE();
    this.enchantmentSeed = buffer.readVarInt();
    buffer.readArray(this.blockProperties, () -> {
      final var name = buffer.readString();
      final var properties = buffer.readCompoundTag();
      return new BlockPropertyData(name, properties);
    });
    buffer.readArray(this.itemEntries, () -> {
      final var identifier = buffer.readString();
      final var id = buffer.readShortLE();
      final var componentBased = buffer.readBoolean();
      if (identifier.equals(Constants.BLOCKING_ITEM_IDENTIFIER)) {
        session.dynamicBlockingId().set(id);
      }
      return new ItemEntry(identifier, id, componentBased);
    });
    this.multiplayerCorrelationId = buffer.readString();
    this.inventoriesServerAuthoritative = buffer.readBoolean();
    this.serverEngine = buffer.readString();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeVarLong(this.uniqueEntityId);
    buffer.writeUnsignedVarLong(this.runtimeEntityId);
    buffer.writeGameType(this.playerGameType());
    buffer.writeVector3f(this.playerPosition());
    buffer.writeVector2f(this.rotation());
    this.writeLevelSettings(buffer);
    buffer.writeString(this.levelId());
    buffer.writeString(this.levelName());
    buffer.writeString(this.premiumWorldTemplateId());
    buffer.writeBoolean(this.trial);
    buffer.writeSyncedPlayerMovementSettings(this.playerMovementSettings());
    buffer.writeLongLE(this.currentTick);
    buffer.writeVarInt(this.enchantmentSeed);
    buffer.writeArray(this.blockProperties, block -> {
      buffer.writeString(block.name());
      buffer.writeCompoundTag(block.properties());
    });
    buffer.writeArray(this.itemEntries, entry -> {
      buffer.writeString(entry.identifier());
      buffer.writeShortLE(entry.id());
      buffer.writeBoolean(entry.componentBased());
    });
    buffer.writeString(this.multiplayerCorrelationId());
    buffer.writeBoolean(this.inventoriesServerAuthoritative);
    buffer.writeString(this.serverEngine());
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the default player permission.
   *
   * @return default player permission.
   */
  @NotNull
  public PlayerPermission defaultPlayerPermission() {
    return Objects.requireNonNull(this.defaultPlayerPermission, "default player permission");
  }

  /**
   * obtains the default spawn.
   *
   * @return default spawn.
   */
  @NotNull
  public Vector3i defaultSpawn() {
    return Objects.requireNonNull(this.defaultSpawn, "default spawn");
  }

  /**
   * obtains the experiments.
   *
   * @return experiments.
   */
  @NotNull
  public List<ExperimentData> experiments() {
    return Collections.unmodifiableList(this.experiments);
  }

  /**
   * obtains the game rules.
   *
   * @return game rules.
   */
  @NotNull
  public List<GameRuleValue> gameRules() {
    return Collections.unmodifiableList(this.gameRules);
  }

  /**
   * obtains the item entries.
   *
   * @return item entries.
   */
  @NotNull
  public List<ItemEntry> itemEntries() {
    return Collections.unmodifiableList(this.itemEntries);
  }

  /**
   * obtains the level game type.
   *
   * @return level game type.
   */
  @NotNull
  public GameType levelGameType() {
    return Objects.requireNonNull(this.levelGameType, "level game type");
  }

  /**
   * obtains the level id.
   *
   * @return level id.
   */
  @NotNull
  public String levelId() {
    return Objects.requireNonNull(this.levelId, "level id");
  }

  /**
   * obtains the level name.
   *
   * @return level name.
   */
  @NotNull
  public String levelName() {
    return Objects.requireNonNull(this.levelName, "level name");
  }

  /**
   * obtains the multiplayer correlation id.
   *
   * @return multiplayer correlation id.
   */
  @NotNull
  public String multiplayerCorrelationId() {
    return Objects.requireNonNull(this.multiplayerCorrelationId, "multiplayer correlation id");
  }

  /**
   * obtains the platform broadcast mode.
   *
   * @return platform broadcast mode.
   */
  @NotNull
  public GamePublishSetting platformBroadcastMode() {
    return Objects.requireNonNull(this.platformBroadcastMode, "platform broadcast mode");
  }

  /**
   * obtains the game type.
   *
   * @return game type.
   */
  @NotNull
  public GameType playerGameType() {
    return Objects.requireNonNull(this.playerGameType, "player game type.");
  }

  /**
   * obtains the player movement settings.
   *
   * @return player movement settings.
   */
  @NotNull
  public SyncedPlayerMovementSettings playerMovementSettings() {
    return Objects.requireNonNull(this.playerMovementSettings, "player movement settings");
  }

  /**
   * obtains the player position.
   *
   * @return player position.
   */
  @NotNull
  public Vector3f playerPosition() {
    return Objects.requireNonNull(this.playerPosition, "player position");
  }

  /**
   * obtains the premium world template id.
   *
   * @return premium world template id.
   */
  @NotNull
  public String premiumWorldTemplateId() {
    return Objects.requireNonNull(this.premiumWorldTemplateId, "premium world template id");
  }

  /**
   * obtains the rotation.
   *
   * @return rotation.
   */
  @NotNull
  public Vector2f rotation() {
    return Objects.requireNonNull(this.rotation, "rotation");
  }

  /**
   * obtains the server engine.
   *
   * @return server engine.
   */
  @NotNull
  public String serverEngine() {
    return Objects.requireNonNull(this.serverEngine, "server engine");
  }

  /**
   * obtains the vanilla version.
   *
   * @return vanilla version.
   */
  @NotNull
  public String vanillaVersion() {
    return Objects.requireNonNull(this.vanillaVersion, "vanilla version");
  }

  /**
   * obtains the xbl broadcast mode.
   *
   * @return xbl broadcast mode.
   */
  @NotNull
  public GamePublishSetting xblBroadcastMode() {
    return Objects.requireNonNull(this.xblBroadcastMode, "xbl broadcast mode");
  }

  /**
   * reads the level settings.
   *
   * @param buffer the buffer to read.
   */
  private void readLevelSettings(@NotNull final MinecraftPacketBuffer buffer) {
    this.seed = buffer.readVarInt();
    this.spawnBiomeType = buffer.readSpawnBiomeType();
    this.customBiomeName = buffer.readString();
    this.dimensionId = buffer.readVarInt();
    this.generatorId = buffer.readVarInt();
    this.levelGameType = buffer.readGameType();
    this.difficulty = buffer.readVarInt();
    this.defaultSpawn = buffer.readVector3i();
    this.achievementsDisabled = buffer.readBoolean();
    this.dayCycleStopTime = buffer.readVarInt();
    this.eduEditionOffers = buffer.readVarInt();
    this.eduFeaturesEnabled = buffer.readBoolean();
    this.educationProductionId = buffer.readString();
    this.rainLevel = buffer.readFloatLE();
    this.lightningLevel = buffer.readFloatLE();
    this.platformLockedContentConfirmed = buffer.readBoolean();
    this.multiplayerGame = buffer.readBoolean();
    this.broadcastingToLan = buffer.readBoolean();
    this.xblBroadcastMode = buffer.readGamePublishSetting();
    this.platformBroadcastMode = buffer.readGamePublishSetting();
    this.commandsEnabled = buffer.readBoolean();
    this.texturePacksRequired = buffer.readBoolean();
    buffer.readArray(this.gameRules, buffer::readGameRule);
    buffer.readExperiments(this.experiments);
    this.experimentsPreviouslyToggled = buffer.readBoolean();
    this.bonusChestEnabled = buffer.readBoolean();
    this.startingWithMap = buffer.readBoolean();
    this.defaultPlayerPermission = buffer.readPlayerPermission();
    this.serverChunkTickRange = buffer.readIntLE();
    this.behaviorPackLocked = buffer.readBoolean();
    this.resourcePackLocked = buffer.readBoolean();
    this.fromLockedWorldTemplate = buffer.readBoolean();
    this.usingMsaGamerTagsOnly = buffer.readBoolean();
    this.fromWorldTemplate = buffer.readBoolean();
    this.worldTemplateOptionLocked = buffer.readBoolean();
    this.onlySpawningV1Villagers = buffer.readBoolean();
    this.vanillaVersion = buffer.readString();
    this.limitedWorldWidth = buffer.readIntLE();
    this.limitedWorldHeight = buffer.readIntLE();
    this.netherType = buffer.readBoolean();
    this.eduSharedUriResource = buffer.readEduSharedUriResource();
    if (buffer.readBoolean()) {
      this.forceExperimentalGameplay = buffer.readBoolean();
    }
  }

  /**
   * writes the level settings.
   *
   * @param buffer the buffer to write.
   */
  private void writeLevelSettings(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeVarInt(this.seed);
    buffer.writeSpawnBiomeType(this.spawnBiomeType());
    buffer.writeString(this.customBiomeName());
    buffer.writeVarInt(this.dimensionId);
    buffer.writeVarInt(this.generatorId);
    buffer.writeGameType(this.levelGameType());
    buffer.writeVarInt(this.difficulty);
    buffer.writeVector3i(this.defaultSpawn());
    buffer.writeBoolean(this.achievementsDisabled);
    buffer.writeVarInt(this.dayCycleStopTime);
    buffer.writeVarInt(this.eduEditionOffers);
    buffer.writeBoolean(this.eduFeaturesEnabled);
    buffer.writeString(this.educationProductionId());
    buffer.writeFloatLE(this.rainLevel);
    buffer.writeFloatLE(this.lightningLevel);
    buffer.writeBoolean(this.platformLockedContentConfirmed);
    buffer.writeBoolean(this.multiplayerGame);
    buffer.writeBoolean(this.broadcastingToLan);
    buffer.writeGamePublishSetting(this.xblBroadcastMode());
    buffer.writeGamePublishSetting(this.platformBroadcastMode());
    buffer.writeBoolean(this.commandsEnabled);
    buffer.writeBoolean(this.texturePacksRequired);
    buffer.writeArray(this.gameRules, buffer::writeGameRule);
    buffer.writeExperiments(this.experiments);
    buffer.writeBoolean(this.experimentsPreviouslyToggled);
    buffer.writeBoolean(this.bonusChestEnabled);
    buffer.writeBoolean(this.startingWithMap);
    buffer.writePlayerPermission(this.defaultPlayerPermission());
    buffer.writeIntLE(this.serverChunkTickRange);
    buffer.writeBoolean(this.behaviorPackLocked);
    buffer.writeBoolean(this.resourcePackLocked);
    buffer.writeBoolean(this.fromLockedWorldTemplate);
    buffer.writeBoolean(this.usingMsaGamerTagsOnly);
    buffer.writeBoolean(this.fromWorldTemplate);
    buffer.writeBoolean(this.worldTemplateOptionLocked);
    buffer.writeBoolean(this.onlySpawningV1Villagers);
    buffer.writeString(this.vanillaVersion());
    buffer.writeIntLE(this.limitedWorldWidth);
    buffer.writeIntLE(this.limitedWorldHeight);
    buffer.writeBoolean(this.netherType);
    buffer.writeString(this.eduSharedUriResource().buttonName());
    buffer.writeString(this.eduSharedUriResource().linkUri());
    buffer.writeBoolean(this.forceExperimentalGameplay);
    if (this.forceExperimentalGameplay) {
      buffer.writeBoolean(true);
    }
  }
}
