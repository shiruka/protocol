package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.MinecraftPacketBuffer;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.TextType;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents text packets.
 */
@Setter
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
  @Nullable
  private String message;

  /**
   * the needs translation.
   */
  @Getter
  private boolean needsTranslation;

  /**
   * the platform chat id.
   */
  @NotNull
  private String platformChatId = "";

  /**
   * hte source name.
   */
  @Nullable
  private String sourceName;

  /**
   * the type.
   */
  @Nullable
  private TextType type;

  /**
   * the xbox unique id.
   */
  @Nullable
  private String xuid;

  @Override
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
    this.type = buffer.readTextType();
    this.needsTranslation = buffer.readBoolean();
    switch (this.type) {
      case CHAT:
      case WHISPER:
      case ANNOUNCEMENT:
        this.sourceName = buffer.readString();
      case RAW:
      case TIP:
      case SYSTEM:
      case OBJECT:
      case OBJECT_WHISPER:
        this.message = buffer.readString();
        break;
      case TRANSLATION:
      case POPUP:
      case JUKEBOX_POPUP:
        this.message = buffer.readString();
        buffer.readArray(this.parameters, buffer::readString);
        break;
      default:
        throw new UnsupportedOperationException("Unsupported TextType %s".formatted(this.type));
    }
    this.xuid = buffer.readString();
    this.platformChatId = buffer.readString();
  }

  @Override
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
    buffer.writeTextType(this.type());
    buffer.writeBoolean(this.needsTranslation);
    switch (this.type()) {
      case CHAT:
      case WHISPER:
      case ANNOUNCEMENT:
        buffer.writeString(this.sourceName());
      case RAW:
      case TIP:
      case SYSTEM:
      case OBJECT:
      case OBJECT_WHISPER:
        buffer.writeString(this.message());
        break;
      case TRANSLATION:
      case POPUP:
      case JUKEBOX_POPUP:
        buffer.writeString(this.message());
        buffer.writeArray(this.parameters, buffer::writeString);
        break;
      default:
        throw new UnsupportedOperationException("Unsupported TextType %s".formatted(this.type()));
    }
    buffer.writeString(this.xuid());
    buffer.writeString(this.platformChatId);
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * obtains the message.
   *
   * @return message.
   */
  @NotNull
  public String message() {
    return Objects.requireNonNull(this.message, "message");
  }

  /**
   * obtains the parameters.
   *
   * @return parameters.
   */
  @NotNull
  public List<String> parameters() {
    return Collections.unmodifiableList(this.parameters);
  }

  /**
   * obtains the source name.
   *
   * @return source name.
   */
  @NotNull
  public String sourceName() {
    return Objects.requireNonNull(this.sourceName, "source name");
  }

  /**
   * obtains the type.
   *
   * @return type.
   */
  @NotNull
  public TextType type() {
    return Objects.requireNonNull(this.type, "type");
  }

  /**
   * obtains the xuid.
   *
   * @return xuid.
   */
  @NotNull
  public String xuid() {
    return Objects.requireNonNull(this.xuid, "xuid");
  }
}
