package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents entity definition trigger event data.
 *
 * @param eventName the event name.
 */
public record EntityDefinitionTriggerEventData(
  String eventName
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.ENTITY_DEFINITION_TRIGGER;
  }
}
