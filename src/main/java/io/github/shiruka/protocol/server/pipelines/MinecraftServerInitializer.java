package io.github.shiruka.protocol.server.pipelines;

import io.github.shiruka.network.pipelines.UserDataCodec;
import io.github.shiruka.protocol.pipelines.CompressorCodec;
import io.github.shiruka.protocol.pipelines.MinecraftPacketCodec;
import io.github.shiruka.protocol.pipelines.MinecraftPacketHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * a class that represents minecraft server initializers.
 */
public final class MinecraftServerInitializer extends ChannelInitializer<Channel> {

  @Override
  protected void initChannel(final Channel ch) {
    ch.pipeline()
      .addLast(UserDataCodec.NAME, new UserDataCodec(0xFE))
      .addLast(CompressorCodec.INSTANCE)
      .addLast(MinecraftPacketCodec.INSTANCE)
      .addLast(MinecraftPacketHandler.INSTANCE);
  }
}
