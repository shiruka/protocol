package io.github.shiruka.protocol.common;

import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents packet definitions.
 *
 * @param <T> type of the packet.
 * @param id the id.
 * @param factory the factory.
 * @param encoder the encoder.
 */
public record PacketDefinition<T extends MinecraftPacket>(
  int id,
  @NotNull Supplier<T> factory,
  @NotNull PacketEncoder<T> encoder
)
  implements Definition {

}
