package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents resource pack stack entries.
 */
public final record ResourcePackStackEntry(
  @NotNull String packId,
  @NotNull String packVersion,
  @NotNull String subPackName
) {

}
