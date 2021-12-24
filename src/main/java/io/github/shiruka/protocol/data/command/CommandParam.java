package io.github.shiruka.protocol.data.command;

import io.github.shiruka.protocol.codec.CodecHelper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a class that represents command parameters.
 */
@RequiredArgsConstructor
public final class CommandParam {

  /**
   * the block position.
   */
  public static final CommandParam BLOCK_POSITION = new CommandParam(CommandParamType.BLOCK_POSITION);

  /**
   * the command.
   */
  public static final CommandParam COMMAND = new CommandParam(CommandParamType.COMMAND);

  /**
   * the filepath.
   */
  public static final CommandParam FILE_PATH = new CommandParam(CommandParamType.FILE_PATH);

  /**
   * the float.
   */
  public static final CommandParam FLOAT = new CommandParam(CommandParamType.FLOAT);

  /**
   * the int.
   */
  public static final CommandParam INT = new CommandParam(CommandParamType.INT);

  /**
   * the int range.
   */
  public static final CommandParam INT_RANGE = new CommandParam(CommandParamType.INT_RANGE);

  /**
   * the json.
   */
  public static final CommandParam JSON = new CommandParam(CommandParamType.JSON);

  /**
   * the message.
   */
  public static final CommandParam MESSAGE = new CommandParam(CommandParamType.MESSAGE);

  /**
   * the operator.
   */
  public static final CommandParam OPERATOR = new CommandParam(CommandParamType.OPERATOR);

  /**
   * the position.
   */
  public static final CommandParam POSITION = new CommandParam(CommandParamType.POSITION);

  /**
   * the string.
   */
  public static final CommandParam STRING = new CommandParam(CommandParamType.STRING);

  /**
   * the target.
   */
  public static final CommandParam TARGET = new CommandParam(CommandParamType.TARGET);

  /**
   * the text.
   */
  public static final CommandParam TEXT = new CommandParam(CommandParamType.TEXT);

  /**
   * the value.
   */
  public static final CommandParam VALUE = new CommandParam(CommandParamType.VALUE);

  /**
   * the wildcard int.
   */
  public static final CommandParam WILDCARD_INT = new CommandParam(CommandParamType.WILDCARD_INT);

  /**
   * the wildcard target.
   */
  public static final CommandParam WILDCARD_TARGET = new CommandParam(CommandParamType.WILDCARD_TARGET);

  /**
   * the default value.
   */
  private int defaultValue = -1;

  /**
   * the parameter type.
   */
  @Nullable
  private CommandParamType paramType = null;

  /**
   * ctor.
   *
   * @param paramType the param type.
   */
  public CommandParam(@NotNull final CommandParamType paramType) {
    this.paramType = paramType;
  }

  /**
   * ctor.
   *
   * @param defaultValue the default value.
   */
  public CommandParam(final int defaultValue) {
    this.defaultValue = defaultValue;
  }

  @Override
  public String toString() {
    final var param = this.paramType == null ? "UNKNOWN" : this.paramType.name();
    return "CommandParam(type=" + param + ", defaultValue=" + this.defaultValue + ")";
  }

  /**
   * gets the value.
   *
   * @param helper the helper to get.
   *
   * @return value.
   */
  public int value(@NotNull final CodecHelper helper) {
    if (this.defaultValue > 0 || this.paramType == null) {
      return this.defaultValue;
    }
    return helper.commandParamId(this);
  }
}
