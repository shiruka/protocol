package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents craft creative stack request action data.
 *
 * @param creativeItemNetworkId the creative item network id.
 */
public record SRADCraftCreative(int creativeItemNetworkId)
  implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CRAFT_CREATIVE;
  }
}
