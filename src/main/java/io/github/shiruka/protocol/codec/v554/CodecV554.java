package io.github.shiruka.protocol.codec.v554;

import io.github.shiruka.protocol.codec.v388.CodecV388;
import io.github.shiruka.protocol.common.Codec;

/**
 * an interface that contains codec for v554.
 */
public interface CodecV554 {
  /**
   * the instance.
   */
  Codec INSTANCE = CodecV388.INSTANCE
    .toBuilder()
    .protocolVersion(554)
    .minecraftVersion("1.19.30")
    .helper(new CodecHelperV554())
    .scanPackageAndRegister()
    .build();
}
