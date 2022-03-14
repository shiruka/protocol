package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents movement corrected event data.
 *
 * @param cheatingScore the cheating score.
 * @param distanceThreshold the distance threshold.
 * @param durationThreshold the duration threshold.
 * @param positionDelta the position delta.
 * @param scoreThreshold the score threshold.
 */
public record MovementCorrectedEventData(
  float cheatingScore,
  float distanceThreshold,
  int durationThreshold,
  float positionDelta,
  float scoreThreshold
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.MOVEMENT_CORRECTED;
  }
}
