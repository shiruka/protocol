package io.github.shiruka.protocol.codec.v291.encoder;

import com.google.common.base.Preconditions;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents event packet encoders.
 */
@PacketId(65)
public final class EventEncoderV291 extends PacketEncoder.Base<Event> {

  @Override
  public void decode(@NotNull final Event packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    final var eventId = buffer.readVarInt();
    Preconditions.checkElementIndex(eventId, Event.Type.VALUES.length, "Event type");
    packet.usePlayerId(buffer.readByte());
    packet.data(helper.readEventData(buffer, Event.Type.VALUES[eventId]));
  }

  @Override
  public void encode(@NotNull final Event packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    final var data = packet.data();
    buffer.writeVarInt(data.type().ordinal());
    buffer.writeByte(packet.usePlayerId());
    helper.writeEventData(buffer, data);
  }
}
