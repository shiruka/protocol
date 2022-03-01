package io.github.shiruka.protocol.codec;

import com.google.common.base.Preconditions;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.packets.Unknown;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import tr.com.infumia.infumialib.reflection.RefField;
import tr.com.infumia.infumialib.reflection.clazz.ClassOf;

/**
 * an interface to determine codecs.
 */
@SuppressWarnings({"unchecked", "rawtypes"})
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
  static Codec create(@NotNull final String minecraftVersion, final int protocolVersion,
                      @NotNull final CodecHelper helper,
                      @NotNull final Object2ObjectMap<Class<? extends MinecraftPacket>, PacketDefinition<?>> packets,
                      @NotNull final Int2ObjectMap<PacketDefinition<?>> packetsById) {
    return new Impl(helper, minecraftVersion, packets, packetsById, protocolVersion, String.valueOf(protocolVersion));
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
  default MinecraftPacket decode(@NotNull final PacketBuffer buffer, final int packetId,
                                 @NotNull final MinecraftChildChannel session) {
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
      throw new PacketEncodeException("Error whilst deserializing %s".formatted(packet), e);
    }
    if (Codec.LOG.isDebugEnabled() && buffer.isReadable()) {
      Codec.LOG.debug("{} still has {} bytes to read!",
        packet.getClass().getSimpleName(), buffer.remaining());
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
  default void encode(@NotNull final PacketBuffer buffer, @NotNull final MinecraftPacket packet,
                      @NotNull final MinecraftSession session) {
    @NotNull final PacketEncoder<MinecraftPacket> encoder;
    if (packet instanceof Unknown) {
      encoder = (PacketEncoder) packet;
    } else {
      encoder = (PacketEncoder) this.packet(packet.getClass())
        .map(PacketDefinition::encoder)
        .orElseThrow();
    }
    try {
      encoder.encode(packet, this.helper(), buffer, session);
    } catch (final Exception e) {
      throw new PacketEncodeException("Error whilst deserializing %s".formatted(packet), e);
    }
    if (Codec.LOG.isDebugEnabled() && buffer.isReadable()) {
      Codec.LOG.debug("{} still has {} bytes to read!",
        packet.getClass().getSimpleName(), buffer.remaining());
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
  default <T extends MinecraftPacket> Optional<PacketDefinition<T>> packet(@NotNull final Class<T> cls) {
    return Optional.ofNullable(this.packets().get(cls))
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
  default Optional<PacketDefinition<? extends MinecraftPacket>> packet(final int id) {
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
    private static final String ENCODERS_PACKAGE = "io.github.shiruka.protocol.codec.v%s.encoders";

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
    private int protocolVersion;

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
      this.packets.values().forEach(definition -> packetsById.put(definition.id(), definition));
      return Codec.create(this.minecraftVersion, this.protocolVersion, this.helper, this.packets, packetsById);
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
    public <T extends MinecraftPacket> Builder registerPacket(@NotNull final PacketEncoder.Base<T> encoder) {
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
    public <T extends MinecraftPacket> Builder registerPacket(final int id, @NotNull final Supplier<T> factory,
                                                              @NotNull final PacketEncoder<T> encoder) {
      Preconditions.checkArgument(id >= 0,
        "Id cannot be negative!");
      final var packetClass = factory.get().getClass();
      Preconditions.checkArgument(!this.packets.containsKey(packetClass),
        "Packet already registered!");
      this.packets.put(packetClass, new PacketDefinition<>(id, factory, encoder));
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
    public Builder removePacket(@NotNull final Class<? extends MinecraftPacket> packetClass) {
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
      Preconditions.checkState(this.protocolVersion != 0, "Protocol version not set!");
      final var classes = new Reflections(Builder.ENCODERS_PACKAGE.formatted(this.protocolVersion))
        .get(Scanners.SubTypes.of(PacketEncoder.Base.class).asClass());
      for (final var cls : classes) {
        new ClassOf<>(cls).getField("INSTANCE")
          .flatMap(RefField::getValue)
          .filter(PacketEncoder.Base.class::isInstance)
          .map(PacketEncoder.Base.class::cast)
          .ifPresent(this::registerPacket);
      }
      return this;
    }

    /**
     * updates the encoder.
     *
     * @param factory the factory to update.
     * @param serializer the serializer to update.
     * @param <T> type of the packet.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public <T extends MinecraftPacket> Builder updatePacketEncoder(@NotNull final Supplier<T> factory,
                                                                   @NotNull final PacketEncoder<T> serializer) {
      final var packetClass = factory.get().getClass();
      final var wildDefinition = this.packets.get(packetClass);
      Preconditions.checkNotNull(wildDefinition, "Packet does not exist!");
      final var uncheckedDefinition = (PacketDefinition<T>) wildDefinition;
      this.packets.replace(
        packetClass,
        uncheckedDefinition,
        new PacketDefinition<>(uncheckedDefinition.id(), uncheckedDefinition.factory(), serializer)
      );
      return this;
    }
  }

  /**
   * a simple implementation of {@link Codec}.
   */
  @RequiredArgsConstructor
  @Accessors(fluent = true)
  final class Impl implements Codec {

    /**
     * the helper.
     */
    @Getter
    @NotNull
    private final CodecHelper helper;

    /**
     * the minecraft version.
     */
    @Getter
    @NotNull
    private final String minecraftVersion;

    /**
     * the packets.
     */
    @NotNull
    private final Object2ObjectMap<Class<? extends MinecraftPacket>, PacketDefinition<?>> packets;

    /**
     * the packets by id.
     */
    @NotNull
    private final Int2ObjectMap<PacketDefinition<?>> packetsById;

    /**
     * the protocol version.
     */
    @Getter
    private final int protocolVersion;

    /**
     * the protocol version as string.
     */
    @Getter
    @NotNull
    private final String protocolVersionAsString;

    @NotNull
    @Override
    public Object2ObjectMap<Class<? extends MinecraftPacket>, PacketDefinition<?>> packets() {
      return Object2ObjectMaps.unmodifiable(this.packets);
    }

    @NotNull
    @Override
    public Int2ObjectMap<PacketDefinition<?>> packetsById() {
      return Int2ObjectMaps.unmodifiable(this.packetsById);
    }
  }
}
