package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.HurtArmor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents hurt armor packet encoders.
 */
@PacketId(38)
public final class HurtArmorEncoderV291 extends PacketEncoder.Base<HurtArmor> {

  @Override
  public void decode(
    @NotNull final HurtArmor packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.damage(buffer.readVarInt());
  }

  @Override
  public void encode(
    @NotNull final HurtArmor packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeVarInt(packet.damage());
  }
}
