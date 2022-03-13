package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.api.nbt.Tag;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.data.GameType;
import io.github.shiruka.protocol.packet.StartGame;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents start game packet encoders.
 */
@PacketId(11)
public final class StartGameEncoderV291 extends PacketEncoder.Base<StartGame> {

  @Override
  public void decode(@NotNull final StartGame packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.playerGameType(GameType.byOrdinal(buffer.readVarInt()));
    packet.playerPosition(buffer.readVector3f());
    packet.rotation(buffer.readVector2f());
    helper.readLevelSettings(packet, buffer);
    packet.levelId(buffer.readString());
    packet.levelName(buffer.readString());
    packet.premiumWorldTemplateId(buffer.readString());
    packet.trial(buffer.readBoolean());
    packet.currentTick(buffer.readLongLE());
    packet.enchantmentSeed(buffer.readVarInt());
    final var paletteLength = buffer.readUnsignedVarInt();
    final var palette = Tag.createList();
    for (var index = 0; index < paletteLength; index++) {
      palette.add(Tag.createCompound()
        .set("block", Tag.createCompound()
          .setString("name", buffer.readString()))
        .setShort("meta", buffer.readShortLE()));
    }
    packet.blockPalette(palette);
    packet.multiplayerCorrelationId(buffer.readString());
  }

  @Override
  public void encode(@NotNull final StartGame packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeVarInt(packet.playerGameType().ordinal());
    buffer.writeVector3f(packet.playerPosition());
    buffer.writeVector2f(packet.rotation());
    helper.writeLevelSettings(packet, buffer);
    buffer.writeString(packet.levelId());
    buffer.writeString(packet.levelName());
    buffer.writeString(packet.premiumWorldTemplateId());
    buffer.writeBoolean(packet.trial());
    buffer.writeLongLE(packet.currentTick());
    buffer.writeVarInt(packet.enchantmentSeed());
    final var palette = packet.blockPalette();
    buffer.writeUnsignedVarInt(palette.size());
    for (final var entry : palette) {
      final var tag = entry.asCompound();
      final var blockTag = tag.getCompoundTag("block")
        .orElseThrow();
      buffer.writeString(blockTag.getString("name").orElseThrow());
      buffer.writeShortLE(tag.getShort("meta").orElseThrow());
    }
    buffer.writeString(packet.multiplayerCorrelationId());
  }
}
