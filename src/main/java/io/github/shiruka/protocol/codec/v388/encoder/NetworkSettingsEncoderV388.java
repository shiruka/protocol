package io.github.shiruka.protocol.codec.v388.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.v554.encoder.NetworkSettingsEncoderV554;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.NetworkSettings;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents network settings packet encoders.
 */
@PacketId(143)
public class NetworkSettingsEncoderV388
  extends PacketEncoder.Base<NetworkSettings> {

  @Override
  public void decode(
    @NotNull final NetworkSettings packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.compressionThreshold(buffer.readUnsignedShortLE());
  }

  @Override
  public void encode(
    @NotNull final NetworkSettings packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeShortLE(packet.compressionThreshold());
  }
}
