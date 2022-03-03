package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.packets.BookEdit;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents book edit packet encoders.
 */
@PacketId(97)
public final class BookEditEncoderV291 extends PacketEncoder.Base<BookEdit> {

  @Override
  public void decode(@NotNull final BookEdit packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.action(helper.bookEditTypes().type(buffer.readUnsignedByte()));
    packet.inventorySlot(buffer.readUnsignedByte());
    switch (packet.action()) {
      case REPLACE_PAGE, ADD_PAGE -> {
        packet.pageNumber(buffer.readUnsignedByte());
        packet.text(buffer.readString());
        packet.photoName(buffer.readString());
      }
      case DELETE_PAGE -> packet.pageNumber(buffer.readUnsignedByte());
      case SWAP_PAGES -> {
        packet.pageNumber(buffer.readUnsignedByte());
        packet.secondaryPageNumber(buffer.readUnsignedByte());
      }
      case SIGN_BOOK -> {
        packet.title(buffer.readString());
        packet.author(buffer.readString());
        packet.xuid(buffer.readString());
      }
      default -> {}
    }
  }

  @Override
  public void encode(@NotNull final BookEdit packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeByte(packet.action().ordinal());
    buffer.writeByte(packet.inventorySlot());
    switch (packet.action()) {
      case REPLACE_PAGE, ADD_PAGE -> {
        buffer.writeByte(packet.pageNumber());
        buffer.writeString(packet.text());
        buffer.writeString(packet.photoName());
      }
      case DELETE_PAGE -> buffer.writeByte(packet.pageNumber());
      case SWAP_PAGES -> {
        buffer.writeByte(packet.pageNumber());
        buffer.writeByte(packet.secondaryPageNumber());
      }
      case SIGN_BOOK -> {
        buffer.writeString(packet.title());
        buffer.writeString(packet.author());
        buffer.writeString(packet.xuid());
      }
      default -> {}
    }
  }
}
