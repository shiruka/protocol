package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import io.github.shiruka.protocol.data.inventory.ItemData;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents craft result deprecated stack request action data.
 *
 * @param resultItems the result items.
 * @param timesCrafted the times crafted.
 */
public record SRADCraftResultsDeprecated(
  ItemData[] resultItems,
  byte timesCrafted
)
  implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CRAFT_RESULTS_DEPRECATED;
  }
}
