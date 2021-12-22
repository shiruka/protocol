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

  /**
   * sets the raytracing capable.
   *
   * @param raytracingCapable the raytracing capable to set.
   *
   * @return creates a new entry instance.
   */
  @NotNull
  public ResourcePackInfoEntry raytracingCapable(final boolean raytracingCapable) {
    return new ResourcePackInfoEntry(this.packId, this.packVersion, this.packSize, this.contentKey, this.subPackName,
      this.contentId, this.scripting, raytracingCapable);
  }
}
