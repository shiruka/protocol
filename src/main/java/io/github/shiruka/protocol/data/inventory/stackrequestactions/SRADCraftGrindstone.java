package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents craft grindstone stack request action data.
 *
 * @param recipeNetworkId the recipe network id.
 * @param repairCost the repair cost.
 */
public record SRADCraftGrindstone(int recipeNetworkId, int repairCost)
  implements StackRequestActionData {
  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CRAFT_REPAIR_AND_DISENCHANT;
  }
}
