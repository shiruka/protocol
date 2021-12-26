package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector2f;
import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.api.nbt.ListTag;
import io.github.shiruka.protocol.MinecraftPacket;
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
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents start game packets.
 */
@Setter
@Getter
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
  private boolean achievementsDisabled;

  /**
   * the behavior pack locked.
   */
  private boolean behaviorPackLocked;

  /**
   * the block palette.
   */
  private ListTag blockPalette;

  /**
   * the block registry checksum.
   */
  private long blockRegistryChecksum;

  /**
   * the bonus chest enabled.
   */
  private boolean bonusChestEnabled;

  /**
   * the broadcasting to lon.
   */
  private boolean broadcastingToLan;

  /**
   * teh commands enabled.
   */
  private boolean commandsEnabled;

  /**
   * the current tick.
   */
  private long currentTick;

  /**
   * the custom biome name.
   */
  private String customBiomeName = "";

  /**
   * the day cycle stop time.
   */
  private int dayCycleStopTime;

  /**
   * the default player permission.
   */
  private PlayerPermission defaultPlayerPermission;

  /**
   * the default spawn.
   */
  private Vector3i defaultSpawn;

  /**
   * the difficulty.
   */
  private int difficulty;

  /**
   * the dimension id.
   */
  private int dimensionId;

  /**
   * the edu edition offers.
   */
  private int eduEditionOffers;

  /**
   * the edu features enabled.
   */
  private boolean eduFeaturesEnabled;

  /**
   * the edu shared uri resource.
   */
  private EduSharedUriResource eduSharedUriResource = EduSharedUriResource.EMPTY;

  /**
   * the education production id.
   */
  private String educationProductionId = "";

  /**
   * the enchantment seed.
   */
  private int enchantmentSeed;

  /**
   * the experiments previously toggled.
   */
  private boolean experimentsPreviouslyToggled;

  /**
   * the force experimental gameplay.
   */
  private boolean forceExperimentalGameplay;

  /**
   * the from locked world template.
   */
  private boolean fromLockedWorldTemplate;

  /**
   * the from world template.
   */
  private boolean fromWorldTemplate;

  /**
   * the generator id.
   */
  private int generatorId;

  /**
   * the inventories server authoritative.
   */
  private boolean inventoriesServerAuthoritative;

  /**
   * the item entries.
   */
  private List<ItemEntry> itemEntries = new ObjectArrayList<>();

  /**
   * the level game type.
   */
  private GameType levelGameType;

  /**
   * the level id.
   */
  private String levelId;

  /***
   * the level name.
   */
  private String levelName;

  /**
   * the lightning level.
   */
  private float lightningLevel;

  /**
   * the limited world height.
   */
  private int limitedWorldHeight;

  /**
   * the limited world width.
   */
  private int limitedWorldWidth;

  /**
   * the multiplayer correlation id.
   */
  private String multiplayerCorrelationId;

  /**
   * the multiplayer game.
   */
  private boolean multiplayerGame;

  /**
   * the nether type.
   */
  private boolean netherType;

  /**
   * the only spawning v1 villagers.
   */
  private boolean onlySpawningV1Villagers;

  /**
   * the platform broadcast mode.
   */
  private GamePublishSetting platformBroadcastMode;

  /**
   * the platform locked content confirmed.
   */
  private boolean platformLockedContentConfirmed;

  /**
   * the player game type.
   */
  private GameType playerGameType;

  /**
   * the player movement settings.
   */
  private SyncedPlayerMovementSettings playerMovementSettings;

  /**
   * the player position.
   */
  private Vector3f playerPosition;

  /**
   * the premium world template id.
   */
  private String premiumWorldTemplateId;

  /**
   * the rain level.
   */
  private float rainLevel;

  /**
   * the resource pack locked.
   */
  private boolean resourcePackLocked;

  /**
   * the rotation.
   */
  private Vector2f rotation;

  /**
   * the runtime entity id.
   */
  private long runtimeEntityId;

  /**
   * the seed.
   */
  private int seed;

  /**
   * the server chunk tick range.
   */
  private int serverChunkTickRange;

  /**
   * the server engine.
   */
  private String serverEngine;

  /**
   * the spawn biome type.
   */
  private SpawnBiomeType spawnBiomeType = SpawnBiomeType.DEFAULT;

  /**
   * the starting with map.
   */
  private boolean startingWithMap;

  /**
   * the texture packs required.
   */
  private boolean texturePacksRequired;

  /**
   * the trial.
   */
  private boolean trial;

  /**
   * the trusting players.
   */
  private boolean trustingPlayers;

  /**
   * the unique entity id.
   */
  private long uniqueEntityId;

  /**
   * the using msa gamer tags only.
   */
  private boolean usingMsaGamerTagsOnly;

  /**
   * the vanilla version.
   */
  private String vanillaVersion;

  /**
   * the world template option locked.
   */
  private boolean worldTemplateOptionLocked;

  /**
   * the xbl broadcast mode.
   */
  private GamePublishSetting xblBroadcastMode;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
