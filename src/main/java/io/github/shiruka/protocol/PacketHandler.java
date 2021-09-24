package io.github.shiruka.protocol;

import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.Unknown;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine packet handlers.
 */
public interface PacketHandler {

  /**
   * the empty packet handler.
   */
  PacketHandler EMPTY = new PacketHandler() {
  };

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Login packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Unknown packet) {
  }
}
