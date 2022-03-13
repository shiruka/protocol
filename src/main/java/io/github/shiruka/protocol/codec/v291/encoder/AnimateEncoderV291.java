package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packet.Animate;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents animate packet encoders.
 */
@PacketId(44)
public final class AnimateEncoderV291 extends PacketEncoder.Base<Animate> {

  @Override
  public void decode(@NotNull final Animate packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var action = Animate.Action.byId(buffer.readVarInt());
    packet.action(action);
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    if (action == Animate.Action.ROW_LEFT || action == Animate.Action.ROW_RIGHT) {
      packet.rowingTime(buffer.readFloatLE());
    }
  }

  @Override
  public void encode(@NotNull final Animate packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var action = packet.action();
    buffer.writeVarInt(action.id());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    if (action == Animate.Action.ROW_LEFT || action == Animate.Action.ROW_RIGHT) {
      buffer.writeFloatLE(packet.rowingTime());
    }
  }
}
