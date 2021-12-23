package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents packet template packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class BlockEvent extends MinecraftPacket {

  /**
   * the block position.
   */
  @Nullable
  private Vector3i blockPosition;

  /**
   * the event data.
   */
  @Getter
  private int eventData;

  /**
   * the event type.
   */
  @Getter
  private int eventType;

  /**
   * obtains the block position.
   *
   * @return block position.
   */
  @NotNull
  public Vector3i blockPosition() {
    return Objects.requireNonNull(this.blockPosition, "block position");
  }

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.blockPosition = buffer.readBlockPosition();
    this.eventType = buffer.readVarInt();
    this.eventData = buffer.readVarInt();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeBlockPosition(this.blockPosition());
    buffer.writeVarInt(this.eventType);
    buffer.writeVarInt(this.eventData);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
