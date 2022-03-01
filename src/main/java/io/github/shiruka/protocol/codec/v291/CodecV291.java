package io.github.shiruka.protocol.codec.v291;

import io.github.shiruka.protocol.codec.Codec;
import io.github.shiruka.protocol.codec.v291.encoders.LoginEncoderV291;

/**
 * an interface that contains codec for v291.
 */
public interface CodecV291 {

  /**
   * the codec.
   */
  Codec INSTANCE = Codec.newBuilder()
    .protocolVersion(291)
    .minecraftVersion("1.7.0")
    .helper(new CodecHelperV291())
    .registerPacket(LoginEncoderV291.INSTANCE)
    .build();
}
