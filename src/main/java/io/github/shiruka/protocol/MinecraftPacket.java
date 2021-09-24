package io.github.shiruka.protocol;

import io.github.shiruka.network.Packet;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an abstract class that represents Minecraft packets.
 */
@Getter
@Setter
@Accessors(fluent = true)
public abstract class MinecraftPacket implements Packet {

  /**
   * the client id.
   */
  private int clientId;

  /**
   * the packet id.
   */
  private int packetId;

  /**
   * the sender id.
   */
  private int senderId;

  /**
   * handles the packet.
   *
   * @param handler the handler to handle.
   */
  public abstract void handle(@NotNull PacketHandler handler);
}
