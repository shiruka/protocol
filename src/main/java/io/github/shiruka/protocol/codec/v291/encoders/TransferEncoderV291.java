package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.Transfer;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents transfer packet encoders.
 */
@PacketId(85)
public final class TransferEncoderV291 extends PacketEncoder.Base<Transfer> {

  @Override
  public void decode(@NotNull final Transfer packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.address(buffer.readString());
    packet.port(buffer.readShortLE());
  }

  @Override
  public void encode(@NotNull final Transfer packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeString(packet.address());
    buffer.writeShortLE(packet.port());
  }
}
