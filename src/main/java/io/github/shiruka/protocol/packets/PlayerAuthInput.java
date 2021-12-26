package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector2f;
import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.ClientPlayMode;
import io.github.shiruka.protocol.data.InputMode;
import io.github.shiruka.protocol.data.PlayerAuthInputData;
import io.github.shiruka.protocol.data.PlayerBlockActionData;
import io.github.shiruka.protocol.data.inventory.ItemStackRequest;
import io.github.shiruka.protocol.data.inventory.ItemUseTransaction;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents player auth input packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class PlayerAuthInput extends MinecraftPacket {

  /**
   * the input data.
   */
  private final Set<PlayerAuthInputData> inputData = EnumSet.noneOf(PlayerAuthInputData.class);

  /**
   * the player actions.
   */
  private final List<PlayerBlockActionData> playerActions = new ObjectArrayList<>();

  /**
   * the delta.
   */
  private Vector3f delta;

  /**
   * the input mode.
   */
  private InputMode inputMode;

  /**
   * the item stack request.
   */
  private ItemStackRequest itemStackRequest;

  /**
   * the item use transaction.
   */
  private ItemUseTransaction itemUseTransaction;

  /**
   * the motion.
   */
  private Vector2f motion;

  /**
   * the play mode.
   */
  private ClientPlayMode playMode;

  /**
   * the position.
   */
  private Vector3f position;

  /**
   * the rotation.
   */
  private Vector3f rotation;

  /**
   * the tick.
   */
  private long tick;

  /**
   * the vr gaze direction.
   */
  private Vector3f vrGazeDirection;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
