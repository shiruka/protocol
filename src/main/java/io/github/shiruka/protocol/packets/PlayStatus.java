package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.PlayStatusStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents play status packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class PlayStatus extends MinecraftPacket {

  /**
   * the status.
   */
  private PlayStatusStatus status;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
