package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import java.util.Objects;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents add behavior tree packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class AddBehaviorTree extends MinecraftPacket {

  /**
   * the behavior tree json.
   */
  @Nullable
  private String behaviorTreeJson;

  /**
   * obtains the behavior tree json.
   *
   * @return behavior tree json.
   */
  @NotNull
  public String behaviorTreeJson() {
    return Objects.requireNonNull(this.behaviorTreeJson, "behavior tree json");
  }

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.behaviorTreeJson = buffer.readString();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeString(this.behaviorTreeJson());
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
