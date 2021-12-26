package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents remove entity packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class RemoveEntity extends MinecraftPacket {

  /**
   * the unique entity id.
   */
  private long uniqueEntityId;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
