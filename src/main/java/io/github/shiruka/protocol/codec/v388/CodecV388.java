package io.github.shiruka.protocol.codec.v388;

import io.github.shiruka.protocol.codec.v313.CodecV313;
import io.github.shiruka.protocol.common.Codec;

/**
 * an interface that contains codec for v388.
 */
public interface CodecV388 {

  /**
   * the instance.
   */
  Codec INSTANCE = CodecV313.INSTANCE
    .toBuilder()
    .protocolVersion(388)
    .minecraftVersion("1.13.0")
    .helper(new CodecHelperV388())
    .scanPackageAndRegister()
    .build();
}
