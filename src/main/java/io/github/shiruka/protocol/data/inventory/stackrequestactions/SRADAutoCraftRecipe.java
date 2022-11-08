package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents auto crafted recipe stack request action data.
 *
 * @param recipeNetworkId the recipe network id.
 * @param timesCrafted the time crafted.
 */
public record SRADAutoCraftRecipe(int recipeNetworkId, byte timesCrafted)
  implements SRADRecipe {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CRAFT_RECIPE_AUTO;
  }
}
