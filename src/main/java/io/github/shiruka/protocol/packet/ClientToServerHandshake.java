package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents client to server handshake packets.
 */
public final class ClientToServerHandshake extends MinecraftPacket.Base {

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
