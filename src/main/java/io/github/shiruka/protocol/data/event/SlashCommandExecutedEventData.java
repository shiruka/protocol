package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents slash command executed event data.
 *
 * @param commandName the command name.
 * @param outputMessages the output messages.
 * @param successCount the success count.
 */
public record SlashCommandExecutedEventData(
  String commandName,
  List<String> outputMessages,
  int successCount
)
  implements Event.Data {
  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.SLASH_COMMAND_EXECUTED;
  }
}
