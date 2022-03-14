package io.github.shiruka.protocol.packet;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
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
 * a class that represents add entity packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class AddEntity extends MinecraftPacket.Base {

  //@formatter:off
  List<AttributeData> attributes;
  List<EntityLinkData> entityLinks;
  int entityType;
  String identifier;
  @Builder.Default EntityDataMap metadata = new EntityDataMap();
  Vector3f motion;
  Vector3f position;
  Vector3f rotation;
  long runtimeEntityId;
  long uniqueEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
