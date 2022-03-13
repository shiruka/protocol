package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.UpdateAttributes;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents update attributes packet encoders.
 */
@PacketId(29)
public final class UpdateAttributesEncoderV291 extends PacketEncoder.Base<UpdateAttributes> {

  @Override
  public void decode(@NotNull final UpdateAttributes packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.attributes(buffer.readArrayUnsignedInt(() ->
      helper.readAttributeFull(buffer, session)));
  }

  @Override
  public void encode(@NotNull final UpdateAttributes packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeArrayUnsignedInt(packet.attributes(), data ->
      helper.writeAttributeFull(buffer, data));
  }
}
