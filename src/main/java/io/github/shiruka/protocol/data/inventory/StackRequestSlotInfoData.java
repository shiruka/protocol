package io.github.shiruka.protocol.data.inventory;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents stack request slot info data.
 *
 * @param container the container.
 * @param slot the slot.
 * @param stackNetworkId the stack network id.
 */
public record StackRequestSlotInfoData(
  @NotNull ContainerSlotType container,
  byte slot,
  int stackNetworkId
) {}
