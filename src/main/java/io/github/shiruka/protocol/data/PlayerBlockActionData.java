package io.github.shiruka.protocol.data;

import io.github.shiruka.api.base.Vector3i;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents player block action data.
 *
 * @param action the action.
 * @param blockPosition the block position.
 * @param face the face.
 */
public record PlayerBlockActionData(
  @NotNull PlayerActionType action,
  Vector3i blockPosition,
  int face
) {}
