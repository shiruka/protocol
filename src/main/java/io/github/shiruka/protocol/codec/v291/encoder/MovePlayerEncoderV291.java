package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.MovePlayer;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents move player packet encoders.
 */
@PacketId(19)
public final class MovePlayerEncoderV291 extends PacketEncoder.Base<MovePlayer> {

  @Override
  public void decode(@NotNull final MovePlayer packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.position(buffer.readVector3f());
    packet.rotation(buffer.readVector3f());
    packet.mode(MovePlayer.Mode.VALUES[buffer.readUnsignedByte()]);
    packet.onGround(buffer.readBoolean());
    packet.ridingRuntimeEntityId(buffer.readUnsignedVarLong());
    if (packet.mode() == MovePlayer.Mode.TELEPORT) {
      packet.teleportationCause(MovePlayer.TeleportationCause.byOrdinal(buffer.readIntLE()));
      packet.entityType(buffer.readIntLE());
    }
  }

  @Override
  public void encode(@NotNull final MovePlayer packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeVector3f(packet.position());
    buffer.writeVector3f(packet.rotation());
    buffer.writeByte(packet.mode().ordinal());
    buffer.writeBoolean(packet.onGround());
    buffer.writeUnsignedVarLong(packet.ridingRuntimeEntityId());
    if (packet.mode() == MovePlayer.Mode.TELEPORT) {
      buffer.writeIntLE(packet.teleportationCause().ordinal());
      buffer.writeIntLE(packet.entityType());
    }
  }
}
