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

  //@formatter:off
  private final List<BlockPropertyData> blockProperties = new ObjectArrayList<>();
  private final List<ExperimentData> experiments = new ObjectArrayList<>();
  private final List<GameRuleValue> gameRules = new ObjectArrayList<>();
  private boolean achievementsDisabled;
  private boolean behaviorPackLocked;
  private ListTag blockPalette;
  private long blockRegistryChecksum;
  private boolean bonusChestEnabled;
  private boolean broadcastingToLan;
  private boolean commandsEnabled;
  private long currentTick;
  private String customBiomeName = "";
  private int dayCycleStopTime;
  private PlayerPermission defaultPlayerPermission;
  private Vector3i defaultSpawn;
  private int difficulty;
  private int dimensionId;
  private int eduEditionOffers;
  private boolean eduFeaturesEnabled;
  private EduSharedUriResource eduSharedUriResource = EduSharedUriResource.EMPTY;
  private String educationProductionId = "";
  private int enchantmentSeed;
  private boolean experimentsPreviouslyToggled;
  private boolean forceExperimentalGameplay;
  private boolean fromLockedWorldTemplate;
  private boolean fromWorldTemplate;
  private int generatorId;
  private boolean inventoriesServerAuthoritative;
  private List<ItemEntry> itemEntries = new ObjectArrayList<>();
  private GameType levelGameType;
  private String levelId;
  private String levelName;
  private float lightningLevel;
  private int limitedWorldHeight;
  private int limitedWorldWidth;
  private String multiplayerCorrelationId;
  private boolean multiplayerGame;
  private boolean netherType;
  private boolean onlySpawningV1Villagers;
  private GamePublishSetting platformBroadcastMode;
  private boolean platformLockedContentConfirmed;
  private GameType playerGameType;
  private SyncedPlayerMovementSettings playerMovementSettings;
  private Vector3f playerPosition;
  private String premiumWorldTemplateId;
  private float rainLevel;
  private boolean resourcePackLocked;
  private Vector2f rotation;
  private long runtimeEntityId;
  private int seed;
  private int serverChunkTickRange;
  private String serverEngine;
  private SpawnBiomeType spawnBiomeType = SpawnBiomeType.DEFAULT;
  private boolean startingWithMap;
  private boolean texturePacksRequired;
  private boolean trial;
  private boolean trustingPlayers;
  private long uniqueEntityId;
  private boolean usingMsaGamerTagsOnly;
  private String vanillaVersion;
  private boolean worldTemplateOptionLocked;
  private GamePublishSetting xblBroadcastMode;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
