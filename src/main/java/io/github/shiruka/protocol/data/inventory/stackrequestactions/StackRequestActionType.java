package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * an enum class that contains stack request action types.
 */
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum StackRequestActionType {
  /**
   * the take.
   */
  TAKE(0),
  /**
   * the place.
   */
  PLACE(1),
  /**
   * the swap.
   */
  SWAP(2),
  /**
   * the drop.
   */
  DROP(3),
  /**
   * the destroy.
   */
  DESTROY(4),
  /**
   * the consume.
   */
  CONSUME(5),
  /**
   * the create.
   */
  CREATE(6),
  /**
   * the labe table combine.
   */
  LAB_TABLE_COMBINE(7),
  /**
   * the beacon payment.
   */
  BEACON_PAYMENT(8),
  /**
   * the mine block.
   */
  MINE_BLOCK(0),
  /**
   * the craft recipe.
   */
  CRAFT_RECIPE(9),
  /**
   * the craft recipe auto.
   */
  CRAFT_RECIPE_AUTO(10),
  /**
   * the craft creative.
   */
  CRAFT_CREATIVE(11),
  /**
   * the craft recipe optional.
   */
  CRAFT_RECIPE_OPTIONAL(0),
  /**
   * the craft repair and disenchant.
   */
  CRAFT_REPAIR_AND_DISENCHANT(0),
  /**
   * the craft loom.
   */
  CRAFT_LOOM(0),
  /**
   * the craft non-implemented deprecated.
   */
  CRAFT_NON_IMPLEMENTED_DEPRECATED(12),
  /**
   * the craft result deprecated.
   */
  CRAFT_RESULTS_DEPRECATED(13);

  /**
   * the id.
   */
  @Getter
  private final int id;
}
