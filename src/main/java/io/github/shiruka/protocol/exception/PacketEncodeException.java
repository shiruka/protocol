package io.github.shiruka.protocol.exception;

/**
 * a class that represents packet serialize exception.
 */
public final class PacketEncodeException extends RuntimeException {

  /**
   * ctor.
   *
   * @param cause the cause.
   */
  public PacketEncodeException(final Throwable cause) {
    super(cause);
  }

  /**
   * ctor.
   *
   * @param message the message.
   * @param cause the cause.
   */
  public PacketEncodeException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
