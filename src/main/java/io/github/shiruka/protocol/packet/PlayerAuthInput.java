package io.github.shiruka.protocol.packet;

import io.github.shiruka.api.base.Vector2f;
import io.github.shiruka.api.base.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents player auth input packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class PlayerAuthInput extends MinecraftPacket.Base {

  Vector3f delta;

  @Builder.Default
  Set<PlayerAuthInputData> inputData = EnumSet.noneOf(
    PlayerAuthInputData.class
  );

  InputMode inputMode;

  ItemStackRequest itemStackRequest;

  ItemUseTransaction itemUseTransaction;

  Vector2f motion;

  ClientPlayMode playMode;

  @Builder.Default
  List<PlayerBlockActionData> playerActions = new ObjectArrayList<>();

  Vector3f position;

  Vector3f rotation;

  long tick;

  Vector3f vrGazeDirection;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
