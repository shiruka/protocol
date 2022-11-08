package io.github.shiruka.protocol.data.inventory.stackrequestactions;

/**
 * an interface to determine recipe stack request action data.
 */
public interface SRADRecipe extends StackRequestActionData {

  /**
   * obtains the recipe network id.
   *
   * @return recipe network id.
   */
  int recipeNetworkId();
}
