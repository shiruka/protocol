package io.github.shiruka.protocol.server;

import io.github.shiruka.network.Identifier;
import io.github.shiruka.network.options.RakNetChannelOptions;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.server.channels.MinecraftServerChannel;
import io.github.shiruka.protocol.server.pipelines.MinecraftServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
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
public final class MinecraftServer implements ServerListener {

  private static final String serverInfo = new StringJoiner(";", "", ";")
    .add("MCPE")
    .add("Motd")
    .add("448")
    .add("1.17.11")
    .add("0")
    .add("10")
    .add("1100224433")
    .toString();

  /**
   * the address.
   */
  @NotNull
  @Getter
  private final InetSocketAddress address;

  /**
   * the bootstrap.
   */
  @NotNull
  private final ServerBootstrap bootstrap = new ServerBootstrap()
    .group(new NioEventLoopGroup())
    .channelFactory(() -> new MinecraftServerChannel(this))
    .option(RakNetChannelOptions.SERVER_ID, 1100224433L)
    .option(RakNetChannelOptions.SERVER_IDENTIFIER, Identifier.simple(MinecraftServer.serverInfo))
    .childHandler(new MinecraftServerInitializer(this));

  /**
   * the default packet handler.
   */
  @NotNull
  @Getter
  private final PacketHandler defaultPacketHandler;

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

  /**
   * binds the server.
   */
  public void bind() {
    this.bootstrap.bind(this.address).syncUninterruptibly();
  }

  @Override
  public void onConnect(@NotNull final MinecraftServerSession session) {
    this.sessions.put(session.address(), session);
    this.serverListener.onConnect(session);
  }

  @Override
  public void prePacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftServerSession session) {
    this.serverListener.prePacket(packet, session);
  }

  @Override
  public void postPacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftServerSession session) {
    this.serverListener.postPacket(packet, session);
  }
}
