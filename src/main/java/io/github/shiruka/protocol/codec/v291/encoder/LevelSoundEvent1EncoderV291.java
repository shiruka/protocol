package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.LevelSoundEvent1;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents client to server handshake packet encoders.
 */
@PacketId(24)
public final class LevelSoundEvent1EncoderV291
  extends PacketEncoder.Base<LevelSoundEvent1> {

  @Override
  public void decode(
    @NotNull final LevelSoundEvent1 packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.sound(helper.soundEvents().type(buffer.readUnsignedByte()));
    packet.position(buffer.readVector3f());
    packet.extraData(buffer.readVarInt());
    packet.pitch(buffer.readVarInt());
    packet.babySound(buffer.readBoolean());
    packet.relativeVolumeDisabled(buffer.readBoolean());
  }

  @Override
  public void encode(
    @NotNull final LevelSoundEvent1 packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeByte(helper.soundEvents().id(packet.sound()));
    buffer.writeVector3f(packet.position());
    buffer.writeVarInt(packet.extraData());
    buffer.writeVarInt(packet.pitch());
    buffer.writeBoolean(packet.babySound());
    buffer.writeBoolean(packet.relativeVolumeDisabled());
  }
}
