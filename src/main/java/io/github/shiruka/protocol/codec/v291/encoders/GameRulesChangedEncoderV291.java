package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.GameRulesChanged;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents game rules changed packet encoders.
 */
@PacketId(72)
public final class GameRulesChangedEncoderV291 extends PacketEncoder.Base<GameRulesChanged> {

  @Override
  public void decode(@NotNull final GameRulesChanged packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.gameRules(buffer.readArrayUnsignedInt(() ->
      helper.readGameRule(buffer)));
  }

  @Override
  public void encode(@NotNull final GameRulesChanged packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeArrayUnsignedInt(packet.gameRules(), value ->
      helper.writeGameRule(buffer, value));
  }
}
