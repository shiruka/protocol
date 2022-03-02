package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents resource pack client response packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class ResourcePackClientResponse extends MinecraftPacket {

  //@formatter:off
  private final List<String> packIds = new ObjectArrayList<>();
  private Status status;
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
    private static final Status[] VALUES = Status.values();

    /**
     * gets resource pack client response status by ordinal.
     *
     * @param ordinal the ordinal.
     *
     * @return resource pack client response status.
     */
    @NotNull
    public static Status byOrdinal(final int ordinal) {
      return Status.VALUES[ordinal];
    }
  }
}
