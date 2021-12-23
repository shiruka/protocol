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
 * a class that represents rider jump packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class RiderJump extends MinecraftPacket {

  /**
   * teh jump strength.
   */
  @Getter
  private int jumpStrength;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.jumpStrength = buffer.readVarInt();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeUnsignedVarInt(this.jumpStrength);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
