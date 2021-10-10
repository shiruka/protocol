package io.github.shiruka.protocol.packets;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.netty.util.AsciiString;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents login packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class Login extends MinecraftPacket {

  /**
   * the chain data.
   */
  @Nullable
  private AsciiString chainData;

  /**
   * the protocol version.
   */
  @Getter
  private int protocolVersion;

  /**
   * the skin data.
   */
  @Nullable
  private AsciiString skinData;

  /**
   * obtains the chain data.
   *
   * @return chain data.
   */
  @NotNull
  public AsciiString chainData() {
    return Objects.requireNonNull(this.chainData, "chain data");
  }

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.protocolVersion = buffer.readInt();
    final var jwt = new PacketBuffer(buffer.readSlice());
    this.chainData = jwt.readLEAsciiString();
    this.skinData = jwt.readLEAsciiString();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    throw new UnsupportedOperationException("Encoding not supported yet!");
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the skin data.
   *
   * @return skin data.
   */
  @NotNull
  public AsciiString skinData() {
    return Objects.requireNonNull(this.skinData, "skin data");
  }
}
