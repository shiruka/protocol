package io.github.shiruka.protocol.server;

import io.github.shiruka.network.server.channels.RakNetChildChannel;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.PacketHandler;
import java.net.InetSocketAddress;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents Minecraft server sessions.
 */
@RequiredArgsConstructor
@Accessors(fluent = true)
public final class MinecraftServerSession implements MinecraftSession {

  /**
   * the channel.
   */
  @NotNull
  @Getter
  private final RakNetChildChannel channel;

  /**
   * the packet handler.
   */
  @NotNull
  @Getter
  @Setter
  private PacketHandler packetHandler = PacketHandler.EMPTY;

  @NotNull
  @Override
  public InetSocketAddress address() {
    return (InetSocketAddress) this.channel.remoteAddress();
  }
}
