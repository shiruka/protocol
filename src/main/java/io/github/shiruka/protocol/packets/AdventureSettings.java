package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.Constants;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.AdventureSetting;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.command.CommandPermission;
import java.util.EnumSet;
import java.util.Set;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents adventure settings packets.
 */
@Getter
@ToString
@Accessors(fluent = true)
public final class AdventureSettings extends MinecraftPacket {

  /**
   * the flags 1.
   */
  public static final AdventureSetting @Nullable [] FLAGS_1 = {
    AdventureSetting.WORLD_IMMUTABLE, AdventureSetting.NO_PVM, AdventureSetting.NO_MVP, null,
    AdventureSetting.SHOW_NAME_TAGS, AdventureSetting.AUTO_JUMP, AdventureSetting.MAY_FLY, AdventureSetting.NO_CLIP,
    AdventureSetting.WORLD_BUILDER, AdventureSetting.FLYING, AdventureSetting.MUTED};

  /**
   * the flags 2.
   */
  public static final AdventureSetting @Nullable [] FLAGS_2 = {
    AdventureSetting.MINE, AdventureSetting.DOORS_AND_SWITCHES, AdventureSetting.OPEN_CONTAINERS,
    AdventureSetting.ATTACK_PLAYERS, AdventureSetting.ATTACK_MOBS, AdventureSetting.OPERATOR, null,
    AdventureSetting.TELEPORT, AdventureSetting.BUILD, AdventureSetting.DEFAULT_LEVEL_PERMISSIONS};

  /**
   * the settings.
   */
  private final Set<AdventureSetting> settings = EnumSet.noneOf(AdventureSetting.class);

  /**
   * the command permission.
   */
  private CommandPermission commandPermission = CommandPermission.NORMAL;

  /**
   * the player permission.
   */
  private PlayerPermission playerPermission = PlayerPermission.VISITOR;

  /**
   * the unique entity id.
   */
  private long uniqueEntityId;

  /**
   * reads the flags.
   *
   * @param flags the flags to read.
   * @param mappings the mappings to read.
   * @param settings the settings to read.
   */
  private static void readFlags(final int flags, @NotNull final AdventureSetting[] mappings, @NotNull final Set<AdventureSetting> settings) {
    for (var i = 0; i < mappings.length; i++) {
      final var setting = mappings[i];
      if (setting != null && (flags & 1 << i) != 0) {
        settings.add(setting);
      }
    }
  }

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    final var flags1 = buffer.readUnsignedVarInt();
    this.commandPermission = buffer.readCommandPermission();
    final var flags2 = buffer.readUnsignedVarInt();
    this.playerPermission = buffer.readPlayerPermission();
    buffer.readUnsignedVarInt();
    this.uniqueEntityId = buffer.readLongLE();
    final var settings = this.settings();
    AdventureSettings.readFlags(flags1, AdventureSettings.FLAGS_1, settings);
    AdventureSettings.readFlags(flags2, AdventureSettings.FLAGS_2, settings);
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    var flags1 = 0;
    var flags2 = 0;
    for (final var setting : this.settings) {
      if (Constants.FLAGS_TO_BIT_1.containsKey(setting)) {
        flags1 |= Constants.FLAGS_TO_BIT_1.getInt(setting);
      } else if (Constants.FLAGS_TO_BIT_2.containsKey(setting)) {
        flags2 |= Constants.FLAGS_TO_BIT_2.getInt(setting);
      }
    }
    buffer.writeUnsignedVarInt(flags1);
    buffer.writeUnsignedVarInt(this.commandPermission.ordinal());
    buffer.writeUnsignedVarInt(flags2);
    buffer.writeUnsignedVarInt(this.playerPermission.ordinal());
    buffer.writeUnsignedVarInt(0);
    buffer.writeLongLE(this.uniqueEntityId);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
