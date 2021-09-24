package io.github.shiruka.protocol.pipelines;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents minecraft packet handlers.
 */
@RequiredArgsConstructor
public final class MinecraftPacketHandler extends SimpleChannelInboundHandler<List<MinecraftPacket>> {

  /**
   * the handler.
   */
  @NotNull
  private final PacketHandler handler;

  @Override
  protected void channelRead0(final ChannelHandlerContext ctx, final List<MinecraftPacket> msg) {
    for (final var packet : msg) {
      packet.handle(this.handler);
    }
  }
}
