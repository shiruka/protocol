package io.github.shiruka.protocol.common;

import com.google.common.base.Preconditions;
import io.github.shiruka.network.PacketBuffer;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

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
   *
   * @throws Exception if something goes wrong when decoding.
   */
  void decode(
    @NotNull T packet,
    @NotNull CodecHelper helper,
    @NotNull PacketBuffer buffer,
    @NotNull MinecraftSession session
  ) throws Exception;

  /**
   * encodes the packet.
   * <p>
   * serializing
   *
   * @param packet the packet to encode.
   * @param helper the helper to encode.
   * @param buffer the buffer to encode.
   * @param session the session to decode.
   *
   * @throws Exception if something goes wrong when encoding.
   */
  void encode(
    @NotNull T packet,
    @NotNull CodecHelper helper,
    @NotNull PacketBuffer buffer,
    @NotNull MinecraftSession session
  ) throws Exception;

  /**
   * an abstract class that represents base packet encoders.
   * <p>
   * annotate your class with {@link PacketId} to specify the packet id.
   *
   * @param <T> type of the packet.
   */
  @Getter
  @Accessors(fluent = true)
  @SuppressWarnings("unchecked")
  @RequiredArgsConstructor(access = AccessLevel.PROTECTED)
  abstract class Base<T extends MinecraftPacket> implements PacketEncoder<T> {

    /**
     * the annotate warning.
     */
    private static final String ANNOTATE =
      "Annotate %s class with PacketId annotation!";

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
     */
    @SneakyThrows
    protected Base() {
      this.id =
        Preconditions
          .checkNotNull(
            this.getClass().getDeclaredAnnotation(PacketId.class),
            Base.ANNOTATE,
            this.getClass().getSimpleName()
          )
          .value();
      final var packetClass = TypeParameterMatcher.find(this, Base.class, "T");
      final var classConstructor = packetClass.getDeclaredConstructor();
      this.factory =
        () -> {
          try {
            return (T) classConstructor.newInstance();
          } catch (
            final InstantiationException
            | IllegalAccessException
            | InvocationTargetException e
          ) {
            throw new RuntimeException(e);
          }
        };
    }
  }
}
