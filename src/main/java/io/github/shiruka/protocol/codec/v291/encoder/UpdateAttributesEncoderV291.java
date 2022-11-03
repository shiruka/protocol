package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.UpdateAttributes;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents update attributes packet encoders.
 */
@PacketId(29)
public final class UpdateAttributesEncoderV291
  extends PacketEncoder.Base<UpdateAttributes> {

  @Override
  public void decode(
    @NotNull final UpdateAttributes packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.attributes(
      buffer.readArrayUnsignedInt(() -> helper.readAttributeFull(buffer))
    );
  }

  @Override
  public void encode(
    @NotNull final UpdateAttributes packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeArrayUnsignedInt(
      packet.attributes(),
      data -> helper.writeAttributeFull(buffer, data)
    );
  }
}
