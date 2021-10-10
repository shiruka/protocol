package io.github.shiruka.protocol.data;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains spawn biome types.
 */
public enum SpawnBiomeType {
  /**
   * the default.
   */
  DEFAULT,
  /**
   * the user defined.
   */
  USER_DEFINED;

  /**
   * the cache.
   */
  private static final SpawnBiomeType[] CACHE = SpawnBiomeType.values();

  /**
   * gets spawn biome type by şd.
   *
   * @param id the id to get.
   *
   * @return spawn biome type.
   */
  @NotNull
  public static SpawnBiomeType byId(final int id) {
    Preconditions.checkState(id >= 0 && id < SpawnBiomeType.CACHE.length, "Unknown SpawnBiomeType ID: %s", id);
    return SpawnBiomeType.CACHE[id];
  }
}
