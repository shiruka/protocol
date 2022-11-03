package io.github.shiruka.protocol.common;

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
