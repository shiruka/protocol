package io.github.shiruka.protocol.codec;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine definitions.
 */
public interface Definition {

  /**
   * obtains the identifier.
   *
   * @return identifier.
   */
  @NotNull
  String identifier();

  /**
   * obtains the runtime id.
   *
   * @return runtime id.
   */
  int runtimeId();
}
