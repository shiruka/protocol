package io.github.shiruka.protocol.server;

import io.github.shiruka.protocol.MinecraftSession;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Minecraft server sessions.
 */
public interface MinecraftServerSession extends MinecraftSession {

  /**
   * obtains the server.
   *
   * @return server.
   */
  @NotNull
  MinecraftServer server();
}
