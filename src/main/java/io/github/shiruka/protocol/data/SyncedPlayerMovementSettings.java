package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents synced player movement settings.
 *
 * @param serverAuthoritativeBlockBreaking the server authoritative block breaking.
 * @param movementMode the movement mode.
 * @param rewindHistorySize the rewind history size.
 */
public record SyncedPlayerMovementSettings(
  boolean serverAuthoritativeBlockBreaking,
  @NotNull AuthoritativeMovementMode movementMode,
  int rewindHistorySize
) {

}
