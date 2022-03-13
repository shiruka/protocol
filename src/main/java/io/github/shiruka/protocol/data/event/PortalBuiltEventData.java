package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents portal built event data.
 */
public record PortalBuiltEventData(
  int dimensionId
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.PORTAL_BUILT;
  }
}
