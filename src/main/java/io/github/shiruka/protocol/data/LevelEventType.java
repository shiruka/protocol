package io.github.shiruka.protocol.data;

/**
 * an enum class that contains level event types.
 */
public enum LevelEventType implements CommonLevelEvent {
  /**
   * the undefined.
   */
  UNDEFINED,
  /**
   * the sound click.
   * <p>
   * sound starts.
   */
  SOUND_CLICK,
  /**
   * the sound click fail.
   */
  SOUND_CLICK_FAIL,
  /**
   * the sound launch.
   */
  SOUND_LAUNCH,
  /**
   * the sound door open.
   */
  SOUND_DOOR_OPEN,
  /**
   * the sound fizz.
   */
  SOUND_FIZZ,
  /**
   * the sound fuse.
   */
  SOUND_FUSE,
  /**
   * the sound play recording.
   */
  SOUND_PLAY_RECORDING,
  /**
   * the sound ghast warning.
   */
  SOUND_GHAST_WARNING,
  /**
   * the sound ghast fireball.
   */
  SOUND_GHAST_FIREBALL,
  /**
   * the sound blaze fireball.
   */
  SOUND_BLAZE_FIREBALL,
  /**
   * the sound zombie door bump.
   */
  SOUND_ZOMBIE_DOOR_BUMP,
  /**
   * the sound zombie door crash.
   */
  SOUND_ZOMBIE_DOOR_CRASH,
  /**
   * the sound zombie infected.
   */
  SOUND_ZOMBIE_INFECTED,
  /**
   * the sound zombie converted.
   */
  SOUND_ZOMBIE_CONVERTED,
  /**
   * the sound enderman teleport.
   */
  SOUND_ENDERMAN_TELEPORT,
  /**
   * the sound anvil broken.
   */
  SOUND_ANVIL_BROKEN,
  /**
   * the sound anvil used.
   */
  SOUND_ANVIL_USED,
  /**
   * the sound anvil land.
   */
  SOUND_ANVIL_LAND,
  /**
   * the sound infinity arrow pickup.
   */
  SOUND_INFINITY_ARROW_PICKUP,
  /**
   * the sound teleport enderpearl.
   */
  SOUND_TELEPORT_ENDERPEARL,
  /**
   * the sound itemframe item add.
   */
  SOUND_ITEMFRAME_ITEM_ADD,
  /**
   * the sound itemframe break.
   */
  SOUND_ITEMFRAME_BREAK,
  /**
   * the sound itemframe place.
   */
  SOUND_ITEMFRAME_PLACE,
  /**
   * the sound itemframe item remove.
   */
  SOUND_ITEMFRAME_ITEM_REMOVE,
  /**
   * the sound itemframe item rotate.
   */
  SOUND_ITEMFRAME_ITEM_ROTATE,
  /**
   * the sound camera.
   */
  SOUND_CAMERA,
  /**
   * the sound experience orb pickup.
   */
  SOUND_EXPERIENCE_ORB_PICKUP,
  /**
   * the sound totem used.
   */
  SOUND_TOTEM_USED,
  /**
   * the sound armor stand break.
   */
  SOUND_ARMOR_STAND_BREAK,
  /**
   * the sound armor stand hit.
   */
  SOUND_ARMOR_STAND_HIT,
  /**
   * the sound armor stand land.
   */
  SOUND_ARMOR_STAND_LAND,
  /**
   * the sound armor stand place.
   */
  SOUND_ARMOR_STAND_PLACE,
  /**
   * the sound pointed dripstone land.
   */
  SOUND_POINTED_DRIPSTONE_LAND,
  /**
   * the sound dye used.
   */
  SOUND_DYE_USED,
  /**
   * the sound ink sace used.
   */
  SOUND_INK_SACE_USED,
  /**
   * the particle shoot.
   * <p>
   * particle starts.
   */
  PARTICLE_SHOOT,
  /**
   * the particle destroy block.
   */
  PARTICLE_DESTROY_BLOCK,
  /**
   * the particle potion splash.
   */
  PARTICLE_POTION_SPLASH,
  /**
   * the particle eye of ender death.
   */
  PARTICLE_EYE_OF_ENDER_DEATH,
  /**
   * the particle mob block spawn.
   */
  PARTICLE_MOB_BLOCK_SPAWN,
  /**
   * the particle crop growth.
   */
  PARTICLE_CROP_GROWTH,
  /**
   * the particle sound guardian ghost.
   */
  PARTICLE_SOUND_GUARDIAN_GHOST,
  /**
   * the particle death smoke.
   */
  PARTICLE_DEATH_SMOKE,
  /**
   * the particle deny block.
   */
  PARTICLE_DENY_BLOCK,
  /**
   * the particle generic spawn.
   */
  PARTICLE_GENERIC_SPAWN,
  /**
   * the particle dragon egg.
   */
  PARTICLE_DRAGON_EGG,
  /**
   * the particle crop eaten.
   */
  PARTICLE_CROP_EATEN,
  /**
   * the particle crit.
   */
  PARTICLE_CRIT,
  /**
   * the particle teleport.
   */
  PARTICLE_TELEPORT,
  /**
   * the particle crack block.
   */
  PARTICLE_CRACK_BLOCK,
  /**
   * the particle bubbles.
   */
  PARTICLE_BUBBLES,
  /**
   * the particle evaporate.
   */
  PARTICLE_EVAPORATE,
  /**
   * the particle destroy armor stand.
   */
  PARTICLE_DESTROY_ARMOR_STAND,
  /**
   * the particle breaking egg.
   */
  PARTICLE_BREAKING_EGG,
  /**
   * the particle destroy egg.
   */
  PARTICLE_DESTROY_EGG,
  /**
   * the particle evaporate water.
   */
  PARTICLE_EVAPORATE_WATER,
  /**
   * the particle destroy block no sound.
   */
  PARTICLE_DESTROY_BLOCK_NO_SOUND,
  /**
   * the particle knockback roar.
   */
  PARTICLE_KNOCKBACK_ROAR,
  /**
   * the particle teleport trail.
   */
  PARTICLE_TELEPORT_TRAIL,
  /**
   * the particle point cloud.
   */
  PARTICLE_POINT_CLOUD,
  /**
   * the particle explosion.
   */
  PARTICLE_EXPLOSION,
  /**
   * the particle block explosion.
   */
  PARTICLE_BLOCK_EXPLOSION,
  /**
   * the particles vibration signal.
   */
  PARTICLES_VIBRATION_SIGNAL,
  /**
   * the particle dripstone drip.
   */
  PARTICLE_DRIPSTONE_DRIP,
  /**
   * the particle fizz effect.
   */
  PARTICLE_FIZZ_EFFECT,
  /**
   * the particle wax on.
   */
  PARTICLE_WAX_ON,
  /**
   * the particle wax off.
   */
  PARTICLE_WAX_OFF,
  /**
   * the particle scrape.
   */
  PARTICLE_SCRAPE,
  /**
   * the particles electric spark.
   */
  PARTICLES_ELECTRIC_SPARK,
  /**
   * the particle turtle egg.
   */
  PARTICLE_TURTLE_EGG,
  /**
   * the particle sculk shriek.
   */
  PARTICLE_SCULK_SHRIEK,
  /**
   * the sculk catalyst bloom.
   */
  SCULK_CATALYST_BLOOM,
  /**
   * the start raining.
   * <p>
   * world starts.
   */
  START_RAINING,
  /**
   * the start thunderstorm.
   */
  START_THUNDERSTORM,
  /**
   * the stop raining.
   */
  STOP_RAINING,
  /**
   * the stop thunderstorm.
   */
  STOP_THUNDERSTORM,
  /**
   * the global pause.
   */
  GLOBAL_PAUSE,
  /**
   * the sim time step.
   */
  SIM_TIME_STEP,
  /**
   * the sim time scale.
   */
  SIM_TIME_SCALE,
  /**
   * the activate block.
   * <p>
   * block starts.
   */
  ACTIVATE_BLOCK,
  /**
   * the cauldron explode.
   */
  CAULDRON_EXPLODE,
  /**
   * the cauldron dye armor.
   */
  CAULDRON_DYE_ARMOR,
  /**
   * the cauldron clean armor.
   */
  CAULDRON_CLEAN_ARMOR,
  /**
   * the cauldron fill potion.
   */
  CAULDRON_FILL_POTION,
  /**
   * the cauldron take potion.
   */
  CAULDRON_TAKE_POTION,
  /**
   * the cauldron fill water.
   */
  CAULDRON_FILL_WATER,
  /**
   * the cauldron take water.
   */
  CAULDRON_TAKE_WATER,
  /**
   * the cauldron add dye.
   */
  CAULDRON_ADD_DYE,
  /**
   * the cauldron clean banner.
   */
  CAULDRON_CLEAN_BANNER,
  /**
   * the cauldron flush.
   */
  CAULDRON_FLUSH,
  /**
   * the agent spawn effect.
   */
  AGENT_SPAWN_EFFECT,
  /**
   * the cauldron fill lava.
   */
  CAULDRON_FILL_LAVA,
  /**
   * the cauldron take lava.
   */
  CAULDRON_TAKE_LAVA,
  /**
   * the cauldron fill powder snow.
   */
  CAULDRON_FILL_POWDER_SNOW,
  /**
   * the cauldron take powder snow.
   */
  CAULDRON_TAKE_POWDER_SNOW,
  /**
   * the block start break.
   * <p>
   * breaking starts
   */
  BLOCK_START_BREAK,
  /**
   * the block stop break.
   */
  BLOCK_STOP_BREAK,
  /**
   * the block update break.
   */
  BLOCK_UPDATE_BREAK,
  /**
   * the set data.
   * <p>
   * other starts
   */
  SET_DATA,
  /**
   * the all players sleeping.
   */
  ALL_PLAYERS_SLEEPING,
  /**
   * the sleeping players.
   */
  SLEEPING_PLAYERS,
  /**
   * the jump prevented.
   */
  JUMP_PREVENTED
}
