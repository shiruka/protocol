package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packet.ResourcePackInfo;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack info packet encoders.
 */
@PacketId(6)
public final class ResourcePackInfoEncoderV291 extends PacketEncoder.Base<ResourcePackInfo> {

  @Override
  public void decode(@NotNull final ResourcePackInfo packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.forcedToAccept(buffer.readBoolean());
    packet.behaviorPackInfos(buffer.readArrayShortLE(() -> helper.readResourcePackInfoEntry(buffer)));
    packet.resourcePackInfos(buffer.readArrayShortLE(() -> helper.readResourcePackInfoEntry(buffer)));
  }

  @Override
  public void encode(@NotNull final ResourcePackInfo packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeBoolean(packet.forcedToAccept());
    buffer.writeArrayShortLE(packet.behaviorPackInfos(), entry -> helper.writeResourcePackEntry(buffer, entry));
    buffer.writeArrayShortLE(packet.resourcePackInfos(), entry -> helper.writeResourcePackEntry(buffer, entry));
  }
}
