package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.UpdateBlock;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents update block packet encoders.
 */
@PacketId(21)
public final class UpdateBlockEncoderV291
  extends PacketEncoder.Base<UpdateBlock> {

  @Override
  public void decode(
    @NotNull final UpdateBlock packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.blockPosition(buffer.readVector3i());
    packet.runtimeId(buffer.readUnsignedVarInt());
    final var flagValue = buffer.readUnsignedVarInt();
    final var flags = packet.flags();
    for (final var flag : UpdateBlock.Flag.VALUES) {
      if ((flagValue & 1 << flag.ordinal()) != 0) {
        flags.add(flag);
      }
    }
    packet.dataLayer(buffer.readUnsignedVarInt());
  }

  @Override
  public void encode(
    @NotNull final UpdateBlock packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeVector3i(packet.blockPosition());
    buffer.writeUnsignedVarInt(packet.runtimeId());
    var flagValue = 0;
    for (final var flag : packet.flags()) {
      flagValue |= 1 << flag.ordinal();
    }
    buffer.writeUnsignedVarInt(flagValue);
    buffer.writeUnsignedVarInt(packet.dataLayer());
  }
}
