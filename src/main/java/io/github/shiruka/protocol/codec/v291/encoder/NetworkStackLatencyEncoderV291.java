package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.NetworkStackLatency;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents network stack latency packet encoders.
 */
@PacketId(115)
public final class NetworkStackLatencyEncoderV291 extends PacketEncoder.Base<NetworkStackLatency> {

  @Override
  public void decode(@NotNull final NetworkStackLatency packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.timestamp(buffer.readLongLE());
  }

  @Override
  public void encode(@NotNull final NetworkStackLatency packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeLongLE(packet.timestamp());
  }
}
