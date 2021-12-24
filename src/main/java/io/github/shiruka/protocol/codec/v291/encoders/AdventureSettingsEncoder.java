package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.api.nbt.VarInts;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.Constants;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.data.AdventureSetting;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.command.CommandPermission;
import io.github.shiruka.protocol.packets.AdventureSettings;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents adventure settings packet encoder.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AdventureSettingsEncoder implements PacketEncoder<AdventureSettings> {

  /**
   * the instance.
   */
  public static final AdventureSettingsEncoder INSTANCE = new AdventureSettingsEncoder();

  /**
   * the flags 1.
   */
  private static final AdventureSetting @Nullable [] FLAGS_1 = {
    AdventureSetting.WORLD_IMMUTABLE, AdventureSetting.NO_PVM, AdventureSetting.NO_MVP, null,
    AdventureSetting.SHOW_NAME_TAGS, AdventureSetting.AUTO_JUMP, AdventureSetting.MAY_FLY, AdventureSetting.NO_CLIP,
    AdventureSetting.WORLD_BUILDER, AdventureSetting.FLYING, AdventureSetting.MUTED};

  /**
   * the flags 2.
   */
  private static final AdventureSetting @Nullable [] FLAGS_2 = {
    AdventureSetting.MINE, AdventureSetting.DOORS_AND_SWITCHES, AdventureSetting.OPEN_CONTAINERS,
    AdventureSetting.ATTACK_PLAYERS, AdventureSetting.ATTACK_MOBS, AdventureSetting.OPERATOR, null,
    AdventureSetting.TELEPORT, AdventureSetting.BUILD, AdventureSetting.DEFAULT_LEVEL_PERMISSIONS};

  /**
   * reads the flags.
   *
   * @param flags the flags to read.
   * @param mappings the mappings to read.
   * @param settings the settings to read.
   */
  private static void readFlags(final int flags, @NotNull final AdventureSetting[] mappings,
                                @NotNull final Set<AdventureSetting> settings) {
    for (var i = 0; i < mappings.length; i++) {
      final var setting = mappings[i];
      if (setting != null && (flags & 1 << i) != 0) {
        settings.add(setting);
      }
    }
  }

  @Override
  public void decode(@NotNull final AdventureSettings packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var flags1 = buffer.readUnsignedVarInt();
    packet.commandPermission(CommandPermission.byOrdinal(buffer.readUnsignedVarInt()));
    final var flags2 = buffer.readUnsignedVarInt();
    packet.playerPermission(PlayerPermission.byOrdinal(buffer.readUnsignedVarInt()));
    buffer.readUnsignedVarInt();
    packet.uniqueEntityId(buffer.readLongLE());
    final var settings = packet.settings();
    AdventureSettingsEncoder.readFlags(flags1, AdventureSettingsEncoder.FLAGS_1, settings);
    AdventureSettingsEncoder.readFlags(flags2, AdventureSettingsEncoder.FLAGS_2, settings);
  }

  @Override
  public void encode(@NotNull final AdventureSettings packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    var flags1 = 0;
    var flags2 = 0;
    for (final var setting : packet.settings()) {
      if (Constants.FLAGS_TO_BIT_1.containsKey(setting)) {
        flags1 |= Constants.FLAGS_TO_BIT_1.getInt(setting);
      } else if (Constants.FLAGS_TO_BIT_2.containsKey(setting)) {
        flags2 |= Constants.FLAGS_TO_BIT_2.getInt(setting);
      }
    }
    buffer.writeUnsignedVarInt(flags1);
    buffer.writeUnsignedVarInt(packet.commandPermission().ordinal());
    buffer.writeUnsignedVarInt(flags2);
    buffer.writeUnsignedVarInt(packet.playerPermission().ordinal());
    buffer.writeUnsignedVarInt(0);
    buffer.writeLongLE(packet.uniqueEntityId());
  }
}
