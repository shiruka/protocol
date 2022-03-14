package io.github.shiruka.protocol.codec.v291;

import io.github.shiruka.protocol.common.Codec;

/**
 * an interface that contains codec for v291.
 */
public interface CodecV291 {

  /**
   * the instance.
   */
  Codec INSTANCE = Codec.newBuilder()
    .protocolVersion(291)
    .minecraftVersion("1.7.0")
    .helper(new CodecHelperV291())
    .scanPackageAndRegister()
    .build();
}
