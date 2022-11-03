package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.ExperimentData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack stack packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class ResourcePackStack extends MinecraftPacket.Base {

  @Builder.Default
  List<Entry> behaviorPacks = new ObjectArrayList<>();

  @Builder.Default
  List<ExperimentData> experiments = new ObjectArrayList<>();

  boolean experimentsPreviouslyToggled;

  boolean forcedToAccept;

  String gameVersion;

  @Builder.Default
  List<Entry> resourcePacks = new ObjectArrayList<>();

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * a record class that represents resource pack stack entries.
   *
   * @param packId the pack id.
   * @param packVersion the pack version.
   * @param subPackName the sub pack name.
   */
  public record Entry(
    @NotNull String packId,
    @NotNull String packVersion,
    @NotNull String subPackName
  ) {}
}
