package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.PlayStatusStatus;
import java.util.Objects;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents play status packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class PlayStatus extends MinecraftPacket {

  /**
   * the status.
   */
  @Nullable
  private PlayStatusStatus status;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.status = buffer.readPlayStatusStatus();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writePlayStatusStatus(this.status());
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the status.
   *
   * @return status.
   */
  @NotNull
  public PlayStatusStatus status() {
    return Objects.requireNonNull(this.status, "status");
  }
}
