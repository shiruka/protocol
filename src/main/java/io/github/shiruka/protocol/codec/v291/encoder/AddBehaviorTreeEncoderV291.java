package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packet.AddBehaviorTree;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add behavior tree packet encoders.
 */
@PacketId(89)
public final class AddBehaviorTreeEncoderV291 extends PacketEncoder.Base<AddBehaviorTree> {

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
