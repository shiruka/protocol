package io.github.shiruka.protocol.codec;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftSession;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import tr.com.infumia.infumialib.misc.TypeParameterMatcher;
import tr.com.infumia.infumialib.reflection.clazz.ClassOf;

/**
 * an interface to determine packet serializers.
 *
 * @param <T> type of the packet.
 */
public interface PacketEncoder<T extends MinecraftPacket> {

  /**
   * decodes the packet.
   * <p>
   * deserializing
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
   * <p>
   * serializing
   *
   * @param packet the packet to encode.
   * @param helper the helper to encode.
   * @param buffer the buffer to encode.
   * @param session the session to decode.
   */
  void encode(@NotNull T packet, @NotNull CodecHelper helper, @NotNull PacketBuffer buffer,
              @NotNull MinecraftSession session);

  /**
   * an abstract class that represents base packet encoders.
   *
   * @param <T> type of the packet.
   */
  @Getter
  @Accessors(fluent = true)
  @SuppressWarnings("unchecked")
  @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
  abstract class Base<T extends MinecraftPacket> implements PacketEncoder<T> {

    /**
     * the factory.
     */
    @NotNull
    private final Supplier<T> factory;

    /**
     * the id.
     */
    private final int id;

    /**
     * ctor.
     *
     * @param id the id.
     */
    protected Base(final int id) {
      this.id = id;
      final var constructor = new ClassOf<>(TypeParameterMatcher.find(this, Base.class, "T"))
        .getConstructor()
        .orElseThrow();
      this.factory = () -> constructor.create()
        .map(instance -> (T) instance)
        .orElseThrow();
    }
  }
}
