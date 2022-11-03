package io.github.shiruka.protocol.data.command;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains command enum constraint type.
 */
public enum CommandEnumConstraintType {
  /**
   * the cheats enabled.
   */
  CHEATS_ENABLED,
  /**
   * the operator permissions.
   */
  OPERATOR_PERMISSIONS,
  /**
   * the host permissions.
   */
  HOST_PERMISSIONS,
  /**
   * the unknown 3.
   */
  UNKNOWN_3;

  /**
   * the values.
   */
  public static final CommandEnumConstraintType[] VALUES = CommandEnumConstraintType.values();

  /**
   * gets type by ordinal.
   *
   * @param ordinal the ordinal to get.
   *
   * @return type.
   */
  @NotNull
  public static CommandEnumConstraintType byOrdinal(final int ordinal) {
    Preconditions.checkArgument(
      ordinal >= 0 && ordinal < CommandEnumConstraintType.VALUES.length,
      "Unknown CommandEnumConstraintType ID: %s",
      ordinal
    );
    return CommandEnumConstraintType.VALUES[ordinal];
  }
}
