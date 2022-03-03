package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.StartGame;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents start game packet encoders.
 */
@PacketId(11)
public final class StartGameEncoderV291 extends PacketEncoder.Base<StartGame> {

  @Override
  public void decode(@NotNull final StartGame packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
  }

  @Override
  public void encode(@NotNull final StartGame packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
  }
}
