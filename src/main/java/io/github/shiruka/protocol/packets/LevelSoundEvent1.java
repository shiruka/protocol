package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
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
public final class LevelSoundEvent1 extends MinecraftPacket {

  /**
   * the baby sound.
   */
  private boolean babySound;

  /**
   * the extra data.
   */
  private int extraData;

  /**
   * the pitch.
   */
  private int pitch;

  /**
   * the position.
   */
  private Vector3f position;

  /**
   * the relative volume disabled.
   */
  private boolean relativeVolumeDisabled;

  /**
   * the sound.
   */
  private SoundEvent sound;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
