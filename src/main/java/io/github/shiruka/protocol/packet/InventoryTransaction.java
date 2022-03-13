package io.github.shiruka.protocol.packet;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.api.common.vectors.Vector3i;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.inventory.InventoryActionData;
import io.github.shiruka.protocol.data.inventory.ItemData;
import io.github.shiruka.protocol.data.inventory.LegacySetItemSlotData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents camera packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class InventoryTransaction extends MinecraftPacket.Base {

  //@formatter:off
  private int actionType;
  private @Builder.Default List<InventoryActionData> actions = new ObjectArrayList<>();
  private int blockFace;
  private Vector3i blockPosition;
  private int blockRuntimeId;
  private Vector3f clickPosition;
  private Vector3f headPosition;
  private int hotBarSlot;
  private ItemData itemInHand;
  private int legacyRequestId;
  private @Builder.Default List<LegacySetItemSlotData> legacySlots = new ObjectArrayList<>();
  private Vector3f playerPosition;
  private long runtimeEntityId;
  private Type transactionType;
  private boolean usingNetIds;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains inventory transaction types.
   */
  public enum Type {
    /**
     * the normal.
     */
    NORMAL,
    /**
     * the inventory mismatch.
     */
    INVENTORY_MISMATCH,
    /**
     * the item use.
     */
    ITEM_USE,
    /**
     * the item use on entity.
     */
    ITEM_USE_ON_ENTITY,
    /**
     * the item release.
     */
    ITEM_RELEASE;

    /**
     * the values.
     */
    public static final Type[] VALUES = Type.values();
  }
}
