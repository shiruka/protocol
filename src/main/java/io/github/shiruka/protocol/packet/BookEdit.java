package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
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
 * a class that represents book edit packet.
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class BookEdit extends MinecraftPacket.Base {

  //@formatter:off
  Action action;
  String author;
  int inventorySlot;
  int pageNumber;
  String photoName;
  int secondaryPageNumber;
  String text;
  String title;
  String xuid;

  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  /**
   * an enum class that contains book edit actions.
   */
  public enum Action {
    /**
     * the replace page.
     */
    REPLACE_PAGE,
    /**
     * the add page.
     */
    ADD_PAGE,
    /**
     * the delete page.
     */
    DELETE_PAGE,
    /**
     * the swap pages.
     */
    SWAP_PAGES,
    /**
     * the sign book.
     */
    SIGN_BOOK
  }
}
