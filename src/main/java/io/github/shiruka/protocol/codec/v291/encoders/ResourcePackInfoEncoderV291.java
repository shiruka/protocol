package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.ResourcePackInfo;
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
    packet.behaviorPackInfos(helper.readResourcePackInfoEntries(buffer));
    packet.resourcePackInfos(helper.readResourcePackInfoEntries(buffer));
  }

  @Override
  public void encode(@NotNull final ResourcePackInfo packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeBoolean(packet.forcedToAccept());
    helper.writeResourcePackInfoEntries(buffer, packet.behaviorPackInfos());
    helper.writeResourcePackInfoEntries(buffer, packet.resourcePackInfos());
  }
}
