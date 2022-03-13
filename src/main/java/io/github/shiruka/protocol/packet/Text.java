package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents text packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class Text extends MinecraftPacket.Base {

  //@formatter:off
  String message;
  boolean needsTranslation;
  @Builder.Default List<String> parameters = new ObjectArrayList<>();
  @Builder.Default String platformChatId = "";
  String sourceName;
  Type type;
  String xboxUniqueId;
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
    public static final Type[] VALUES = Type.values();
  }
}
