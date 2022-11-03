package io.github.shiruka.protocol.server.channels;

import io.github.shiruka.network.server.channels.RakNetServerChannel;
import io.github.shiruka.protocol.server.MinecraftServer;
import java.net.InetSocketAddress;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents Minecraft server channels.
 */
@RequiredArgsConstructor
public final class MinecraftServerChannel extends RakNetServerChannel {

  /**
   * the server.
   */
  @NotNull
  private final MinecraftServer server;

  @NotNull
  @Override
  public MinecraftChildChannel newChild(
    @NotNull final InetSocketAddress address
  ) {
    return new MinecraftChildChannel(this, address, this.server);
  }
}
