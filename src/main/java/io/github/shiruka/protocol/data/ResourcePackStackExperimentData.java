package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents experiment data.
 */
public final record ResourcePackStackExperimentData(
  @NotNull String name,
  boolean enabled
) {

  /**
   * ctor.
   *
   * @param name the name.
   */
  public ResourcePackStackExperimentData(@NotNull final String name) {
    this(name, true);
  }
}
