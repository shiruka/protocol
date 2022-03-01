package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.AddBehaviorTree;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add behavior tree packet encoders.
 */
public final class AddBehaviorTreeEncoderV291 extends PacketEncoder.Base<AddBehaviorTree> {

  /**
   * ctor.
   */
  private AddBehaviorTreeEncoderV291() {
    super(89);
  }

  @Override
  public void decode(@NotNull final AddBehaviorTree packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.behaviorTreeJson(buffer.readString());
  }

  @Override
  public void encode(@NotNull final AddBehaviorTree packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeString(packet.behaviorTreeJson());
  }
}
