package io.github.shiruka.protocol.server;

import io.netty.bootstrap.ServerBootstrap;
import java.net.InetSocketAddress;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents minecraft server sessions.
 */
@RequiredArgsConstructor
public final class MinecraftServer {

  /**
   * the address.
   */
  @NotNull
  private final InetSocketAddress address;

  /**
   * the bootstrap.
   */
  @NotNull
  private final ServerBootstrap bootstrap;
}
