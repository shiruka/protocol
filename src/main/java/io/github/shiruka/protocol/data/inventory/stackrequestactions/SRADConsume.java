package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import io.github.shiruka.protocol.data.inventory.StackRequestSlotInfoData;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents consume stack request action data.
 *
 * @param count the count.
 * @param source the source.
 */
public record SRADConsume(byte count, @NotNull StackRequestSlotInfoData source)
  implements StackRequestActionData {
  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CONSUME;
  }
}
