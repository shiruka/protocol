package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents disconnect packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class Disconnect extends MinecraftPacket {

  /**
   * the kick message.
   */
  private String kickMessage;

  /**
   * the message skipped.
   */
  private boolean messageSkipped;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
