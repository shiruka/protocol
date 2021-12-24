package io.github.shiruka.protocol.codec;

import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine codecs.
 */
public interface Codec {

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
   * a class that represents builder for codec.
   */
  @Accessors(fluent = true)
  final class Builder {

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
  }
}
