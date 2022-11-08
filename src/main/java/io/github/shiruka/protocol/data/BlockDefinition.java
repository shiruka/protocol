package io.github.shiruka.protocol.data;

import io.github.shiruka.nbt.CompoundTag;
import io.github.shiruka.protocol.common.IdentifierDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents block definitions.
 *
 * @param identifier the identifier.
 * @param id the id.
 * @param state the state.
 */
public record BlockDefinition(
  @NotNull String identifier,
  int id,
  @NotNull CompoundTag state
)
  implements IdentifierDefinition {}
