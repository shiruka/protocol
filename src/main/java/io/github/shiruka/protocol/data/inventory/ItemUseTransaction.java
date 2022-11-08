package io.github.shiruka.protocol.data.inventory;

import io.github.shiruka.api.base.Vector3f;
import io.github.shiruka.api.base.Vector3i;
import java.util.List;

/**
 * a record class that represents item use transaction.
 *
 * @param legacyRequestId the legacy request id.
 * @param legacySlots the legacy slots.
 * @param usingNetIds the using net ids.
 * @param actions the actions.
 * @param actionType the action type.
 * @param blockPosition the block position.
 * @param blockFace the block face.
 * @param hotbarSlot the hotbar slot.
 * @param itemInHand the item in hand.
 * @param playerPosition the player position.
 * @param clickPosition the click position.
 * @param blockRuntimeId the block runtime id.
 */
public record ItemUseTransaction(
  int legacyRequestId,
  List<LegacySetItemSlotData> legacySlots,
  boolean usingNetIds,
  List<InventoryActionData> actions,
  int actionType,
  Vector3i blockPosition,
  int blockFace,
  int hotbarSlot,
  ItemData itemInHand,
  Vector3f playerPosition,
  Vector3f clickPosition,
  int blockRuntimeId
) {

}
