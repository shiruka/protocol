package io.github.shiruka.protocol.codec.v291.encoders;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.MinecraftSession;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.packets.AddPainting;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add painting packet encoders.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class AddPaintingEncoder implements PacketEncoder<AddPainting> {

  /**
   * the instance.
   */
  public static final AddPaintingEncoder INSTANCE = new AddPaintingEncoder();

  @Override
  public void decode(@NotNull final AddPainting packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.uniqueEntityId(buffer.readVarLong());
    packet.runtimeEntityId(buffer.readUnsignedVarLong());
    packet.position(buffer.readBlockPosition().toFloat());
    packet.direction(buffer.readInt());
    packet.motive(buffer.readString());
  }

  @Override
  public void encode(@NotNull final AddPainting packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeVarLong(packet.uniqueEntityId());
    buffer.writeUnsignedVarLong(packet.runtimeEntityId());
    buffer.writeBlockPosition(packet.position().toInt());
    buffer.writeVarInt(packet.direction());
    buffer.writeString(packet.motive());
  }
}
