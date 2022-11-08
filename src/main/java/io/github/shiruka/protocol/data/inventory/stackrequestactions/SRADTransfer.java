package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import io.github.shiruka.protocol.data.inventory.StackRequestSlotInfoData;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine transfer stack request action data.
 */
public interface SRADTransfer extends StackRequestActionData {

  /**
   * obtains the count.
   *
   * @return count.
   */
  byte count();

  /**
   * obtains the destination.
   *
   * @return destination.
   */
  @NotNull
  StackRequestSlotInfoData destination();

  /**
   * obtains the source.
   *
   * @return source.
   */
  @NotNull
  StackRequestSlotInfoData source();
}
