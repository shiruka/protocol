package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add hanging entity packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class AddHangingEntity extends MinecraftPacket {

  //@formatter:off
  private int direction;
  private Vector3f position;
  private long runtimeEntityId;
  private long uniqueEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
