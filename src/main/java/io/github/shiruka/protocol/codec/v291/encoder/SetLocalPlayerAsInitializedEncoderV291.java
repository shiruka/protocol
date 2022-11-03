package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.SetLocalPlayerAsInitialized;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents set local player as initialized packet encoders.
 */
@PacketId(113)
public final class SetLocalPlayerAsInitializedEncoderV291
  extends PacketEncoder.Base<SetLocalPlayerAsInitialized> {

  @Override
  public void decode(
    @NotNull final SetLocalPlayerAsInitialized packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
  }

  @Override
  public void encode(
    @NotNull final SetLocalPlayerAsInitialized packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
  }
}
