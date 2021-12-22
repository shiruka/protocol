package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents player auth input packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class PlayerAuthInput extends MinecraftPacket {

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
