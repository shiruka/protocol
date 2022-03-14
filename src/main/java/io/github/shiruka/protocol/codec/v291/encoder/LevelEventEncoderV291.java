package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.LevelEvent;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents level event packet encoders.
 */
@PacketId(25)
public final class LevelEventEncoderV291 extends PacketEncoder.Base<LevelEvent> {

  @Override
  public void decode(@NotNull final LevelEvent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.type(helper.levelEvents().type(buffer.readVarInt()));
    packet.position(buffer.readVector3f());
    packet.data(buffer.readVarInt());
  }

  @Override
  public void encode(@NotNull final LevelEvent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarInt(helper.levelEvents().id(packet.type()));
    buffer.writeVector3f(packet.position());
    buffer.writeVarInt(packet.data());
  }
}
