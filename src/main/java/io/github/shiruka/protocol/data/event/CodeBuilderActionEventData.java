package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents code builder action event data.
 *
 * @param action the action.
 */
public record CodeBuilderActionEventData(String action) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.CODE_BUILDER_ACTION;
  }
}
