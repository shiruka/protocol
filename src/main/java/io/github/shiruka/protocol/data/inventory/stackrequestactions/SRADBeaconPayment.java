package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents beacon payment stack request action data.
 *
 * @param primaryEffect the primary effect.
 * @param secondaryEffect the secondary effect.
 */
public record SRADBeaconPayment(
  int primaryEffect,
  int secondaryEffect
) implements StackRequestActionData {

  @NotNull
  @Override
  public StackRequestActionType type() {
    return StackRequestActionType.BEACON_PAYMENT;
  }
}
