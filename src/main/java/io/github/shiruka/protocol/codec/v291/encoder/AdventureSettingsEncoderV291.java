package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.AdventureSettings;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents adventure settings packet encoder.
 */
@PacketId(55)
public final class AdventureSettingsEncoderV291 extends PacketEncoder.Base<AdventureSettings> {

  @Override
  public void decode(@NotNull final AdventureSettings packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    helper.readAdventureSettings(packet, buffer);
  }

  @Override
  public void encode(@NotNull final AdventureSettings packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    helper.writeAdventureSettings(packet, buffer);
  }
}
