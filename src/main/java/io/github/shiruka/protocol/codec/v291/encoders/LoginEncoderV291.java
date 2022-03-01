package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.Login;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents login packet encoders.
 */
public final class LoginEncoderV291 extends PacketEncoder.Base<Login> {

  /**
   * the instance.
   */
  public static final LoginEncoderV291 INSTANCE = new LoginEncoderV291();

  /**
   * ctor.
   */
  private LoginEncoderV291() {
    super(Login::new, 1);
  }

  @Override
  public void decode(@NotNull final Login packet, @NotNull final CodecHelper helper, @NotNull final PacketBuffer buffer,
                     @NotNull final MinecraftSession session) {
    packet.protocolVersion(buffer.readInt());
    final var jwt = new PacketBuffer(buffer.readSlice());
    packet.chainData(jwt.readLEAsciiString());
    packet.skinData(jwt.readLEAsciiString());
  }

  @Override
  public void encode(@NotNull final Login packet, @NotNull final CodecHelper helper, @NotNull final PacketBuffer buffer,
                     @NotNull final MinecraftSession session) {
    buffer.writeInt(packet.protocolVersion());
    final var chainData = packet.chainData();
    final var skinData = packet.skinData();
    buffer.writeUnsignedVarInt(chainData.length() + skinData.length() + 8);
    buffer.writeLEAsciiString(chainData);
    buffer.writeLEAsciiString(skinData);
  }
}
