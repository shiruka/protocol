package io.github.shiruka.protocol.server.pipelines;

import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents Minecraft connection pipelines.
 */
@RequiredArgsConstructor
public final class MinecraftServerConnection extends ChannelInboundHandlerAdapter {

  /**
   * the name.
   */
  public static final String NAME = "rn-mc-server-connect";

  /**
   * the server.
   */
  @NotNull
  private final MinecraftServer server;

  @Override
  public void channelActive(final ChannelHandlerContext ctx) throws Exception {
    this.server.onConnect(MinecraftChildChannel.cast(ctx));
    super.channelActive(ctx);
  }

  @Override
  public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
    this.server.onDisconnect(MinecraftChildChannel.cast(ctx));
    super.channelInactive(ctx);
  }
}
