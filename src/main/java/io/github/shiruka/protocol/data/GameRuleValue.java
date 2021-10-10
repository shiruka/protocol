package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents game rule values.
 *
 * @param name the name.
 * @param editable the editable.
 * @param value the value.
 */
public final record GameRuleValue(
  @NotNull String name,
  boolean editable,
  Object value
) {

  /**
   * ctor.
   *
   * @param name the name.
   * @param value the value.
   */
  public GameRuleValue(@NotNull final String name, final Object value) {
    this(name, false, value);
  }
}
