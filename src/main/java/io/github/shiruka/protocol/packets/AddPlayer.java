package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import io.github.shiruka.protocol.data.inventory.ItemData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add player packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class AddPlayer extends MinecraftPacket {

  /**
   * the adventure settings.
   */
  private final AdventureSettings adventureSettings = new AdventureSettings();

  /**
   * the entity links.
   */
  private final List<EntityLinkData> entityLinks = new ObjectArrayList<>();

  /**
   * the metadata.
   */
  private final EntityDataMap metadata = new EntityDataMap();

  /**
   * the build platform.
   */
  private int buildPlatform;

  /**
   * the device id.
   */
  private String deviceId;

  /**
   * the hand.
   */
  private ItemData hand;

  /**
   * the motion.
   */
  private Vector3f motion;

  /**
   * the platform chat id.
   */
  private String platformChatId;

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

  /**
   * the username.
   */
  private String username;

  /**
   * the uuid.
   */
  private UUID uuid;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
