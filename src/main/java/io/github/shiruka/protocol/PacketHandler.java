package io.github.shiruka.protocol;

import io.github.shiruka.protocol.packets.AddEntity;
import io.github.shiruka.protocol.packets.AddItemEntity;
import io.github.shiruka.protocol.packets.AddPlayer;
import io.github.shiruka.protocol.packets.AdventureSettings;
import io.github.shiruka.protocol.packets.ClientToServerHandshake;
import io.github.shiruka.protocol.packets.Disconnect;
import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.PlayStatus;
import io.github.shiruka.protocol.packets.PlayerAuthInput;
import io.github.shiruka.protocol.packets.RemoveEntity;
import io.github.shiruka.protocol.packets.ResourcePackClientResponse;
import io.github.shiruka.protocol.packets.ResourcePackInfo;
import io.github.shiruka.protocol.packets.ResourcePackStack;
import io.github.shiruka.protocol.packets.ServerToClientHandshake;
import io.github.shiruka.protocol.packets.SetTime;
import io.github.shiruka.protocol.packets.StartGame;
import io.github.shiruka.protocol.packets.TakeItemEntity;
import io.github.shiruka.protocol.packets.Text;
import io.github.shiruka.protocol.packets.Unknown;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine packet handlers.
 */
public interface PacketHandler {

  /**
   * the empty packet handler.
   */
  PacketHandler EMPTY = new PacketHandler() {
  };

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final PlayerAuthInput packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final PlayStatus packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Login packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Unknown packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ServerToClientHandshake packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AddEntity packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AddItemEntity packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AddPlayer packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ClientToServerHandshake packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Disconnect packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final RemoveEntity packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ResourcePackClientResponse packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ResourcePackInfo packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ResourcePackStack packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final SetTime packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final StartGame packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final TakeItemEntity packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Text packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AdventureSettings packet) {
  }
}
