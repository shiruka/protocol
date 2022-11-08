package io.github.shiruka.protocol.data.command;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents command enum constraint data.
 *
 * @param option the option.
 * @param enumData the enum data.
 * @param constraints the constraints.
 */
public record CommandEnumConstraintData(
  @NotNull String option,
  @NotNull CommandEnumData enumData,
  @NotNull CommandEnumConstraintType[] constraints
) {

}
