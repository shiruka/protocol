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
 * a class that represents text packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class Text extends MinecraftPacket {

  //@formatter:off
  private String message;
  private boolean needsTranslation;
  private List<String> parameters = new ObjectArrayList<>();
  private String platformChatId = "";
  private String sourceName;
  private Type type;
  private String xboxUniqueId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains text types.
   */
  public enum Type {
    /**
     * the raw.
     */
    RAW,
    /**
     * the chat.
     */
    CHAT,
    /**
     * the translation.
     */
    TRANSLATION,
    /**
     * the popup.
     */
    POPUP,
    /**
     * the jukebox popup.
     */
    JUKEBOX_POPUP,
    /**
     * the tip.
     */
    TIP,
    /**
     * the system.
     */
    SYSTEM,
    /**
     * the whisper.
     */
    WHISPER,
    /**
     * the announcement.
     */
    ANNOUNCEMENT,
    /**
     * the object.
     */
    OBJECT,
    /**
     * the object whisper.
     */
    OBJECT_WHISPER;

    /**
     * the cache.
     */
    private static final Type[] VALUES = Type.values();

    /**
     * gets text type by ordinal.
     *
     * @param ordinal the ordinal.
     *
     * @return text type.
     */
    @NotNull
    public static Type byOrdinal(final int ordinal) {
      return Type.VALUES[ordinal];
    }
  }
}
