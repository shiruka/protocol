package io.github.shiruka.protocol.server;

import io.github.shiruka.network.Constants;
import io.github.shiruka.network.Identifier;
import io.github.shiruka.network.options.RakNetChannelOptions;
import io.github.shiruka.protocol.common.Codec;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import io.github.shiruka.protocol.server.channels.MinecraftServerChannel;
import io.github.shiruka.protocol.server.pipelines.MinecraftServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents minecraft server sessions.
 */
@Accessors(fluent = true)
@RequiredArgsConstructor
public final class MinecraftServer implements ServerListener, Identifier {

  /**
   * the address.
   */
  @NotNull
  @Getter
  private final InetSocketAddress address;

  /**
   * the codec.
   */
  @NotNull
  @Getter
  private final Codec codec;

  /**
   * the server id.
   */
  @Getter
  private final long serverId = Constants.RANDOM.nextLong();

  /**
   * the bootstrap.
   */
  private final ServerBootstrap bootstrap = new ServerBootstrap()
    .group(new NioEventLoopGroup())
    .channelFactory(() -> new MinecraftServerChannel(this))
    .option(RakNetChannelOptions.SERVER_ID, this.serverId)
    .option(RakNetChannelOptions.SERVER_IDENTIFIER, this)
    .childHandler(new MinecraftServerInitializer(this));

  /**
   * the sessions.
   */
  private final Map<InetSocketAddress, MinecraftChildChannel> sessions = new HashMap<>();

  /**
   * the default Minecraft server session packet handler.
   */
  @NotNull
  @Getter
  @Setter
  private Function<MinecraftChildChannel, PacketHandler> defaultPacketHandler = session -> new PacketHandler() {
  };

  /**
   * the max connections.
   */
  @Getter
  @Setter
  private int maxConnections = 1024;

  /**
   * the identifier.
   */
  @NotNull
  @Getter
  @Setter
  private String motd = "Minecraft Server";

  /**
   * the server listener.
   */
  @NotNull
  @Getter
  @Setter
  private ServerListener serverListener = ServerListener.EMPTY;

  /**
   * ctor.
   *
   * @param codec the codec.
   */
  public MinecraftServer(@NotNull final Codec codec) {
    this(new InetSocketAddress("127.0.0.1", 19132), codec);
  }

  /**
   * binds the server.
   */
  public void bind() {
    this.bootstrap.bind(this.address)
      .syncUninterruptibly()
      .addListener((ChannelFutureListener) future -> {
        if (future.isSuccess()) {
          MinecraftServer.this.serverListener.onStart();
        }
      });
  }

  @NotNull
  @Override
  public String build() {
    return new StringJoiner(";", "", ";")
      .add("MCPE")
      .add(this.motd)
      .add(this.codec.protocolVersionAsString())
      .add(this.codec.minecraftVersion())
      .add(String.valueOf(this.sessions.size()))
      .add(String.valueOf(this.maxConnections))
      .add(String.valueOf(this.serverId))
      .toString();
  }

  @Override
  public void onConnect(@NotNull final MinecraftChildChannel session) {
    this.sessions.put(session.address(), session);
    this.serverListener.onConnect(session);
  }

  @Override
  public void onDisconnect(@NotNull final MinecraftChildChannel session) {
    this.sessions.remove(session.address());
    this.serverListener.onDisconnect(session);
  }

  @Override
  public void postPacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftChildChannel session) {
    this.serverListener.postPacket(packet, session);
  }

  @Override
  public void prePacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftChildChannel session) {
    this.serverListener.prePacket(packet, session);
  }
}
