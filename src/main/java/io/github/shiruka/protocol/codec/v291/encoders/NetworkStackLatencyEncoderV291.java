package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.NetworkStackLatency;
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
