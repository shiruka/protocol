package io.github.shiruka.protocol.codec;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine codecs.
 */
public interface Codec {

  /**
   * creates a simple codec.
   *
   * @param minecraftVersion the minecraft version to create.
   * @param protocolVersion the protocol version to create.
   * @param helper the helper to create.
   *
   * @return codec.
   */
  @NotNull
  static Codec create(@NotNull final String minecraftVersion, final int protocolVersion,
                      @NotNull final CodecHelper helper) {
    return new Impl(minecraftVersion, protocolVersion, helper);
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
    return new Builder(this.minecraftVersion(), this.protocolVersion(), this.helper());
  }

  /**
   * a class that represents builder for codec.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  @Accessors(fluent = true)
  final class Builder {

    /**
     * the helper.
     */
    @NotNull
    @Setter
    private CodecHelper helper = CodecHelper.EMPTY;

    /**
     * the minecraft version.
     */
    @NotNull
    @Setter
    private String minecraftVersion = "";

    /**
     * the protocol version.
     */
    @Setter
    private int protocolVersion = 0;

    /**
     * ctor.
     *
     * @param minecraftVersion the minecraft version.
     * @param protocolVersion the protocol version.
     * @param helper the helper.
     */
    private Builder(@NotNull final String minecraftVersion, final int protocolVersion,
                    @NotNull final CodecHelper helper) {
      this.minecraftVersion = minecraftVersion;
      this.protocolVersion = protocolVersion;
      this.helper = helper;
    }

    /**
     * builds the codec.
     *
     * @return built codec.
     */
    @NotNull
    public Codec build() {
      return Codec.create(this.minecraftVersion, this.protocolVersion, this.helper);
    }
  }

  /**
   * a simple implementation of {@link Codec}.
   */
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

    /**
     * ctor.
     *
     * @param minecraftVersion the minecraft version.
     * @param protocolVersion the protocol version.
     * @param helper the helper.
     */
    private Impl(@NotNull final String minecraftVersion, final int protocolVersion,
                 @NotNull final CodecHelper helper) {
      this.minecraftVersion = minecraftVersion;
      this.protocolVersion = protocolVersion;
      this.protocolVersionAsString = String.valueOf(protocolVersion);
      this.helper = helper;
    }
  }
}
