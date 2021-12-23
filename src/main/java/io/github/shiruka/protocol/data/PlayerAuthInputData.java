package io.github.shiruka.protocol.data;

/**
 * an enum class that contains player auth input data.
 */
public enum PlayerAuthInputData {
  /**
   * the ascend.
   */
  ASCEND,
  /**
   * the descend.
   */
  DESCEND,
  /**
   * the north jump.
   */
  NORTH_JUMP,
  /**
   * the jump down.
   */
  JUMP_DOWN,
  /**
   * the sprint down.
   */
  SPRINT_DOWN,
  /**
   * the change height.
   */
  CHANGE_HEIGHT,
  /**
   * the jumping.
   */
  JUMPING,
  /**
   * the auto jumping in water.
   */
  AUTO_JUMPING_IN_WATER,
  /**
   * the sneaking.
   */
  SNEAKING,
  /**
   * the sneak down.
   */
  SNEAK_DOWN,
  /**
   * the up.
   */
  UP,
  /**
   * the down.
   */
  DOWN,
  /**
   * the left.
   */
  LEFT,
  /**
   * the right.
   */
  RIGHT,
  /**
   * the up left.
   */
  UP_LEFT,
  /**
   * the up right.
   */
  UP_RIGHT,
  /**
   * the want up.
   */
  WANT_UP,
  /**
   * the want down.
   */
  WANT_DOWN,
  /**
   * the want down slow.
   */
  WANT_DOWN_SLOW,
  /**
   * the want up slow.
   */
  WANT_UP_SLOW,
  /**
   * the sprinting.
   */
  SPRINTING,
  /**
   * the ascend scaffolding.
   */
  ASCEND_SCAFFOLDING,
  /**
   * the descend scaffolding.
   */
  DESCEND_SCAFFOLDING,
  /**
   * the sneak toggle down.
   */
  SNEAK_TOGGLE_DOWN,
  /**
   * the persist sneak.
   */
  PERSIST_SNEAK,
  /**
   * the start sprinting.
   */
  START_SPRINTING,
  /**
   * the stop sprinting.
   */
  STOP_SPRINTING,
  /**
   * the start sneaking.
   */
  START_SNEAKING,
  /**
   * the stop sneaking.
   */
  STOP_SNEAKING,
  /**
   * the start swimming.
   */
  START_SWIMMING,
  /**
   * the stop swimming.
   */
  STOP_SWIMMING,
  /**
   * the start jumping.
   */
  START_JUMPING,
  /**
   * the start gliding.
   */
  START_GLIDING,
  /**
   * the stop gliding.
   */
  STOP_GLIDING,
  /**
   * the perform item interaction.
   */
  PERFORM_ITEM_INTERACTION,
  /**
   * the perform block actions.
   */
  PERFORM_BLOCK_ACTIONS,
  /**
   * the perform item stack request.
   */
  PERFORM_ITEM_STACK_REQUEST;

  /**
   * the values.
   */
  public static final PlayerAuthInputData[] VALUES = PlayerAuthInputData.values();
}
