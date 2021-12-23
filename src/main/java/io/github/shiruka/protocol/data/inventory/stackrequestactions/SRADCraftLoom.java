package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents craft loom stack request action data.
 *
 * @param patternId the pattern id.
 */
public record SRADCraftLoom(
  @NotNull String patternId
) implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CRAFT_LOOM;
  }
}
