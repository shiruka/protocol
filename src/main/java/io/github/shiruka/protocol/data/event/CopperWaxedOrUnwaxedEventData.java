package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.data.BlockDefinition;
import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents copper waxed or unwaxed event data.
 *
 * @param definition the definition.
 */
public record CopperWaxedOrUnwaxedEventData(BlockDefinition definition)
  implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.COPPER_WAXED_OR_UNWAXED;
  }
}
