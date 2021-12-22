package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector2f;
import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.ClientPlayMode;
import io.github.shiruka.protocol.data.InputMode;
import io.github.shiruka.protocol.data.PlayerAuthInputData;
import io.github.shiruka.protocol.data.PlayerBlockActionData;
import io.github.shiruka.protocol.data.inventory.ItemStackRequest;
import io.github.shiruka.protocol.data.inventory.ItemUseTransaction;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents player auth input packets.
 */
@Setter
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
  @Nullable
  private Vector3f delta;

  /**
   * the input mode.
   */
  @Nullable
  private InputMode inputMode;

  /**
   * the item stack request.
   */
  @Nullable
  private ItemStackRequest itemStackRequest;

  /**
   * the item use transaction.
   */
  @Nullable
  private ItemUseTransaction itemUseTransaction;

  /**
   * the motion.
   */
  @Nullable
  private Vector2f motion;

  /**
   * the play mode.
   */
  @Nullable
  private ClientPlayMode playMode;

  /**
   * the position.
   */
  @Nullable
  private Vector3f position;

  /**
   * the rotation.
   */
  @Nullable
  private Vector3f rotation;

  /**
   * the tick.
   */
  @Getter
  private long tick;

  /**
   * the vr gaze direction.
   */
  @Nullable
  private Vector3f vrGazeDirection;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    final var rotation = this.rotation();
    final var motion = this.motion();
    buffer.writeFloatLE(rotation.x());
    buffer.writeFloatLE(rotation.y());
    buffer.writeVector3f(this.position());
    buffer.writeFloatLE(motion.x());
    buffer.writeFloatLE(motion.y());
    buffer.writeFloatLE(rotation.z());
    buffer.writeUnsignedVarLong(this.inputData.stream()
      .mapToLong(data -> 1L << data.ordinal())
      .reduce(0L, (a, b) -> a | b));
    buffer.writeUnsignedVarInt(this.inputMode().ordinal());
    buffer.writeUnsignedVarInt(this.playMode().ordinal());
    if (this.playMode == ClientPlayMode.REALITY) {
      buffer.writeVector3f(this.vrGazeDirection());
    }
    buffer.writeUnsignedVarLong(this.tick);
    buffer.writeVector3f(this.delta());
    if (this.inputData.contains(PlayerAuthInputData.PERFORM_ITEM_INTERACTION)) {
      buffer.writeItemUseTransaction(session, this.itemUseTransaction());
    }
    if (this.inputData.contains(PlayerAuthInputData.PERFORM_ITEM_STACK_REQUEST)) {
      buffer.writeItemStackRequest(session, this.itemStackRequest());
    }
    if (this.inputData.contains(PlayerAuthInputData.PERFORM_BLOCK_ACTIONS)) {
      buffer.writeVarInt(this.playerActions.size());
      this.playerActions.forEach(buffer::writePlayerBlockActionData);
    }
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the delta.
   *
   * @return delta.
   */
  @NotNull
  public Vector3f delta() {
    return Objects.requireNonNull(this.delta, "delta");
  }

  /**
   * obtains the input data.
   *
   * @return input data.
   */
  @NotNull
  public Set<PlayerAuthInputData> inputData() {
    return Collections.unmodifiableSet(this.inputData);
  }

  /**
   * obtains input mode.
   *
   * @return input mode.
   */
  @NotNull
  public InputMode inputMode() {
    return Objects.requireNonNull(this.inputMode, "input mode");
  }

  /**
   * obtains the item stack request.
   *
   * @return item stack request.
   */
  @NotNull
  public ItemStackRequest itemStackRequest() {
    return Objects.requireNonNull(this.itemStackRequest, "item stack request");
  }

  /**
   * obtains the item use transaction.
   *
   * @return item use transaction.
   */
  @NotNull
  public ItemUseTransaction itemUseTransaction() {
    return Objects.requireNonNull(this.itemUseTransaction, "item use transaction");
  }

  /**
   * obtains the motion.
   *
   * @return motion.
   */
  @NotNull
  public Vector2f motion() {
    return Objects.requireNonNull(this.motion, "motion");
  }

  /**
   * obtains the play mode.
   *
   * @return play mode.
   */
  @NotNull
  public ClientPlayMode playMode() {
    return Objects.requireNonNull(this.playMode, "play mode");
  }

  /**
   * obtains the player actions.
   *
   * @return player actions.
   */
  @NotNull
  public List<PlayerBlockActionData> playerActions() {
    return Collections.unmodifiableList(this.playerActions);
  }

  /**
   * obtains the position.
   *
   * @return position.
   */
  @NotNull
  public Vector3f position() {
    return Objects.requireNonNull(this.position, "position");
  }

  /**
   * obtains the rotation.
   *
   * @return rotation.
   */
  @NotNull
  public Vector3f rotation() {
    return Objects.requireNonNull(this.rotation, "rotation");
  }

  /**
   * obtains the vr gaze direction.
   *
   * @return vr gaze direction.
   */
  @NotNull
  public Vector3f vrGazeDirection() {
    return Objects.requireNonNull(this.vrGazeDirection, "vr gaze direction");
  }
}
