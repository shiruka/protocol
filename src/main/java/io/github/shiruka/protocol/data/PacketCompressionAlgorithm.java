package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * an enum class that contains packet compression algorithms.
 */
public enum PacketCompressionAlgorithm {
  /**
   * the zlib.
   */
  ZLIB,
  /**
   * the snappy.
   */
  SNAPPY;

  /**
   * the values.
   */
  private static final PacketCompressionAlgorithm[] VALUES = PacketCompressionAlgorithm.values();

  /**
   * gets the algorithm by ordinal.
   *
   * @param ordinal the ordinal to get.
   *
   * @return algorithm.
   */
  @NotNull
  public static PacketCompressionAlgorithm byOrdinal(final int ordinal) {
    return PacketCompressionAlgorithm.VALUES[ordinal];
  }
}
