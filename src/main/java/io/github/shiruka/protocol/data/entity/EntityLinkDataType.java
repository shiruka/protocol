package io.github.shiruka.protocol.data.entity;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains entity link data types.
 */
public enum EntityLinkDataType {
  /**
   * the remove.
   */
  REMOVE,
  /**
   * the rider.
   */
  RIDER,
  /**
   * the passenger.
   */
  PASSENGER;

  /**
   * the cache.
   */
  private static final EntityLinkDataType[] CACHE = EntityLinkDataType.values();

  /**
   * gets type by ordinal.
   *
   * @param ordinal the ordinal to get.
   *
   * @return type.
   */
  @NotNull
  public static EntityLinkDataType byOrdinal(final int ordinal) {
    Preconditions.checkState(ordinal >= 0 && ordinal < EntityLinkDataType.CACHE.length,
      "Unknown EntityLinkDataType ID: %s", ordinal);
    return EntityLinkDataType.CACHE[ordinal];
  }
}
