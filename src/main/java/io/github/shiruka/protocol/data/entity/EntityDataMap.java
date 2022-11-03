package io.github.shiruka.protocol.data.entity;

import io.github.shiruka.api.base.Vector3f;
import io.github.shiruka.api.base.Vector3i;
import io.github.shiruka.nbt.CompoundTag;
import io.github.shiruka.nbt.Tag;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents entity data maps.
 */
@ToString
@EqualsAndHashCode
@SuppressWarnings("unchecked")
public final class EntityDataMap implements Map<EntityData, Object> {

  /**
   * the map.
   */
  private final Map<EntityData, Object> map = new LinkedHashMap<>();

  /**
   * converts the map into text.
   *
   * @param map the map to convert.
   *
   * @return converted text.
   */
  @NotNull
  private static String mapToString(
    @NotNull final Map<EntityData, Object> map
  ) {
    final var iterator = map.entrySet().iterator();
    if (!iterator.hasNext()) {
      return "{}";
    }
    final var sb = new StringBuilder();
    sb.append('{');
    while (true) {
      final var e = iterator.next();
      final var key = e.getKey();
      if (key == EntityData.FLAGS_2) {
        continue;
      }
      final var value = e.getValue();
      sb.append(key.toString()).append('=').append(value.toString());
      if (!iterator.hasNext()) {
        return sb.append('}').toString();
      }
      sb.append(',').append(' ');
    }
  }

  /**
   * gets the byte.
   *
   * @param key the key to get.
   *
   * @return byte value.
   */
  public byte getByte(@NotNull final EntityData key) {
    return this.getByte(key, (byte) 0);
  }

  /**
   * gets the byte.
   *
   * @param key the key to get.
   * @param defaultValue the default value to get.
   *
   * @return byte value.
   */
  public byte getByte(@NotNull final EntityData key, final byte defaultValue) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * gets the flags.
   *
   * @return flags.
   */
  @Nullable
  public EntityFlags getFlags() {
    return (EntityFlags) this.map.get(EntityData.FLAGS);
  }

  /**
   * gets the float.
   *
   * @param key the key to get.
   *
   * @return float value.
   */
  public float getFloat(@NotNull final EntityData key) {
    return this.getFloat(key, 0f);
  }

  /**
   * gets the float.
   *
   * @param key the key to get.
   * @param defaultValue the default value.
   *
   * @return float value.
   */
  public float getFloat(
    @NotNull final EntityData key,
    final float defaultValue
  ) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * gets the int.
   *
   * @param key the key to get.
   *
   * @return int value.
   */
  public int getInt(@NotNull final EntityData key) {
    return this.getInt(key, 0);
  }

  /**
   * gets the int.
   *
   * @param key the key to get.
   * @param defaultValue the default value.
   *
   * @return int value.
   */
  public int getInt(@NotNull final EntityData key, final int defaultValue) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * gets the long.
   *
   * @param key the key to get.
   *
   * @return long value.
   */
  public long getLong(@NotNull final EntityData key) {
    return this.getLong(key, 0);
  }

  /**
   * gets the long.
   *
   * @param key the key to get.
   * @param defaultValue the default value.
   *
   * @return long value.
   */
  public long getLong(@NotNull final EntityData key, final long defaultValue) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * gets or creates flags.
   *
   * @return flags.
   */
  @NotNull
  public EntityFlags getOrCreateFlags() {
    var flags = this.getFlags();
    if (flags == null) {
      this.putFlags(flags = new EntityFlags());
    }
    return flags;
  }

  /**
   * gets the position.
   *
   * @param key the key to get.
   *
   * @return position.
   */
  @NotNull
  public Vector3i getPos(@NotNull final EntityData key) {
    return this.getPos(key, Vector3i.ZERO);
  }

  /**
   * gets the position.
   *
   * @param key the key to get.
   * @param defaultValue the default value.
   *
   * @return position.
   */
  @NotNull
  public Vector3i getPos(
    @NotNull final EntityData key,
    final Vector3i defaultValue
  ) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * gets the short.
   *
   * @param key the key to get.
   *
   * @return short.
   */
  public short getShort(@NotNull final EntityData key) {
    return this.getShort(key, (short) 0);
  }

  /**
   * gets the short.
   *
   * @param key the key to get.
   * @param defaultValue the default value.
   *
   * @return short.
   */
  public short getShort(
    @NotNull final EntityData key,
    final short defaultValue
  ) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * gets the string.
   *
   * @param key the key to get.
   *
   * @return string.
   */
  @NotNull
  public String getString(@NotNull final EntityData key) {
    return this.getString(key, "");
  }

  /**
   * gets the string.
   *
   * @param key the key to get.
   * @param defaultValue the default value.
   *
   * @return string.
   */
  @NotNull
  public String getString(
    @NotNull final EntityData key,
    final String defaultValue
  ) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * gets the tag.
   *
   * @param key the key to get.
   *
   * @return tag.
   */
  @NotNull
  public CompoundTag getTag(@NotNull final EntityData key) {
    return this.getTag(key, Tag.createCompound());
  }

