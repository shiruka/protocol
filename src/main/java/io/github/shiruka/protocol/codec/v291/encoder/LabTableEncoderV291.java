package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.LabTable;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents lab table packet encoders.
 */
@PacketId(109)
public final class LabTableEncoderV291 extends PacketEncoder.Base<LabTable> {

  @Override
  public void decode(
    @NotNull final LabTable packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.type(LabTable.Type.VALUES[buffer.readUnsignedByte()]);
    packet.position(buffer.readVector3i());
    packet.reactionType(
      LabTable.ReactionType.VALUES[buffer.readUnsignedByte()]
    );
  }

  @Override
  public void encode(
    @NotNull final LabTable packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeByte(packet.type().ordinal());
    buffer.writeVector3i(packet.position());
    buffer.writeByte(packet.reactionType().ordinal());
  }
}
