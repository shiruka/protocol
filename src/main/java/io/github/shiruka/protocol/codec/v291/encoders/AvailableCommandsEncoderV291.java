package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.AvailableCommands;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents available commands' packet encoders.
 */
@PacketId(76)
public final class AvailableCommandsEncoderV291 extends PacketEncoder.Base<AvailableCommands> {

  @Override
  public void decode(@NotNull final AvailableCommands packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.commands(helper.readCommands(buffer));
  }

  @Override
  public void encode(@NotNull final AvailableCommands packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    helper.writeCommands(buffer, packet.commands());
  }
}
