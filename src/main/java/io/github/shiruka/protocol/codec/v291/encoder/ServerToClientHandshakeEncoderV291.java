package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.ServerToClientHandshake;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents server to client handshake packet encoders.
 */
@PacketId(3)
public final class ServerToClientHandshakeEncoderV291
  extends PacketEncoder.Base<ServerToClientHandshake> {

  @Override
  public void decode(
    @NotNull final ServerToClientHandshake packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.jwt(buffer.readString());
  }

  @Override
  public void encode(
    @NotNull final ServerToClientHandshake packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeString(packet.jwt());
  }
}
