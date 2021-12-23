package io.github.shiruka.protocol.packets;

import io.github.shiruka.api.common.vectors.Vector3f;
import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.codec.Constants;
import io.github.shiruka.protocol.data.SoundEvent;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents level sound event 1 packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class LevelSoundEvent1 extends MinecraftPacket {

  /**
   * the baby sound.
   */
  @Getter
  private boolean babySound;

  /**
   * the extra data.
   */
  @Getter
  private int extraData;

  /**
   * the pitch.
   */
  @Getter
  private int pitch;

  /**
   * the position.
   */
  @Nullable
  private Vector3f position;

  /**
   * the relative volume disabled.
   */
  @Getter
  private boolean relativeVolumeDisabled;

  /**
   * the sound.
   */
  @Nullable
  private SoundEvent sound;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.sound = Constants.SOUND_EVENTS.get(buffer.readUnsignedByte());
    this.position = buffer.readVector3f();
    this.extraData = buffer.readVarInt();
    this.pitch = buffer.readVarInt();
    this.babySound = buffer.readBoolean();
    this.relativeVolumeDisabled = buffer.readBoolean();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeByte(Constants.SOUND_EVENTS.get(this.sound()));
    buffer.writeVector3f(this.position());
    buffer.writeVarInt(this.extraData);
    buffer.writeVarInt(this.pitch);
    buffer.writeBoolean(this.babySound);
    buffer.writeBoolean(this.relativeVolumeDisabled);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
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
   * obtains the sound.
   *
   * @return sound.
   */
  @NotNull
  public SoundEvent sound() {
    return Objects.requireNonNull(this.sound, "sound");
  }
}
