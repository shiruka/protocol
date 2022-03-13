package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import io.github.shiruka.protocol.data.inventory.ItemData;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add player packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class AddPlayer extends MinecraftPacket.Base {

  //@formatter:off
  @Builder.Default AdventureSettings adventureSettings = AdventureSettings.newBuilder().build();
  int buildPlatform;
  String deviceId;
  List<EntityLinkData> entityLinks;
  ItemData hand;
  @Builder.Default EntityDataMap metadata = new EntityDataMap();
  Vector3f motion;
  String platformChatId;
  Vector3f position;
  Vector3f rotation;
  long runtimeEntityId;
  long uniqueEntityId;
  String username;
  UUID uuid;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
