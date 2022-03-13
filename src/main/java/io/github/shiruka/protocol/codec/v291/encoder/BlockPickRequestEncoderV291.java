package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packet.BlockPickRequest;
import org.jetbrains.annotations.NotNull;

/**
 * a class that block pick request packet encoders.
 */
@PacketId(97)
public final class BlockPickRequestEncoderV291 extends PacketEncoder.Base<BlockPickRequest> {

  @Override
  public void decode(@NotNull final BlockPickRequest packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.blockPosition(buffer.readVector3i());
    packet.addUserData(buffer.readBoolean());
    packet.hotBarSlot(buffer.readUnsignedByte());
  }

  @Override
  public void encode(@NotNull final BlockPickRequest packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVector3i(packet.blockPosition());
    buffer.writeBoolean(packet.addUserData());
    buffer.writeByte(packet.hotBarSlot());
  }
}
