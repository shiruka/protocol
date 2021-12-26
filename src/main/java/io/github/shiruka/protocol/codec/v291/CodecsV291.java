package io.github.shiruka.protocol.codec.v291;

import io.github.shiruka.protocol.codec.Codec;
import io.github.shiruka.protocol.codec.v291.encoders.LoginEncoderV291;
import io.github.shiruka.protocol.packets.Login;

/**
 * an interface that contains codec for v291.
 */
public interface CodecsV291 {

  /**
   * the codec.
   */
  Codec CODEC = Codec.newBuilder()
    .protocolVersion(291)
    .minecraftVersion("1.7.0")
    .helper(new CodecHelperV291())
    .registerPacket(1, Login::new, LoginEncoderV291.INSTANCE)
    .build();
}
