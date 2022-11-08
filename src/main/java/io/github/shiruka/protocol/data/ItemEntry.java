package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents item entries.
 *
 * @param identifier the identifier.
 * @param id the id.
 * @param componentBased the component based.
 */
public record ItemEntry(
  @NotNull String identifier,
  short id,
  boolean componentBased
) {

  /**
   * ctor.
   *
   * @param identifier the identifier.
   * @param id the id.
   */
  public ItemEntry(@NotNull final String identifier, final short id) {
    this(identifier, id, false);
  }
}
