package io.github.shiruka.protocol.codec;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine codecs.
 */
public interface Codec {

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
}
