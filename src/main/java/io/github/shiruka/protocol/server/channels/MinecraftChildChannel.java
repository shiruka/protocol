package io.github.shiruka.protocol.server.channels;

import io.github.shiruka.network.server.channels.RakNetChildChannel;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.MinecraftServerSession;
import io.netty.channel.ChannelHandlerContext;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents Minecraft child channels.
 */
@Accessors(fluent = true)
public final class MinecraftChildChannel extends RakNetChildChannel implements MinecraftServerSession {

  /**
   * the dynamic blocking id.
   */
  @Getter
  private final AtomicInteger dynamicBlockingId = new AtomicInteger(-1);

  /**
   * the server.
   */
  @NotNull
  @Getter
  private final MinecraftServer server;

  /**
   * the packet handler.
   */
  @NotNull
  private volatile PacketHandler packetHandler;

  /**
   * ctor.
   *
   * @param parent the parent.
   * @param address the address.
   * @param server the server.
   */
  public MinecraftChildChannel(@NotNull final MinecraftServerChannel parent, @NotNull final InetSocketAddress address,
                               @NotNull final MinecraftServer server) {
    super(parent, address);
    this.server = server;
    this.packetHandler = server.defaultPacketHandler();
  }

  /**
   * casts the context's channel as {@code this}.
   *
   * @param ctx the ctx to cast.
   *
   * @return Minecraft child channel.
   */
  @NotNull
  public static MinecraftChildChannel cast(@NotNull final ChannelHandlerContext ctx) {
    return (MinecraftChildChannel) ctx.channel();
  }

  @NotNull
  @Override
  public InetSocketAddress address() {
    return this.remoteAddress0();
  }

  @NotNull
  @Override
  public synchronized MinecraftSession packetHandler(@NotNull final PacketHandler packetHandler) {
    this.packetHandler = packetHandler;
    return this;
  }

  @NotNull
  @Override
  public synchronized PacketHandler packetHandler() {
    return this.packetHandler;
  }
}
