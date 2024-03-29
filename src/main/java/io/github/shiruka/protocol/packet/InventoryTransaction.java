package io.github.shiruka.protocol.packet;

import io.github.shiruka.api.base.Vector3f;
import io.github.shiruka.api.base.Vector3i;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.inventory.InventoryActionData;
import io.github.shiruka.protocol.data.inventory.ItemData;
import io.github.shiruka.protocol.data.inventory.LegacySetItemSlotData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class InventoryTransaction extends MinecraftPacket.Base {

  int actionType;

  @Builder.Default
  List<InventoryActionData> actions = new ObjectArrayList<>();

  int blockFace;

  Vector3i blockPosition;

  int blockRuntimeId;

  Vector3f clickPosition;

  Vector3f headPosition;

  int hotBarSlot;

  ItemData itemInHand;

  int legacyRequestId;

  @Builder.Default
  List<LegacySetItemSlotData> legacySlots = new ObjectArrayList<>();

  Vector3f playerPosition;

  long runtimeEntityId;

  Type transactionType;

  boolean usingNetIds;

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
