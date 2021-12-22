package io.github.shiruka.protocol.data;

import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents resource pack info entries.
 *
 * @param packId the pack id.
 * @param packVersion the pack version.
 * @param packSize the pack size.
 * @param contentKey the content key.
 * @param subPackName the sub pack name.
 * @param contentId the content id.
 * @param scripting the scripting.
 * @param raytracingCapable the raytracing capable.
 */
public record ResourcePackInfoEntry(
  @NotNull String packId,
  @NotNull String packVersion,
  long packSize,
  @NotNull String contentKey,
  @NotNull String subPackName,
  @NotNull String contentId,
  boolean scripting,
  boolean raytracingCapable
) {

}
