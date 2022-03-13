package io.github.shiruka.protocol.common;

import io.netty.util.AbstractReferenceCounted;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine minecraft packets.
 */
public interface MinecraftPacket {

  /**
   * obtains the client id.
   *
   * @return client id.
   */
  int clientId();

  /**
   * sets the client id.
   *
   * @param clientId the client id to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  MinecraftPacket clientId(int clientId);

  /**
   * handles the packet.
   *
   * @param handler the handler to handle.
   */
  void handle(@NotNull PacketHandler handler);

  /**
   * sets the packet id.
   *
   * @param packetId the packet id to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  MinecraftPacket packetId(int packetId);

  /**
   * obtains the packet id.
   *
   * @return packet id.
   */
  int packetId();

  /**
   * sets the sender id.
   *
   * @param senderId the sender id to set.
   *
   * @return {@code this} for the chain.
   */
  @NotNull
  MinecraftPacket senderId(int senderId);

  /**
   * obtains the sender id.
   *
   * @return sender id.
   */
  int senderId();

  /**
   * an abstract implementation of minecraft packets.
   */
  @Getter
  @Setter
  @Accessors(fluent = true)
  abstract class Base implements MinecraftPacket {

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
  }

  /**
   * an abstract referenced count implementation of minecraft packets.
   */
  @Getter
  @Setter
  @Accessors(fluent = true)
  abstract class ReferencedCount extends AbstractReferenceCounted implements MinecraftPacket {

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
  }
}
