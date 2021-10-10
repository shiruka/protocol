package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.math.vectors.Vector2f;
import io.github.shiruka.api.math.vectors.Vector3f;
import io.github.shiruka.api.math.vectors.Vector3i;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.EduSharedUriResource;
import io.github.shiruka.protocol.data.ExperimentData;
import io.github.shiruka.protocol.data.GamePublishSetting;
import io.github.shiruka.protocol.data.GameRuleValue;
import io.github.shiruka.protocol.data.GameType;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.SpawnBiomeType;
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
   * the limited world height
   */
  @Getter
  private int limitedWorldHeight;

  /**
   * the limited world width.
   */
  @Getter
  private int limitedWorldWidth;

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
   * the player position.
   */
  @Nullable
  private Vector3f playerPosition;

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

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
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
}
