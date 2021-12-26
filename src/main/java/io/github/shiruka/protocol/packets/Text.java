package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.TextType;
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

  /**
   * the parameters.
   */
  private final List<String> parameters = new ObjectArrayList<>();

  /**
   * the message.
   */
  private String message;

  /**
   * the needs translation.
   */
  private boolean needsTranslation;

  /**
   * the platform chat id.
   */
  private String platformChatId = "";

  /**
   * hte source name.
   */
  private String sourceName;

  /**
   * the type.
   */
  private TextType type;

  /**
   * the xbox unique id.
   */
  private String xboxUniqueId;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
