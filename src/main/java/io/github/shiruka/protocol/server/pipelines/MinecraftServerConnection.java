package io.github.shiruka.protocol.server.pipelines;

import io.github.shiruka.network.server.channels.RakNetChildChannel;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.MinecraftServerSession;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents Minecraft connection pipelines.
 */
@RequiredArgsConstructor
public final class MinecraftServerConnection extends ChannelDuplexHandler {

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
  public void channelActive(final ChannelHandlerContext ctx) {
    this.server.onConnect(new MinecraftServerSession((RakNetChildChannel) ctx.channel()));
  }
}
