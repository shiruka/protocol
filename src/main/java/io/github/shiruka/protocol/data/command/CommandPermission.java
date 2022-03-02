package io.github.shiruka.protocol.data.command;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains command permissions.
 */
public enum CommandPermission {
  /**
   * the normal.
   */
  NORMAL,
  /**
   * the operator.
   */
  OPERATOR,
  /**
   * the host.
   */
  HOST,
  /**
   * the automation.
   */
  AUTOMATION,
  /**
   * the admin.
   */
  ADMIN;

  /**
   * the cache.
   */
  private static final CommandPermission[] VALUES = CommandPermission.values();

  /**
   * gets the command permission by ordinal.
   *
   * @param ordinal the ordinal to get.
   *
   * @return command permission.
   */
  @NotNull
  public static CommandPermission byOrdinal(final int ordinal) {
    return CommandPermission.VALUES[ordinal];
  }
}
