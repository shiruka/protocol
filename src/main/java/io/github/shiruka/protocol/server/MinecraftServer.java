package io.github.shiruka.protocol.server;

import io.netty.bootstrap.ServerBootstrap;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents minecraft server sessions.
 */
@RequiredArgsConstructor
@Accessors(fluent = true)
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

  /**
   * the sessions.
   */
  private final Map<InetSocketAddress, MinecraftServerSession> sessions = new HashMap<>();

  /**
   * the server listener.
   */
  @NotNull
  @Getter
  @Setter
  private ServerListener serverListener = ServerListener.EMPTY;
}
