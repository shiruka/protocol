package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents create stack request action data.
 *
 * @param slot the slot.
 */
public record SRADCreate(
  byte slot
) implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.CREATE;
  }
}
