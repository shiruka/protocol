package io.github.shiruka.protocol.data;

import io.github.shiruka.protocol.packets.PlayerAuthInput;
import io.github.shiruka.protocol.packets.StartGame;
import org.jetbrains.annotations.NotNull;

/**
 * the authoritative movement mode chosen by the server in the {@link StartGame} to verify the clients movement is
 * valid.
 */
public enum AuthoritativeMovementMode {
  /**
   * movement is completely controlled by the client and does not send {@link PlayerAuthInput}.
   */
  CLIENT,
  /**
   * movement is verified by the server using the {@link PlayerAuthInput}.
   */
  SERVER,
  /**
   * the server with rewind.
   */
  SERVER_WITH_REWIND;

  /**
   * the cache.
   */
  private static final AuthoritativeMovementMode[] CACHE = AuthoritativeMovementMode.values();

  /**
   * gets authoritative movement mode by ordinal.
   *
   * @param ordinal the ordinal to get.
   *
   * @return authoritative movement mode.
   */
  @NotNull
  public static AuthoritativeMovementMode byOrdinal(final int ordinal) {
    return AuthoritativeMovementMode.CACHE[ordinal];
  }
}
