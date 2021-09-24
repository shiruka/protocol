package io.github.shiruka.protocol.server.channels;

import io.github.shiruka.network.server.channels.RakNetServerChannel;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.netty.channel.socket.DatagramChannel;
import java.net.InetSocketAddress;
import java.util.function.Supplier;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents Minecraft server channels.
 */
@Accessors(fluent = true)
public final class MinecraftServerChannel extends RakNetServerChannel {

  /**
   * the server.
   */
  @NotNull
  private final MinecraftServer server;

  /**
   * ctor.
   *
   * @param server the server.
   */
  public MinecraftServerChannel(@NotNull final MinecraftServer server) {
    super();
    this.server = server;
  }

  /**
   * ctor.
   *
   * @param supplier the supplier.
   * @param server the server.
   */
  public MinecraftServerChannel(@NotNull final Supplier<? extends DatagramChannel> supplier,
                                @NotNull final MinecraftServer server) {
    super(supplier);
    this.server = server;
  }

  /**
   * ctor.
   *
   * @param cls the cls.
   * @param server the server.
   */
  public MinecraftServerChannel(@NotNull final Class<? extends DatagramChannel> cls,
                                @NotNull final MinecraftServer server) {
    super(cls);
    this.server = server;
  }

  @NotNull
  @Override
  public MinecraftChildChannel newChild(@NotNull final InetSocketAddress address) {
    return new MinecraftChildChannel(this, address, this.server);
  }
}
