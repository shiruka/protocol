package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
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

  //@formatter:off
  private final AdventureSettings adventureSettings = new AdventureSettings();
  private final List<EntityLinkData> entityLinks = new ObjectArrayList<>();
  private final EntityDataMap metadata = new EntityDataMap();
  private int buildPlatform;
  private String deviceId;
  private ItemData hand;
  private Vector3f motion;
  private String platformChatId;
  private Vector3f position;
  private Vector3f rotation;
  private long runtimeEntityId;
  private long uniqueEntityId;
  private String username;
  private UUID uuid;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
