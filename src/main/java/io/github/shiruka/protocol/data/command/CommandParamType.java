package io.github.shiruka.protocol.data.command;

/**
 * an enum class that contains command parameter types.
 */
public enum CommandParamType {
  /**
   * the int.
   */
  INT,
  /**
   * the float.
   */
  FLOAT,
  /**
   * the value.
   */
  VALUE,
  /**
   * the wildcard int.
   */
  WILDCARD_INT,
  /**
   * the operator.
   */
  OPERATOR,
  /**
   * the target.
   */
  TARGET,
  /**
   * the wildcard target.
   */
  WILDCARD_TARGET,
  /**
   * the file path.
   */
  FILE_PATH,
  /**
   * the int range.
   */
  INT_RANGE,
  /**
   * the string.
   */
  STRING,
  /**
   * the position.
   */
  POSITION,
  /**
   * the block position.
   */
  BLOCK_POSITION,
  /**
   * the message.
   */
  MESSAGE,
  /**
   * the text.
   */
  TEXT,
  /**
   * the json.
   */
  JSON,
  /**
   * the command.
   */
  COMMAND;

  /**
   * the values.
   */
  public static final CommandParamType[] VALUES = CommandParamType.values();
}
