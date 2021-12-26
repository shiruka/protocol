package io.github.shiruka.protocol.codec;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.common.Int2ObjectBiMap;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents int type maps.
 *
 * @param <T> type of the object.
 */
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class IntTypeMap<T> {

  /**
   * the map.
   */
  private final Int2ObjectBiMap<T> map;

  /**
   * the type.
   */
  @NotNull
  private final String type;

  /**
   * creates an empty int type map.
   *
   * @param cls the cls to create.
   * @param <T> type of the class.
   *
   * @return a newly created int type map.
   */
  @NotNull
  public static <T> IntTypeMap<T> empty(@NotNull final Class<T> cls) {
    return IntTypeMap.empty(cls.getSimpleName());
  }

  /**
   * creates an empty int type map.
   *
   * @param type the type to create.
   * @param <T> type of the class.
   *
   * @return a newly created int type map.
   */
  @NotNull
  public static <T> IntTypeMap<T> empty(@NotNull final String type) {
    return new IntTypeMap<>(new Int2ObjectBiMap<>(), type);
  }

  /**
   * creates a new builder from the type's class.
   *
   * @param cls the cls to create.
   * @param <T> type of the class.
   *
   * @return a newly created builder.
   */
  @NotNull
  public static <T> Builder<T> newBuilder(@NotNull final Class<T> cls) {
    return IntTypeMap.newBuilder(cls.getSimpleName());
  }

  /**
   * creates a new builder from the type name.
   *
   * @param type the type to create.
   * @param <T> type of the class.
   *
   * @return a newly created builder.
   */
  @NotNull
  public static <T> Builder<T> newBuilder(@NotNull final String type) {
    return new Builder<>(type);
  }

  /**
   * gets the id.
   *
   * @param value the value to get.
   *
   * @return id.
   */
  public int id(@NotNull final T value) {
    return this.map.get(value);
  }

  /**
   * creates a builder from {@code this}.
   *
   * @return builder.
   */
  @NotNull
  public Builder<T> toBuilder() {
    final var builder = new Builder<T>(this.type);
    this.map.forEach((t, value) -> builder.insert(value, t));
    return builder;
  }

  /**
   * gets the type.
   *
   * @param id the id to get.
   *
   * @return type.
   */
  public T type(final int id) {
    return this.map.get(id);
  }

  /**
   * a class that represents builder for {@link IntTypeMap}.
   *
   * @param <T> type of the value.
   */
  @SuppressWarnings("unchecked")
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class Builder<T> {

    /**
     * the type.
     */
    @NotNull
    private final String type;

    /**
     * the map.
     */
    @Nullable
    private Object[] map = new Object[0];

    /**
     * calculates power of two ceiling.
     *
     * @param value the value to calculate.
     *
     * @return calculated value.
     */
    private static int powerOfTwoCeiling(final int value) {
      var tempValue = value;
      tempValue--;
      tempValue |= value >> 1;
      tempValue |= value >> 2;
      tempValue |= value >> 4;
      tempValue |= value >> 8;
      tempValue |= value >> 16;
      tempValue++;
      return tempValue;
    }

    /**
     * builds the {@link IntTypeMap}.
     *
     * @return built map.
     */
    @NotNull
    public IntTypeMap<T> build() {
      final var map = new Int2ObjectBiMap<T>();
      IntStream.range(0, this.map.length).forEach(index -> {
        final var type = this.map[index];
        if (type != null) {
          map.put(index, (T) type);
        }
      });
      return new IntTypeMap<>(map, this.type);
    }

    /**
     * inserts the value to the index.
     *
     * @param index the index to insert.
     * @param value the value to insert.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> insert(final int index, @NotNull final T value) {
      this.ensureIndex(index);
      Preconditions.checkArgument(this.map[index] == null,
        "Cannot insert into non-null value");
      this.map[index] = value;
      return this;
    }

    /**
     * moves the value from old index to new index.
     *
     * @param oldIndex the old index to move.
     * @param newIndex the new index to move.
     * @param value the value to move.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> move(final int oldIndex, final int newIndex, @NotNull final T value) {
      Preconditions.checkArgument(oldIndex < this.map.length,
        "Cannot move out of bounds value!");
      Preconditions.checkArgument(this.map[oldIndex] == value,
        "oldIndex value does not equal expected!");
      this.ensureIndex(newIndex);
      this.map[oldIndex] = null;
      this.map[newIndex] = value;
      return this;
    }

    /**
     * removes the value at index.
     *
     * @param index the index to remove.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> remove(final int index) {
      this.map[index] = null;
      return this;
    }

    /**
     * replaces the value at the index with the new value.
     *
     * @param index the index to replace.
     * @param value the value to replace.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> replace(final int index, @NotNull final T value) {
      Preconditions.checkArgument(index < this.map.length,
        "Cannot replace out of bounds value!");
      Preconditions.checkArgument(this.map[index] != null,
        "Cannot replace null value!");
      this.map[index] = value;
      return this;
    }

    /**
     * shifts the indexes.
     *
     * @param startIndex the start index to shift.
     * @param amount the amount to shift.
     * @param length the length to shift.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> shift(final int startIndex, final int amount, final int length) {
      Preconditions.checkArgument(startIndex < this.map.length,
        "Start index is out of bounds!");
      final var endIndex = startIndex + length;
      Preconditions.checkArgument(endIndex <= this.map.length, "" +
        "Length exceeds array bounds!");
      this.ensureCapacity(this.map.length + amount);
      System.arraycopy(this.map, startIndex, this.map, startIndex + amount, length);
      IntStream.range(0, amount)
        .map(operand -> operand + startIndex)
        .forEach(value -> this.map[value] = null);
      return this;
    }

    /**
     * shifts the indexes.
     *
     * @param startIndex the start index to shift.
     * @param amount the amount to shift.
     *
     * @return {@code this} for the builder chain.
     */
    @NotNull
    public Builder<T> shift(final int startIndex, final int amount) {
      return this.shift(startIndex, amount, this.map.length - startIndex);
    }

    /**
     * ensures the capacity.
     *
     * @param size the size to ensure.
     */
    private void ensureCapacity(final int size) {
      if (size <= this.map.length) {
        return;
      }
      final var newSize = Builder.powerOfTwoCeiling(size + 1);
      final var newTypes = new Object[newSize];
      System.arraycopy(this.map, 0, newTypes, 0, this.map.length);
      this.map = newTypes;
    }

    /**
     * ensures the index.
     *
     * @param index the index to ensure.
     */
    private void ensureIndex(final int index) {
      this.ensureCapacity(index + 1);
    }
  }
}
