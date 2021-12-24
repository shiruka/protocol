package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.inventory.ItemData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add item entity packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class AddItemEntity extends MinecraftPacket {

  /**
   * the metadata.
   */
  private final EntityDataMap metadata = new EntityDataMap();

  /**
   * the from fishing.
   */
  private boolean fromFishing;

  /**
   * the item in hand.
   */
  private ItemData itemInHand;

  /**
   * the motion.
   */
  private Vector3f motion;

  /**
   * the position.
   */
  private Vector3f position;

  /**
   * the runtime entity id.
   */
  private long runtimeEntityId;

  /**
   * the unique entity id.
   */
  private long uniqueEntityId;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
