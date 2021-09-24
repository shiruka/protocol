package io.github.shiruka.protocol;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents packet registries.
 */
public final class PacketRegistry {

  /**
   * the packets
   */
  private static final Int2ObjectMap<MinecraftPacket> PACKETS = new Int2ObjectOpenHashMap<>();

  /**
   * ctor.
   */
  private PacketRegistry() {
  }

  /**
   * gets a Minecraft packet by id.
   *
   * @param packetId the packet id to get.
   *
   * @return Minecraft packet, if not found unknown packet instance.
   */
  @NotNull
  public static MinecraftPacket get(final int packetId) {
    return Objects.requireNonNull(PacketRegistry.PACKETS.get(packetId), "Packet %s not found!"
      .formatted(packetId));
  }
}
