package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.RiderJump;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents rider jump packet encoders.
 */
@PacketId(20)
public final class RiderJumpEncoderV291 extends PacketEncoder.Base<RiderJump> {

  @Override
  public void decode(
    @NotNull final RiderJump packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.jumpStrength(buffer.readVarInt());
  }

  @Override
  public void encode(
    @NotNull final RiderJump packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeUnsignedVarInt(packet.jumpStrength());
  }
}
