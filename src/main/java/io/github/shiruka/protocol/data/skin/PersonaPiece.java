package io.github.shiruka.protocol.data.skin;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents persona pieces.
 */
public final record PersonaPiece(
  @NotNull String id,
  @NotNull String type,
  @NotNull String packId,
  boolean isDefault,
  @NotNull String productId
) {

  /**
   * a record class that represents persona piece tints.
   */
  public final record Tint(
    @NotNull String pieceType,
    @NotNull List<String> colors
  ) {

  }
}
