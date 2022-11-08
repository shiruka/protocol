package io.github.shiruka.protocol.data;

import io.github.shiruka.protocol.common.IdentifierDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents item definitions.
 *
 * @param identifier the identifier.
 * @param id the id.
 * @param componentBased the component based.
 */
public record ItemDefinition(
  @NotNull String identifier,
  int id,
  boolean componentBased
)
  implements IdentifierDefinition {
  /**
   * the air.
   */
  public static final ItemDefinition AIR = new ItemDefinition(
    "minecraft:air",
    0,
    false
  );

  /**
   * the legacy firework.
   */
  public static final ItemDefinition LEGACY_FIREWORK = new ItemDefinition(
    "minecraft:fireworks_rocket",
    401,
    false
  );
}
