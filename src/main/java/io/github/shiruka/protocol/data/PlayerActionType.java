package io.github.shiruka.protocol.data;

/**
 * an enum class that contains player action types.
 */
public enum PlayerActionType {
  /**
   * the start break.
   */
  START_BREAK,
  /**
   * the abort break.
   */
  ABORT_BREAK,
  /**
   * the stop break.
   */
  STOP_BREAK,
  /**
   * the get updated block.
   */
  GET_UPDATED_BLOCK,
  /**
   * the drop item.
   */
  DROP_ITEM,
  /**
   * the start sleep.
   */
  START_SLEEP,
  /**
   * the stop sleep.
   */
  STOP_SLEEP,
  /**
   * the respawn.
   */
  RESPAWN,
  /**
   * the jump.
   */
  JUMP,
  /**
   * the start sprint.
   */
  START_SPRINT,
  /**
   * the stop spring.
   */
  STOP_SPRINT,
  /**
   * the start sneak.
   */
  START_SNEAK,
  /**
   * the stop sneak.
   */
  STOP_SNEAK,
  /**
   * the dimension change request or creative destroy block.
   */
  DIMENSION_CHANGE_REQUEST_OR_CREATIVE_DESTROY_BLOCK,
  /**
   * the dimension change success.
   */
  DIMENSION_CHANGE_SUCCESS,
  /**
   * the start glide.
   */
  START_GLIDE,
  /**
   * the stop glide.
   */
  STOP_GLIDE,
  /**
   * the build denied.
   */
  BUILD_DENIED,
  /**
   * the continue break.
   */
  CONTINUE_BREAK,
  /**
   * the change skin.
   */
  CHANGE_SKIN,
  /**
   * the set enchantment seed.
   */
  SET_ENCHANTMENT_SEED,
  /**
   * the start swimming.
   */
  START_SWIMMING,
  /**
   * the stop swimming.
   */
  STOP_SWIMMING,
  /**
   * the start spin attack.
   */
  START_SPIN_ATTACK,
  /**
   * the stop spin attack.
   */
  STOP_SPIN_ATTACK,
  /**
   * the block interact.
   */
  BLOCK_INTERACT,
  /**
   * the block predict destroy.
   */
  BLOCK_PREDICT_DESTROY,
  /**
   * the block continue destroy.
   */
  BLOCK_CONTINUE_DESTROY;

  /**
   * the values.
   */
  public static final PlayerActionType[] VALUES = PlayerActionType.values();
}
