package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents npc request packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class NpcRequest extends MinecraftPacket.Base {

  //@formatter:off
  int actionType;
  String command;
  Type requestType;
  long runtimeEntityId;
  String sceneName;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains npc request types.
   */
  public enum Type {
    /**
     * the set action.
     */
    SET_ACTION,
    /**
     * the execute command action.
     */
    EXECUTE_COMMAND_ACTION,
    /**
     * the execute closing commands.
     */
    EXECUTE_CLOSING_COMMANDS,
    /**
     * the set name.
     */
    SET_NAME,
    /**
     * the set skin.
     */
    SET_SKIN,
    /**
     * the set interaction text.
     */
    SET_INTERACTION_TEXT,
    /**
     * the execute opening commands.
     */
    EXECUTE_OPENING_COMMANDS;

    /**
     * the values.
     */
    public static final Type[] VALUES = Type.values();
  }
}
