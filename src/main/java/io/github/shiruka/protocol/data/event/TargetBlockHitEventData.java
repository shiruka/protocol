package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents target block hit event data.
 *
 * @param redstoneLevel the redstone level.
 */
public record TargetBlockHitEventData(
  int redstoneLevel
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.TARGET_BLOCK_HIT;
  }
}
