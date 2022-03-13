package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.ExperimentData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack stack packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class ResourcePackStack extends MinecraftPacket.Base {

  //@formatter:off
  private List<Entry> behaviorPacks = new ObjectArrayList<>();
  private List<ExperimentData> experiments = new ObjectArrayList<>();
  private boolean experimentsPreviouslyToggled;
  private boolean forcedToAccept;
  private String gameVersion;
  private List<Entry> resourcePacks = new ObjectArrayList<>();
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * a record class that represents resource pack stack entries.
   *
   * @param packId the pack id.
   * @param packVersion the pack version.
   * @param subPackName the sub pack name.
   */
  public record Entry(
    @NotNull String packId,
    @NotNull String packVersion,
    @NotNull String subPackName
  ) {

  }
}
