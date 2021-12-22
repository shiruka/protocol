package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents server to client handshake packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class ServerToClientHandshake extends MinecraftPacket {

  /**
   * the jwt.
   */
  @Nullable
  private String jwt;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.jwt = buffer.readString();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeString(this.jwt());
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the jwt.
   *
   * @return jwt.
   */
  @NotNull
  public String jwt() {
    return Objects.requireNonNull(this.jwt, "jwt");
  }
}
