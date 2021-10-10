package io.github.shiruka.protocol;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.data.PlayStatusStatus;
import io.github.shiruka.protocol.data.ResourcePackClientResponseStatus;
import io.github.shiruka.protocol.data.ResourcePackInfoEntry;
import io.github.shiruka.protocol.data.ResourcePackStackEntry;
import io.github.shiruka.protocol.data.ExperimentData;
import io.github.shiruka.protocol.data.TextType;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that delegates {@link PacketBuffer} and adds more helpful methods.
 */
@RequiredArgsConstructor
public final class MinecraftPacketBuffer {

  /**
   * the buffer.
   */
  @NotNull
  @Delegate
  private final PacketBuffer buffer;

  /**
   * reads the array.
   *
   * @param array the array to read.
   * @param supplier the supplier to read.
   * @param <T> type of the array.
   */
  public <T> void readArray(@NotNull final Collection<T> array, @NotNull final Supplier<T> supplier) {
    final var length = this.readUnsignedInt();
    IntStream.iterate(0, i -> i < length, i -> i + 1)
      .mapToObj(i -> supplier.get())
      .forEach(array::add);
  }

  /**
   * reads the array shor le.
   *
   * @param array the array to read.
   * @param supplier the supplier to read.
   * @param <T> type of the array.
   */
  public <T> void readArrayShortLE(@NotNull final Collection<T> array, final Supplier<T> supplier) {
    final var length = this.readUnsignedShortLE();
    IntStream.range(0, length)
      .mapToObj(i -> supplier.get())
      .forEach(array::add);
  }

  /**
   * reads the expiriments.
   *
   * @param experiments the experiments to read.
   */
  public void readExperiments(@NotNull final List<ExperimentData> experiments) {
    final var count = this.buffer.readIntLE();
    IntStream.range(0, count)
      .mapToObj(i -> new ExperimentData(this.readString(), this.buffer.readBoolean()))
      .forEach(experiments::add);
  }

  /**
   * reads the play status status.
   *
   * @return play status status.
   */
  @NotNull
  public PlayStatusStatus readPlayStatusStatus() {
    return PlayStatusStatus.byOrdinal(this.readInt());
  }

  /**
   * reads the resource pack client response status.
   *
   * @return resource pack client response status.
   */
  @NotNull
  public ResourcePackClientResponseStatus readResourcePackClientResponseStatus() {
    return ResourcePackClientResponseStatus.byOrdinal(this.buffer.readUnsignedByte());
  }

  /**
   * reads the entry.
   *
   * @return entry.
   */
  @NotNull
  public ResourcePackInfoEntry readResourcePackEntry() {
    final var packId = this.readString();
    final var packVersion = this.readString();
    final var packSize = this.readLongLE();
    final var contentKey = this.readString();
    final var subPackName = this.readString();
    final var contentId = this.readString();
    final var isScripting = this.readBoolean();
    return new ResourcePackInfoEntry(packId, packVersion, packSize, contentKey, subPackName, contentId,
      isScripting, false);
  }

  /**
   * reads the resource pack entry.
   *
   * @return entry.
   */
  @NotNull
  public ResourcePackInfoEntry readResourcePackEntryWithRaytracing() {
    final var packId = this.readString();
    final var packVersion = this.readString();
    final var packSize = this.readLongLE();
    final var contentKey = this.readString();
    final var subPackName = this.readString();
    final var contentId = this.readString();
    final var isScripting = this.readBoolean();
    final var raytracingCapable = this.readBoolean();
    return new ResourcePackInfoEntry(packId, packVersion, packSize, contentKey, subPackName, contentId,
      isScripting, raytracingCapable);
  }

  /**
   * reads the entry.
   *
   * @return entry.
   */
  @NotNull
  public ResourcePackStackEntry readResourcePackStackEntry() {
    final var packId = this.readString();
    final var packVersion = this.readString();
    final var subPackName = this.readString();
    return new ResourcePackStackEntry(packId, packVersion, subPackName);
  }

  /**
   * reads the text type.
   *
   * @return text type.
   */
  @NotNull
  public TextType readTextType() {
    return TextType.values()[this.readUnsignedByte()];
  }

  /**
   * writes the array.
   *
   * @param array the array to write.
   * @param consumer the consumer to write.
   * @param <T> type of the array.
   */
  public <T> void writeArray(@NotNull final Collection<T> array,
                             @NotNull final Consumer<T> consumer) {
    this.writeUnsignedInt(array.size());
    array.forEach(consumer);
  }

  /**
   * writes the array short le.
   *
   * @param array the array to write.
   * @param consumer the consumer to write.
   * @param <T> type of the array.
   */
  public <T> void writeArrayShortLE(@NotNull final Collection<T> array, @NotNull final Consumer<T> consumer) {
    this.buffer.writeShortLE(array.size());
    array.forEach(consumer);
  }

  /**
   * writes the experiments.
   *
   * @param experiments the experiments to write.
   */
  public void writeExperiments(@NotNull final List<ExperimentData> experiments) {
    this.writeIntLE(experiments.size());
    for (final var experiment : experiments) {
      this.writeString(experiment.name());
      this.writeBoolean(experiment.enabled());
    }
  }

  /**
   * writes the play status status.
   *
   * @param status the status to write.
   */
  public void writePlayStatusStatus(@NotNull final PlayStatusStatus status) {
    this.writeInt(status.ordinal());
  }

  /**
   * writes the resource pack client response status.
   *
   * @param status the status to write.
   */
  public void writeResourcePackClientResponseStatus(@NotNull final ResourcePackClientResponseStatus status) {
    this.writeByte(status.ordinal());
  }

  /**
   * writes the resource pack entry.
   *
   * @param entry the entry to write.
   */
  public void writeResourcePackEntry(@NotNull final ResourcePackInfoEntry entry) {
    this.buffer.writeString(entry.packId());
    this.buffer.writeString(entry.packVersion());
    this.buffer.writeLongLE(entry.packSize());
    this.buffer.writeString(entry.contentKey());
    this.buffer.writeString(entry.subPackName());
    this.buffer.writeString(entry.contentId());
    this.buffer.writeBoolean(entry.scripting());
  }

  /**
   * writes the resource pack entry.
   *
   * @param entry the entry to write.
   */
  public void writeResourcePackEntryWithRaytracing(@NotNull final ResourcePackInfoEntry entry) {
    this.writeResourcePackEntry(entry);
    this.writeBoolean(entry.raytracingCapable());
  }

  /**
   * writes the entry.
   *
   * @param entry the entry to write.
   */
  public void writeResourcePackStackEntry(@NotNull final ResourcePackStackEntry entry) {
    this.writeString(entry.packId());
    this.writeString(entry.packVersion());
    this.writeString(entry.subPackName());
  }

  /**
   * writes the text type.
   *
   * @param type the type to write.
   */
  public void writeTextType(@NotNull final TextType type) {
    this.writeByte(type.ordinal());
  }
}
