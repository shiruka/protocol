package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents achievement awarded event data.
 *
 * @param achievementId the achievement id.
 */
public record AchievementAwardedEventData(
  int achievementId
) implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.ACHIEVEMENT_AWARDED;
  }
}
