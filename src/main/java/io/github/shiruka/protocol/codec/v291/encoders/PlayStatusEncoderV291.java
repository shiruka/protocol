package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.data.PlayStatusStatus;
import io.github.shiruka.protocol.packets.PlayStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents play status packet encoders.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class PlayStatusEncoderV291 implements PacketEncoder<PlayStatus> {

  /**
   * the instance.
   */
  public static final PlayStatusEncoderV291 INSTANCE = new PlayStatusEncoderV291();

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
