package io.github.shiruka.protocol.server;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftSession;
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
   * runs when a Minecraft packets comes.
   *
   * @param packet the packet to come.
   * @param session the session come from.
   */
  default void onPacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftSession session) {
  }
}