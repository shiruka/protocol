package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import io.github.shiruka.protocol.data.inventory.ItemData;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents play status packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class AddPlayer extends MinecraftPacket {

  /**
   * the adventure settings.
   */
  @Getter
  private final AdventureSettings adventureSettings = new AdventureSettings();

  /**
   * the entity links.
   */
  private final List<EntityLinkData> entityLinks = new ObjectArrayList<>();

  /**
   * the metadata.
   */
  @Getter
  private final EntityDataMap metadata = new EntityDataMap();

  /**
   * the build platform.
   */
  @Getter
  private int buildPlatform;

  /**
   * the device id.
   */
  @Nullable
  private String deviceId;

  /**
   * the hand.
   */
  @Nullable
  private ItemData hand;

  /**
   * the motion.
   */
  @Nullable
  private Vector3f motion;

  /**
   * the platform chat id.
   */
  @Nullable
  private String platformChatId;

  /**
   * the position.
   */
  @Nullable
  private Vector3f position;

  /**
   * the rotation.
   */
  @Nullable
  private Vector3f rotation;

  /**
   * the runtime entity id.
   */
  @Getter
  private long runtimeEntityId;

  /**
   * the unique entity id.
   */
  @Getter
  private long uniqueEntityId;

  /**
   * the username.
   */
  @Nullable
  private String username;

  /**
   * the uuid.
   */
  @Nullable
  private UUID uuid;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    this.uuid = buffer.readUUID();
    this.username = buffer.readString();
    this.uniqueEntityId = buffer.readVarLong();
    this.runtimeEntityId = buffer.readUnsignedVarLong();
    this.platformChatId = buffer.readString();
    this.position = buffer.readVector3f();
    this.motion = buffer.readVector3f();
    this.rotation = buffer.readVector3f();
    this.hand = buffer.readItem(session);
    buffer.readEntityData(this.metadata());
    this.adventureSettings().decode(buffer);
    buffer.readArray(this.entityLinks, buffer::readEntityLink);
    this.deviceId = buffer.readString();
    this.buildPlatform = buffer.readIntLE();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    buffer.writeUUID(this.uuid());
    buffer.writeString(this.username());
    buffer.writeVarLong(this.uniqueEntityId);
    buffer.writeUnsignedVarLong(this.runtimeEntityId);
    buffer.writeString(this.platformChatId());
    buffer.writeVector3f(this.position());
    buffer.writeVector3f(this.motion());
    buffer.writeVector3f(this.rotation());
    buffer.writeItem(this.hand(), session);
    buffer.writeEntityData(this.metadata());
    this.adventureSettings().encode(buffer);
    buffer.writeArray(this.entityLinks, buffer::writeEntityLink);
    buffer.writeString(this.deviceId());
    buffer.writeIntLE(this.buildPlatform);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the device id.
   *
   * @return device id.
   */
  @NotNull
  public String deviceId() {
    return Objects.requireNonNull(this.deviceId, "device id");
  }

  /**
   * obtains the entity links.
   *
   * @return entity links.
   */
  @NotNull
  public List<EntityLinkData> entityLinks() {
    return Collections.unmodifiableList(this.entityLinks);
  }

  /**
   * obtains the hand.
   *
   * @return hand.
   */
  @NotNull
  public ItemData hand() {
    return Objects.requireNonNull(this.hand, "hand");
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
   * obtains the platform chat id.
   *
   * @return platform chat id.
   */
  @NotNull
  public String platformChatId() {
    return Objects.requireNonNull(this.platformChatId, "platform chat id");
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

  /**
   * obtains the username.
   *
   * @return username.
   */
  @NotNull
  public String username() {
    return Objects.requireNonNull(this.username, "username");
  }

  /**
   * obtains the uuid.
   *
   * @return uuid.
   */
  @NotNull
  public UUID uuid() {
    return Objects.requireNonNull(this.uuid, "uuid");
  }
}
