package io.github.shiruka.protocol.codec.v291.encoder;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketId;
import io.github.shiruka.protocol.packet.InventoryTransaction;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents inventory transaction packet encoders.
 */
@PacketId(30)
public final class InventoryTransactionEncoderV291
  extends PacketEncoder.Base<InventoryTransaction> {

  @Override
  public void decode(
    @NotNull final InventoryTransaction packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    final var transactionType =
      InventoryTransaction.Type.VALUES[buffer.readUnsignedVarInt()];
    packet.transactionType(transactionType);
    packet.actions(helper.readInventoryActions(buffer));
    helper.readInventoryTransactionType(buffer, packet);
  }

  @Override
  public void encode(
    @NotNull final InventoryTransaction packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    buffer.writeUnsignedVarInt(packet.transactionType().ordinal());
    helper.writeInventoryActions(buffer, packet.actions(), false);
    helper.writeInventoryTransactionType(buffer, packet);
  }
}
