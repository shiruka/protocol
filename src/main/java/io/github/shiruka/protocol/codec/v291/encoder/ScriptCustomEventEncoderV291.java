package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.ScriptCustomEvent;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents script custom event packet encoders.
 */
@PacketId(117)
public final class ScriptCustomEventEncoderV291
  extends PacketEncoder.Base<ScriptCustomEvent> {

  @Override
  public void decode(
    @NotNull final ScriptCustomEvent packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    packet.eventName(buffer.readString());
    packet.data(buffer.readString());
  }

  @Override
  public void encode(
    @NotNull final ScriptCustomEvent packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeString(packet.eventName());
    buffer.writeString(packet.data());
  }
}
