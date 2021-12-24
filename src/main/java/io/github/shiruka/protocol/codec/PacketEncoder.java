package io.github.shiruka.protocol.codec;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftSession;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine packet serializers.
 *
 * @param <T> type of the packet.
 */
public interface PacketEncoder<T extends MinecraftPacket> {

  /**
   * decodes the packet.
   *
   * @param packet the packet to decode.
   * @param helper the helper to decode.
   * @param buffer the buffer to decode.
   * @param session the session to decode.
   */
  void decode(@NotNull T packet, @NotNull CodecHelper helper, @NotNull PacketBuffer buffer,
              @NotNull MinecraftSession session);

  /**
   * encodes the packet.
   *
   * @param packet the packet to encode.
   * @param helper the helper to encode.
   * @param buffer the buffer to encode.
   * @param session the session to decode.
   */
  void encode(@NotNull T packet, @NotNull CodecHelper helper, @NotNull PacketBuffer buffer,
              @NotNull MinecraftSession session);
}
