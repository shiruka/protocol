package io.github.shiruka.protocol.data.event;

import io.github.shiruka.protocol.packet.Event;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents agent created event data.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AgentCreatedEventData implements Event.Data {

  /**
   * the instance.
   */
  public static final AgentCreatedEventData INSTANCE = new AgentCreatedEventData();

  @NotNull
  @Override
  public Event.Type type() {
    return Event.Type.AGENT_CREATED;
  }
}
