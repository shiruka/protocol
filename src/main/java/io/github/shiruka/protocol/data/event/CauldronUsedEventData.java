package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents cauldron used event data.
 */
public record CauldronUsedEventData(
  int color,
  int fillLevel,
  int potionId
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.CAULDRON_USED;
  }
}
