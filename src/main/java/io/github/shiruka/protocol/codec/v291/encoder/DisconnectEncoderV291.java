package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.Disconnect;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents disconnect packet encoders.
 */
@PacketId(5)
public final class DisconnectEncoderV291 extends PacketEncoder.Base<Disconnect> {

  @Override
  public void decode(@NotNull final Disconnect packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.messageSkipped(buffer.readBoolean());
    if (!packet.messageSkipped()) {
      packet.kickMessage(buffer.readString());
    }
  }

  @Override
  public void encode(@NotNull final Disconnect packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeBoolean(packet.messageSkipped());
    if (!packet.messageSkipped()) {
      buffer.writeString(packet.kickMessage());
    }
  }
}
