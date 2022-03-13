package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents fish bucketed event data.
 *
 * @param bucketedEntityType the bucketed entity type.
 * @param pattern the pattern.
 * @param preset the preset.
 * @param releaseEvent the release event.
 */
public record FishBucketedEventData(
  int bucketedEntityType,
  int pattern,
  int preset,
  boolean releaseEvent
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.FISH_BUCKETED;
  }
}
