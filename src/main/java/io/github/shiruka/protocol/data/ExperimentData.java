package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents experiment data.
 *
 * @param name the name.
 * @param enabled the enabled.
 */
public record ExperimentData(@NotNull String name, boolean enabled) {

  /**
   * ctor.
   *
   * @param name the name.
   */
  public ExperimentData(@NotNull final String name) {
    this(name, true);
  }
}
