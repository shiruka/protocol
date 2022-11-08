package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.data.ItemDefinition;
import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents pinglin barter event data.
 *
 * @param definition the definition.
 * @param targetingPlayer the targeting player.
 */
public record PiglinBarterEventData(
  ItemDefinition definition,
  boolean targetingPlayer
)
  implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.PIGLIN_BARTER;
  }
}
