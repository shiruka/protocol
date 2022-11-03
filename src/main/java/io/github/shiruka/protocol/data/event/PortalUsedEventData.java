package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents portal used event data.
 *
 * @param fromDimensionId the from dimension id.
 * @param toDimensionId the to dimension id.
 */
public record PortalUsedEventData(int fromDimensionId, int toDimensionId)
  implements Event.Data {
  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.PORTAL_USED;
  }
}
