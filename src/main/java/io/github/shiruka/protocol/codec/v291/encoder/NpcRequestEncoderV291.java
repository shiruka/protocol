package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.NpcRequest;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents npc request packet encoders.
 */
@PacketId(98)
public final class NpcRequestEncoderV291
  extends PacketEncoder.Base<NpcRequest> {

  @Override
  public void decode(
    @NotNull final NpcRequest packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.requestType(NpcRequest.Type.VALUES[buffer.readUnsignedByte()]);
    packet.command(buffer.readString());
    packet.actionType(buffer.readUnsignedByte());
  }

  @Override
  public void encode(
    @NotNull final NpcRequest packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeByte(packet.requestType().ordinal());
    buffer.writeString(packet.command());
    buffer.writeByte(packet.actionType());
  }
}
