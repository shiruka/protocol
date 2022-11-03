package io.github.shiruka.protocol.data.command;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents command enum data.
 *
 * @param isSoft the is soft
 * @param name the name.
 * @param values the values.
 */
public record CommandEnumData(
  boolean isSoft,
  @NotNull String name,
  @NotNull String[] values
) {}
