package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents craft recipe stack request action data.
 *
 * @param recipeNetworkId the recipe network id.
 */
public record SRADCraftRecipe(
  int recipeNetworkId
) implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CRAFT_RECIPE;
  }
}
