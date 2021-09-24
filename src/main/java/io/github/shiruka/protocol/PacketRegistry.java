package io.github.shiruka.protocol;

import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.Unknown;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents packet registries.
 */
public final class PacketRegistry {

  /**
   * the packets.
   */
  private static final Int2ObjectMap<Supplier<? extends MinecraftPacket>> PACKETS = new Int2ObjectOpenHashMap<>();

  /**
   * ctor.
   */
  private PacketRegistry() {
  }

  /**
   * gets a Minecraft packet by id.
   *
   * @param id the id to get.
   *
   * @return Minecraft packet, if not found unknown packet instance.
   */
  @NotNull
  public static MinecraftPacket get(final int id) {
    return Objects.requireNonNull(PacketRegistry.PACKETS.get(id), "Packet %s not found!"
      .formatted(id)).get();
  }

  /**
   * registers the packet.
   *
   * @param id the id to register.
   * @param packet the packet to register.
   */
  public static void register(final int id, @NotNull final Supplier<? extends MinecraftPacket> packet) {
    PacketRegistry.PACKETS.put(id, packet);
  }

  /**
   * register default packets.
   */
  public static void registerDefaults() {
    PacketRegistry.register(Ids.LOGIN, Login::new);
    PacketRegistry.PACKETS.defaultReturnValue(Unknown::new);
  }
}
