package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.MoveEntityAbsolute;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents move entity absolute packet encoders.
 */
@PacketId(18)
public final class MoveEntityAbsoluteEncoderV291 extends PacketEncoder.Base<MoveEntityAbsolute> {

  /**
   * the flag on ground.
   */
  private static final int FLAG_ON_GROUND = 0x1;

  /**
   * the flag teleported.
   */
  private static final int FLAG_TELEPORTED = 0x2;

  @Override
  public void decode(@NotNull final MoveEntityAbsolute packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    final var flags = buffer.readUnsignedByte();
    packet.onGround((flags & MoveEntityAbsoluteEncoderV291.FLAG_ON_GROUND) != 0);
    packet.teleported((flags & MoveEntityAbsoluteEncoderV291.FLAG_TELEPORTED) != 0);
    packet.position(buffer.readVector3f());
    packet.rotation(buffer.readByteRotation());
  }

  @Override
  public void encode(@NotNull final MoveEntityAbsolute packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    var flags = 0;
    if (packet.onGround()) {
      flags |= MoveEntityAbsoluteEncoderV291.FLAG_ON_GROUND;
    }
    if (packet.teleported()) {
      flags |= MoveEntityAbsoluteEncoderV291.FLAG_TELEPORTED;
    }
    buffer.writeByte(flags);
    buffer.writeVector3f(packet.position());
    buffer.writeByteRotation(packet.rotation());
  }
}
