package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents code builder scoreboard event data.
 */
public record CodeBuilderScoreboardEventData(
  String objectiveName,
  int score
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.CODE_BUILDER_SCOREBOARD;
  }
}
