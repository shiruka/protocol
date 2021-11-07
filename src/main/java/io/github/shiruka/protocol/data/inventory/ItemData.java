package io.github.shiruka.protocol.data.inventory;

import com.google.common.base.Preconditions;
import io.github.shiruka.api.nbt.CompoundTag;
import java.util.Arrays;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents item data.
 */
@Accessors(fluent = true)
@Builder(toBuilder = true, builderClassName = "Builder", builderMethodName = "newBuilder")
public final class ItemData {

  /**
   * the empty.
   */
  private static final String[] EMPTY = new String[0];

  /**
   * the air.
   */
  public static final ItemData AIR = new ItemData(0, 0, ItemData.EMPTY, ItemData.EMPTY, 0, 0, 0, 0, null, false);

  /**
   * the block runtime id.
   */
  @Getter
  private final int blockRuntimeId;

  /**
   * the blocking ticks.
   */
  @Getter
  private final long blockingTicks;

  /**
   * the can break.
   */
  @Getter
  @Nullable
  private final String[] canBreak;

  /**
   * the can place.
   */
  @Getter
  @Nullable
  private final String[] canPlace;

  /**
   * the count.
   */
  @Getter
  private final int count;

  /**
   * the damage.
   */
  @Getter
  private final int damage;

  /**
   * the id.
   */
  @Getter
  private final int id;

  /**
   * the net id.
   */
  @Getter
  private final int netId;

  /**
   * the tag.
   */
  @Getter
  @Nullable
  private final CompoundTag tag;

  /**
   * the using net id.
   */
  @Getter
  private final boolean usingNetId;

  /**
   * ctor.
   *
   * @param id the id.
   * @param damage the damage.
   * @param count the count.
   * @param tag the tag.
   * @param canPlace the can place.
   * @param canBreak the can break.
   * @param blockingTicks the blocking ticks.
   * @param blockRuntimeId the block runtime id.
   * @param usingNetId the using net id.
   * @param netId the net id.
   */
  private ItemData(final int blockRuntimeId, final long blockingTicks, @Nullable final String[] canBreak,
                   @Nullable final String[] canPlace, final int count, final int damage, final int id, final int netId,
                   @Nullable final CompoundTag tag, final boolean usingNetId) {
    Preconditions.checkArgument(count < 256, "Count exceeds maximum of 255!");
    this.id = id;
    this.damage = damage;
    this.count = count;
    this.tag = tag;
    this.canPlace = canPlace == null ? ItemData.EMPTY : canPlace;
    this.canBreak = canBreak == null ? ItemData.EMPTY : canBreak;
    this.blockingTicks = blockingTicks;
    this.blockRuntimeId = blockRuntimeId;
    this.netId = netId;
    this.usingNetId = usingNetId;
  }

  /**
   * checks if the item equals.
   *
   * @param other the other to check.
   * @param amount the amount to check.
   * @param metadata the metadata to check.
   * @param userdata the userdata to check.
   *
   * @return {@code true} if the item equals the other.
   */
  public boolean equals(@NotNull final ItemData other, final boolean amount, final boolean metadata,
                        final boolean userdata) {
    return this.id == other.id &&
      (!amount || this.count == other.count) &&
      (!metadata || this.damage == other.damage &&
        this.blockRuntimeId == other.blockRuntimeId) &&
      (!userdata || Objects.equals(this.tag, other.tag) &&
        Arrays.equals(this.canPlace, other.canPlace) &&
        Arrays.equals(this.canBreak, other.canBreak));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.damage, this.count, this.tag, Arrays.hashCode(this.canPlace),
      Arrays.hashCode(this.canBreak), this.blockingTicks, this.blockRuntimeId);
  }

  @Override
  public boolean equals(@NotNull final Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof ItemData)) {
      return false;
    }
    return this.equals((ItemData) obj, true, true, true);
  }

  /**
   * checks if the item is {@code null}.
   *
   * @return {@code true} if the item is {@code null}.
   */
  public boolean isNull() {
    return this.count <= 0;
  }

  /**
   * checks if the item is valid.
   *
   * @return {@code true} if the item is valid.
   */
  public boolean isValid() {
    return !this.isNull() && this.id != 0;
  }
}
