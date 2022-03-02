package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import java.util.EnumSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents update block packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class UpdateBlock extends MinecraftPacket {

  //@formatter:off
  private final Set<Flag> flags = EnumSet.noneOf(Flag.class);
  private Vector3i blockPosition;
  private int dataLayer;
  private int runtimeId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
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
