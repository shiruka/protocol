package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.AddHangingEntity;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add hanging entity packet encoders.
 */
public final class AddHangingEntityEncoderV291 extends PacketEncoder.Base<AddHangingEntity> {

  /**
   * ctor.
   */
  private AddHangingEntityEncoderV291() {
    super(16);
  }

  @Override
  public void decode(@NotNull final AddHangingEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.position(buffer.readBlockPosition().toFloat());
    packet.direction(buffer.readVarInt());
  }

  @Override
  public void encode(@NotNull final AddHangingEntity packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeBlockPosition(packet.position().toInt());
    buffer.writeVarInt(packet.direction());
  }
}
