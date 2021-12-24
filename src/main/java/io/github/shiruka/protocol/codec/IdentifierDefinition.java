package io.github.shiruka.protocol.codec;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine definitions.
 */
public interface IdentifierDefinition extends Definition {

  /**
   * obtains the identifier.
   *
   * @return identifier.
   */
  @NotNull
  String identifier();
}
