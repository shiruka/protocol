package io.github.shiruka.protocol.data;

import io.github.shiruka.api.common.vectors.Vector3i;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a record class that represents player block action data.
 *
 * @param action the action.
 * @param blockPosition the block position.
 * @param face the face.
 */
public record PlayerBlockActionData(
  @NotNull PlayerActionType action,
  @Nullable Vector3i blockPosition,
  int face
) {

}
