package io.github.shiruka.protocol.packets;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents play status packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class ClientToServerHandshake extends MinecraftPacket {

  @Override
  public void decode(@NotNull final PacketBuffer buffer) {
  }

  @Override
  public void encode(@NotNull final PacketBuffer buffer) {
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
