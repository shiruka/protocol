package io.github.shiruka.protocol.data.command;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents command symbol data.
 *
 * @param value the value.
 * @param commandEnum the command enum.
 * @param softEnum the soft enum.
 * @param postfix the postfix.
 */
public record CommandSymbolData(
  int value,
  boolean commandEnum,
  boolean softEnum,
  boolean postfix
) {

  /**
   * the arg flag enum.
   */
  private static final int ARG_FLAG_ENUM = 0x200000;

  /**
   * the arg flag postfix.
   */
  private static final int ARG_FLAG_POSTFIX = 0x1000000;

  /**
   * the arg flag soft enum.
   */
  private static final int ARG_FLAG_SOFT_ENUM = 0x4000000;

  /**
   * the arg flag valid.
   */
  private static final int ARG_FLAG_VALID = 0x100000;

  /**
   * deserializes the command symbol data.
   *
   * @param type the type to deserializer.
   *
   * @return command symbol data.
   */
  @NotNull
  public static CommandSymbolData deserialize(final int type) {
    final var value = type & 0xffff;
    final var commandEnum = (type & CommandSymbolData.ARG_FLAG_ENUM) != 0;
    final var softEnum = (type & CommandSymbolData.ARG_FLAG_SOFT_ENUM) != 0;
    final var postfix = (type & CommandSymbolData.ARG_FLAG_POSTFIX) != 0;
    Preconditions.checkState(
      postfix || (type & CommandSymbolData.ARG_FLAG_VALID) != 0,
      "Invalid command param type: %s",
      type
    );
    return new CommandSymbolData(value, commandEnum, softEnum, postfix);
  }

  /**
   * serializes the data.
   *
   * @return serialized data.
   */
  public int serialize() {
    var value = this.value;
    if (this.commandEnum) {
      value |= CommandSymbolData.ARG_FLAG_ENUM;
    }
    if (this.softEnum) {
      value |= CommandSymbolData.ARG_FLAG_SOFT_ENUM;
    }
    if (this.postfix) {
      value |= CommandSymbolData.ARG_FLAG_POSTFIX;
    } else {
      value |= CommandSymbolData.ARG_FLAG_VALID;
    }
    return value;
  }
}
