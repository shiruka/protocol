package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
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

  //@formatter:off
  private AsciiString chainData;
  private int protocolVersion;
  private AsciiString skinData;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
