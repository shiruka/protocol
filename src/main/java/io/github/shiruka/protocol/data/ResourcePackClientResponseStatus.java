package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains resource pack client response status.
 */
public enum ResourcePackClientResponseStatus {
  /**
   * the none.
   */
  NONE,
  /**
   * the refused.
   */
  REFUSED,
  /**
   * the send packs.
   */
  SEND_PACKS,
  /**
   * the have all packs.
   */
  HAVE_ALL_PACKS,
  /**
   * the completed.
   */
  COMPLETED;

  /**
   * the cache.
   */
  private static final ResourcePackClientResponseStatus[] CACHE = ResourcePackClientResponseStatus.values();

  /**
   * gets resource pack client response status by ordinal.
   *
   * @param ordinal the ordinal.
   *
   * @return resource pack client response status.
   */
  @NotNull
  public static ResourcePackClientResponseStatus byOrdinal(final int ordinal) {
    return ResourcePackClientResponseStatus.CACHE[ordinal];
  }
}
