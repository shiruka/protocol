package io.github.shiruka.protocol;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.data.ResourcePackStackEntry;
import io.github.shiruka.protocol.data.ResourcePackStackExperimentData;
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
   * @param function the function to read.
   * @param <T> type of the array.
   */
  public <T> void readArray(@NotNull final Collection<T> array, @NotNull final Supplier<T> function) {
    final var length = this.readUnsignedInt();
    IntStream.iterate(0, i -> i < length, i -> i + 1)
      .mapToObj(i -> function.get())
      .forEach(array::add);
  }

  /**
   * reads the expiriments.
   *
   * @param experiments the experiments to read.
   */
  public void readExperiments(@NotNull final List<ResourcePackStackExperimentData> experiments) {
    final var count = this.buffer.readIntLE();
    IntStream.range(0, count)
      .mapToObj(i -> new ResourcePackStackExperimentData(this.readString(), this.buffer.readBoolean()))
      .forEach(experiments::add);
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
   * writes the experiments.
   *
   * @param experiments the experiments to write.
   */
  public void writeExperiments(@NotNull final List<ResourcePackStackExperimentData> experiments) {
    this.writeIntLE(experiments.size());
    for (final var experiment : experiments) {
      this.writeString(experiment.name());
      this.writeBoolean(experiment.enabled());
    }
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
}
