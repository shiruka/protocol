package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector2f;
import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.ClientPlayMode;
import io.github.shiruka.protocol.data.InputMode;
import io.github.shiruka.protocol.data.PlayerAuthInputData;
import io.github.shiruka.protocol.data.PlayerBlockActionData;
import io.github.shiruka.protocol.data.inventory.ItemStackRequest;
import io.github.shiruka.protocol.data.inventory.ItemUseTransaction;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents player auth input packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class PlayerAuthInput extends MinecraftPacket {

  //@formatter:off
  private final Set<PlayerAuthInputData> inputData = EnumSet.noneOf(PlayerAuthInputData.class);
  private final List<PlayerBlockActionData> playerActions = new ObjectArrayList<>();
  private Vector3f delta;
  private InputMode inputMode;
  private ItemStackRequest itemStackRequest;
  private ItemUseTransaction itemUseTransaction;
  private Vector2f motion;
  private ClientPlayMode playMode;
  private Vector3f position;
  private Vector3f rotation;
  private long tick;
  private Vector3f vrGazeDirection;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
