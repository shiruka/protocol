package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.EntityEvent;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents entity event packet encoders.
 */
@Log4j2
@PacketId(27)
public final class EntityEventEncoderV291 extends PacketEncoder.Base<EntityEvent> {

  @Override
  public void decode(@NotNull final EntityEvent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    final var event = buffer.readUnsignedByte();
    packet.type(helper.entityEventTypes().type(event));
    packet.data(buffer.readVarInt());
    if (packet.type() == null) {
      EntityEventEncoderV291.log.debug("Unknown EntityEvent {} in packet {}", event, packet);
    }
  }

  @Override
  public void encode(@NotNull final EntityEvent packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeByte(helper.entityEventTypes().id(packet.type()));
    buffer.writeVarInt(packet.data());
  }
}
