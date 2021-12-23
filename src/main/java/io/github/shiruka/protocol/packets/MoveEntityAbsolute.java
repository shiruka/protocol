package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Setter
@ToString
@Accessors(fluent = true)
public final class MoveEntityAbsolute extends MinecraftPacket {

  /**
   * the flag on ground.
   */
  private static final int FLAG_ON_GROUND = 1;

  /**
   * the flag teleported.
   */
  private static final int FLAG_TELEPORTED = 2;

  /**
   * the on ground.
   */
  @Getter
  private boolean onGround;

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
   * the runtime entity id.
   */
  @Getter
  private long runtimeEntityId;

  /**
   * the teleported.
   */
  @Getter
  private boolean teleported;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.runtimeEntityId = buffer.readUnsignedVarLong();
    final var flags = buffer.readUnsignedByte();
    this.onGround = (flags & MoveEntityAbsolute.FLAG_ON_GROUND) != 0;
    this.teleported = (flags & MoveEntityAbsolute.FLAG_TELEPORTED) != 0;
    this.position = buffer.readVector3f();
    this.rotation = buffer.readByteRotation();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeUnsignedVarLong(this.runtimeEntityId);
    var flags = 0;
    if (this.onGround) {
      flags |= MoveEntityAbsolute.FLAG_ON_GROUND;
    }
    if (this.teleported) {
      flags |= MoveEntityAbsolute.FLAG_TELEPORTED;
    }
    buffer.writeByte(flags);
    buffer.writeVector3f(this.position());
    buffer.writeByteRotation(this.rotation());
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
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
}
