package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
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
 * a class that represents resource pack client response packets.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class ResourcePackClientResponse extends MinecraftPacket.Base {

  //@formatter:off
  @Builder.Default List<String> packIds = new ObjectArrayList<>();
  Status status;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains resource pack client response status.
   */
  public enum Status {
    /**
     * the none.
     */
    NONE,
    /**
     * the refused.
     */
    REFUSED,
    /**
     * the send packs.
     */
    SEND_PACKS,
    /**
     * the have all packs.
     */
    HAVE_ALL_PACKS,
    /**
     * the completed.
     */
    COMPLETED;

    /**
     * the cache.
     */
    public static final Status[] VALUES = Status.values();
  }
}
