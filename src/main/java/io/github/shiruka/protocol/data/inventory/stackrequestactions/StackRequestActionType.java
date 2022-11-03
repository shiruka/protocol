package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

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
  MINE_BLOCK(9),
  /**
   * the craft recipe.
   */
  CRAFT_RECIPE(10),
  /**
   * the craft recipe auto.
   */
  CRAFT_RECIPE_AUTO(11),
  /**
   * the craft creative.
   */
  CRAFT_CREATIVE(12),
  /**
   * the craft recipe optional.
   */
  CRAFT_RECIPE_OPTIONAL(13),
  /**
   * the craft repair and disenchant.
   */
  CRAFT_REPAIR_AND_DISENCHANT(14),
  /**
   * the craft loom.
   */
  CRAFT_LOOM(15),
  /**
   * the craft non-implemented deprecated.
   */
  CRAFT_NON_IMPLEMENTED_DEPRECATED(16),
  /**
   * the craft result deprecated.
   */
  CRAFT_RESULTS_DEPRECATED(17);

  /**
   * the values.
   */
  public static final StackRequestActionType[] VALUES = StackRequestActionType.values();

  /**
   * the id.
   */
  @Getter
  private final int id;

  /**
   * gets stack request action type by id.
   *
   * @param id the id to get.
   *
   * @return stack request action type.
   */
  @NotNull
  public static StackRequestActionType byId(final int id) {
    return Arrays
      .stream(StackRequestActionType.VALUES)
      .filter(stackRequestActionType -> stackRequestActionType.id == id)
      .findFirst()
      .orElseThrow(() ->
        new IllegalStateException(
          "Stack request action type %s not found!".formatted(id)
        )
      );
  }
}
