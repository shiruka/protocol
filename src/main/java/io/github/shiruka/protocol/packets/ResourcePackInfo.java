package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack info packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class ResourcePackInfo extends MinecraftPacket.Base {

  //@formatter:off
  private List<Entry> behaviorPackInfos = new ObjectArrayList<>();
  private boolean forcedToAccept;
  private boolean forcingServerPacksEnabled;
  private List<Entry> resourcePackInfos = new ObjectArrayList<>();
  private boolean scriptingEnabled;
  //@formatter:on

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
  public record Entry(
    @NotNull String packId,
    @NotNull String packVersion,
    long packSize,
    @NotNull String contentKey,
    @NotNull String subPackName,
    @NotNull String contentId,
    boolean scripting,
    boolean raytracingCapable
  ) {

    /**
     * sets the raytracing capable.
     *
     * @param raytracingCapable the raytracing capable to set.
     *
     * @return creates a new entry instance.
     */
    @NotNull
    public Entry raytracingCapable(final boolean raytracingCapable) {
      return new Entry(this.packId, this.packVersion, this.packSize, this.contentKey, this.subPackName,
        this.contentId, this.scripting, raytracingCapable);
    }
  }
}
