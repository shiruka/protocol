package io.github.shiruka.protocol.packets;

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
 * a class that represents play status packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class Disconnect extends MinecraftPacket {

  /**
   * the kick message.
   */
  @Nullable
  private String kickMessage;

  /**
   * the message skipped.
   */
  @Getter
  private boolean messageSkipped;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.messageSkipped = buffer.readBoolean();
    if (!this.messageSkipped) {
      this.kickMessage = buffer.readString();
    }
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeBoolean(this.messageSkipped);
    if (!this.messageSkipped) {
      buffer.writeString(this.kickMessage());
    }
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the kick message.
   *
   * @return kick message.
   */
  @NotNull
  public String kickMessage() {
    return Objects.requireNonNull(this.kickMessage, "kick message");
  }
}
