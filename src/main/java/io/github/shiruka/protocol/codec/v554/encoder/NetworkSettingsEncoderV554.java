package io.github.shiruka.protocol.codec.v554.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.v388.encoder.NetworkSettingsEncoderV388;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.data.PacketCompressionAlgorithm;
import io.github.shiruka.protocol.packet.NetworkSettings;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents network settings packet encoders.
 */
@PacketId(143)
public final class NetworkSettingsEncoderV554
  extends NetworkSettingsEncoderV388 {

  @Override
  public void decode(
    @NotNull final NetworkSettings packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    super.decode(packet, helper, buffer, session);
    packet.compressionAlgorithm(
      PacketCompressionAlgorithm.byOrdinal(buffer.readUnsignedShortLE())
    );
    packet.clientThrottleEnabled(buffer.readBoolean());
    packet.clientThrottleThreshold(buffer.readUnsignedByte());
    packet.clientThrottleScalar(buffer.readFloatLE());
  }

  @Override
  public void encode(
    @NotNull final NetworkSettings packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    super.encode(packet, helper, buffer, session);
    buffer.writeUnsignedShortLE(packet.compressionAlgorithm().ordinal());
    buffer.writeBoolean(packet.clientThrottleEnabled());
    buffer.writeUnsignedByte(packet.clientThrottleThreshold());
    buffer.writeFloatLE(packet.clientThrottleScalar());
  }
}
