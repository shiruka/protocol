package io.github.shiruka.protocol;

import io.github.shiruka.network.Packet;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
   * ctor.
   */
  protected MinecraftPacket() {
  }

  /**
   * ctor.
   *
   * @param clientId the client id.
   * @param packetId the packet id.
   * @param senderId the sender id.
   */
  protected MinecraftPacket(final int clientId, final int packetId, final int senderId) {
    this.clientId = clientId;
    this.packetId = packetId;
    this.senderId = senderId;
  }
}
