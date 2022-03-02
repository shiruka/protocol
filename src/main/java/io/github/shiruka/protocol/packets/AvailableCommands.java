package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.command.CommandData;
import io.github.shiruka.protocol.data.command.CommandEnumConstraintData;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents available commands packets.
 */
@Getter
@Setter
@ToString
@Accessors(fluent = true)
public class AvailableCommands extends MinecraftPacket {

  //@formatter:off
  private List<CommandData> commands;
  private List<CommandEnumConstraintData> constraints;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
