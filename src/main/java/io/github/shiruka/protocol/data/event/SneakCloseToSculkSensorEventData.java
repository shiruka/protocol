package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents sneak close to sculk sensor event data.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SneakCloseToSculkSensorEventData implements Event.Data {

  /**
   * the instance.
   */
  public static final SneakCloseToSculkSensorEventData INSTANCE = new SneakCloseToSculkSensorEventData();

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.SNEAK_CLOSE_TO_SCULK_SENSOR;
  }
}
