package io.github.shiruka.protocol.data.inventory.stackrequestactions;

import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine stack request action data.
 */
public interface StackRequestActionData {
  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  StackRequestActionType type();
}
