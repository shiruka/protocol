package io.github.shiruka.protocol.server.pipelines;

import io.github.shiruka.network.pipelines.UserDataCodec;
import io.github.shiruka.protocol.pipeline.MinecraftPacketCodec;
import io.github.shiruka.protocol.pipeline.MinecraftPacketHandler;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents minecraft server initializers.
 */
@RequiredArgsConstructor
public final class MinecraftServerInitializer
  extends ChannelInitializer<Channel> {

  /**
   * the server.
   */
  @NotNull
  private final MinecraftServer server;

  @Override
  protected void initChannel(@NotNull final Channel ch) {
    ch
      .pipeline()
      .addLast(
        MinecraftServerConnection.NAME,
        new MinecraftServerConnection(this.server)
      )
      .addLast(UserDataCodec.NAME, new UserDataCodec(0xFE))
      .addLast(MinecraftPacketCodec.NAME, new MinecraftPacketCodec(this.server))
      .addLast(
        MinecraftPacketHandler.NAME,
        new MinecraftPacketHandler(this.server)
      );
  }
}
