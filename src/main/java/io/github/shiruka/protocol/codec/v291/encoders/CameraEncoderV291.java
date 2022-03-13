package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.Camera;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents camera packet encoders.
 */
@PacketId(73)
public final class CameraEncoderV291 extends PacketEncoder.Base<Camera> {

  @Override
  public void decode(@NotNull final Camera packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.cameraUniqueEntityId(buffer.readVarLong());
    packet.playerUniqueEntityId(buffer.readVarLong());
  }

  @Override
  public void encode(@NotNull final Camera packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.cameraUniqueEntityId());
    buffer.writeVarLong(packet.playerUniqueEntityId());
  }
}
