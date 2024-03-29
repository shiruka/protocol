package io.github.shiruka.protocol.common;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;
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
   * obtains the dynamic blocking id.
   *
   * @return dynamic blocking id.
   */
  @NotNull
  AtomicInteger dynamicBlockingId();

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