  /**
   * gets the tag.
   *
   * @param key the key to get.
   * @param defaultValue the default value.
   *
   * @return tag.
   */
  @NotNull
  public CompoundTag getTag(
    @NotNull final EntityData key,
    @NotNull final CompoundTag defaultValue
  ) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * gets the type.
   *
   * @param key the key to get.
   *
   * @return type.
   */
  @Nullable
  public EntityDataType getType(@NotNull final EntityData key) {
    final var value = this.map.get(key);
    if (value == null) {
      return null;
    }
    return EntityDataType.byObject(value);
  }

  /**
   * gets the vector 3f.
   *
   * @param key the key to get.
   *
   * @return vector 3f.
   */
  @NotNull
  public Vector3f getVector3f(@NotNull final EntityData key) {
    return this.getVector3f(key, Vector3f.ZERO);
  }

  /**
   * gets the vector 3f.
   *
   * @param key the key to get.
   * @param defaultValue the default value.
   *
   * @return vector 3f.
   */
  @NotNull
  public Vector3f getVector3f(
    @NotNull final EntityData key,
    @NotNull final Vector3f defaultValue
  ) {
    return this.getOrDefault(key, defaultValue);
  }

  /**
   * puts the byte.
   *
   * @param key the key to put.
   * @param value the vale to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putByte(@NotNull final EntityData key, final int value) {
    this.put(key, (byte) value);
    return this;
  }

  /**
   * puts the flags.
   *
   * @param flags the flags to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putFlags(@NotNull final EntityFlags flags) {
    this.map.put(EntityData.FLAGS, flags);
    this.map.put(EntityData.FLAGS_2, flags);
    return this;
  }

  /**
   * puts the float.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putFloat(
    @NotNull final EntityData key,
    final float value
  ) {
    this.put(key, value);
    return this;
  }

  /**
   * puts the int.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putInt(@NotNull final EntityData key, final int value) {
    this.put(key, value);
    return this;
  }

  /**
   * puts the long.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putLong(
    @NotNull final EntityData key,
    final long value
  ) {
    this.put(key, value);
    return this;
  }

  /**
   * puts the position.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putPos(
    @NotNull final EntityData key,
    @NotNull final Vector3i value
  ) {
    this.put(key, value);
    return this;
  }

  /**
   * puts the short.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putShort(
    @NotNull final EntityData key,
    final int value
  ) {
    this.put(key, (short) value);
    return this;
  }

  /**
   * puts the string.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putString(
    @NotNull final EntityData key,
    @NotNull final String value
  ) {
    this.put(key, value);
    return this;
  }

  /**
   * puts the tag.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putTag(
    @NotNull final EntityData key,
    @NotNull final CompoundTag value
  ) {
    this.put(key, value);
    return this;
  }

  /**
   * puts the vector 3f.
   *
   * @param key the key to put.
   * @param value the value to put.
   *
   * @return {@code this} for the builder chain.
   */
  @NotNull
  public EntityDataMap putVector3f(
    @NotNull final EntityData key,
    @NotNull final Vector3f value
  ) {
    this.put(key, value);
    return this;
  }

  @Override
  public int size() {
    return this.map.size();
  }

  @Override
  public boolean isEmpty() {
    return this.map.isEmpty();
  }

  @Override
  public boolean containsKey(@NotNull final Object key) {
    return this.map.containsKey(key);
  }

  @Override
  public boolean containsValue(@NotNull final Object value) {
    return this.map.containsValue(value);
  }

  @Nullable
  @Override
  public Object get(@Nullable final Object key) {
    return null;
  }

  @Override
  public Object put(
    @NotNull final EntityData key,
    @NotNull final Object value
  ) {
    EntityDataType.byObject(value);
    if (key == EntityData.FLAGS || key == EntityData.FLAGS_2) {
      return this.putFlags((EntityFlags) value);
    }
    return this.map.put(key, value);
  }

  @Override
  public Object remove(@NotNull final Object key) {
    return this.map.remove(key);
  }

  @Override
  public void putAll(@NotNull final Map<? extends EntityData, ?> map) {
    this.map.putAll(map);
  }

  @Override
  public void clear() {
    this.map.clear();
  }

  @NotNull
  @Override
  public Set<EntityData> keySet() {
    return this.map.keySet();
  }

  @NotNull
  @Override
  public Collection<Object> values() {
    return this.map.values();
  }

  @NotNull
  @Override
  public Set<Entry<EntityData, Object>> entrySet() {
    return this.map.entrySet();
  }

  /**
   * gets or returns the default value.
   *
   * @param key the key to get.
   * @param defaultValue the default value to get.
   * @param <T> type of the expected value.
   *
   * @return value.
   */
  private <T> T getOrDefault(
    @NotNull final EntityData key,
    @NotNull final T defaultValue
  ) {
    final var object = this.map.getOrDefault(key, defaultValue);
    try {
      return (T) object;
    } catch (final ClassCastException e) {
      return defaultValue;
    }
  }
}
