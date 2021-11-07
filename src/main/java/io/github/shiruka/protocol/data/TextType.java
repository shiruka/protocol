package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains text types.
 */
public enum TextType {
  /**
   * the raw.
   */
  RAW,
  /**
   * the chat.
   */
  CHAT,
  /**
   * the translation.
   */
  TRANSLATION,
  /**
   * the popup.
   */
  POPUP,
  /**
   * the jukebox popup.
   */
  JUKEBOX_POPUP,
  /**
   * the tip.
   */
  TIP,
  /**
   * the system.
   */
  SYSTEM,
  /**
   * the whisper.
   */
  WHISPER,
  /**
   * the announcement.
   */
  ANNOUNCEMENT,
  /**
   * the object.
   */
  OBJECT,
  /**
   * the object whisper.
   */
  OBJECT_WHISPER;

  /**
   * the cache.
   */
  private static final TextType[] CACHE = TextType.values();

  /**
   * gets text type by ordinal.
   *
   * @param ordinal the ordinal.
   *
   * @return text type.
   */
  @NotNull
  public static TextType byOrdinal(final int ordinal) {
    return TextType.CACHE[ordinal];
  }
}
