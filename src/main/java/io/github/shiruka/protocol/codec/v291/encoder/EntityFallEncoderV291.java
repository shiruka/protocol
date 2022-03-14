package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.EntityFall;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents entity fall packet encoders.
 */
@PacketId(37)
public final class EntityFallEncoderV291 extends PacketEncoder.Base<EntityFall> {

  @Override
  public void decode(@NotNull final EntityFall packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.fallDistance(buffer.readFloatLE());
    packet.inVoid(buffer.readBoolean());
  }

  @Override
  public void encode(@NotNull final EntityFall packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeFloatLE(packet.fallDistance());
    buffer.writeBoolean(packet.inVoid());
  }
}
