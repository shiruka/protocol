package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.data.PlayStatusStatus;
import io.github.shiruka.protocol.packets.PlayStatus;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents play status packet encoders.
 */
@PacketId(2)
public final class PlayStatusEncoderV291 extends PacketEncoder.Base<PlayStatus> {

  @Override
  public void decode(@NotNull final PlayStatus packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.status(PlayStatusStatus.byOrdinal(buffer.readInt()));
  }

  @Override
  public void encode(@NotNull final PlayStatus packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeInt(packet.status().ordinal());
  }
}
