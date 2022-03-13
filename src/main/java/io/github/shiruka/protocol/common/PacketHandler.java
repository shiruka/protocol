package io.github.shiruka.protocol.common;

import io.github.shiruka.protocol.packet.AddBehaviorTree;
import io.github.shiruka.protocol.packet.AddEntity;
import io.github.shiruka.protocol.packet.AddHangingEntity;
import io.github.shiruka.protocol.packet.AddItemEntity;
import io.github.shiruka.protocol.packet.AddPainting;
import io.github.shiruka.protocol.packet.AddPlayer;
import io.github.shiruka.protocol.packet.AdventureSettings;
import io.github.shiruka.protocol.packet.Animate;
import io.github.shiruka.protocol.packet.AutomationClientConnect;
import io.github.shiruka.protocol.packet.AvailableCommands;
import io.github.shiruka.protocol.packet.BlockEntityData;
import io.github.shiruka.protocol.packet.BlockEvent;
import io.github.shiruka.protocol.packet.BlockPickRequest;
import io.github.shiruka.protocol.packet.BookEdit;
import io.github.shiruka.protocol.packet.BossEvent;
import io.github.shiruka.protocol.packet.Camera;
import io.github.shiruka.protocol.packet.ChangeDimension;
import io.github.shiruka.protocol.packet.ChunkRadiusUpdated;
import io.github.shiruka.protocol.packet.ClientToServerHandshake;
import io.github.shiruka.protocol.packet.Disconnect;
import io.github.shiruka.protocol.packet.EntityEvent;
import io.github.shiruka.protocol.packet.EntityFall;
import io.github.shiruka.protocol.packet.EntityPickRequest;
import io.github.shiruka.protocol.packet.Event;
import io.github.shiruka.protocol.packet.Explode;
import io.github.shiruka.protocol.packet.GameRulesChanged;
import io.github.shiruka.protocol.packet.GuiDataPickItem;
import io.github.shiruka.protocol.packet.HurtArmor;
import io.github.shiruka.protocol.packet.Interact;
import io.github.shiruka.protocol.packet.InventoryContent;
import io.github.shiruka.protocol.packet.InventorySlot;
import io.github.shiruka.protocol.packet.InventoryTransaction;
import io.github.shiruka.protocol.packet.ItemFrameDropItem;
import io.github.shiruka.protocol.packet.LabTable;
import io.github.shiruka.protocol.packet.LevelChunk;
import io.github.shiruka.protocol.packet.LevelEvent;
import io.github.shiruka.protocol.packet.LevelSoundEvent1;
import io.github.shiruka.protocol.packet.Login;
import io.github.shiruka.protocol.packet.MobEffect;
import io.github.shiruka.protocol.packet.MoveEntityAbsolute;
import io.github.shiruka.protocol.packet.MovePlayer;
import io.github.shiruka.protocol.packet.NetworkStackLatency;
import io.github.shiruka.protocol.packet.NpcRequest;
import io.github.shiruka.protocol.packet.PlayStatus;
import io.github.shiruka.protocol.packet.PlayerAuthInput;
import io.github.shiruka.protocol.packet.PurchaseReceipt;
import io.github.shiruka.protocol.packet.RemoveEntity;
import io.github.shiruka.protocol.packet.ResourcePackClientResponse;
import io.github.shiruka.protocol.packet.ResourcePackInfo;
import io.github.shiruka.protocol.packet.ResourcePackStack;
import io.github.shiruka.protocol.packet.RiderJump;
import io.github.shiruka.protocol.packet.ScriptCustomEvent;
import io.github.shiruka.protocol.packet.ServerToClientHandshake;
import io.github.shiruka.protocol.packet.SetTime;
import io.github.shiruka.protocol.packet.StartGame;
import io.github.shiruka.protocol.packet.TakeItemEntity;
import io.github.shiruka.protocol.packet.Text;
import io.github.shiruka.protocol.packet.TickSync;
import io.github.shiruka.protocol.packet.Transfer;
import io.github.shiruka.protocol.packet.Unknown;
import io.github.shiruka.protocol.packet.UpdateAttributes;
import io.github.shiruka.protocol.packet.UpdateBlock;
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

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final EntityFall packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final AddPainting packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final MobEffect packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final UpdateAttributes packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final EntityPickRequest packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Event packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final Interact packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final InventoryContent packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final InventoryTransaction packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final InventorySlot packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final PurchaseReceipt packet) {
  }

  /**
   * handles the packet.
   *
   * @param packet the packet to handle.
   */
  default void handle(@NotNull final ScriptCustomEvent packet) {
  }
}
