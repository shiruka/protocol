package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents mob born event data.
 *
 * @param color the color.
 * @param entityType the entity type.
 * @param variant the variant.
 */
public record MobBornEventData(int color, int entityType, int variant)
  implements Event.Data {
  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.MOB_BORN;
  }
}
