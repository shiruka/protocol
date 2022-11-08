package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents craft recipe optional stack request action data.
 *
 * @param recipeNetworkId the recipe network id.
 * @param filteredStringIndex the filtered string index.
 */
public record SRADCraftRecipeOptional(
  int recipeNetworkId,
  int filteredStringIndex
)
  implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CRAFT_RECIPE_OPTIONAL;
  }
}
