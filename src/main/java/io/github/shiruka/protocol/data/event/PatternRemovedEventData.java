package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents pattern removed event data.
 *
 * @param auxValue the aux value.
 * @param itemId the item id.
 * @param patternColor the pattern color.
 * @param patternIndex the pattern index.
 * @param patternsSize the pattern size.
 */
public record PatternRemovedEventData(
  int auxValue,
  int itemId,
  int patternColor,
  int patternIndex,
  int patternsSize
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.PATTERN_REMOVED;
  }
}
