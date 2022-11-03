package io.github.shiruka.protocol.packet;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.CodecHelper;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.common.PacketEncoder;
import io.github.shiruka.protocol.common.PacketHandler;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.ReferenceCounted;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents unknown packets.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class Unknown
  extends MinecraftPacket.Base
  implements PacketEncoder<Unknown>, ReferenceCounted {

  PacketBuffer payload;

  @Override
  public void decode(
    @NotNull final Unknown packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    this.payload = buffer.readRetainedSlice(buffer.remaining());
  }

  @Override
  public void encode(
    @NotNull final Unknown packet,
    @NotNull final CodecHelper helper,
    @NotNull final PacketBuffer buffer,
    @NotNull final MinecraftSession session
  ) {
    final var payload = this.payload();
    buffer.writeBytes(payload, payload.readerIndex(), payload.remaining());
  }

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }

  @Override
  public int refCnt() {
    if (this.payload == null) {
      return 0;
    }
    return this.payload.buffer().refCnt();
  }

  @Override
  public Unknown retain() {
    if (this.payload != null) {
      this.payload.retain();
    }
    return this;
  }

  @Override
  public Unknown retain(final int increment) {
    if (this.payload != null) {
      this.payload.buffer().retain(increment);
    }
    return this;
  }

  @Override
  public Unknown touch() {
    if (this.payload != null) {
      this.payload.buffer().touch();
    }
    return this;
  }

  @Override
  public Unknown touch(final Object hint) {
    if (this.payload != null) {
      this.payload.touch(hint);
    }
    return this;
  }

  @Override
  public boolean release() {
    return this.payload == null || this.payload.release();
  }

  @Override
  public boolean release(final int decrement) {
    return this.payload == null || this.payload.buffer().release(decrement);
  }

  @Override
  public String toString() {
    final var hex = this.payload == null || this.payload.buffer().refCnt() == 0
      ? "null"
      : ByteBufUtil.hexDump(this.payload.buffer());
    return "UNKNOWN - " + this.packetId() + " - Hex: " + hex;
  }
}
