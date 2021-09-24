package io.github.shiruka.protocol;

import java.net.InetSocketAddress;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine Minecraft sessions.
 */
public interface MinecraftSession {

  /**
   * obtains the address.
   *
   * @return address.
   */
  @NotNull
  InetSocketAddress address();

  /**
   * sets the packet handler.
   *
   * @param packetHandler the packet handler to set.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  MinecraftSession packetHandler(@NotNull PacketHandler packetHandler);

  /**
   * obtains the packet handler.
   *
   * @return packet handler.
   */
  @NotNull
  PacketHandler packetHandler();
}
