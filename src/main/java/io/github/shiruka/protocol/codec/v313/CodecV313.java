package io.github.shiruka.protocol.codec.v313;

import io.github.shiruka.protocol.codec.v291.CodecV291;
import io.github.shiruka.protocol.common.Codec;

/**
 * an interface that contains codec for v291.
 */
public interface CodecV313 {
  /**
   * the instance.
   */
  Codec INSTANCE = CodecV291.INSTANCE
    .toBuilder()
    .protocolVersion(313)
    .minecraftVersion("1.8.0")
    .helper(new CodecHelperV313())
    .scanPackageAndRegister()
    .build();
}
