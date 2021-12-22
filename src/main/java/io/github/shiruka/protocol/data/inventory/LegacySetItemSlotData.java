package io.github.shiruka.protocol.data.inventory;

/**
 * a record class that represents legacy set item slot data.
 *
 * @param containerId the container id.
 * @param slots the slots.
 */
public record LegacySetItemSlotData(
  int containerId,
  byte[] slots
) {

}
