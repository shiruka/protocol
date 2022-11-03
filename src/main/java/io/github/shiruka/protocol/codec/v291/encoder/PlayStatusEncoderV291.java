package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.PlayStatus;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents play status packet encoders.
 */
@PacketId(2)
public final class PlayStatusEncoderV291
  extends PacketEncoder.Base<PlayStatus> {

  @Override
  public void decode(
    @NotNull final PlayStatus packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.status(PlayStatus.Status.VALUES[buffer.readInt()]);
  }

  @Override
  public void encode(
    @NotNull final PlayStatus packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeInt(packet.status().ordinal());
  }
}
