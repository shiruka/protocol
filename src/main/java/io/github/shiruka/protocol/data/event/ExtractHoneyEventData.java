package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents extract honey event data.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtractHoneyEventData implements Event.Data {

  /**
   * the instance.
   */
  public static final ExtractHoneyEventData INSTANCE = new ExtractHoneyEventData();

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.EXTRACT_HONEY;
  }
}
