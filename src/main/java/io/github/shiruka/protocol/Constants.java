package io.github.shiruka.protocol;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

/**
 * a class that represents constants.
 */
public final class Constants {

  /**
   * the blocking item identifier.
   */
  public static final String BLOCKING_ITEM_IDENTIFIER = "minecraft:shield";

  /**
   * the game rule types.
   */
  public static final Object2IntMap<Class<?>> GAME_RULE_TYPES;

  static {
    GAME_RULE_TYPES = new Object2IntOpenHashMap<>(3, 0.5f);
    Constants.GAME_RULE_TYPES.defaultReturnValue(-1);
    Constants.GAME_RULE_TYPES.put(Boolean.class, 1);
    Constants.GAME_RULE_TYPES.put(Integer.class, 2);
    Constants.GAME_RULE_TYPES.put(Float.class, 3);
  }

  /**
   * ctor.
   */
  private Constants() {
  }
}
