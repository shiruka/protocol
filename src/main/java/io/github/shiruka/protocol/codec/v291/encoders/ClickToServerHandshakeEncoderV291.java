package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.packets.BlockEvent;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents block event packet encoders.
 */
@PacketId(4)
public final class ClickToServerHandshakeEncoderV291 extends PacketEncoder.Base<BlockEvent> {

  @Override
  public void decode(@NotNull final BlockEvent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
  }

  @Override
  public void encode(@NotNull final BlockEvent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
  }
}
