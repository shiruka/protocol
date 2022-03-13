package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.SoundEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents level sound event 1 packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class LevelSoundEvent1 extends MinecraftPacket.Base {

  //@formatter:off
  private boolean babySound;
  private int extraData;
  private int pitch;
  private Vector3f position;
  private boolean relativeVolumeDisabled;
  private SoundEvent sound;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
