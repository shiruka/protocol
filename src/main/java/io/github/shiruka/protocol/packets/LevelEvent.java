package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.codec.Constants;
import io.github.shiruka.protocol.data.LevelEventType;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents level event packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class LevelEvent extends MinecraftPacket {

  /**
   * the data.
   */
  @Getter
  private int data;

  /**
   * the position.
   */
  @Nullable
  private Vector3f position;

  /**
   * the type.
   */
  @Nullable
  private LevelEventType type;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    final var eventId = buffer.readVarInt();
    this.type = Constants.LEVEL_EVENTS.get(eventId);
    this.position = buffer.readVector3f();
    this.data = buffer.readVarInt();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeInt(Constants.LEVEL_EVENTS.get(this.type()));
    buffer.writeVector3f(this.position());
    buffer.writeVarInt(this.data());
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
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  public LevelEventType type() {
    return Objects.requireNonNull(this.type, "type");
  }
}
