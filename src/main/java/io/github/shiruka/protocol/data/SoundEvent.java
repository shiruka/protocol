package io.github.shiruka.protocol.data;

/**
 * an enum class that contains sound events.
 */
public enum SoundEvent {
  /**
   * the item use on.
   */
  ITEM_USE_ON,
  /**
   * the hit.
   */
  HIT,
  /**
   * the step.
   */
  STEP,
  /**
   * the fly.
   */
  FLY,
  /**
   * the jump.
   */
  JUMP,
  /**
   * the break.
   */
  BREAK,
  /**
   * the place.
   */
  PLACE,
  /**
   * the heavy step.
   */
  HEAVY_STEP,
  /**
   * the gallop.
   */
  GALLOP,
  /**
   * the fall.
   */
  FALL,
  /**
   * the ambient.
   */
  AMBIENT,
  /**
   * the ambient baby.
   */
  AMBIENT_BABY,
  /**
   * the ambient in water.
   */
  AMBIENT_IN_WATER,
  /**
   * the breathe.
   */
  BREATHE,
  /**
   * the death.
   */
  DEATH,
  /**
   * the death in water.
   */
  DEATH_IN_WATER,
  /**
   * the death to zombie.
   */
  DEATH_TO_ZOMBIE,
  /**
   * the hurt.
   */
  HURT,
  /**
   * the hurt in water.
   */
  HURT_IN_WATER,
  /**
   * the mad.
   */
  MAD,
  /**
   * the boost.
   */
  BOOST,
  /**
   * the bow.
   */
  BOW,
  /**
   * the squish big.
   */
  SQUISH_BIG,
  /**
   * the squish small.
   */
  SQUISH_SMALL,
  /**
   * the fall big.
   */
  FALL_BIG,
  /**
   * the fall small.
   */
  FALL_SMALL,
  /**
   * the splash.
   */
  SPLASH,
  /**
   * the fizz.
   */
  FIZZ,
  /**
   * the flap.
   */
  FLAP,
  /**
   * the swim.
   */
  SWIM,
  /**
   * the drink.
   */
  DRINK,
  /**
   * the eat.
   */
  EAT,
  /**
   * the takeoff.
   */
  TAKEOFF,
  /**
   * the shake.
   */
  SHAKE,
  /**
   * the plop.
   */
  PLOP,
  /**
   * the land.
   */
  LAND,
  /**
   * the saddle.
   */
  SADDLE,
  /**
   * the armor.
   */
  ARMOR,
  /**
   * the mob armor stand place.
   */
  MOB_ARMOR_STAND_PLACE,
  /**
   * the add chest.
   */
  ADD_CHEST,
  /**
   * the throw.
   */
  THROW,
  /**
   * the attack.
   */
  ATTACK,
  /**
   * the attack nodamage.
   */
  ATTACK_NODAMAGE,
  /**
   * the attack strong.
   */
  ATTACK_STRONG,
  /**
   * the warn.
   */
  WARN,
  /**
   * the shear.
   */
  SHEAR,
  /**
   * the milk.
   */
  MILK,
  /**
   * the thunder.
   */
  THUNDER,
  /**
   * the explode.
   */
  EXPLODE,
  /**
   * the fire.
   */
  FIRE,
  /**
   * the ignite.
   */
  IGNITE,
  /**
   * the fuse.
   */
  FUSE,
  /**
   * the stare.
   */
  STARE,
  /**
   * the spawn.
   */
  SPAWN,
  /**
   * the shoot.
   */
  SHOOT,
  /**
   * the break block.
   */
  BREAK_BLOCK,
  /**
   * the launch.
   */
  LAUNCH,
  /**
   * the blast.
   */
  BLAST,
  /**
   * the large blast.
   */
  LARGE_BLAST,
  /**
   * the twinkle.
   */
  TWINKLE,
  /**
   * the remedy.
   */
  REMEDY,
  /**
   * the unfect.
   */
  UNFECT,
  /**
   * the levelup.
   */
  LEVELUP,
  /**
   * the bow hit.
   */
  BOW_HIT,
  /**
   * the bullet hit.
   */
  BULLET_HIT,
  /**
   * the extinguish fire.
   */
  EXTINGUISH_FIRE,
  /**
   * the item fizz.
   */
  ITEM_FIZZ,
  /**
   * the chest open.
   */
  CHEST_OPEN,
  /**
   * the chest closed.
   */
  CHEST_CLOSED,
  /**
   * the shulkerbox open.
   */
  SHULKERBOX_OPEN,
  /**
   * the shulkerbox closed.
   */
  SHULKERBOX_CLOSED,
  /**
   * the enderchest open.
   */
  ENDERCHEST_OPEN,
  /**
   * the enderchest closed.
   */
  ENDERCHEST_CLOSED,
  /**
   * the power on.
   */
  POWER_ON,
  /**
   * the power off.
   */
  POWER_OFF,
  /**
   * the attach.
   */
  ATTACH,
  /**
   * the detach.
   */
  DETACH,
  /**
   * the deny.
   */
  DENY,
  /**
   * the tripod.
   */
  TRIPOD,
  /**
   * the pop.
   */
  POP,
  /**
   * the drop slot.
   */
  DROP_SLOT,
  /**
   * the note.
   */
  NOTE,
  /**
   * the thorns.
   */
  THORNS,
  /**
   * the piston in.
   */
  PISTON_IN,
  /**
   * the piston out.
   */
  PISTON_OUT,
  /**
   * the portal.
   */
  PORTAL,
  /**
   * the water.
   */
  WATER,
  /**
   * the lava pop.
   */
  LAVA_POP,
  /**
   * the lava.
   */
  LAVA,
  /**
   * the burp.
   */
  BURP,
  /**
   * the bucket fill water.
   */
  BUCKET_FILL_WATER,
  /**
   * the bucket fill lava.
   */
  BUCKET_FILL_LAVA,
  /**
   * the bucket empty water.
   */
  BUCKET_EMPTY_WATER,
  /**
   * the bucket empty lava.
   */
  BUCKET_EMPTY_LAVA,
  /**
   * the armor equip chain.
   */
  ARMOR_EQUIP_CHAIN,
  /**
   * the armor equip diamond.
   */
  ARMOR_EQUIP_DIAMOND,
  /**
   * the armor equip generic.
   */
  ARMOR_EQUIP_GENERIC,
  /**
   * the armor equip gold.
   */
  ARMOR_EQUIP_GOLD,
  /**
   * the armor equip iron.
   */
  ARMOR_EQUIP_IRON,
  /**
   * the armor equip leather.
   */
  ARMOR_EQUIP_LEATHER,
  /**
   * the armor equip elytra.
   */
  ARMOR_EQUIP_ELYTRA,
  /**
   * the record 13.
   */
  RECORD_13,
  /**
   * the record cat.
   */
  RECORD_CAT,
  /**
   * the record blocks.
   */
  RECORD_BLOCKS,
  /**
   * the record chirp.
   */
  RECORD_CHIRP,
  /**
   * the record far.
   */
  RECORD_FAR,
  /**
   * the record mall.
   */
  RECORD_MALL,
  /**
   * the record mellohi.
   */
  RECORD_MELLOHI,
  /**
   * the record stal.
   */
  RECORD_STAL,
  /**
   * the record strad.
   */
  RECORD_STRAD,
  /**
   * the record ward.
   */
  RECORD_WARD,
  /**
   * the record 11.
   */
  RECORD_11,
  /**
   * the record wait.
   */
  RECORD_WAIT,
  /**
   * the stop record.
   */
  STOP_RECORD,
  /**
   * the flop.
   */
  FLOP,
  /**
   * the elderguardian curse.
   */
  ELDERGUARDIAN_CURSE,
  /**
   * the mob warning.
   */
  MOB_WARNING,
  /**
   * the mob warning baby.
   */
  MOB_WARNING_BABY,
  /**
   * the teleport.
   */
  TELEPORT,
  /**
   * the shulker open.
   */
  SHULKER_OPEN,
  /**
   * the shulker close.
   */
  SHULKER_CLOSE,
  /**
   * the haggle.
   */
  HAGGLE,
  /**
   * the haggle yes.
   */
  HAGGLE_YES,
  /**
   * the haggle no.
   */
  HAGGLE_NO,
  /**
   * the haggle idle.
   */
  HAGGLE_IDLE,
  /**
   * the chorus grow.
   */
  CHORUS_GROW,
  /**
   * the chorus death.
   */
  CHORUS_DEATH,
  /**
   * the glass.
   */
  GLASS,
  /**
   * the potion brewed.
   */
  POTION_BREWED,
  /**
   * the cast spell.
   */
  CAST_SPELL,
  /**
   * the prepare attack.
   */
  PREPARE_ATTACK,
  /**
   * the prepare summon.
   */
  PREPARE_SUMMON,
  /**
   * the prepare wololo.
   */
  PREPARE_WOLOLO,
  /**
   * the fang.
   */
  FANG,
  /**
   * the charge.
   */
  CHARGE,
  /**
   * the camera take picture.
   */
  CAMERA_TAKE_PICTURE,
  /**
   * the leashknot place.
   */
  LEASHKNOT_PLACE,
  /**
   * the leashknot break.
   */
  LEASHKNOT_BREAK,
  /**
   * the growl.
   */
  GROWL,
  /**
   * the whine.
   */
  WHINE,
  /**
   * the pant.
   */
  PANT,
  /**
   * the purr.
   */
  PURR,
  /**
   * the purreow.
   */
  PURREOW,
  /**
   * the death min volume.
   */
  DEATH_MIN_VOLUME,
  /**
   * the death mid volume.
   */
  DEATH_MID_VOLUME,
  /**
   * the imitate blaze.
   */
  IMITATE_BLAZE,
  /**
   * the imitate cave spider.
   */
  IMITATE_CAVE_SPIDER,
  /**
   * the imitate creeper.
   */
  IMITATE_CREEPER,
  /**
   * the imitate elder guardian.
   */
  IMITATE_ELDER_GUARDIAN,
  /**
   * the imitate ender dragon.
   */
  IMITATE_ENDER_DRAGON,
  /**
   * the imitate enderman.
   */
  IMITATE_ENDERMAN,
  /**
   * the imitate evocation illager.
   */
  IMITATE_EVOCATION_ILLAGER,
  /**
   * the imitate ghast.
   */
  IMITATE_GHAST,
  /**
   * the imitate husk.
   */
  IMITATE_HUSK,
  /**
   * the imitate illusion illager.
   */
  IMITATE_ILLUSION_ILLAGER,
  /**
   * the imitate magma cube.
   */
  IMITATE_MAGMA_CUBE,
  /**
   * the imitate polar bear.
   */
  IMITATE_POLAR_BEAR,
  /**
   * the imitate shulker.
   */
  IMITATE_SHULKER,
  /**
   * the imitate silverfish.
   */
  IMITATE_SILVERFISH,
  /**
   * the imitate skeleton.
   */
  IMITATE_SKELETON,
  /**
   * the imitate slime.
   */
  IMITATE_SLIME,
  /**
   * the imitate spider.
   */
  IMITATE_SPIDER,
  /**
   * the imitate stray.
   */
  IMITATE_STRAY,
  /**
   * the imitate vex.
   */
  IMITATE_VEX,
  /**
   * the imitate vindication illager.
   */
  IMITATE_VINDICATION_ILLAGER,
  /**
   * the imitate witch.
   */
  IMITATE_WITCH,
  /**
   * the imitate wither.
   */
  IMITATE_WITHER,
  /**
   * the imitate wither skeleton.
   */
  IMITATE_WITHER_SKELETON,
  /**
   * the imitate wolf.
   */
  IMITATE_WOLF,
  /**
   * the imitate zombie.
   */
  IMITATE_ZOMBIE,
  /**
   * the imitate zombie pigman.
   */
  IMITATE_ZOMBIE_PIGMAN,
  /**
   * the imitate zombie villager.
   */
  IMITATE_ZOMBIE_VILLAGER,
  /**
   * the block end portal frame fill.
   */
  BLOCK_END_PORTAL_FRAME_FILL,
  /**
   * the block end portal spawn.
   */
  BLOCK_END_PORTAL_SPAWN,
  /**
   * the random anvil use.
   */
  RANDOM_ANVIL_USE,
  /**
   * the bottle dragonbreath.
   */
  BOTTLE_DRAGONBREATH,
  /**
   * the portal travel.
   */
  PORTAL_TRAVEL,
  /**
   * the item trident hit.
   */
  ITEM_TRIDENT_HIT,
  /**
   * the item trident return.
   */
  ITEM_TRIDENT_RETURN,
  /**
   * the item trident riptide 1.
   */
  ITEM_TRIDENT_RIPTIDE_1,
  /**
   * the item trident riptide 2.
   */
  ITEM_TRIDENT_RIPTIDE_2,
  /**
   * the item trident riptide 3.
   */
  ITEM_TRIDENT_RIPTIDE_3,
  /**
   * the item trident throw.
   */
  ITEM_TRIDENT_THROW,
  /**
   * the item trident thunder.
   */
  ITEM_TRIDENT_THUNDER,
  /**
   * the item trident hit ground.
   */
  ITEM_TRIDENT_HIT_GROUND,
  /**
   * the default.
   */
  DEFAULT,
  /**
   * the element constructor open.
   */
  ELEMENT_CONSTRUCTOR_OPEN,
  /**
   * the fletching table use.
   */
  FLETCHING_TABLE_USE,
  /**
   * the ice bomb hit.
   */
  ICE_BOMB_HIT,
  /**
   * the balloon pop.
   */
  BALLOON_POP,
  /**
   * the lt reaction ice bomb.
   */
  LT_REACTION_ICE_BOMB,
  /**
   * the lt reaction bleach.
   */
  LT_REACTION_BLEACH,
  /**
   * the lt reaction e paste.
   */
  LT_REACTION_E_PASTE,
  /**
   * the lt reaction e paste2.
   */
  LT_REACTION_E_PASTE2,
  /**
   * the lt reaction fertilizer.
   */
  LT_REACTION_FERTILIZER,
  /**
   * the lt reaction fireball.
   */
  LT_REACTION_FIREBALL,
  /**
   * the lt reaction mg salt.
   */
  LT_REACTION_MG_SALT,
  /**
   * the lt reaction misc fire.
   */
  LT_REACTION_MISC_FIRE,
  /**
   * the lt reaction fire.
   */
  LT_REACTION_FIRE,
  /**
   * the lt reaction misc explosion.
   */
  LT_REACTION_MISC_EXPLOSION,
  /**
   * the lt reaction misc mystical.
   */
  LT_REACTION_MISC_MYSTICAL,
  /**
   * the lt reaction misc mystical2.
   */
  LT_REACTION_MISC_MYSTICAL2,
  /**
   * the lt reaction product.
   */
  LT_REACTION_PRODUCT,
  /**
   * the sparkler use.
   */
  SPARKLER_USE,
  /**
   * the glowstick use.
   */
  GLOWSTICK_USE,
  /**
   * the sparkler active.
   */
  SPARKLER_ACTIVE,
  /**
   * the convert to drowned.
   */
  CONVERT_TO_DROWNED,
  /**
   * the bucket fill fish.
   */
  BUCKET_FILL_FISH,
  /**
   * the bucket empty fish.
   */
  BUCKET_EMPTY_FISH,
  /**
   * the bubble up.
   */
  BUBBLE_UP,
  /**
   * the bubble down.
   */
  BUBBLE_DOWN,
  /**
   * the bubble pop.
   */
  BUBBLE_POP,
  /**
   * the bubble up inside.
   */
  BUBBLE_UP_INSIDE,
  /**
   * the bubble down inside.
   */
  BUBBLE_DOWN_INSIDE,
  /**
   * the baby hurt.
   */
  BABY_HURT,
  /**
   * the baby death.
   */
  BABY_DEATH,
  /**
   * the baby step.
   */
  BABY_STEP,
  /**
   * the baby spawn.
   */
  BABY_SPAWN,
  /**
   * the born.
   */
  BORN,
  /**
   * the block turtle egg break.
   */
  BLOCK_TURTLE_EGG_BREAK,
  /**
   * the block turtle egg crack.
   */
  BLOCK_TURTLE_EGG_CRACK,
  /**
   * the block turtle egg hatch.
   */
  BLOCK_TURTLE_EGG_HATCH,
  /**
   * the turtle lay egg.
   */
  TURTLE_LAY_EGG,
  /**
   * the block turtle egg attack.
   */
  BLOCK_TURTLE_EGG_ATTACK,
  /**
   * the beacon activate.
   */
  BEACON_ACTIVATE,
  /**
   * the beacon ambient.
   */
  BEACON_AMBIENT,
  /**
   * the beacon deactivate.
   */
  BEACON_DEACTIVATE,
  /**
   * the beacon power.
   */
  BEACON_POWER,
  /**
   * the conduit activate.
   */
  CONDUIT_ACTIVATE,
  /**
   * the conduit ambient.
   */
  CONDUIT_AMBIENT,
  /**
   * the conduit attack.
   */
  CONDUIT_ATTACK,
  /**
   * the conduit deactivate.
   */
  CONDUIT_DEACTIVATE,
  /**
   * the conduit short.
   */
  CONDUIT_SHORT,
  /**
   * the swoop.
   */
  SWOOP,
  /**
   * the block bamboo sapling place.
   */
  BLOCK_BAMBOO_SAPLING_PLACE,
  /**
   * the pre sneeze.
   */
  PRE_SNEEZE,
  /**
   * the sneeze.
   */
  SNEEZE,
  /**
   * the ambient tame.
   */
  AMBIENT_TAME,
  /**
   * the scared.
   */
  SCARED,
  /**
   * the block scaffolding climb.
   */
  BLOCK_SCAFFOLDING_CLIMB,
  /**
   * the crossbow loading start.
   */
  CROSSBOW_LOADING_START,
  /**
   * the crossbow loading middle.
   */
  CROSSBOW_LOADING_MIDDLE,
  /**
   * the crossbow loading end.
   */
  CROSSBOW_LOADING_END,
  /**
   * the crossbow shoot.
   */
  CROSSBOW_SHOOT,
  /**
   * the crossbow quick charge start.
   */
  CROSSBOW_QUICK_CHARGE_START,
  /**
   * the crossbow quick charge middle.
   */
  CROSSBOW_QUICK_CHARGE_MIDDLE,
  /**
   * the crossbow quick charge end.
   */
  CROSSBOW_QUICK_CHARGE_END,
  /**
   * the ambient aggressive.
   */
  AMBIENT_AGGRESSIVE,
  /**
   * the ambient worried.
   */
  AMBIENT_WORRIED,
  /**
   * the cant breed.
   */
  CANT_BREED,
  /**
   * the shield block.
   */
  SHIELD_BLOCK,
  /**
   * the lectern book place.
   */
  LECTERN_BOOK_PLACE,
  /**
   * the grindstone use.
   */
  GRINDSTONE_USE,
  /**
   * the bell.
   */
  BELL,
  /**
   * the campfire crackle.
   */
  CAMPFIRE_CRACKLE,
  /**
   * the sweet berry bush hurt.
   */
  SWEET_BERRY_BUSH_HURT,
  /**
   * the sweet berry bush pick.
   */
  SWEET_BERRY_BUSH_PICK,
  /**
   * the roar.
   */
  ROAR,
  /**
   * the stun.
   */
  STUN,
  /**
   * the cartography table use.
   */
  CARTOGRAPHY_TABLE_USE,
  /**
   * the table use.
   */
  TABLE_USE,
  /**
   * the stonecutter use.
   */
  STONECUTTER_USE,
  /**
   * the composter empty.
   */
  COMPOSTER_EMPTY,
  /**
   * the composter fill.
   */
  COMPOSTER_FILL,
  /**
   * the composter fill layer.
   */
  COMPOSTER_FILL_LAYER,
  /**
   * the composter ready.
   */
  COMPOSTER_READY,
  /**
   * the barrel open.
   */
  BARREL_OPEN,
  /**
   * the barrel close.
   */
  BARREL_CLOSE,
  /**
   * the raid horn.
   */
  RAID_HORN,
  /**
   * the loom use.
   */
  LOOM_USE,
  /**
   * the ambient in raid.
   */
  AMBIENT_IN_RAID,
  /**
   * the ui cartography table use.
   */
  UI_CARTOGRAPHY_TABLE_USE,
  /**
   * the ui stonecutter use.
   */
  UI_STONECUTTER_USE,
  /**
   * the ui loom use.
   */
  UI_LOOM_USE,
  /**
   * the smoker use.
   */
  SMOKER_USE,
  /**
   * the blast furnace use.
   */
  BLAST_FURNACE_USE,
  /**
   * the smithing table use.
   */
  SMITHING_TABLE_USE,
  /**
   * the screech.
   */
  SCREECH,
  /**
   * the sleep.
   */
  SLEEP,
  /**
   * the furnace use.
   */
  FURNACE_USE,
  /**
   * the mooshroom convert.
   */
  MOOSHROOM_CONVERT,
  /**
   * the milk suspiciously.
   */
  MILK_SUSPICIOUSLY,
  /**
   * the celebrate.
   */
  CELEBRATE,
  /**
   * the jump prevent.
   */
  JUMP_PREVENT,
  /**
   * the ambient pollinate.
   */
  AMBIENT_POLLINATE,
  /**
   * the beehive drip.
   */
  BEEHIVE_DRIP,
  /**
   * the beehive enter.
   */
  BEEHIVE_ENTER,
  /**
   * the beehive exit.
   */
  BEEHIVE_EXIT,
  /**
   * the beehive work.
   */
  BEEHIVE_WORK,
  /**
   * the beehive shear.
   */
  BEEHIVE_SHEAR,
  /**
   * the honeybottle drink.
   */
  HONEYBOTTLE_DRINK,
  /**
   * the ambient cave.
   */
  AMBIENT_CAVE,
  /**
   * the retreat.
   */
  RETREAT,
  /**
   * the convert to zombified.
   */
  CONVERT_TO_ZOMBIFIED,
  /**
   * the admire.
   */
  ADMIRE,
  /**
   * the step lava.
   */
  STEP_LAVA,
  /**
   * the tempt.
   */
  TEMPT,
  /**
   * the panic.
   */
  PANIC,
  /**
   * the angry.
   */
  ANGRY,
  /**
   * the ambient warped forest.
   */
  AMBIENT_WARPED_FOREST,
  /**
   * the ambient soulsand valley.
   */
  AMBIENT_SOULSAND_VALLEY,
  /**
   * the ambient nether wastes.
   */
  AMBIENT_NETHER_WASTES,
  /**
   * the ambient basalt deltas.
   */
  AMBIENT_BASALT_DELTAS,
  /**
   * the ambient crimson forest.
   */
  AMBIENT_CRIMSON_FOREST,
  /**
   * the respawn anchor charge.
   */
  RESPAWN_ANCHOR_CHARGE,
  /**
   * the respawn anchor deplete.
   */
  RESPAWN_ANCHOR_DEPLETE,
  /**
   * the respawn anchor set spawn.
   */
  RESPAWN_ANCHOR_SET_SPAWN,
  /**
   * the respawn anchor ambient.
   */
  RESPAWN_ANCHOR_AMBIENT,
  /**
   * the soul escape quiet.
   */
  SOUL_ESCAPE_QUIET,
  /**
   * the soul escape loud.
   */
  SOUL_ESCAPE_LOUD,
  /**
   * the record pigstep.
   */
  RECORD_PIGSTEP,
  /**
   * the link compass to lodestone.
   */
  LINK_COMPASS_TO_LODESTONE,
  /**
   * the use smithing table.
   */
  USE_SMITHING_TABLE,
  /**
   * the equip netherite.
   */
  EQUIP_NETHERITE,
  /**
   * the ambient loop warped forest.
   */
  AMBIENT_LOOP_WARPED_FOREST,
  /**
   * the ambient loop soulsand valley.
   */
  AMBIENT_LOOP_SOULSAND_VALLEY,
  /**
   * the ambient loop nether wastes.
   */
  AMBIENT_LOOP_NETHER_WASTES,
  /**
   * the ambient loop basalt deltas.
   */
  AMBIENT_LOOP_BASALT_DELTAS,
  /**
   * the ambient loop crimson forest.
   */
  AMBIENT_LOOP_CRIMSON_FOREST,
  /**
   * the ambient addition warped forest.
   */
  AMBIENT_ADDITION_WARPED_FOREST,
  /**
   * the ambient addition soulsand valley.
   */
  AMBIENT_ADDITION_SOULSAND_VALLEY,
  /**
   * the ambient addition nether wastes.
   */
  AMBIENT_ADDITION_NETHER_WASTES,
  /**
   * the ambient addition basalt deltas.
   */
  AMBIENT_ADDITION_BASALT_DELTAS,
  /**
   * the ambient addition crimson forest.
   */
  AMBIENT_ADDITION_CRIMSON_FOREST,
  /**
   * the sculk sensor power on.
   */
  SCULK_SENSOR_POWER_ON,
  /**
   * the sculk sensor power off.
   */
  SCULK_SENSOR_POWER_OFF,
  /**
   * the bucket fill powder snow.
   */
  BUCKET_FILL_POWDER_SNOW,
  /**
   * the bucket empty powder snow.
   */
  BUCKET_EMPTY_POWDER_SNOW,
  /**
   * the pointed dripstone cauldron drip water.
   */
  POINTED_DRIPSTONE_CAULDRON_DRIP_WATER,
  /**
   * the pointed dripstone cauldron drip lava.
   */
  POINTED_DRIPSTONE_CAULDRON_DRIP_LAVA,
  /**
   * the pointed dripstone drip water.
   */
  POINTED_DRIPSTONE_DRIP_WATER,
  /**
   * the pointed dripstone drip lava.
   */
  POINTED_DRIPSTONE_DRIP_LAVA,
  /**
   * the cave vines pick berries.
   */
  CAVE_VINES_PICK_BERRIES,
  /**
   * the big dripleaf tilt down.
   */
  BIG_DRIPLEAF_TILT_DOWN,
  /**
   * the big dripleaf tilt up.
   */
  BIG_DRIPLEAF_TILT_UP,
  /**
   * the copper wax on.
   */
  COPPER_WAX_ON,
  /**
   * the copper wax off.
   */
  COPPER_WAX_OFF,
  /**
   * the scrape.
   */
  SCRAPE,
  /**
   * the player hurt drown.
   */
  PLAYER_HURT_DROWN,
  /**
   * the player hurt on fire.
   */
  PLAYER_HURT_ON_FIRE,
  /**
   * the player hurt freeze.
   */
  PLAYER_HURT_FREEZE,
  /**
   * the use spyglass.
   */
  USE_SPYGLASS,
  /**
   * the stop using spyglass.
   */
  STOP_USING_SPYGLASS,
  /**
   * the amethyst block chime.
   */
  AMETHYST_BLOCK_CHIME,
  /**
   * the ambient screamer.
   */
  AMBIENT_SCREAMER,
  /**
   * the hurt screamer.
   */
  HURT_SCREAMER,
  /**
   * the death screamer.
   */
  DEATH_SCREAMER,
  /**
   * the milk screamer.
   */
  MILK_SCREAMER,
  /**
   * the jump to block.
   */
  JUMP_TO_BLOCK,
  /**
   * the pre ram.
   */
  PRE_RAM,
  /**
   * the pre ram screamer.
   */
  PRE_RAM_SCREAMER,
  /**
   * the ram impact.
   */
  RAM_IMPACT,
  /**
   * the ram impact screamer.
   */
  RAM_IMPACT_SCREAMER,
  /**
   * the squid ink squirt.
   */
  SQUID_INK_SQUIRT,
  /**
   * the glow squid ink squirt.
   */
  GLOW_SQUID_INK_SQUIRT,
  /**
   * the convert to stray.
   */
  CONVERT_TO_STRAY,
  /**
   * the cake add candle.
   */
  CAKE_ADD_CANDLE,
  /**
   * the extinguish candle.
   */
  EXTINGUISH_CANDLE,
  /**
   * the ambient candle.
   */
  AMBIENT_CANDLE,
  /**
   * the block click.
   */
  BLOCK_CLICK,
  /**
   * the block click fail.
   */
  BLOCK_CLICK_FAIL,
  /**
   * the sculk shrieker shriek.
   */
  SCULK_SHRIEKER_SHRIEK,
  /**
   * the warden nearby close.
   */
  WARDEN_NEARBY_CLOSE,
  /**
   * the warden nearby closer.
   */
  WARDEN_NEARBY_CLOSER,
  /**
   * the warden nearby closest.
   */
  WARDEN_NEARBY_CLOSEST,
  /**
   * the warden slightly angry.
   */
  WARDEN_SLIGHTLY_ANGRY,
  /**
   * the sculk catalyst bloom.
   */
  SCULK_CATALYST_BLOOM,
  /**
   * the record otherside.
   */
  RECORD_OTHERSIDE,
  /**
   * the undefined.
   */
  UNDEFINED,
}
