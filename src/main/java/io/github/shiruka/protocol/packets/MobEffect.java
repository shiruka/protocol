package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents mob effect packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class MobEffect extends MinecraftPacket.Base {

  //@formatter:off
  int amplifier;
  int duration;
  int effectId;
  Event event;
  boolean particles;
  long runtimeEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains mob effect events.
   */
  public enum Event {
    /**
     * the none.
     */
    NONE,
    /**
     * the add.
     */
    ADD,
    /**
     * the modify.
     */
    MODIFY,
    /**
     * the remove;
     */
    REMOVE;

    /**
     * the values.
     */
    public static final Event[] VALUES = Event.values();
  }
}
