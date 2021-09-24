package io.github.shiruka.protocol.packets;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import org.jetbrains.annotations.NotNull;

public final class Login extends MinecraftPacket {

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
