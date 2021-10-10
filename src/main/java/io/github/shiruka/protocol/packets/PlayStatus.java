package io.github.shiruka.protocol.packets;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
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
  public void decode(@NotNull final PacketBuffer buffer) {
    this.status = PlayStatusStatus.byOrdinal(buffer.readInt());
  }

  @Override
  public void encode(@NotNull final PacketBuffer buffer) {
    buffer.writeInt(this.status().ordinal());
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
