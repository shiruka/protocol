package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents boss killed event data.
 *
 * @param bossEntityType the boss entity type.
 * @param bossUniqueEntityId the boss unique entity id.
 * @param playerPartySize the player party size.
 */
public record BossKilledEventData(
  int bossEntityType,
  long bossUniqueEntityId,
  int playerPartySize
)
  implements Event.Data {
  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.BOSS_KILLED;
  }
}
