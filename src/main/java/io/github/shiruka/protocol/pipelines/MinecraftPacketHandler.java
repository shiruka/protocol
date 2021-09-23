package io.github.shiruka.protocol.pipelines;

import io.github.shiruka.protocol.MinecraftPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * a class that represents minecraft packet handlers.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MinecraftPacketHandler extends SimpleChannelInboundHandler<List<MinecraftPacket>> {

  /**
   * the instance.
   */
  public static final MinecraftPacketHandler INSTANCE = new MinecraftPacketHandler();

  @Override
  protected void channelRead0(final ChannelHandlerContext ctx, final List<MinecraftPacket> msg) {
    for (final var packet : msg) {
    }
  }
}
