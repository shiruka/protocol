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
 * a class that represents take item entity packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class TakeItemEntity extends MinecraftPacket {

  /**
   * the item runtime entity id.
   */
  @Getter
  private long itemRuntimeEntityId;

  /**
   * the runtime entity id.
   */
  @Getter
  private long runtimeEntityId;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.itemRuntimeEntityId = buffer.readUnsignedVarLong();
    this.runtimeEntityId = buffer.readUnsignedVarLong();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeUnsignedVarLong(this.itemRuntimeEntityId);
    buffer.writeUnsignedVarLong(this.runtimeEntityId);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
