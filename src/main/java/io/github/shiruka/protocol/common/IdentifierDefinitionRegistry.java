package io.github.shiruka.protocol.common;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents identifier definition registries.
 *
 * @param idRegistry the id registry.
 * @param identifierRegistry the identifier registry.
 * @param <T> type of the identifier definition.
 */
public record IdentifierDefinitionRegistry<T extends IdentifierDefinition>(
  @NotNull Int2ObjectMap<T> idRegistry,
  @NotNull Object2ObjectMap<String, T> identifierRegistry
) {

  /**
   * creates a new builder.
   *
   * @param <T> type of the definition.
   *
   * @return a newly created builder.
   */
  @NotNull
  public static <T extends IdentifierDefinition> Builder<T> newBuilder() {
    return new Builder<>();
  }

  /**
   * gets the definition id by id.
   *
   * @param id the id to get.
   *
   * @return definition.
   */
  @NotNull
  public T byId(final int id) {
    return this.idRegistry.get(id);
  }

  /**
   * gets the definition by identifier.
   *
   * @param identifier the identifier to get.
   *
   * @return definition.
   */
  @NotNull
  public T byIdentifier(@NotNull final String identifier) {
    return this.identifierRegistry.get(identifier);
  }

  /**
   * a class that represents builder for {@link IdentifierDefinitionRegistry}.
   *
   * @param <T> type of the definition.
   */
  public static final class Builder<T extends IdentifierDefinition> {

    /**
     * the id registry.
     */
    private final Int2ObjectMap<T> idRegistry = new Int2ObjectOpenHashMap<>();

    /**
     * the identifier registry.
     */
    private final Object2ObjectMap<String, T> identifierRegistry = new Object2ObjectOpenHashMap<>();

    /**
     * adds all the definitions.
     *
     * @param definitions the definitions to add.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    @SafeVarargs
    public final Builder<T> addAll(@NotNull final T... definitions) {
      Arrays.stream(definitions).forEach(this::add);
      return this;
    }

    /**
     * adds the definition.
     *
     * @param definition the definition to add.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> add(@NotNull final T definition) {
      final var id = definition.id();
      final var identifier = definition.identifier();
      Preconditions.checkArgument(
        !this.identifierRegistry.containsKey(identifier),
        "Identifier is already registered!"
      );
      Preconditions.checkArgument(
        !this.idRegistry.containsKey(id),
        "ID is already registered!"
      );
      this.idRegistry.put(id, definition);
      this.identifierRegistry.put(identifier, definition);
      return this;
    }

    /**
     * adds all the definitions.
     *
     * @param definitions the definitions to add.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> addAll(@NotNull final Collection<T> definitions) {
      definitions.forEach(this::add);
      return this;
    }

    /**
     * builds the definition registry.
     *
     * @return definition registry.
     */
    @NotNull
    public IdentifierDefinitionRegistry<T> build() {
      return new IdentifierDefinitionRegistry<>(
        this.idRegistry,
        this.identifierRegistry
      );
    }

    /**
     * removes the definitions.
     *
     * @param definition the definition to remove.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> remove(@NotNull final T definition) {
      final var id = definition.id();
      final var identifier = definition.identifier();
      Preconditions.checkArgument(
        this.identifierRegistry.containsKey(identifier),
        "Identifier is mot registered!"
      );
      Preconditions.checkArgument(
        this.idRegistry.containsKey(id),
        "ID is not registered!"
      );
      this.idRegistry.remove(id);
      this.identifierRegistry.remove(identifier);
      return this;
    }
  }
}
