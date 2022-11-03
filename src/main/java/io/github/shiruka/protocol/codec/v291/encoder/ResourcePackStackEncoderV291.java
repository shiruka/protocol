package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.ResourcePackStack;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack stack packet encoders.
 */
@PacketId(7)
public final class ResourcePackStackEncoderV291
  extends PacketEncoder.Base<ResourcePackStack> {

  @Override
  public void decode(
    @NotNull final ResourcePackStack packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.forcedToAccept(buffer.readBoolean());
    packet.behaviorPacks(
      buffer.readArrayUnsignedInt(() ->
        helper.readResourcePackStackEntry(buffer)
      )
    );
    packet.resourcePacks(
      buffer.readArrayUnsignedInt(() ->
        helper.readResourcePackStackEntry(buffer)
      )
    );
  }

  @Override
  public void encode(
    @NotNull final ResourcePackStack packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeBoolean(packet.forcedToAccept());
    buffer.writeArrayShortLE(
      packet.behaviorPacks(),
      entry -> helper.writeResourcePackStackEntry(buffer, entry)
    );
    buffer.writeArrayShortLE(
      packet.resourcePacks(),
      entry -> helper.writeResourcePackStackEntry(buffer, entry)
    );
  }
}
