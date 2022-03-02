package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.ClientToServerHandshake;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents client to server handshake packet encoders.
 */
@PacketId(4)
public final class ClientToServerHandshakeEncoderV291 extends PacketEncoder.Base<ClientToServerHandshake> {

  @Override
  public void decode(@NotNull final ClientToServerHandshake packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
  }

  @Override
  public void encode(@NotNull final ClientToServerHandshake packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
  }
}
