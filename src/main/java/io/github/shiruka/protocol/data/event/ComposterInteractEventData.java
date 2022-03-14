package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.data.BlockInteractionType;
import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents composter interaction event data.
 *
 * @param blockInteractionType the block interaction type.
 * @param itemId the item id.
 */
public record ComposterInteractEventData(
  BlockInteractionType blockInteractionType,
  int itemId
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.COMPOSTER_INTERACT;
  }
}
