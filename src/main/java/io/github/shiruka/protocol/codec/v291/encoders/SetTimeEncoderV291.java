package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.SetTime;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents set time packet encoders.
 */
@PacketId(10)
public final class SetTimeEncoderV291 extends PacketEncoder.Base<SetTime> {

  @Override
  public void decode(@NotNull final SetTime packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.time(buffer.readVarInt());
  }

  @Override
  public void encode(@NotNull final SetTime packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarInt(packet.time());
  }
}
