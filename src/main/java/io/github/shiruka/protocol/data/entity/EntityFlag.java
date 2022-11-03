package io.github.shiruka.protocol.data.entity;

/**
 * an enum class that contains entity flags.
 */
public enum EntityFlag {
  /**
   * the on fire.
   */
  ON_FIRE,
  /**
   * the sneaking.
   */
  SNEAKING,
  /**
   * the riding.
   */
  RIDING,
  /**
   * the sprinting.
   */
  SPRINTING,
  /**
   * the using item.
   */
  USING_ITEM,
  /**
   * the invisible.
   */
  INVISIBLE,
  /**
   * the tempted.
   */
  TEMPTED,
  /**
   * the in love.
   */
  IN_LOVE,
  /**
   * the saddled.
   */
  SADDLED,
  /**
   * the powered.
   */
  POWERED,
  /**
   * the ignited.
   */
  IGNITED,
  /**
   * the baby.
   */
  BABY,
  /**
   * the converting.
   */
  CONVERTING,
  /**
   * the critical.
   */
  CRITICAL,
  /**
   * the can show name.
   */
  CAN_SHOW_NAME,
  /**
   * the always show name.
   */
  ALWAYS_SHOW_NAME,
  /**
   * the no ai.
   */
  NO_AI,
  /**
   * the silent.
   */
  SILENT,
  /**
   * the wall climbing.
   */
  WALL_CLIMBING,
  /**
   * the can climb.
   */
  CAN_CLIMB,
  /**
   * the can swim.
   */
  CAN_SWIM,
  /**
   * the can fly.
   */
  CAN_FLY,
  /**
   * the can walk.
   */
  CAN_WALK,
  /**
   * the resting.
   */
  RESTING,
  /**
   * the sitting.
   */
  SITTING,
  /**
   * the angry.
   */
  ANGRY,
  /**
   * the interested.
   */
  INTERESTED,
  /**
   * the charged.
   */
  CHARGED,
  /**
   * the tamed.
   */
  TAMED,
  /**
   * the orphaned.
   */
  ORPHANED,
  /**
   * the leashed.
   */
  LEASHED,
  /**
   * the sheared.
   */
  SHEARED,
  /**
   * the gliding.
   */
  GLIDING,
  /**
   * the elder.
   */
  ELDER,
  /**
   * the moving.
   */
  MOVING,
  /**
   * the breathing.
   */
  BREATHING,
  /**
   * the chested.
   */
  CHESTED,
  /**
   * the stackable.
   */
  STACKABLE,
  /**
   * the show bottom.
   */
  SHOW_BOTTOM,
  /**
   * the standing.
   */
  STANDING,
  /**
   * the shaking.
   */
  SHAKING,
  /**
   * the idling.
   */
  IDLING,
  /**
   * the casting.
   */
  CASTING,
  /**
   * the charging.
   */
  CHARGING,
  /**
   * the wasd controlled.
   */
  WASD_CONTROLLED,
  /**
   * the can power jump.
   */
  CAN_POWER_JUMP,
  /**
   * the lingering.
   */
  LINGERING,
  /**
   * the has collision.
   */
  HAS_COLLISION,
  /**
   * the has gravity.
   */
  HAS_GRAVITY,
  /**
   * the fire immune.
   */
  FIRE_IMMUNE,
  /**
   * the dancing.
   */
  DANCING,
  /**
   * the enchanted.
   */
  ENCHANTED,
  /**
   * the return trident.
   */
  RETURN_TRIDENT,
  /**
   * the container is private.
   */
  CONTAINER_IS_PRIVATE,
  /**
   * the is transforming.
   */
  IS_TRANSFORMING,
  /**
   * the damage nearby mobs.
   */
  DAMAGE_NEARBY_MOBS,
  /**
   * the swimming.
   */
  SWIMMING,
  /**
   * the bribed.
   */
  BRIBED,
  /**
   * the is pregnant.
   */
  IS_PREGNANT,
  /**
   * the laying egg.
   */
  LAYING_EGG,
  /**
   * the rider can pick.
   */
  RIDER_CAN_PICK,
  /**
   * the transition sitting.
   */
  TRANSITION_SITTING,
  /**
   * the eating.
   */
  EATING,
  /**
   * the laying down.
   */
  LAYING_DOWN,
  /**
   * the sneezing.
   */
  SNEEZING,
  /**
   * the trusting.
   */
  TRUSTING,
  /**
   * the rolling.
   */
  ROLLING,
  /**
   * the scared.
   */
  SCARED,
  /**
   * the in scaffolding.
   */
  IN_SCAFFOLDING,
  /**
   * the over scaffolding.
   */
  OVER_SCAFFOLDING,
  /**
   * the fall through scaffolding.
   */
  FALL_THROUGH_SCAFFOLDING,
  /**
   * the blocking.
   */
  BLOCKING,
  /**
   * the transition blocking.
   */
  TRANSITION_BLOCKING,
  /**
   * the blocked using shield.
   */
  BLOCKED_USING_SHIELD,
  /**
   * the blocked using damaged shield.
   */
  BLOCKED_USING_DAMAGED_SHIELD,
  /**
   * the sleeping.
   */
  SLEEPING,
  /**
   * the wants to wake.
   */
  WANTS_TO_WAKE,
  /**
   * the trade interest.
   */
  TRADE_INTEREST,
  /**
   * the door breaker.
   */
  DOOR_BREAKER,
  /**
   * the breaking obstruction.
   */
  BREAKING_OBSTRUCTION,
  /**
   * the door opener.
   */
  DOOR_OPENER,
  /**
   * the is pillager captain.
   */
  IS_ILLAGER_CAPTAIN,
  /**
   * the stunned.
   */
  STUNNED,
  /**
   * the roaring.
   */
  ROARING,
  /**
   * the delayed attack.
   */
  DELAYED_ATTACK,
  /**
   * the is avoiding mobs.
   */
  IS_AVOIDING_MOBS,
  /**
   * the is avoiding block.
   */
  IS_AVOIDING_BLOCK,
  /**
   * the facing target to range attack.
   */
  FACING_TARGET_TO_RANGE_ATTACK,
  /**
   * the hidden when invisible.
   */
  HIDDEN_WHEN_INVISIBLE,
  /**
   * the is in ui.
   */
  IS_IN_UI,
  /**
   * the stalking.
   */
  STALKING,
  /**
   * the emoting.
   */
  EMOTING,
  /**
   * the celebrating.
   */
  CELEBRATING,
  /**
   * the admiring.
   */
  ADMIRING,
  /**
   * the celebrating special.
   */
  CELEBRATING_SPECIAL,
  /**
   * the out of control.
   */
  OUT_OF_CONTROL,
  /**
   * the ram attack.
   */
  RAM_ATTACK,
  /**
   * the playing dead.
   */
  PLAYING_DEAD,
  /**
   * the in ascendable block.
   */
  IN_ASCENDABLE_BLOCK,
  /**
   * the over descendable block.
   */
  OVER_DESCENDABLE_BLOCK,
}
