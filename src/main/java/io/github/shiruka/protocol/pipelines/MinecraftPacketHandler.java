package io.github.shiruka.protocol.pipelines;

import io.github.shiruka.protocol.MinecraftPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * a class that represents minecraft packet handler pipelines.
 */
@RequiredArgsConstructor
public final class MinecraftPacketHandler extends SimpleChannelInboundHandler<List<MinecraftPacket>> {

  /**
   * the name.
   */
  public static final String NAME = "rn-mc-handler";

  @Override
  protected void channelRead0(final ChannelHandlerContext ctx, final List<MinecraftPacket> msg) {
    for (final var packet : msg) {
      System.out.println(packet);
//      packet.handle(this.handler);
    }
  }
}
