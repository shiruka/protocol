package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents entity interact event data.
 *
 * @param interactionType the interaction type.
 * @param legacyEntityTypeId the legacy entity type id.
 * @param paletteColor the palette color.
 * @param variant the variant.
 */
public record EntityInteractEventData(
  int interactionType,
  int legacyEntityTypeId,
  int paletteColor,
  int variant
)
  implements Event.Data {
  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.ENTITY_INTERACT;
  }
}
