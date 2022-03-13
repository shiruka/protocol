package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import java.util.EnumSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents update block packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class UpdateBlock extends MinecraftPacket.Base {

  //@formatter:off
  Vector3i blockPosition;
  int dataLayer;
  @Builder.Default Set<Flag> flags = EnumSet.noneOf(Flag.class);
  int runtimeId;
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
