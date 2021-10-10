package io.github.shiruka.protocol.data;

import io.github.shiruka.protocol.packets.StartGame;

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
  SERVER_WITH_REWIND
}
