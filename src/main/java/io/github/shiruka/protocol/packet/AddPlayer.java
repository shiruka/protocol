package io.github.shiruka.protocol.packet;

import io.github.shiruka.api.base.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import io.github.shiruka.protocol.data.inventory.ItemData;
import java.util.List;
import java.util.UUID;
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
 * a class that represents add player packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class AddPlayer extends MinecraftPacket.Base {

  @Builder.Default
  AdventureSettings adventureSettings = AdventureSettings.newBuilder().build();

  int buildPlatform;

  String deviceId;

  List<EntityLinkData> entityLinks;

  ItemData hand;

  @Builder.Default
  EntityDataMap metadata = new EntityDataMap();

  Vector3f motion;

  String platformChatId;

  Vector3f position;

  Vector3f rotation;

  long runtimeEntityId;

  long uniqueEntityId;

  String username;

  UUID uuid;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
