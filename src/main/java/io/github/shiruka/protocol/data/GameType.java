package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains game types.
 */
public enum GameType {
  /**
   * the survival.
   */
  SURVIVAL,
  /**
   * the creative.
   */
  CREATIVE,
  /**
   * the adventure.
   */
  ADVENTURE,
  /**
   * the survival viewer.
   */
  SURVIVAL_VIEWER,
  /**
   * the creative viewer.
   */
  CREATIVE_VIEWER,
  /**
   * the default.
   */
  DEFAULT,
  /**
   * the world default.
   */
  WORLD_DEFAULT;

  /**
   * the cache.
   */
  private static final GameType[] CACHE = GameType.values();

  /**
   * gets game type by id.
   *
   * @param id the id to get.
   *
   * @return game type.
   */
  @NotNull
  public static GameType byId(final int id) {
    return GameType.CACHE[id];
  }
}
