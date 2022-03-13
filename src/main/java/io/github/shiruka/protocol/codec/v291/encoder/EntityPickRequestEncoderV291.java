package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.EntityPickRequest;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents entity pick request packet encoders.
 */
@PacketId(35)
public final class EntityPickRequestEncoderV291 extends PacketEncoder.Base<EntityPickRequest> {

  @Override
  public void decode(@NotNull final EntityPickRequest packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.runtimeEntityId(buffer.readLongLE());
    packet.hotBarSlot(buffer.readUnsignedByte());
  }

  @Override
  public void encode(@NotNull final EntityPickRequest packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeLongLE(packet.runtimeEntityId());
    buffer.writeByte(packet.hotBarSlot());
  }
}
