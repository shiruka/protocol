package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents agent command event data.
 *
 * @param command the command.
 * @param dataKey the data key.
 * @param dataValue the data value.
 * @param output the output.
 * @param result the result.
 */
public record AgentCommandEventData(
  String command,
  String dataKey,
  int dataValue,
  String output,
  AgentResult result
)
  implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.AGENT_COMMAND;
  }
}
