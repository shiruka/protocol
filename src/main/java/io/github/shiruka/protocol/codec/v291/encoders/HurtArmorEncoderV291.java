package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.HurtArmor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents hurt armor packet encoders.
 */
@PacketId(38)
public final class HurtArmorEncoderV291 extends PacketEncoder.Base<HurtArmor> {

  @Override
  public void decode(@NotNull final HurtArmor packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.damage(buffer.readVarInt());
  }

  @Override
  public void encode(@NotNull final HurtArmor packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarInt(packet.damage());
  }
}
