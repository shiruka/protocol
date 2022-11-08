package io.github.shiruka.protocol.codec.v557;

import io.github.shiruka.protocol.codec.v313.CodecV313;
import io.github.shiruka.protocol.codec.v554.CodecV554;
import io.github.shiruka.protocol.common.Codec;

/**
 * an interface that contains codec for v291.
 */
public interface CodecV557 {

  /**
   * the instance.
   */
  Codec INSTANCE = CodecV554.INSTANCE
    .toBuilder()
    .rakNetProtocolVersion(11)
    .protocolVersion(557)
    .minecraftVersion("1.19.41")
    .helper(new CodecHelperV557())
    .scanPackageAndRegister()
    .build();
}
