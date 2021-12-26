package io.github.shiruka.protocol.data.command;

/**
 * an enum class that contains command param options.
 */
public enum CommandParamOption {
  /**
   * the suppress enum autocompletion.
   */
  SUPPRESS_ENUM_AUTOCOMPLETION,
  /**
   * the has semantic constraint.
   */
  HAS_SEMANTIC_CONSTRAINT,
  /**
   * the enum as chained command.
   */
  ENUM_AS_CHAINED_COMMAND;

  /**
   * the values.
   */
  public static final CommandParamOption[] VALUES = CommandParamOption.values();
}
