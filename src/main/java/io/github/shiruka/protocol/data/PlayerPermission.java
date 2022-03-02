package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains player permissions.
 */
public enum PlayerPermission {
  /**
   * the visitor.
   */
  VISITOR,
  /**
   * the member.
   */
  MEMBER,
  /**
   * the operator.
   */
  OPERATOR,
  /**
   * the custom.
   */
  CUSTOM;

  /**
   * the cache.
   */
  private static final PlayerPermission[] VALUES = PlayerPermission.values();

  /**
   * gets the player permission by ordinal.
   *
   * @param ordinal the ordinal to get.
   *
   * @return player permission.
   */
  @NotNull
  public static PlayerPermission byOrdinal(final int ordinal) {
    return PlayerPermission.VALUES[ordinal];
  }
}
