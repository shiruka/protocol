package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents book edit packet.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class BookEdit extends MinecraftPacket.Base {

  //@formatter:off
  private Action action;
  private String author;
  private int inventorySlot;
  private int pageNumber;
  private String photoName;
  private int secondaryPageNumber;
  private String text;
  private String title;
  private String xuid;

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
