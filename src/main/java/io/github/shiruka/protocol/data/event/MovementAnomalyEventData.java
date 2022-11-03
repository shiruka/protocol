package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents movement anomaly event data.
 *
 * @param cheatingScore the cheating score.
 * @param averagePositionDelta the average position delta.
 * @param eventType the event type.
 * @param maxPositionDelta the max position delta.
 * @param minPositionDelta the min position delta.
 * @param totalPositionDelta the total position delta.
 */
public record MovementAnomalyEventData(
  float averagePositionDelta,
  float cheatingScore,
  int eventType,
  float maxPositionDelta,
  float minPositionDelta,
  float totalPositionDelta
)
  implements Event.Data {
  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.MOVEMENT_ANOMALY;
  }
}
