package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents mob killed event data.
 *
 * @param entityDamageCause the entity damage cause.
 * @param killerEntityType the killer entity type.
 * @param killerUniqueEntityId the killer unique entity id.
 * @param victimUniqueEntityId the victim unique entity id.
 * @param villagerDisplayName the villager display name.
 * @param villagerTradeTier the villager trade tier.
 */
public record MobKilledEventData(
  int entityDamageCause,
  int killerEntityType,
  long killerUniqueEntityId,
  long victimUniqueEntityId,
  String villagerDisplayName,
  int villagerTradeTier
)
  implements Event.Data {
  /**
   * ctor.
   *
   * @param entityDamageCause the entity damage cause.
   * @param killerEntityType the killer entity type.
   * @param killerUniqueEntityId the killer unique entity id.
   * @param victimUniqueEntityId the victim unique entity id.
   */
  public MobKilledEventData(
    final int entityDamageCause,
    final int killerEntityType,
    final long killerUniqueEntityId,
    final long victimUniqueEntityId
  ) {
    this(
      entityDamageCause,
      killerEntityType,
      killerUniqueEntityId,
      victimUniqueEntityId,
      "",
      -1
    );
  }

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.MOB_KILLED;
  }
}
