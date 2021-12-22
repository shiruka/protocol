package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents add entity packets.
 */
@Setter
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
  @Nullable
  private String identifier;

  /**
   * the motion.
   */
  @Nullable
  private Vector3f motion;

  /**
   * the position.
   */
  private Vector3f position;

  /**
   * the rotation.
   */
  @Nullable
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
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.uniqueEntityId = buffer.readVarLong();
    this.runtimeEntityId = buffer.readUnsignedVarLong();
    this.identifier = buffer.readString();
    this.position = buffer.readVector3f();
    this.motion = buffer.readVector3f();
    this.rotation = buffer.readVector3f();
    buffer.readArray(this.attributes, buffer::readAttribute);
    buffer.readEntityData(this.metadata);
    buffer.readArray(this.entityLinks, buffer::readEntityLink);
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeVarLong(this.uniqueEntityId);
    buffer.writeUnsignedLong(this.runtimeEntityId);
    buffer.writeString(this.identifier());
    buffer.writeVector3f(this.position());
    buffer.writeVector3f(this.motion());
    buffer.writeVector3f(this.rotation());
    buffer.writeArray(this.attributes, buffer::writeAttribute);
    buffer.writeEntityData(this.metadata);
    buffer.writeArray(this.entityLinks, buffer::writeEntityLink);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the identifier.
   *
   * @return identifier.
   */
  @NotNull
  public String identifier() {
    return Objects.requireNonNull(this.identifier, "identifier");
  }

  /**
   * obtains the motion.
   *
   * @return motion.
   */
  @NotNull
  public Vector3f motion() {
    return Objects.requireNonNull(this.motion, "motion");
  }

  /**
   * obtains the position.
   *
   * @return position.
   */
  @NotNull
  public Vector3f position() {
    return Objects.requireNonNull(this.position, "position");
  }

  /**
   * obtains the rotation.
   *
   * @return rotation.
   */
  @NotNull
  public Vector3f rotation() {
    return Objects.requireNonNull(this.rotation, "rotation");
  }
}
