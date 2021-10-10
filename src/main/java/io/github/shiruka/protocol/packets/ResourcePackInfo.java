package io.github.shiruka.protocol.packets;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
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
  private final List<Entry> behaviorPackInfos = new ObjectArrayList<>();

  /**
   * the resource pack infos.
   */
  private final List<Entry> resourcePackInfos = new ObjectArrayList<>();

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

  /**
   * reads the entry.
   *
   * @param buffer the buffer to read.
   *
   * @return entry.
   */
  @NotNull
  private static Entry readEntry(@NotNull final PacketBuffer buffer) {
    final var packId = buffer.readString();
    final var packVersion = buffer.readString();
    final var packSize = buffer.readLongLE();
    final var contentKey = buffer.readString();
    final var subPackName = buffer.readString();
    final var contentId = buffer.readString();
    final var isScripting = buffer.readBoolean();
    return new Entry(packId, packVersion, packSize, contentKey, subPackName, contentId,
      isScripting, false);
  }

  /**
   * reads the resource pack entry.
   *
   * @param buffer the buffer to read.
   *
   * @return entry.
   */
  @NotNull
  private static Entry readResourcePackEntry(@NotNull final PacketBuffer buffer) {
    final var packId = buffer.readString();
    final var packVersion = buffer.readString();
    final var packSize = buffer.readLongLE();
    final var contentKey = buffer.readString();
    final var subPackName = buffer.readString();
    final var contentId = buffer.readString();
    final var isScripting = buffer.readBoolean();
    final var raytracingCapable = buffer.readBoolean();
    return new Entry(packId, packVersion, packSize, contentKey, subPackName, contentId,
      isScripting, raytracingCapable);
  }

  /**
   * writes the entry.
   *
   * @param buffer the buffer to write.
   * @param entry the entry to write.
   */
  private static void writeEntry(@NotNull final PacketBuffer buffer, @NotNull final Entry entry) {
    buffer.writeString(entry.packId());
    buffer.writeString(entry.packVersion());
    buffer.writeLongLE(entry.packSize());
    buffer.writeString(entry.contentKey());
    buffer.writeString(entry.subPackName());
    buffer.writeString(entry.contentId());
    buffer.writeBoolean(entry.scripting());
  }

  /**
   * writes the resource pack entry.
   *
   * @param buffer the buffer to write.
   * @param entry the entry to write.
   */
  private static void writeResourcePackEntry(@NotNull final PacketBuffer buffer, @NotNull final Entry entry) {
    ResourcePackInfo.writeEntry(buffer, entry);
    buffer.writeBoolean(entry.raytracingCapable());
  }

  @Override
  public void decode(@NotNull final PacketBuffer buffer) {
    this.forcedToAccept = buffer.readBoolean();
    this.scriptingEnabled = buffer.readBoolean();
    this.forcingServerPacksEnabled = buffer.readBoolean();
    buffer.readArrayShortLE(this.behaviorPackInfos, ResourcePackInfo::readEntry);
    buffer.readArrayShortLE(this.resourcePackInfos, ResourcePackInfo::readResourcePackEntry);
  }

  @Override
  public void encode(@NotNull final PacketBuffer buffer) {
    buffer.writeBoolean(this.forcedToAccept);
    buffer.writeBoolean(this.scriptingEnabled);
    buffer.writeBoolean(this.forcingServerPacksEnabled);
    buffer.writeArrayShortLE(this.behaviorPackInfos, ResourcePackInfo::writeEntry);
    buffer.writeArrayShortLE(this.resourcePackInfos, ResourcePackInfo::writeResourcePackEntry);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * a record class that represents resource pack info entries.
   *
   * @param packId the pack id.
   * @param packVersion the pack version.
   * @param packSize the pack size.
   * @param contentKey the content key.
   * @param subPackName the sub pack name.
   * @param contentId the content id.
   * @param scripting the scripting.
   * @param raytracingCapable the raytracing capable.
   */
  public final record Entry(
    @NotNull String packId,
    @NotNull String packVersion,
    long packSize,
    @NotNull String contentKey,
    @NotNull String subPackName,
    @NotNull String contentId,
    boolean scripting,
    boolean raytracingCapable
  ) {

  }
}
