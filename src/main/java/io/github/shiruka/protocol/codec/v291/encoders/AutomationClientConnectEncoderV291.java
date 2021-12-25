package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.AutomationClientConnect;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents automation client connect packet encoders.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class AutomationClientConnectEncoderV291 implements PacketEncoder<AutomationClientConnect> {

  /**
   * the instance.
   */
  public static final AutomationClientConnectEncoderV291 INSTANCE = new AutomationClientConnectEncoderV291();

  @Override
  public void decode(@NotNull final AutomationClientConnect packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.address(buffer.readString());
  }

  @Override
  public void encode(@NotNull final AutomationClientConnect packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeString(packet.address());
  }
}
