package io.github.shiruka.protocol.packets;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents unknown packets.
 */
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public final class Unknown extends MinecraftPacket {

  /**
   * the payload.
   */
  @Nullable
  private PacketBuffer payload;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.payload = buffer.readRetainedSlice(buffer.remaining());
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    final var payload = this.payload();
    buffer.writeBytes(payload, payload.readerIndex(), payload.remaining());
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the payload.
   *
   * @return payload.
   */
  @NotNull
  public PacketBuffer payload() {
    return Objects.requireNonNull(this.payload, "payload");
  }
}
