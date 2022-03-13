package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.inventory.ItemData;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add item entity packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class AddItemEntity extends MinecraftPacket.Base {

  //@formatter:off
  boolean fromFishing;
  ItemData itemInHand;
  @Builder.Default EntityDataMap metadata = new EntityDataMap();
  Vector3f motion;
  Vector3f position;
  long runtimeEntityId;
  long uniqueEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
