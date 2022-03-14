package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents bell used event data.
 *
 * @param itemId the item id.
 */
public record BellBlockUsedEventData(
  int itemId
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.BELL_USED;
  }
}
