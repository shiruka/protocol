package io.github.shiruka.protocol;

import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.UnknownPacket;
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
   * @param login the login to handle.
   */
  default void handle(@NotNull final Login login) {
  }

  /**
   * handles the packet.
   *
   * @param login the login to handle.
   */
  default void handle(@NotNull final UnknownPacket login) {
  }
}
