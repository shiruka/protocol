package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents packet template packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class BlockEvent extends MinecraftPacket {

  //@formatter:off
  private Vector3i blockPosition;
  private int eventData;
  private int eventType;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
