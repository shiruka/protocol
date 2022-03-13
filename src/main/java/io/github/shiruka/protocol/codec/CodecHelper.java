package io.github.shiruka.protocol.codec;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.CommonLevelEvent;
import io.github.shiruka.protocol.data.EntityEventType;
import io.github.shiruka.protocol.data.GameRuleValue;
import io.github.shiruka.protocol.data.ItemDefinition;
import io.github.shiruka.protocol.data.SoundEvent;
import io.github.shiruka.protocol.data.command.CommandData;
import io.github.shiruka.protocol.data.command.CommandEnumData;
import io.github.shiruka.protocol.data.command.CommandParam;
import io.github.shiruka.protocol.data.entity.EntityData;
import io.github.shiruka.protocol.data.entity.EntityDataMap;
import io.github.shiruka.protocol.data.entity.EntityDataType;
import io.github.shiruka.protocol.data.entity.EntityFlag;
import io.github.shiruka.protocol.data.entity.EntityLinkData;
import io.github.shiruka.protocol.data.inventory.ItemData;
import io.github.shiruka.protocol.packets.AdventureSettings;
import io.github.shiruka.protocol.packets.BookEdit;
import io.github.shiruka.protocol.packets.BossEvent;
import io.github.shiruka.protocol.packets.ResourcePackInfo;
import io.github.shiruka.protocol.packets.ResourcePackStack;
import io.github.shiruka.protocol.packets.StartGame;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine codec helpers.
 */
public interface CodecHelper {

  /**
   * the log.
   */
  Logger LOG = LogManager.getLogger(CodecHelper.class);

  /**
   * obtains the book edit types.
   *
   * @return book edit types.
   */
  @NotNull
  IntTypeMap<BookEdit.Action> bookEditTypes();

  /**
   * gets command parameters by id.
   *
   * @param id the id to get.
   *
   * @return command parameter.
   */
  @NotNull
  default CommandParam commandParam(final int id) {
    final var commandParam = this.commandParameters().type(id);
    if (commandParam == null) {
      CodecHelper.LOG.debug("Requested undefined CommandParam {}", id);
      return new CommandParam(id);
    }
    return commandParam;
  }

  /**
   * gets parameter id.
   *
   * @param param the param to get.
   *
   * @return parameter id.
   */
  default int commandParamId(@NotNull final CommandParam param) {
    return this.commandParameters().id(param);
  }

  /**
   * obtains the command parameters.
   *
   * @return command parameters.
   */
  @NotNull
  IntTypeMap<CommandParam> commandParameters();

  /**
   * obtains the entity data.
   *
   * @return entity data.
   */
  @NotNull
  IntTypeMap<EntityData> entityData();

  /**
   * obtains the entity data types.
   *
   * @return entity data types.
   */
  @NotNull
  IntTypeMap<EntityDataType> entityDataTypes();

  /**
   * obtains the entity event types.
   *
   * @return entity event types.
   */
  @NotNull
  IntTypeMap<EntityEventType> entityEventTypes();

  /**
   * obtains the entity flags.
   *
   * @return entity flags.
   */
  @NotNull
  IntTypeMap<EntityFlag> entityFlags();

  /**
   * obtains the game rule type.
   *
   * @return game rule type.
   */
  @NotNull
  IntTypeMap<Class<?>> gameRuleTypes();

  /**
   * obtains the item definitions.
   *
   * @return item definitions.
   */
  @NotNull
  IdentifierDefinitionRegistry<ItemDefinition> itemDefinitions();

  /**
   * obtains the level events.
   *
   * @return level events.
   */
  @NotNull
  IntTypeMap<CommonLevelEvent> levelEvents();

  /**
   * reads the adventure settings and write into the packet.
   *
   * @param packet the packet to read.
   * @param buffer the buffer to read.
   */
  void readAdventureSettings(@NotNull AdventureSettings packet, @NotNull PacketBuffer buffer);

  /**
   * reads the attribute.
   *
   * @param buffer the buffer to read.
   * @param session the session to read.
   *
   * @return attribute.
   */
  @NotNull
  AttributeData readAttribute(@NotNull PacketBuffer buffer, @NotNull MinecraftSession session);

  /**
   * reads the boss event action.
   *
   * @param packet the packet to read.
   * @param buffer the buffer to read.
   */
  void readBossEventAction(@NotNull BossEvent packet, @NotNull PacketBuffer buffer);

  /**
   * reads the command enum.
   *
   * @param buffer the buffer to read.
   * @param soft the soft to read.
   *
   * @return command enum data.
   */
  @NotNull
  CommandEnumData readCommandEnum(@NotNull PacketBuffer buffer, boolean soft);

  /**
   * reads the commands.
   *
   * @param buffer the buffer to read.
   *
   * @return command data.
   */
  @NotNull
  List<CommandData> readCommands(@NotNull PacketBuffer buffer);

