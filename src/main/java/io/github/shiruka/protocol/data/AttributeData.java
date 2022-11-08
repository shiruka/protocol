package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents attribute data.
 *
 * @param name the name.
 * @param minimum the minimum.
 * @param maximum the maximum.
 * @param defaultValue the default value.
 * @param value the value.
 */
public record AttributeData(
  @NotNull String name,
  float minimum,
  float maximum,
  float defaultValue,
  float value
) {

  /**
   * ctor.
   *
   * @param name the name.
   * @param minimum the minimum.
   * @param maximum the maximum.
   * @param value the value.
   */
  public AttributeData(
    @NotNull final String name,
    final float minimum,
    final float maximum,
    final float value
  ) {
    this(name, minimum, maximum, maximum, value);
  }
}
