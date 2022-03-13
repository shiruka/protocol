package io.github.shiruka.protocol.common;

import io.github.shiruka.protocol.packets.AddBehaviorTree;
import io.github.shiruka.protocol.packets.AddEntity;
import io.github.shiruka.protocol.packets.AddHangingEntity;
import io.github.shiruka.protocol.packets.AddItemEntity;
import io.github.shiruka.protocol.packets.AddPlayer;
import io.github.shiruka.protocol.packets.AdventureSettings;
import io.github.shiruka.protocol.packets.Animate;
import io.github.shiruka.protocol.packets.AutomationClientConnect;
import io.github.shiruka.protocol.packets.AvailableCommands;
import io.github.shiruka.protocol.packets.BlockEntityData;
import io.github.shiruka.protocol.packets.BlockEvent;
import io.github.shiruka.protocol.packets.BlockPickRequest;
import io.github.shiruka.protocol.packets.BookEdit;
import io.github.shiruka.protocol.packets.BossEvent;
import io.github.shiruka.protocol.packets.Camera;
import io.github.shiruka.protocol.packets.ChangeDimension;
import io.github.shiruka.protocol.packets.ChunkRadiusUpdated;
import io.github.shiruka.protocol.packets.ClientToServerHandshake;
import io.github.shiruka.protocol.packets.Disconnect;
import io.github.shiruka.protocol.packets.EntityEvent;
import io.github.shiruka.protocol.packets.Explode;
import io.github.shiruka.protocol.packets.GameRulesChanged;
import io.github.shiruka.protocol.packets.GuiDataPickItem;
import io.github.shiruka.protocol.packets.HurtArmor;
import io.github.shiruka.protocol.packets.ItemFrameDropItem;
import io.github.shiruka.protocol.packets.LabTable;
import io.github.shiruka.protocol.packets.LevelChunk;
import io.github.shiruka.protocol.packets.LevelEvent;
import io.github.shiruka.protocol.packets.LevelSoundEvent1;
import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.MoveEntityAbsolute;
import io.github.shiruka.protocol.packets.MovePlayer;
import io.github.shiruka.protocol.packets.NetworkStackLatency;
import io.github.shiruka.protocol.packets.NpcRequest;
import io.github.shiruka.protocol.packets.PlayStatus;
import io.github.shiruka.protocol.packets.PlayerAuthInput;
import io.github.shiruka.protocol.packets.RemoveEntity;
import io.github.shiruka.protocol.packets.ResourcePackClientResponse;
import io.github.shiruka.protocol.packets.ResourcePackInfo;
import io.github.shiruka.protocol.packets.ResourcePackStack;
import io.github.shiruka.protocol.packets.RiderJump;
import io.github.shiruka.protocol.packets.ServerToClientHandshake;
import io.github.shiruka.protocol.packets.SetTime;
import io.github.shiruka.protocol.packets.StartGame;
import io.github.shiruka.protocol.packets.TakeItemEntity;
import io.github.shiruka.protocol.packets.Text;
import io.github.shiruka.protocol.packets.TickSync;
import io.github.shiruka.protocol.packets.Transfer;
import io.github.shiruka.protocol.packets.Unknown;
import io.github.shiruka.protocol.packets.UpdateBlock;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine packet handlers.
 */
public interface PacketHandler {

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

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AddBehaviorTree packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final MoveEntityAbsolute packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final MovePlayer packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final RiderJump packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final UpdateBlock packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AddHangingEntity packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final TickSync packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final LevelSoundEvent1 packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final LevelEvent packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final BlockEvent packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Animate packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AutomationClientConnect packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AvailableCommands packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final BlockEntityData packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final BlockPickRequest packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final BookEdit packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Explode packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final EntityEvent packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final BossEvent packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Camera packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ChangeDimension packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ChunkRadiusUpdated packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final HurtArmor packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ItemFrameDropItem packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Transfer packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final LabTable packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final LevelChunk packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final GameRulesChanged packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final GuiDataPickItem packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final NetworkStackLatency packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final NpcRequest packet) {
  }
}
