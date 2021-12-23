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
 * a class that represents tick sync packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class TickSync extends MinecraftPacket {

  /**
   * the request timestamp.
   */
  @Getter
  private long requestTimestamp;

  /**
   * the response timestamp.
   */
  @Getter
  private long responseTimestamp;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.requestTimestamp = buffer.readLongLE();
    this.responseTimestamp = buffer.readLongLE();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeLongLE(this.requestTimestamp());
    buffer.writeLongLE(this.responseTimestamp);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
