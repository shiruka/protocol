package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents player died event data.
 *
 * @param attackerEntityId the attacker entity id.
 * @param attackerVariant the attacker variant.
 * @param entityDamageCause the entity damage cause.
 * @param inRaid the in raid.
 */
public record PlayerDiedEventData(
  int attackerEntityId,
  int attackerVariant,
  int entityDamageCause,
  boolean inRaid
)
  implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.PLAYER_DIED;
  }
}
