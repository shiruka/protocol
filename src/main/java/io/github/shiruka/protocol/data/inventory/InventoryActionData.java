package io.github.shiruka.protocol.data.inventory;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents inventory action data.
 *
 * @param source the source.
 * @param slot the slot.
 * @param fromItem the from item.
 * @param toItem the to item.
 * @param stackNetworkId the stack network id.
 */
public record InventoryActionData(
  @NotNull InventorySource source,
  int slot,
  @NotNull ItemData fromItem,
  @NotNull ItemData toItem,
  int stackNetworkId
) {
  /**
   * ctor.
   *
   * @param source the source.
   * @param slot the slot.
   * @param fromItem the from item.
   * @param toItem the to item.
   */
  public InventoryActionData(
    @NotNull final InventorySource source,
    final int slot,
    @NotNull final ItemData fromItem,
    @NotNull final ItemData toItem
  ) {
    this(source, slot, fromItem, toItem, 0);
  }

  /**
   * reverses the items.
   *
   * @return reversed inventory action data.
   */
  @NotNull
  public InventoryActionData reverse() {
    return new InventoryActionData(
      this.source,
      this.slot,
      this.toItem,
      this.fromItem,
      this.stackNetworkId
    );
  }
}