  /**
   * reads the entity data.
   *
   * @param buffer the buffer to read.
   * @param session the session to read.
   * @param map the entity data to read.
   */
  void readEntityData(@NotNull PacketBuffer buffer, @NotNull MinecraftSession session, @NotNull EntityDataMap map);

  /**
   * reads the entity link.
   *
   * @param buffer the buffer to read.
   * @param session the session to read.
   *
   * @return entity link data.
   */
  @NotNull
  EntityLinkData readEntityLink(@NotNull PacketBuffer buffer, @NotNull MinecraftSession session);

  /**
   * reads the game rule value.
   *
   * @param buffer the buffer to read.
   *
   * @return game rule value.
   */
  @NotNull
  GameRuleValue readGameRule(@NotNull PacketBuffer buffer);

  /**
   * reads the item.
   *
   * @param buffer the buffer to read.
   * @param session the session to read.
   *
   * @return item data.
   */
  @NotNull
  ItemData readItem(@NotNull PacketBuffer buffer, @NotNull MinecraftSession session);

  /**
   * reads the buffer to write the level settings to the start game packet.
   *
   * @param packet the packet to read.
   * @param buffer the buffer to read.
   */
  void readLevelSettings(@NotNull StartGame packet, @NotNull PacketBuffer buffer);

  /**
   * reads the resource pack info entry.
   *
   * @param buffer the buffer to read.
   *
   * @return resource pack info entry.
   */
  @NotNull
  ResourcePackInfo.Entry readResourcePackInfoEntry(@NotNull PacketBuffer buffer);

  /**
   * reads the resource pack stack entry.
   *
   * @param buffer the buffer to read.
   *
   * @return resource pack stack entry.
   */
  @NotNull
  ResourcePackStack.Entry readResourcePackStackEntry(@NotNull PacketBuffer buffer);

  /**
   * obtains the sound events.
   *
   * @return sound events.
   */
  @NotNull
  IntTypeMap<SoundEvent> soundEvents();

  /**
   * writes the adventure settings packet to buffer.
   *
   * @param packet the packet to write.
   * @param buffer the buffer to write.
   */
  void writeAdventureSettings(@NotNull AdventureSettings packet, @NotNull PacketBuffer buffer);

  /**
   * writes the attribute data.
   *
   * @param buffer the buffer to write.
   * @param data the data to write.
   */
  void writeAttribute(@NotNull PacketBuffer buffer, @NotNull AttributeData data);

  /**
   * writes the boss event.
   *
   * @param packet the packet to write.
   * @param buffer the buffer to write.
   */
  void writeBossEventAction(@NotNull BossEvent packet, @NotNull PacketBuffer buffer);

  /**
   * writes the command enum.
   *
   * @param buffer the buffer to write.
   * @param data the data to write.
   */
  void writeCommandEnum(@NotNull PacketBuffer buffer, @NotNull CommandEnumData data);

  /**
   * writes the commands.
   *
   * @param buffer the buffer to write.
   * @param commands the commands to write.
   */
  void writeCommands(@NotNull PacketBuffer buffer, @NotNull List<CommandData> commands);

  /**
   * writes the entity data.
   *
   * @param buffer the buffer to write.
   * @param session the session to write.
   * @param map the map to write.
   */
  void writeEntityData(@NotNull PacketBuffer buffer, @NotNull MinecraftSession session, @NotNull EntityDataMap map);

  /**
   * writes the entity link.
   *
   * @param buffer the buffer to write.
   * @param session the session to write.
   * @param link the link to write.
   */
  void writeEntityLink(@NotNull PacketBuffer buffer, @NotNull MinecraftSession session, @NotNull EntityLinkData link);

  /**
   * writes the game rule to buffer.
   *
   * @param buffer the buffer to write.
   * @param gameRule the game rule to write.
   */
  void writeGameRule(@NotNull PacketBuffer buffer, @NotNull GameRuleValue gameRule);

  /**
   * writes the item.
   *
   * @param buffer the buffer to write.
   * @param session the session to write.
   * @param item the item to write.
   */
  void writeItem(@NotNull PacketBuffer buffer, @NotNull MinecraftSession session, @NotNull ItemData item);

  /**
   * writes the level settings.
   *
   * @param packet the packet to write.
   * @param buffer the buffer to write.
   */
  void writeLevelSettings(@NotNull StartGame packet, @NotNull PacketBuffer buffer);

  /**
   * writes the resource pack entry.
   *
   * @param buffer the buffer to write.
   * @param entry the entry to write.
   */
  void writeResourcePackEntry(@NotNull PacketBuffer buffer, @NotNull ResourcePackInfo.Entry entry);

  /**
   * writes the entry to buffer.
   *
   * @param buffer the buffer to write.
   * @param entry the entry to write.
   */
  void writeResourcePackStackEntry(@NotNull PacketBuffer buffer, @NotNull ResourcePackStack.Entry entry);
}
