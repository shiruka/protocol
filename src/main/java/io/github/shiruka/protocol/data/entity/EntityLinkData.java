package io.github.shiruka.protocol.data.entity;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents entity link data.
 *
 * @param from the from.
 * @param to the to.
 * @param type the type.
 * @param immediate the immediate.
 * @param riderInitiated the rider initiated.
 */
public record EntityLinkData(
  long from,
  long to,
  @NotNull EntityLinkDataType type,
  boolean immediate,
  boolean riderInitiated
) {
  /**
   * ctor.
   *
   * @param from the from.
   * @param to the to.
   * @param type the type.
   * @param immediate the immediate.
   */
  public EntityLinkData(
    final long from,
    final long to,
    @NotNull final EntityLinkDataType type,
    final boolean immediate
  ) {
    this(from, to, type, immediate, false);
  }
}
