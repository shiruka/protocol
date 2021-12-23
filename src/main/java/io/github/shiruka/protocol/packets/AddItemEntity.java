package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.inventory.ItemData;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents add item entity packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class AddItemEntity extends MinecraftPacket {

  /**
   * the metadata.
   */
  @Getter
  private final EntityDataMap metadata = new EntityDataMap();

  /**
   * the from fishing.
   */
  @Getter
  private boolean fromFishing;

  /**
   * the item in hand.
   */
  @Nullable
  private ItemData itemInHand;

  /**
   * the motion.
   */
  @Nullable
  private Vector3f motion;

  /**
   * the position.
   */
  @Nullable
  private Vector3f position;

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

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    this.uniqueEntityId = buffer.readVarLong();
    this.runtimeEntityId = buffer.readUnsignedVarLong();
    this.itemInHand = buffer.readItem(session);
    this.position = buffer.readVector3f();
    this.motion = buffer.readVector3f();
    buffer.readEntityData(this.metadata);
    this.fromFishing = buffer.readBoolean();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    buffer.writeVarLong(this.uniqueEntityId);
    buffer.writeUnsignedVarLong(this.runtimeEntityId);
    buffer.writeItem(this.itemInHand(), session);
    buffer.writeVector3f(this.position());
    buffer.writeVector3f(this.motion());
    buffer.writeEntityData(this.metadata);
    buffer.writeBoolean(this.fromFishing);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the item in hand.
   *
   * @return item in hand.
   */
  @NotNull
  public ItemData itemInHand() {
    return Objects.requireNonNull(this.itemInHand, "item in hand");
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
}
