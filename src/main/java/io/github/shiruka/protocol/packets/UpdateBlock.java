package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents update block packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class UpdateBlock extends MinecraftPacket {

  /**
   * the flags.
   */
  private final Set<Flag> flags = EnumSet.noneOf(Flag.class);

  /**
   * the block position.
   */
  @Nullable
  private Vector3i blockPosition;

  /**
   * the data layer.
   */
  @Getter
  private int dataLayer;

  /**
   * the runtime id.
   */
  @Getter
  private int runtimeId;

  /**
   * obtains the block position.
   *
   * @return block position.
   */
  @NotNull
  public Vector3i blockPosition() {
    return Objects.requireNonNull(this.blockPosition, "block position");
  }

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.blockPosition = buffer.readBlockPosition();
    this.runtimeId = buffer.readUnsignedVarInt();
    final var flagValue = buffer.readUnsignedVarInt();
    for (final var flag : Flag.VALUES) {
      if ((flagValue & 1 << flag.ordinal()) != 0) {
        this.flags.add(flag);
      }
    }
    this.dataLayer = buffer.readUnsignedVarInt();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeBlockPosition(this.blockPosition());
    buffer.writeUnsignedVarInt(this.runtimeId);
    var flagValue = 0;
    for (final var flag : this.flags) {
      flagValue |= 1 << flag.ordinal();
    }
    buffer.writeUnsignedVarInt(flagValue);
    buffer.writeUnsignedVarInt(this.dataLayer);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the flags.
   *
   * @return flags.
   */
  @NotNull
  public Set<Flag> flags() {
    return Collections.unmodifiableSet(this.flags);
  }

  /**
   * an enum class that represents flags.
   */
  public enum Flag {
    /**
     * the neighbors.
     */
    NEIGHBORS,
    /**
     * the network.
     */
    NETWORK,
    /**
     * the not graphic.
     */
    NO_GRAPHIC,
    /**
     * the unused.
     */
    UNUSED,
    /**
     * the priority.
     */
    PRIORITY;

    /**
     * the values.
     */
    public static final Flag[] VALUES = Flag.values();
  }
}
