package io.github.shiruka.protocol.codec;

import io.github.shiruka.protocol.codec.v291.CodecV291;
import io.github.shiruka.protocol.codec.v313.CodecV313;
import io.github.shiruka.protocol.common.Codec;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

/**
 * an interface that contains utility methods for codecs.
 */
public interface Codecs {

  /**
   * the ALL.
   */
  Collection<Codec> ALL = Set.of(
    CodecV291.INSTANCE, CodecV313.INSTANCE
  );

  /**
   * the by protocol version.
   */
  Map<Integer, Codec> ALL_BY_PROTOCOL_VERSION = Codecs.ALL.stream()
    .collect(Collectors.toMap(Codec::protocolVersion, Function.identity()));

  /**
   * finds the codec by protocol version.
   *
   * @param protocolVersion the protocol version to find.
   *
   * @return codec.
   */
  @Nullable
  static Codec findByProtocolVersion(final int protocolVersion) {
    return Codecs.ALL_BY_PROTOCOL_VERSION.get(protocolVersion);
  }

  /**
   * gets the latest protocol version.
   *
   * @return latest protocol version.
   */
  static int latestProtocolVersion() {
    return Codecs.ALL.stream()
      .mapToInt(Codec::protocolVersion)
      .sorted()
      .boxed()
      .toList()
      .get(0);
  }
}
