package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.MobEffect;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents mob effect packet encoders.
 */
@PacketId(28)
public final class MobEffectEncoderV291 extends PacketEncoder.Base<MobEffect> {

  @Override
  public void decode(@NotNull final MobEffect packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.event(MobEffect.Event.VALUES[buffer.readUnsignedByte()]);
    packet.effectId(buffer.readVarInt());
    packet.amplifier(buffer.readVarInt());
    packet.particles(buffer.readBoolean());
    packet.duration(buffer.readVarInt());
  }

  @Override
  public void encode(@NotNull final MobEffect packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeByte(packet.event().ordinal());
    buffer.writeVarInt(packet.effectId());
    buffer.writeInt(packet.amplifier());
    buffer.writeBoolean(packet.particles());
    buffer.writeInt(packet.duration());
  }
}
