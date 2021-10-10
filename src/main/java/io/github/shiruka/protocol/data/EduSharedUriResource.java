package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents edu shared uri resources.
 *
 * @param buttonName the button name.
 * @param linkUri the link uri.
 */
public final record EduSharedUriResource(
  @NotNull String buttonName,
  @NotNull String linkUri
) {

  /**
   * the empty.
   */
  public static final EduSharedUriResource EMPTY = new EduSharedUriResource("", "");
}
