package io.github.shiruka.protocol.packet;

import io.github.shiruka.api.common.vectors.Vector2f;
import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.api.nbt.ListTag;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents start game packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class StartGame extends MinecraftPacket.Base {

  //@formatter:off
  boolean achievementsDisabled;
  boolean behaviorPackLocked;
  ListTag blockPalette;
  @Builder.Default List<BlockPropertyData> blockProperties = new ObjectArrayList<>();
  long blockRegistryChecksum;
  boolean bonusChestEnabled;
  boolean broadcastingToLan;
  boolean commandsEnabled;
  long currentTick;
  @Builder.Default String customBiomeName = "";
  int dayCycleStopTime;
  PlayerPermission defaultPlayerPermission;
  Vector3i defaultSpawn;
  int difficulty;
  int dimensionId;
  int eduEditionOffers;
  boolean eduFeaturesEnabled;
  @Builder.Default EduSharedUriResource eduSharedUriResource = EduSharedUriResource.EMPTY;
  @Builder.Default String educationProductionId = "";
  int enchantmentSeed;
  @Builder.Default List<ExperimentData> experiments = new ObjectArrayList<>();
  boolean experimentsPreviouslyToggled;
  boolean forceExperimentalGameplay;
  boolean fromLockedWorldTemplate;
  boolean fromWorldTemplate;
  @Builder.Default List<GameRuleValue> gameRules = new ObjectArrayList<>();
  int generatorId;
  boolean inventoriesServerAuthoritative;
  @Builder.Default List<ItemEntry> itemEntries = new ObjectArrayList<>();
  GameType levelGameType;
  String levelId;
  String levelName;
  float lightningLevel;
  int limitedWorldHeight;
  int limitedWorldWidth;
  String multiplayerCorrelationId;
  boolean multiplayerGame;
  boolean netherType;
  boolean onlySpawningV1Villagers;
  GamePublishSetting platformBroadcastMode;
  boolean platformLockedContentConfirmed;
  GameType playerGameType;
  SyncedPlayerMovementSettings playerMovementSettings;
  Vector3f playerPosition;
  String premiumWorldTemplateId;
  float rainLevel;
  boolean resourcePackLocked;
  Vector2f rotation;
  long runtimeEntityId;
  int seed;
  int serverChunkTickRange;
  String serverEngine;
  @Builder.Default SpawnBiomeType spawnBiomeType = SpawnBiomeType.DEFAULT;
  boolean startingWithMap;
  boolean texturePacksRequired;
  boolean trial;
  boolean trustingPlayers;
  long uniqueEntityId;
  boolean usingMsaGamerTagsOnly;
  String vanillaVersion;
  boolean worldTemplateOptionLocked;
  GamePublishSetting xblBroadcastMode;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
