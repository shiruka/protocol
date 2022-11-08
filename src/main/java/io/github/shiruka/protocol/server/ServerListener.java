package io.github.shiruka.protocol.server;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine server listeners.
 */
public interface ServerListener {

  /**
   * the empty server listener.
   */
  ServerListener EMPTY = new ServerListener() {
  };

  /**
   * runs when a Minecraft user wants to connect to the server.
   *
   * @param session the session to run.
   */
  default void onConnect(@NotNull final MinecraftChildChannel session) {
  }

  /**
   * runs when a Minecraft user disconnects from the server.
   *
   * @param session the session to run.
   */
  default void onDisconnect(@NotNull final MinecraftChildChannel session) {
  }

  /**
   * runs when the server is ready to accept connections.
   */
  default void onStart() {
  }

  /**
   * runs after a Minecraft packets comes.
   *
   * @param packet the packet to run.
   * @param session the session to run.
   */
  default void postPacket(
    @NotNull final MinecraftPacket packet,
    @NotNull final MinecraftChildChannel session
  ) {
  }

  /**
   * runs before a Minecraft packets comes.
   *
   * @param packet the packet to run.
   * @param session the session to run.
   */
  default void prePacket(
    @NotNull final MinecraftPacket packet,
    @NotNull final MinecraftChildChannel session
  ) {
  }
}
