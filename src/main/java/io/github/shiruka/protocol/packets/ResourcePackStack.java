package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.ExperimentData;
import io.github.shiruka.protocol.data.ResourcePackStackEntry;
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
public final class ResourcePackStack extends MinecraftPacket {

  //@formatter:off
  private final List<ResourcePackStackEntry> behaviorPacks = new ObjectArrayList<>();
  private final List<ExperimentData> experiments = new ObjectArrayList<>();
  private final List<ResourcePackStackEntry> resourcePacks = new ObjectArrayList<>();
  private boolean experimentsPreviouslyToggled;
  private boolean forcedToAccept;
  private String gameVersion;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
