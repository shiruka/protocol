package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents craft non-implemented stack request action data.
 */
public record SRADCraftNonImplemented() implements StackRequestActionData {
  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CRAFT_NON_IMPLEMENTED_DEPRECATED;
  }
}
