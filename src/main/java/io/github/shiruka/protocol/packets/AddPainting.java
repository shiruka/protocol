package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents add painting packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class AddPainting extends AddHangingEntity {

  /**
   * the motive.
   */
  @Nullable
  private String motive;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.uniqueEntityId(buffer.readVarLong());
    this.runtimeEntityId(buffer.readUnsignedVarLong());
    this.position(buffer.readVector3f());
    this.direction(buffer.readInt());
    this.motive = buffer.readString();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeVarLong(this.uniqueEntityId());
    buffer.writeUnsignedVarLong(this.runtimeEntityId());
    buffer.writeVector3f(this.position());
    buffer.writeVarInt(this.direction());
    buffer.writeString(this.motive());
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the motive.
   *
   * @return motive.
   */
  @NotNull
  public String motive() {
    return Objects.requireNonNull(this.motive, "motive");
  }
}
