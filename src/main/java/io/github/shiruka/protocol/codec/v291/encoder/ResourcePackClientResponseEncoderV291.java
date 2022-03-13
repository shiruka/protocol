package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packet.ResourcePackClientResponse;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack client response packet encoders.
 */
@PacketId(8)
public final class ResourcePackClientResponseEncoderV291 extends PacketEncoder.Base<ResourcePackClientResponse> {

  @Override
  public void decode(@NotNull final ResourcePackClientResponse packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.status(ResourcePackClientResponse.Status.VALUES[buffer.readUnsignedByte()]);
    buffer.readArrayShortLE(buffer::readString);
  }

  @Override
  public void encode(@NotNull final ResourcePackClientResponse packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeByte(packet.status().ordinal());
    buffer.writeArrayShortLE(packet.packIds(), buffer::writeString);
  }
}
