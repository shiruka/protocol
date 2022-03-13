package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.BossEvent;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents client to server handshake packet encoders.
 */
@PacketId(74)
public final class BossEventEncoderV291 extends PacketEncoder.Base<BossEvent> {

  @Override
  public void decode(@NotNull final BossEvent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.bossUniqueEntityId(buffer.readVarInt());
    packet.action(BossEvent.Action.VALUES[buffer.readUnsignedVarInt()]);
    helper.readBossEventAction(packet, buffer);
  }

  @Override
  public void encode(@NotNull final BossEvent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.bossUniqueEntityId());
    buffer.writeUnsignedVarInt(packet.action().ordinal());
    helper.writeBossEventAction(packet, buffer);
  }
}
