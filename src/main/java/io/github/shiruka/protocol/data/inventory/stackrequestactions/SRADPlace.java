package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import io.github.shiruka.protocol.data.inventory.StackRequestSlotInfoData;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents place stack request action data.
 *
 * @param count the count.
 * @param source the source.
 * @param destination the destination.
 */
public record SRADPlace(
  byte count,
  @NotNull StackRequestSlotInfoData source,
  @NotNull StackRequestSlotInfoData destination
)
  implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.PLACE;
  }
}
