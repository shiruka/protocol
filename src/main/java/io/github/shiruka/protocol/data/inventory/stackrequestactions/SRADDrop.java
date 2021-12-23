package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import io.github.shiruka.protocol.data.inventory.StackRequestSlotInfoData;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that drop stack request action data.
 *
 * @param count the count.
 * @param source the source.
 * @param randomly the randomly.
 */
public record SRADDrop(
  byte count,
  @NotNull StackRequestSlotInfoData source,
  boolean randomly
) implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.DROP;
  }
}
