package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.Animate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents animate packet encoders.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class AnimateEncoder implements PacketEncoder<Animate> {

  /**
   * the instance.
   */
  public static final AnimateEncoder INSTANCE = new AnimateEncoder();

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
