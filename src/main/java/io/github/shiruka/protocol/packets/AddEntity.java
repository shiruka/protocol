package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
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
public final class AddEntity extends MinecraftPacket {

  /**
   * the attributes.
   */
  private final List<AttributeData> attributes = new ObjectArrayList<>();

  /**
   * the entity links.
   */
  private final List<EntityLinkData> entityLinks = new ObjectArrayList<>();

  /**
   * the metadata.
   */
  private final EntityDataMap metadata = new EntityDataMap();

  /**
   * the entity type.
   */
  private int entityType;

  /**
   * the identifier.
   */
  private String identifier;

  /**
   * the motion.
   */
  private Vector3f motion;

  /**
   * the position.
   */
  private Vector3f position;

  /**
   * the rotation.
   */
  private Vector3f rotation;

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
