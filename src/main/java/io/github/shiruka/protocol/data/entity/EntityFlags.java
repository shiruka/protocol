package io.github.shiruka.protocol.data.entity;

import io.github.shiruka.api.common.Int2ObjectBiMap;
import java.util.EnumSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents entity flags.
 */
@Log4j2
@ToString(doNotUseGetters = true)
@EqualsAndHashCode
@RequiredArgsConstructor
public final class EntityFlags {

  /**
   * the flags.
   */
  @NotNull
  private final Set<EntityFlag> flags;

  /**
   * ctor.
   */
  public EntityFlags() {
    this(EnumSet.noneOf(EntityFlag.class));
  }

  /**
   * checks if the flag contains.
   *
   * @param flag the flag to check.
   *
   * @return {@code true} if the flag contains.
   */
  public boolean contains(@NotNull final EntityFlag flag) {
    return this.flags.contains(flag);
  }

  /**
   * copies the flags and created a new entity flags instance.
   *
   * @return a new entity flags instance.
   */
  @NotNull
  public EntityFlags copy() {
    final var copy = new EntityFlags();
    copy.merge(this);
    return copy;
  }

  /**
   * sets the value with the value.
   *
   * @param flag the flag to set.
   * @param value the value to set.
   *
   * @return {@code true} if the flag set successfully.
   */
  public boolean flag(@NotNull final EntityFlag flag, final boolean value) {
    final var oldValue = this.flags.contains(flag);
    if (oldValue == value) {
      return false;
    }
    if (value) {
      this.flags.add(flag);
    } else {
      this.flags.remove(flag);
    }
    return true;
  }

  /**
   * gets the flag.
   *
   * @param index the index to get.
   * @param flagMappings the flag mappings to get.
   *
   * @return flag value.
   */
  public long get(final int index, @NotNull final Int2ObjectBiMap<EntityFlag> flagMappings) {
    final int value;
    final var lower = index * 64;
    final var upper = lower + 64;
    value = this.flags.stream()
      .mapToInt(flagMappings::get)
      .filter(flagIndex -> flagIndex >= lower && flagIndex < upper)
      .map(flagIndex -> (int) (1L << (flagIndex & 0x3f)))
      .reduce(0, (a, b) -> a | b);
    return value;
  }

  /**
   * merges the flags.
   *
   * @param flags the flags to merge.
   */
  public void merge(final EntityFlags flags) {
    this.flags.addAll(flags.flags);
  }

  /**
   * sets the flag.
   *
   * @param value the value to set.
   * @param index the index to set.
   * @param flagMappings the flag mappings to set.
   */
  public void set(final long value, final int index, @NotNull final Int2ObjectBiMap<EntityFlag> flagMappings) {
    final var lower = index * 64;
    final var upper = lower + 64;
    for (var i = lower; i < upper; i++) {
      final var idx = i & 0x3f;
      if ((value & 1L << idx) != 0) {
        final var flag = flagMappings.get(i);
        if (flag != null) {
          this.flags.add(flag);
        } else {
          EntityFlags.log.debug("Unknown entity flag index {} detected", i);
        }
      }
    }
  }
}
