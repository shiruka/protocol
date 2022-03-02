package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.ResourcePackClientResponse;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack client response packet encoders.
 */
@PacketId(8)
public final class ResourcePackClientResponseEncoderV291 extends PacketEncoder.Base<ResourcePackClientResponse> {

  @Override
  public void decode(@NotNull final ResourcePackClientResponse packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.status(ResourcePackClientResponse.Status.byOrdinal(buffer.readUnsignedByte()));
    buffer.readArrayShortLE(buffer::readString);
  }

  @Override
  public void encode(@NotNull final ResourcePackClientResponse packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeByte(packet.status().ordinal());
    buffer.writeArrayShortLE(packet.packIds(), buffer::writeString);
  }
}
