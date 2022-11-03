package io.github.shiruka.protocol.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * an annotation class that represents packet ids.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PacketId {
  /**
   * obtains the packet id.
   *
   * @return packet id.
   */
  int value();
}
