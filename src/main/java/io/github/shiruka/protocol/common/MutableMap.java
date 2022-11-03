package io.github.shiruka.protocol.common;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents mutable map with builder pattern.
 *
 * @param <K> type of the key.
 * @param <V> type of the value.
 */
public final class MutableMap<K, V> extends HashMap<K, V> {

  /**
   * ctor.
   *
   * @param m the map.
   */
  private MutableMap(@NotNull final Map<? extends K, ? extends V> m) {
    super(m);
  }

  /**
   * ctor.
   */
  private MutableMap() {
    super();
  }

  /**
   * creates an empty map.
   *
   * @param <K> type of the key.
   * @param <V> type of the value.
   *
   * @return mutable map.
   */
  @NotNull
  public static <K, V> MutableMap<K, V> of() {
    return new MutableMap<>();
  }

  /**
   * creates mutable map from the given map.
   *
   * @param m the map to create.
   * @param <K> type of the key.
   * @param <V> type of the value.
   *
   * @return mutable map.
   */
  @NotNull
  public static <K, V> MutableMap<K, V> of(
    @NotNull final Map<? extends K, ? extends V> m
  ) {
    return new MutableMap<>(m);
  }

  /**
   * creates a simple mutable map with the single key-value.
   *
   * @param key the key to create.
   * @param value the value to create.
   * @param <K> type of the key.
   * @param <V> type of the value.
   *
   * @return mutable map.
   */
  @NotNull
  public static <K, V> MutableMap<K, V> of(
    @NotNull final K key,
    @NotNull final V value
  ) {
    return MutableMap.<K, V>of().with(key, value);
  }

  /**
   * puts the key-value.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public MutableMap<K, V> with(@NotNull final K key, @NotNull final V value) {
    this.put(key, value);
    return this;
  }

  /**
   * removes the key-value.
   *
   * @param key the key to remove.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public MutableMap<K, V> without(@NotNull final K key) {
    this.remove(key);
    return this;
  }
}
