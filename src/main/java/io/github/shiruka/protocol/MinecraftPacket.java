package io.github.shiruka.protocol;

import io.github.shiruka.network.Packet;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
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
   * decodes the packet.
   *
   * @param buffer the buffer to decode.
   * @param session the session to decode.
   */
  public final void decode(@NotNull final PacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    this.decode(new MinecraftPacketBuffer(buffer), session);
  }

  @Override
  public final void decode(@NotNull final PacketBuffer buffer) {
    this.decode(new MinecraftPacketBuffer(buffer));
  }

  @Override
  public final void encode(@NotNull final PacketBuffer buffer) {
    this.encode(new MinecraftPacketBuffer(buffer));
  }

  /**
   * encodes the packet.
   *
   * @param buffer the buffer to encode.
   * @param session the session to decode.
   */
  public final void encode(@NotNull final PacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    this.encode(new MinecraftPacketBuffer(buffer), session);
  }

  /**
   * decodes the packet.
   *
   * @param buffer the buffer to decode.
   */
  public void decode(@NotNull final MinecraftPacketBuffer buffer) {
  }

  /**
   * decodes the packet.
   *
   * @param buffer the buffer to decode.
   * @param session the session to decode.
   */
  public void decode(@NotNull final MinecraftPacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    this.decode(buffer);
  }

  /**
   * encodes the packet.
   *
   * @param buffer the buffer to encode.
   */
  public void encode(@NotNull final MinecraftPacketBuffer buffer) {
  }

  /**
   * encodes the packet.
   *
   * @param buffer the buffer to encode.
   * @param session the session to decode.
   */
  public void encode(@NotNull final MinecraftPacketBuffer buffer, @NotNull final MinecraftChildChannel session) {
    this.encode(buffer);
  }

  /**
   * handles the packet.
   *
   * @param handler the handler to handle.
   */
  public abstract void handle(@NotNull PacketHandler handler);
}
