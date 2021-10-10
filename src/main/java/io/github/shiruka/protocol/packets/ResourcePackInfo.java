package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.ResourcePackInfoEntry;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents play status packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class ResourcePackInfo extends MinecraftPacket {

  /**
   * the behavior pack infos.
   */
  private final List<ResourcePackInfoEntry> behaviorPackInfos = new ObjectArrayList<>();

  /**
   * the resource pack infos.
   */
  private final List<ResourcePackInfoEntry> resourcePackInfos = new ObjectArrayList<>();

  /**
   * the forced to accept.
   */
  private boolean forcedToAccept;

  /**
   * the forcing server packs enabled.
   */
  private boolean forcingServerPacksEnabled;

  /**
   * the scripting enabled.
   */
  private boolean scriptingEnabled;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.forcedToAccept = buffer.readBoolean();
    this.scriptingEnabled = buffer.readBoolean();
    this.forcingServerPacksEnabled = buffer.readBoolean();
    buffer.readArrayShortLE(this.behaviorPackInfos, buffer::readResourcePackEntry);
    buffer.readArrayShortLE(this.resourcePackInfos, buffer::readResourcePackEntryWithRaytracing);
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeBoolean(this.forcedToAccept);
    buffer.writeBoolean(this.scriptingEnabled);
    buffer.writeBoolean(this.forcingServerPacksEnabled);
    buffer.writeArrayShortLE(this.behaviorPackInfos, buffer::writeResourcePackEntry);
    buffer.writeArrayShortLE(this.resourcePackInfos, buffer::writeResourcePackEntryWithRaytracing);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
