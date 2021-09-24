package io.github.shiruka.protocol.packets;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents unknown packets.
 */
@Accessors(fluent = true)
public final class Unknown extends MinecraftPacket {

  /**
   * the payload.
   */
  @Nullable
  private PacketBuffer payload;

  /**
   * ctor.
   */
  public Unknown() {
  }

  /**
   * ctor.
   *
   * @param payload the payload.
   */
  public Unknown(@Nullable final PacketBuffer payload) {
    this.payload = payload;
  }

  @Override
  public void decode(@NotNull final PacketBuffer buffer) {
    this.payload = buffer.readRetainedSlice(buffer.remaining());
  }

  @Override
  public void encode(@NotNull final PacketBuffer buffer) {
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
