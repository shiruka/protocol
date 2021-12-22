package io.github.shiruka.protocol.data.skin;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents persona pieces.
 *
 * @param id the id.
 * @param type the type.
 * @param packId the pack id.
 * @param isDefault the is default.
 * @param productId the product id.
 */
public record PersonaPiece(
  @NotNull String id,
  @NotNull String type,
  @NotNull String packId,
  boolean isDefault,
  @NotNull String productId
) {

  /**
   * a record class that represents persona piece tints.
   *
   * @param pieceType the piece type.
   * @param colors the colors.
   */
  public record Tint(
    @NotNull String pieceType,
    @NotNull List<String> colors
  ) {

  }
}
