package io.github.shiruka.protocol.codec;

import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.data.AttributeData;
import io.github.shiruka.protocol.data.ItemDefinition;
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
   * writes the item.
   *
   * @param buffer the buffer to write.
   * @param session the session to write.
   * @param item the item to write.
   */
  void writeItem(@NotNull PacketBuffer buffer, @NotNull MinecraftSession session, @NotNull ItemData item);
}
