package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.AutomationClientConnect;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents automation client connect packet encoders.
 */
@PacketId(95)
public final class AutomationClientConnectEncoderV291
  extends PacketEncoder.Base<AutomationClientConnect> {

  @Override
  public void decode(
    @NotNull final AutomationClientConnect packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.address(buffer.readString());
  }

  @Override
  public void encode(
    @NotNull final AutomationClientConnect packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeString(packet.address());
  }
}
