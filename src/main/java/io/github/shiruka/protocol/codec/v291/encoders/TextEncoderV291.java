package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.Text;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents text packet encoders.
 */
@PacketId(9)
public final class TextEncoderV291 extends PacketEncoder.Base<Text> {

  @Override
  public void decode(@NotNull final Text packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var type = Text.Type.byOrdinal(buffer.readUnsignedByte());
    packet.type(type);
    packet.needsTranslation(buffer.readBoolean());
    switch (type) {
      case CHAT:
      case WHISPER:
      case ANNOUNCEMENT:
        packet.sourceName(buffer.readString());
      case RAW:
      case TIP:
      case SYSTEM:
        packet.message(buffer.readString());
        break;
      case TRANSLATION:
      case POPUP:
      case JUKEBOX_POPUP:
        packet.message(buffer.readString());
        packet.parameters(buffer.readArrayUnsignedInt(buffer::readString));
        break;
      default:
        throw new UnsupportedOperationException("Unsupported TextType " + type);
    }
    packet.xboxUniqueId(buffer.readString());
    packet.platformChatId(buffer.readString());
  }

  @Override
  public void encode(@NotNull final Text packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var type = packet.type();
    buffer.writeByte(type.ordinal());
    buffer.writeBoolean(packet.needsTranslation());
    switch (type) {
      case CHAT:
      case WHISPER:
      case ANNOUNCEMENT:
        buffer.writeString(packet.sourceName());
      case RAW:
      case TIP:
      case SYSTEM:
        buffer.writeString(packet.message());
        break;
      case TRANSLATION:
      case POPUP:
      case JUKEBOX_POPUP:
        buffer.writeString(packet.message());
        buffer.writeArrayUnsignedInt(packet.parameters(), buffer::writeString);
        break;
      default:
        throw new UnsupportedOperationException("Unsupported TextType " + type);
    }
    buffer.writeString(packet.xboxUniqueId());
    buffer.writeString(packet.platformChatId());
  }
}
