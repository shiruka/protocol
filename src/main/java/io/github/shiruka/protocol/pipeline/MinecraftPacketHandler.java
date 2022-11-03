package io.github.shiruka.protocol.pipeline;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents minecraft packet handler pipelines.
 */
@RequiredArgsConstructor
public final class MinecraftPacketHandler
  extends SimpleChannelInboundHandler<List<MinecraftPacket>> {

  /**
   * the name.
   */
  public static final String NAME = "rn-mc-handler";

  /**
   * the server.
   */
  @NotNull
  private final MinecraftServer server;

  @Override
  protected void channelRead0(
    final ChannelHandlerContext ctx,
    final List<MinecraftPacket> msg
  ) {
    final var session = MinecraftChildChannel.cast(ctx);
    for (final var packet : msg) {
      this.server.prePacket(packet, session);
      packet.handle(session.packetHandler());
      this.server.postPacket(packet, session);
    }
  }
}
