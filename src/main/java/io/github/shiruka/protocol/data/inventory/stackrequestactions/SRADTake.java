package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import io.github.shiruka.protocol.data.inventory.StackRequestSlotInfoData;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents take stack request action data.
 *
 * @param count the count.
 * @param source the source.
 * @param destination the destination.
 */
public record SRADTake(
  byte count,
  @NotNull StackRequestSlotInfoData source,
  @NotNull StackRequestSlotInfoData destination
)
  implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.TAKE;
  }
}
