package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents pet died event data.
 *
 * @param entityDamageCause the entity damage cause.
 * @param killerUniqueEntityId the killer unique entity id.
 * @param ownerKilled the owner killed.
 * @param petEntityType the pet entity type.
 * @param petUniqueEntityId the pet unique entity id.
 */
public record PetDiedEventData(
  int entityDamageCause,
  long killerUniqueEntityId,
  boolean ownerKilled,
  int petEntityType,
  long petUniqueEntityId
)
  implements Event.Data {

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.PET_DIED;
  }
}
