package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add behavior tree packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class AddBehaviorTree extends MinecraftPacket {

  //@formatter:off
  private String behaviorTreeJson;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
