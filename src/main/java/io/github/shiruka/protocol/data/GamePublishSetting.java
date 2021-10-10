package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains game publish settings.
 */
public enum GamePublishSetting {
  /**
   * the no multi play.
   */
  NO_MULTI_PLAY,
  /**
   * the invite only.
   */
  INVITE_ONLY,
  /**
   * the friends only.
   */
  FRIENDS_ONLY,
  /**
   * the friends of friends.
   */
  FRIENDS_OF_FRIENDS,
  /**
   * the public.
   */
  PUBLIC;

  /**
   * the cache.
   */
  private static final GamePublishSetting[] CACHE = GamePublishSetting.values();

  /**
   * gets the game publish setting by ordinal.
   *
   * @param ordinal the ordinal to get.
   *
   * @return game publish setting.
   */
  @NotNull
  public static GamePublishSetting byOrdinal(final int ordinal) {
    return GamePublishSetting.CACHE[ordinal];
  }
}
