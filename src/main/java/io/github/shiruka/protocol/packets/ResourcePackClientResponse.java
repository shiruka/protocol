package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.ResourcePackClientResponseStatus;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents resource pack client response packets.
 */
@Setter
@ToString
@Accessors(fluent = true)
public final class ResourcePackClientResponse extends MinecraftPacket {

  /**
   * the pack ids.
   */
  private final List<String> packIds = new ObjectArrayList<>();

  /**
   * the status.
   */
  @Nullable
  private ResourcePackClientResponseStatus status;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.readPlayStatusStatus();
    this.status = buffer.readResourcePackClientResponseStatus();
    buffer.readArrayShortLE(this.packIds, buffer::readString);
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeResourcePackClientResponseStatus(this.status());
    buffer.writeArrayShortLE(this.packIds, buffer::writeString);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the pack ids.
   *
   * @return pack ids.
   */
  @NotNull
  public List<String> packIds() {
    return Collections.unmodifiableList(this.packIds);
  }

  /**
   * obtains the status.
   *
   * @return status.
   */
  @NotNull
  public ResourcePackClientResponseStatus status() {
    return Objects.requireNonNull(this.status, "status");
  }
}
