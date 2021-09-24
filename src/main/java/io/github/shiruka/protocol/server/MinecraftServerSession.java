package io.github.shiruka.protocol.server;

import io.github.shiruka.protocol.MinecraftSession;
import java.net.InetSocketAddress;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents Minecraft server sessions.
 */
@RequiredArgsConstructor
@Accessors(fluent = true)
public final class MinecraftServerSession implements MinecraftSession {

  /**
   * the address.
   */
  @NotNull
  @Getter
  private final InetSocketAddress address;
}
