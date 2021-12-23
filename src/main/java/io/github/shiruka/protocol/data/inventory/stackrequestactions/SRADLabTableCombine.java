package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents lab table combine stack request action data.
 */
public record SRADLabTableCombine() implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.LAB_TABLE_COMBINE;
  }
}
