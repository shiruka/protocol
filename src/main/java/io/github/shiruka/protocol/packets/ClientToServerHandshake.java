package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents client to server handshake packets.
 */
public final class ClientToServerHandshake extends MinecraftPacket {

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
