package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents move player packets.
 */
@Log4j2
@Setter
@ToString
@Accessors(fluent = true)
public final class MovePlayer extends MinecraftPacket {

  /**
   * the entity type.
   */
  @Getter
  private int entityType;

  /**
   * the mode.
   */
  @Nullable
  private Mode mode;

  /**
   * te on ground.
   */
  @Getter
  private boolean onGround;

  /**
   * the position.
   */
  @Nullable
  private Vector3f position;

  /**
   * the riding runtime entity id.
   */
  @Getter
  private long ridingRuntimeEntityId;

  /**
   * the rotation.
   */
  @Nullable
  private Vector3f rotation;

  /**
   * the runtime entity id.
   */
  @Getter
  private long runtimeEntityId;

  /**
   * the teleportation cause.
   */
  @Nullable
  private TeleportationCause teleportationCause;

  /**
   * the tick.
   */
  @Getter
  private long tick;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.runtimeEntityId = buffer.readUnsignedVarLong();
    this.position = buffer.readVector3f();
    this.rotation = buffer.readVector3f();
    this.mode = Mode.VALUES[buffer.readUnsignedByte()];
    this.onGround = buffer.readBoolean();
    this.ridingRuntimeEntityId = buffer.readUnsignedVarLong();
    if (this.mode == Mode.TELEPORT) {
      this.teleportationCause = TeleportationCause.byOrdinal(buffer.readIntLE());
      this.entityType = buffer.readIntLE();
    }
    this.tick = buffer.readUnsignedVarLong();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeUnsignedVarLong(this.runtimeEntityId);
    buffer.writeVector3f(this.position());
    buffer.writeVector3f(this.rotation());
    buffer.writeByte(this.mode().ordinal());
    buffer.writeBoolean(this.onGround);
    buffer.writeUnsignedVarLong(this.ridingRuntimeEntityId);
    if (this.mode == Mode.TELEPORT) {
      buffer.writeIntLE(this.teleportationCause().ordinal());
      buffer.writeIntLE(this.entityType);
    }
    buffer.writeUnsignedVarLong(this.tick);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the mode.
   *
   * @return mode.
   */
  @NotNull
  public Mode mode() {
    return Objects.requireNonNull(this.mode, "mode");
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
   * obtains the teleportation cause.
   *
   * @return teleportation cause.
   */
  @NotNull
  public TeleportationCause teleportationCause() {
    return Objects.requireNonNull(this.teleportationCause, "teleportation cause");
  }

  /**
   * an enum class that represents modes.
   */
  public enum Mode {
    /**
     * the normal.
     */
    NORMAL,
    /**
     * the respawn.
     */
    RESPAWN,
    /**
     * the teleport.
     */
    TELEPORT,
    /**
     * the head rotation.
     */
    HEAD_ROTATION;

    /**
     * the values.
     */
    private static final Mode[] VALUES = Mode.values();
  }

  /**
   * an enum class that contains teleportation causes.
   */
  public enum TeleportationCause {
    /**
     * the unknown.
     */
    UNKNOWN,
    /**
     * the projectile.
     */
    PROJECTILE,
    /**
     * the chorus fruit.
     */
    CHORUS_FRUIT,
    /**
     * the command.
     */
    COMMAND,
    /**
     * the behavior.
     */
    BEHAVIOR;

    /**
     * the values.
     */
    private static final TeleportationCause[] VALUES = TeleportationCause.values();

    /**
     * gets teleportation cause by ordinal.
     *
     * @param ordinal the ordinal to get.
     *
     * @return teleportation cause.
     */
    @NotNull
    public static TeleportationCause byOrdinal(final int ordinal) {
      return Optional.of(ordinal)
        .filter(id -> id >= 0 && id < TeleportationCause.VALUES.length)
        .map(id -> TeleportationCause.VALUES[id])
        .orElse(TeleportationCause.UNKNOWN);
    }
  }
}
