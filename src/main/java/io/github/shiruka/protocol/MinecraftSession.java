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
}
