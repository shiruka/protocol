package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
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
public final class SetTime extends MinecraftPacket {

  /**
   * the time.
   */
  @Getter
  private int time;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.time = buffer.readVarInt();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeVarInt(this.time);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
