package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.netty.util.AsciiString;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents login packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class Login extends MinecraftPacket {

  /**
   * the chain data.
   */
  private AsciiString chainData;

  /**
   * the protocol version.
   */
  private int protocolVersion;

  /**
   * the skin data.
   */
  private AsciiString skinData;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
