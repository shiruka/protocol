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
 * a class that represents play status packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class PlayStatus extends MinecraftPacket.Base {

  Status status;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains play status.
   */
  public enum Status {
    /**
     * sent to confirm login success and move onto resource pack sequence.
     */
    LOGIN_SUCCESS,
    /**
     * displays outdated client disconnection screen.
     */
    LOGIN_FAILED_CLIENT_OLD,
    /**
     * displays outdated server disconnection screen.
     */
    LOGIN_FAILED_SERVER_OLD,
    /**
     * spawns player into the world.
     */
    PLAYER_SPAWN,
    /**
     * displays "Unable to connect to world. Your school does not have access to this server.".
     */
    LOGIN_FAILED_INVALID_TENANT,
    /**
     * sent when a Education Edition client joins an Bedrock server.
     */
    LOGIN_FAILED_EDITION_MISMATCH_EDU_TO_VANILLA,
    /**
     * sent when a Bedrock client joins an Education server.
     */
    LOGIN_FAILED_EDITION_MISMATCH_VANILLA_TO_EDU,
    /**
     * sent to a split screen player when the server is full.
     */
    FAILED_SERVER_FULL_SUB_CLIENT;

    /**
     * the cache.
     */
    public static final Status[] VALUES = Status.values();
  }
}
