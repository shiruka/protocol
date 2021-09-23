package io.github.shiruka.protocol;

import org.jetbrains.annotations.NotNull;

/**
 * a class that represents packet registries.
 */
public final class PacketRegistry {

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
    return null;
  }
}
