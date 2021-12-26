package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.ResourcePackClientResponseStatus;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack client response packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class ResourcePackClientResponse extends MinecraftPacket {

  /**
   * the pack ids.
   */
  private final List<String> packIds = new ObjectArrayList<>();

  /**
   * the status.
   */
  private ResourcePackClientResponseStatus status;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
