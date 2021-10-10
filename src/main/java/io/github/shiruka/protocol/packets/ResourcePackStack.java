package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.ResourcePackStackEntry;
import io.github.shiruka.protocol.data.ResourcePackStackExperimentData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.Objects;
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
public final class ResourcePackStack extends MinecraftPacket {

  /**
   * the behaviour packs.
   */
  private final List<ResourcePackStackEntry> behaviorPacks = new ObjectArrayList<>();

  /**
   * the experiments.
   */
  private final List<ResourcePackStackExperimentData> experiments = new ObjectArrayList<>();

  /**
   * the resource packs.
   */
  private final List<ResourcePackStackEntry> resourcePacks = new ObjectArrayList<>();

  /**
   * the experiments previously toggled.
   */
  private boolean experimentsPreviouslyToggled;

  /**
   * the forced to accept.
   */
  private boolean forcedToAccept;

  /**
   * the game version
   */
  @Nullable
  private String gameVersion;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.forcedToAccept = buffer.readBoolean();
    buffer.readArray(this.behaviorPacks, buffer::readResourcePackStackEntry);
    buffer.readArray(this.resourcePacks, buffer::readResourcePackStackEntry);
    this.gameVersion = buffer.readString();
    buffer.readExperiments(this.experiments);
    this.experimentsPreviouslyToggled = buffer.readBoolean();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeBoolean(this.forcedToAccept);
    buffer.writeArray(this.behaviorPacks, buffer::writeResourcePackStackEntry);
    buffer.writeArray(this.resourcePacks, buffer::writeResourcePackStackEntry);
    buffer.writeString(this.gameVersion());
    buffer.writeExperiments(this.experiments);
    buffer.writeBoolean(this.experimentsPreviouslyToggled);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the game version.
   *
   * @return game version.
   */
  @NotNull
  public String gameVersion() {
    return Objects.requireNonNull(this.gameVersion, "game version.");
  }
}
