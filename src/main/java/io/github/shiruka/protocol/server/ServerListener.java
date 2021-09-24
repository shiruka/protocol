package io.github.shiruka.protocol.server;

import io.github.shiruka.protocol.MinecraftPacket;
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
   * runs when a Minecraft user disconnects from the server.
   *
   * @param session the session to run.
   */
  default void onDisconnect(@NotNull final MinecraftServerSession session) {
  }

  /**
   * runs when a Minecraft user wants to connect to the server.
   *
   * @param session the session to run.
   */
  default void onConnect(@NotNull final MinecraftServerSession session) {
  }

  /**
   * runs after a Minecraft packets comes.
   *
   * @param packet the packet to run.
   * @param session the session to run.
   */
  default void postPacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftServerSession session) {
  }

  /**
   * runs before a Minecraft packets comes.
   *
   * @param packet the packet to run.
   * @param session the session to run.
   */
  default void prePacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftServerSession session) {
  }
}
