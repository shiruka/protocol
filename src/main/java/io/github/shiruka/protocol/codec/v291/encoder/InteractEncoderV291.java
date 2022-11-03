package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.Interact;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents interact packet encoders.
 */
@PacketId(33)
public final class InteractEncoderV291 extends PacketEncoder.Base<Interact> {

  @Override
  public void decode(
    @NotNull final Interact packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.action(Interact.Action.VALUES[buffer.readUnsignedByte()]);
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    if (packet.action() == Interact.Action.MOUSEOVER) {
      packet.mousePosition(buffer.readVector3f());
    }
  }

  @Override
  public void encode(
    @NotNull final Interact packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeByte(packet.action().ordinal());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    if (packet.action() == Interact.Action.MOUSEOVER) {
      buffer.writeVector3f(packet.mousePosition());
    }
  }
}
