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

  //@formatter:off
  private final EntityDataMap metadata = new EntityDataMap();
  private boolean fromFishing;
  private ItemData itemInHand;
  private Vector3f motion;
  private Vector3f position;
  private long runtimeEntityId;
  private long uniqueEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
