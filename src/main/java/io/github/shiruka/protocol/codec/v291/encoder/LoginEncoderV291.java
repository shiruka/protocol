package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.Login;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents login packet encoders.
 */
@PacketId(1)
public final class LoginEncoderV291 extends PacketEncoder.Base<Login> {

  @Override
  public void decode(
    @NotNull final Login packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.protocolVersion(buffer.readInt());
    final var jwt = new PacketBuffer(buffer.readSlice());
    packet.chainData(jwt.readLEAsciiString());
    packet.skinData(jwt.readLEAsciiString());
  }

  @Override
  public void encode(
    @NotNull final Login packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeInt(packet.protocolVersion());
    final var chainData = packet.chainData();
    final var skinData = packet.skinData();
    buffer.writeUnsignedVarInt(chainData.length() + skinData.length() + 8);
    buffer.writeLEAsciiString(chainData);
    buffer.writeLEAsciiString(skinData);
  }
}
