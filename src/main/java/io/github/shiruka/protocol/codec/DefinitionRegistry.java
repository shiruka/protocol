package io.github.shiruka.protocol.codec;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents definition registries.
 *
 * @param <T> type of the definition.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class DefinitionRegistry<T extends Definition> {

  /**
   * the identifier registry.
   */
  @NotNull
  private final Object2ObjectMap<String, T> identifierRegistry;

  /**
   * the runtime id registry.
   */
  @NotNull
  private final Int2ObjectMap<T> runtimeIdRegistry;

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
   * gets the definition id by runtime id.
   *
   * @param runtimeId the runtime id to get.
   *
   * @return definition.
   */
  @NotNull
  public T byRuntimeId(final int runtimeId) {
    return this.runtimeIdRegistry.get(runtimeId);
  }

  /**
   * a class that represents builder for {@link DefinitionRegistry}.
   *
   * @param <T> type of the definition.
   */
  public static final class Builder<T extends Definition> {

    /**
     * the identifier registry.
     */
    private final Object2ObjectMap<String, T> identifierRegistry = new Object2ObjectOpenHashMap<>();

    /**
     * the runtime id registry.
     */
    private final Int2ObjectMap<T> runtimeIdRegistry = new Int2ObjectOpenHashMap<>();

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
      final var runtimeId = definition.runtimeId();
      final var identifier = definition.identifier();
      Preconditions.checkArgument(!this.identifierRegistry.containsKey(identifier),
        "Identifier is already registered!");
      Preconditions.checkArgument(!this.runtimeIdRegistry.containsKey(runtimeId),
        "Runtime ID is already registered!");
      this.runtimeIdRegistry.put(runtimeId, definition);
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
    public DefinitionRegistry<T> build() {
      return new DefinitionRegistry<>(this.identifierRegistry, this.runtimeIdRegistry);
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
      final var runtimeId = definition.runtimeId();
      final var identifier = definition.identifier();
      Preconditions.checkArgument(this.identifierRegistry.containsKey(identifier),
        "Identifier is mot registered!");
      Preconditions.checkArgument(this.runtimeIdRegistry.containsKey(runtimeId),
        "Runtime ID is not registered!");
      this.runtimeIdRegistry.remove(runtimeId);
      this.identifierRegistry.remove(identifier);
      return this;
    }
  }
}
