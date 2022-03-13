package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add entity packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class AddEntity extends MinecraftPacket.Base {

  //@formatter:off
  private final EntityDataMap metadata = new EntityDataMap();
  private List<AttributeData> attributes;
  private List<EntityLinkData> entityLinks;
  private int entityType;
  private String identifier;
  private Vector3f motion;
  private Vector3f position;
  private Vector3f rotation;
  private long runtimeEntityId;
  private long uniqueEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
