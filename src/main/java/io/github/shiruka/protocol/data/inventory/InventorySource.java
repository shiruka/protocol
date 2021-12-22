package io.github.shiruka.protocol.data.inventory;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents inventory sources.
 */
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class InventorySource {

  /**
   * the creative source.
   */
  private static final InventorySource CREATIVE_SOURCE = new InventorySource(ContainerId.NONE, Flag.NONE, Type.CREATIVE);

  /**
   * the global source.
   */
  private static final InventorySource GLOBAL_SOURCE = new InventorySource(ContainerId.NONE, Flag.NONE, Type.GLOBAL);

  /**
   * the invalid source.
   */
  private static final InventorySource INVALID_SOURCE = new InventorySource(ContainerId.NONE, Flag.NONE, Type.INVALID);

  /**
   * the container id.
   */
  @Getter
  private final int containerId;

  /**
   * the flag.
   */
  @NotNull
  @Getter
  private final Flag flag;

  /**
   * the type.
   */
  @NotNull
  @Getter
  private final Type type;

  /**
   * creates a source from container id.
   *
   * @param containerId the container id to create.
   *
   * @return source.
   */
  @NotNull
  public static InventorySource containerId(final int containerId) {
    return new InventorySource(containerId, Flag.NONE, Type.CONTAINER);
  }

  /**
   * obtains the creative source.
   *
   * @return creative source.
   */
  @NotNull
  public static InventorySource creative() {
    return InventorySource.CREATIVE_SOURCE;
  }

  /**
   * obtains the global source.
   *
   * @return global source.
   */
  @NotNull
  public static InventorySource global() {
    return InventorySource.GLOBAL_SOURCE;
  }

  /**
   * obtains the invalid source.
   *
   * @return invalid source.
   */
  @NotNull
  public static InventorySource invalid() {
    return InventorySource.INVALID_SOURCE;
  }

  /**
   * creates non implemented source.
   *
   * @param containerId the container id to create.
   *
   * @return non implemented source.
   */
  @NotNull
  public static InventorySource nonImplementedTodo(final int containerId) {
    return new InventorySource(containerId, Flag.NONE, Type.NON_IMPLEMENTED_TODO);
  }

  /**
   * creates untracked interaction ui source.
   *
   * @param containerId the container id to create.
   *
   * @return untracked interaction ui source.
   */
  @NotNull
  public static InventorySource untrackedInteractionUI(final int containerId) {
    return new InventorySource(containerId, Flag.NONE, Type.UNTRACKED_INTERACTION_UI);
  }

  /**
   * creates world interaction source.
   *
   * @param flag the flag to create.
   *
   * @return world interaction source.
   */
  @NotNull
  public static InventorySource worldInteraction(@NotNull final Flag flag) {
    return new InventorySource(ContainerId.NONE, flag, Type.WORLD_INTERACTION);
  }

  /**
   * an enum class that contains inventory source flags.
   */
  public enum Flag {
    /**
     * the drop item.
     */
    DROP_ITEM,
    /**
     * the pickup item.
     */
    PICKUP_ITEM,
    /**
     * the none.
     */
    NONE
  }

  /**
   * an enum class that contains inventory source types.
   */
  @Accessors(fluent = true)
  @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
  public enum Type {
    /**
     * the invalid.
     */
    INVALID(-1),
    /**
     * the container.
     */
    CONTAINER(0),
    /**
     * the global.
     */
    GLOBAL(1),
    /**
     * the world interaction.
     */
    WORLD_INTERACTION(2),
    /**
     * the creative.
     */
    CREATIVE(3),
    /**
     * the untracked interaction ui.
     */
    UNTRACKED_INTERACTION_UI(100),
    /**
     * the non implemented todo.
     */
    NON_IMPLEMENTED_TODO(99999);

    /**
     * the cache by id.
     */
    private static final Int2ObjectMap<Type> BY_ID = new Int2ObjectOpenHashMap<>(6);

    /**
     * the values.
     */
    private static final Type[] VALUES = Type.values();

    /**
     * the id.
     */
    @Getter
    private final int id;

    static {
      Arrays.stream(Type.VALUES).forEach(type -> Type.BY_ID.put(type.id, type));
      Type.BY_ID.defaultReturnValue(Type.INVALID);
    }

    /**
     * finds type by id.
     *
     * @param id the id to find.
     *
     * @return found type.
     */
    @NotNull
    public static Type byId(final int id) {
      return Type.BY_ID.get(id);
    }
  }
}
