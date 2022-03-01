package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.ResourcePackInfoEntry;
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
public final class ResourcePackInfo extends MinecraftPacket {

  //@formatter:off
  private final List<ResourcePackInfoEntry> behaviorPackInfos = new ObjectArrayList<>();
  private final List<ResourcePackInfoEntry> resourcePackInfos = new ObjectArrayList<>();
  private boolean forcedToAccept;
  private boolean forcingServerPacksEnabled;
  private boolean scriptingEnabled;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
