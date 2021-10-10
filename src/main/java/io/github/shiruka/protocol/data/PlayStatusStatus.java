package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains play status.
 */
public enum PlayStatusStatus {

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
  private static final PlayStatusStatus[] CACHE = PlayStatusStatus.values();

  /**
   * gets play status status by ordinal.
   *
   * @param ordinal the ordinal.
   *
   * @return play status status.
   */
  @NotNull
  public static PlayStatusStatus byOrdinal(final int ordinal) {
    return PlayStatusStatus.CACHE[ordinal];
  }
}
