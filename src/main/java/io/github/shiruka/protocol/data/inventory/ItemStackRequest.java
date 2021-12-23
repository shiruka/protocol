package io.github.shiruka.protocol.data.inventory;

import io.github.shiruka.protocol.data.inventory.stackrequestactions.StackRequestActionData;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents item stack requests.
 *
 * @param requestId the request id.
 * @param actions the actions.
 * @param filterStrings the filter strings.
 */
public record ItemStackRequest(
  int requestId,
  @NotNull StackRequestActionData[] actions,
  @NotNull String[] filterStrings
) {

}
