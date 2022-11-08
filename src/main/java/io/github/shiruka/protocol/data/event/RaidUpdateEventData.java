package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents raid update event data.
 *
 * @param currentWave the current wave.
 * @param totalWaves the total waves.
 * @param winner the winner.
 */
public record RaidUpdateEventData(
  int currentWave,
  int totalWaves,
  boolean winner
)
  implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.RAID_UPDATE;
  }
}
