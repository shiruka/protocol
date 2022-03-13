package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.PurchaseReceipt;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents purchase receipt packet encoders.
 */
@PacketId(92)
public final class PurchaseReceiptEncoderV291 extends PacketEncoder.Base<PurchaseReceipt> {

  @Override
  public void decode(@NotNull final PurchaseReceipt packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    packet.receipts(buffer.readArrayUnsignedInt(buffer::readString));
  }

  @Override
  public void encode(@NotNull final PurchaseReceipt packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    buffer.writeArrayUnsignedInt(packet.receipts(), buffer::writeString);
  }
}
