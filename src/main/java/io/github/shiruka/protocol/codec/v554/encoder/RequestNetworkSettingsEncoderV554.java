package io.github.shiruka.protocol.codec.v554.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.RequestNetworkSettings;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add behavior tree packet encoders.
 */
@PacketId(193)
public final class RequestNetworkSettingsEncoderV554
  extends PacketEncoder.Base<RequestNetworkSettings> {

  @Override
  public void decode(
    @NotNull final RequestNetworkSettings packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.protocolVersion(buffer.readInt());
  }

  @Override
  public void encode(
    @NotNull final RequestNetworkSettings packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeInt(packet.protocolVersion());
  }
}
