package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.command.CommandData;
import io.github.shiruka.protocol.data.command.CommandEnumConstraintData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
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

  /**
   * the commands.
   */
  private final List<CommandData> commands = new ObjectArrayList<>();

  /**
   * the constraints.
   */
  private final List<CommandEnumConstraintData> constraints = new ObjectArrayList<>();

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
