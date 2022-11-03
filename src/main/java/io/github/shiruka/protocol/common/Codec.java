package io.github.shiruka.protocol.common;

import com.google.common.base.Preconditions;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.exception.PacketEncodeException;
import io.github.shiruka.protocol.packet.Unknown;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.reflections.Reflections;

/**
 * an interface to determine codecs.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public interface Codec {
  /**
   * the log.
   */
  Logger LOG = LogManager.getLogger("Codec");

  /**
   * creates a simple codec.
   *
   * @param minecraftVersion the minecraft version to create.
   * @param protocolVersion the protocol version to create.
   * @param helper the helper to create.
   * @param packets the packets to create.
   * @param packetsById the packets by id to create.
   *
   * @return codec.
   */
  @NotNull
  static Codec create(
    @NotNull final String minecraftVersion,
    final int protocolVersion,
    @NotNull final CodecHelper helper,
    @NotNull final Object2ObjectMap<Class<? extends MinecraftPacket>, PacketDefinition<?>> packets,
    @NotNull final Int2ObjectMap<PacketDefinition<?>> packetsById
  ) {
    return new Impl(
      minecraftVersion,
      protocolVersion,
      String.valueOf(protocolVersion),
      helper,
      packets,
      packetsById
    );
  }

  /**
   * creates a new builder.
   *
   * @return a newly created builder.
   */
  @NotNull
  static Builder newBuilder() {
    return new Builder();
  }

  /**
   * decodes the packet.
   *
   * @param buffer the buffer to decode.
   * @param packetId the packet id to decode.
   * @param session the session to decode.
   *
   * @return decoded packet.
   */
  @NotNull
  default MinecraftPacket decode(
    @NotNull final PacketBuffer buffer,
    final int packetId,
    @NotNull final MinecraftChildChannel session
  ) {
    final var definitionOptional = this.packet(packetId);
    final MinecraftPacket packet;
    final PacketEncoder<MinecraftPacket> encoder;
    if (definitionOptional.isEmpty()) {
      packet = new Unknown();
      encoder = (PacketEncoder) packet;
    } else {
      final var definition = definitionOptional.get();
      packet = definition.factory().get();
      encoder = (PacketEncoder) definition.encoder();
    }
    try {
      encoder.decode(packet, this.helper(), buffer, session);
    } catch (final Exception e) {
      throw new PacketEncodeException(
        "Error whilst decoding %s".formatted(packet),
        e
      );
    }
    if (Codec.LOG.isDebugEnabled() && buffer.isReadable()) {
      Codec.LOG.debug(
        "{} still has {} bytes to read!",
        packet.getClass().getSimpleName(),
        buffer.remaining()
      );
    }
    return packet;
  }

  /**
   * encodes the packet.
   *
   * @param buffer the buffer to encode.
   * @param packet the packet to encode.
   * @param session the session to encode.
   */
  default void encode(
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftPacket packet,
    @NotNull final MinecraftSession session
  ) {
    @NotNull
    final PacketEncoder<MinecraftPacket> encoder;
    if (packet instanceof Unknown) {
      encoder = (PacketEncoder) packet;
    } else {
      encoder =
        (PacketEncoder) this.packet(packet.getClass())
          .map(PacketDefinition::encoder)
          .orElseThrow();
    }
    try {
      encoder.encode(packet, this.helper(), buffer, session);
    } catch (final Exception e) {
      throw new PacketEncodeException(
        "Error whilst encoding %s".formatted(packet),
        e
      );
    }
    if (Codec.LOG.isDebugEnabled() && buffer.isReadable()) {
      Codec.LOG.debug(
        "{} still has {} bytes to read!",
        packet.getClass().getSimpleName(),
        buffer.remaining()
      );
    }
  }

  /**
   * obtains the helper.
   *
   * @return helper.
   */
  @NotNull
  CodecHelper helper();

  /**
   * obtains the minecraft version.
   *
   * @return minecraft version.
   */
  @NotNull
  String minecraftVersion();

  /**
   * gets the packet by class.
   *
   * @param cls the cls to get.
   * @param <T> type of the packet.
   *
   * @return packet definition.
   */
  @NotNull
  default <T extends MinecraftPacket> Optional<PacketDefinition<T>> packet(
    @NotNull final Class<T> cls
  ) {
    return Optional
      .ofNullable(this.packets().get(cls))
      .map(definition -> (PacketDefinition<T>) definition);
  }

  /**
   * gets the packet by id.
   *
   * @param id the id to get.
   *
   * @return packet definition.
   */
  @NotNull
  default Optional<PacketDefinition<? extends MinecraftPacket>> packet(
    final int id
  ) {
    return Optional.ofNullable(this.packetsById().get(id));
  }

  /**
   * obtains the packets.
   *
   * @return packets.
   */
  @NotNull
  Object2ObjectMap<Class<? extends MinecraftPacket>, PacketDefinition<?>> packets();

  /**
   * obtains the packets.
   *
   * @return packets.
   */
  @NotNull
  Int2ObjectMap<PacketDefinition<?>> packetsById();

  /**
   * obtains the protocol version.
   *
   * @return protocol version.
   */
  int protocolVersion();

  /**
   * obtains the protocol version as string.
   *
   * @return protocol version as string.
   */
  @NotNull
  String protocolVersionAsString();

  /**
   * converts {@code this} to {@link Builder}.
   *
   * @return converted builder.
   */
  @NotNull
  default Builder toBuilder() {
    return new Builder(this);
  }

  /**
   * a class that represents builder for codec.
   */
  @RequiredArgsConstructor
  @Accessors(fluent = true)
  final class Builder {

    /**
     * the encoders package.
     */
    private static final String ENCODERS_PACKAGE =
      "io.github.shiruka.protocol.codec.v%s.encoder";

    /**
     * the packets.
     */
    @NotNull
    private final Object2ObjectMap<Class<? extends MinecraftPacket>, PacketDefinition<?>> packets;

    /**
     * the helper.
     */
    @Setter
    @Nullable
    private CodecHelper helper;

    /**
     * the minecraft version.
     */
    @Setter
    @NotNull
    private String minecraftVersion = "";

    /**
     * the protocol version.
     */
    @Setter
    private int protocolVersion = -1;

    /**
     * ctor.
     *
     * @param copy the copy version.
     */
    private Builder(@NotNull final Codec copy) {
      this.minecraftVersion = copy.minecraftVersion();
      this.protocolVersion = copy.protocolVersion();
      this.helper = copy.helper();
      this.packets = new Object2ObjectOpenHashMap<>(copy.packets());
    }

    /**
     * ctor.
     */
    private Builder() {
      this(new Object2ObjectOpenHashMap<>());
    }

    /**
     * builds the codec.
     *
     * @return built codec.
     */
    @NotNull
    public Codec build() {
      Preconditions.checkNotNull(this.helper, "Helper not set!");
      final var packetsById = new Int2ObjectOpenHashMap<PacketDefinition<?>>();
      this.packets.values()
        .forEach(definition -> packetsById.put(definition.id(), definition));
      return Codec.create(
        this.minecraftVersion,
        this.protocolVersion,
        this.helper,
        this.packets,
        packetsById
      );
    }

    /**
     * registers the packet.
     *
     * @param encoder the encoder to register.
     * @param <T> type of the packet.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public <T extends MinecraftPacket> Builder registerPacket(
      @NotNull final PacketEncoder.Base<T> encoder
    ) {
      return this.registerPacket(encoder.id(), encoder.factory(), encoder);
    }

    /**
     * registers the packet.
     *
     * @param id the id to register.
     * @param factory the factory to register.
     * @param encoder the encoder to register.
     * @param <T> type of the packet.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public <T extends MinecraftPacket> Builder registerPacket(
      @Range(from = 0, to = Integer.MAX_VALUE) final int id,
      @NotNull final Supplier<T> factory,
      @NotNull final PacketEncoder<T> encoder
    ) {
      return this.registerPacket(new PacketDefinition<>(id, factory, encoder));
    }

    /**
     * registers the packet.
     *
     * @param definition the definition o register.
     * @param <T> type of the packet.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public <T extends MinecraftPacket> Builder registerPacket(
      @NotNull final PacketDefinition<T> definition
    ) {
      this.packets.put(definition.factory().get().getClass(), definition);
      return this;
    }

    /**
     * registers the packets by packet encoders.
     *
     * @param classes the classes to scan.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder registerPackets(
      @NotNull final Collection<Class<? extends PacketEncoder.Base>> classes
    ) {
      for (final var cls : classes) {
        try {
          final var constructor = cls.getConstructor();
          constructor.setAccessible(true);
          this.registerPacket(constructor.newInstance());
        } catch (final Exception e) {
          throw new RuntimeException(e);
        }
      }
      return this;
    }

    /**
     * removes the packet.
     *
     * @param packetClass the packet class to remove.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder removePacket(
      @NotNull final Class<? extends MinecraftPacket> packetClass
    ) {
      this.packets.remove(packetClass);
      return this;
    }

    /**
     * scans the encoder package and registers the found packets.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder scanPackageAndRegister() {
      Preconditions.checkState(
        this.protocolVersion != -1,
        "Protocol version not set!"
      );
      return this.scanPackageAndRegister(
          Builder.ENCODERS_PACKAGE.formatted(this.protocolVersion)
        );
    }

    /**
     * scans the encoder package and registers the found packets.
     *
     * @param packageName the package name to scan.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder scanPackageAndRegister(@NotNull final String packageName) {
      return this.registerPackets(
          new Reflections(packageName).getSubTypesOf(PacketEncoder.Base.class)
        );
    }
  }

  /**
   * a simple implementation of {@link Codec}.
   *
   * @param minecraftVersion the minecraft version.
   * @param protocolVersion the protocol version.
   * @param protocolVersionAsString the protocol version as string.
   * @param helper the helper.
   * @param packets the packets.
   * @param packetsById the packets by id.
   */
  record Impl(
    @NotNull String minecraftVersion,
    int protocolVersion,
    @NotNull String protocolVersionAsString,
    @NotNull CodecHelper helper,
    @NotNull
    Object2ObjectMap<Class<? extends MinecraftPacket>, PacketDefinition<?>> packets,
    @NotNull Int2ObjectMap<PacketDefinition<?>> packetsById
  )
    implements Codec {}
}
