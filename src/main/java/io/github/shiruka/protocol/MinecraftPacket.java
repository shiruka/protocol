package io.github.shiruka.protocol;

import io.github.shiruka.network.Packet;
import io.github.shiruka.network.PacketBuffer;
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

  @Override
  public final void decode(@NotNull final PacketBuffer buffer) {
    this.decode(new MinecraftPacketBuffer(buffer));
  }

  @Override
  public final void encode(@NotNull final PacketBuffer buffer) {
    this.encode(new MinecraftPacketBuffer(buffer));
  }

  /**
   * decodes the packet.
   *
   * @param buffer the buffer to decode.
   */
  public abstract void decode(@NotNull MinecraftPacketBuffer buffer);

  /**
   * encodes the packet.
   *
   * @param buffer the buffer to encode.
   */
  public abstract void encode(@NotNull MinecraftPacketBuffer buffer);

  /**
   * handles the packet.
   *
   * @param handler the handler to handle.
   */
  public abstract void handle(@NotNull PacketHandler handler);
}
