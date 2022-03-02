package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.ServerToClientHandshake;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents server to client handshake packet encoders.
 */
@PacketId(3)
public final class ServerToClientHandshakeEncoderV291 extends PacketEncoder.Base<ServerToClientHandshake> {

  @Override
  public void decode(@NotNull final ServerToClientHandshake packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.jwt(buffer.readString());
  }

  @Override
  public void encode(@NotNull final ServerToClientHandshake packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeString(packet.jwt());
  }
}
