package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents strider ridden in lava in overworld event data.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StriderRiddenInLavaInOverworldEventData
  implements Event.Data {

  /**
   * the intance.
   */
  public static final StriderRiddenInLavaInOverworldEventData INSTANCE = new StriderRiddenInLavaInOverworldEventData();

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.STRIDER_RIDDEN_IN_LAVA_IN_OVERWORLD;
  }
}
