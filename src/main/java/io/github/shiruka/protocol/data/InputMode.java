package io.github.shiruka.protocol.data;

/**
 * an enum class that contains input modes.
 */
public enum InputMode {
  /**
   * the undefined.
   */
  UNDEFINED,
  /**
   * the mouse.
   */
  MOUSE,
  /**
   * the touch.
   */
  TOUCH,
  /**
   * the gamepad.
   */
  GAMEPAD,
  /**
   * the motion controller.
   */
  MOTION_CONTROLLER;

  /**
   * the values.
   */
  public static final InputMode[] VALUES = InputMode.values();
}
