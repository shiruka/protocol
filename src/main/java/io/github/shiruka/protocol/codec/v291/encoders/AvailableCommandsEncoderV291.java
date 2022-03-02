package io.github.shiruka.protocol.codec.v291.encoders;

import com.google.common.base.Preconditions;
import io.github.shiruka.network.PacketBuffer;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.codec.PacketEncoder;
import io.github.shiruka.protocol.codec.PacketId;
import io.github.shiruka.protocol.common.MinecraftSession;
import io.github.shiruka.protocol.data.command.CommandData;
import io.github.shiruka.protocol.data.command.CommandEnumData;
import io.github.shiruka.protocol.data.command.CommandParam;
import io.github.shiruka.protocol.data.command.CommandParamData;
import io.github.shiruka.protocol.data.command.CommandParamOption;
import io.github.shiruka.protocol.data.command.CommandSymbolData;
import io.github.shiruka.protocol.packets.AvailableCommands;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents available commands' packet encoders.
 */
@PacketId(76)
public final class AvailableCommandsEncoderV291 extends PacketEncoder.Base<AvailableCommands> {

  /**
   * the read byte.
   */
  private static final ToIntFunction<PacketBuffer> READ_BYTE = PacketBuffer::readUnsignedByte;

  /**
   * the read int.
   */
  private static final ToIntFunction<PacketBuffer> READ_INT = PacketBuffer::readIntLE;

  /**
   * the read short.
   */
  private static final ToIntFunction<PacketBuffer> READ_SHORT = PacketBuffer::readUnsignedShortLE;

  /**
   * the write byte.
   */
  private static final ObjIntConsumer<PacketBuffer> WRITE_BYTE = PacketBuffer::writeByte;

  /**
   * the write int.
   */
  private static final ObjIntConsumer<PacketBuffer> WRITE_INT = PacketBuffer::writeIntLE;

  /**
   * the write short.
   */
  private static final ObjIntConsumer<PacketBuffer> WRITE_SHORT = PacketBuffer::writeShortLE;

  /**
   * reads the command.
   *
   * @param buffer the buffer to read.
   * @param helper the helper to read.
   * @param enums the enums read.
   * @param softEnums the soft enums.
   * @param postFixes the post fixes to read.
   *
   * @return command.
   */
  @NotNull
  private static CommandData readCommand(@NotNull final PacketBuffer buffer, @NotNull final CodecHelper helper,
                                         @NotNull final List<CommandEnumData> enums,
                                         @NotNull final List<CommandEnumData> softEnums,
                                         @NotNull final List<String> postFixes) {
    final var name = buffer.readString();
    final var description = buffer.readString();
    final var flags = buffer.readByte();
    final var permissions = buffer.readByte();
    final var aliasesIndex = buffer.readIntLE();
    final var overloads = new CommandParamData[buffer.readUnsignedVarInt()][];
    for (var index = 0; index < overloads.length; index++) {
      overloads[index] = new CommandParamData[buffer.readUnsignedVarInt()];
      Arrays.setAll(overloads[index], index2 ->
        AvailableCommandsEncoderV291.readParameter(buffer, helper, enums, softEnums, postFixes));
    }
    final var flagList = IntStream.range(0, 6)
      .filter(index -> (flags & 1 << index) != 0)
      .mapToObj(index -> CommandData.Flag.VALUES[index])
      .collect(Collectors.toCollection(ObjectArrayList::new));
    final var aliases = aliasesIndex == -1 ? null : enums.get(aliasesIndex);
    return new CommandData(name, description, flagList, permissions, aliases, overloads);
  }

  /**
   * reads the command enums.
   *
   * @param buffer the buffer to read.
   * @param values the value to read.
   * @param enums the enums to read.
   */
  private static void readCommandEnums(@NotNull final PacketBuffer buffer, @NotNull final List<String> values,
                                       @NotNull final List<CommandEnumData> enums) {
    final var valuesSize = values.size();
    final ToIntFunction<PacketBuffer> indexReader;
    if (valuesSize < 0x100) {
      indexReader = AvailableCommandsEncoderV291.READ_BYTE;
    } else if (valuesSize < 0x10000) {
      indexReader = AvailableCommandsEncoderV291.READ_SHORT;
    } else {
      indexReader = AvailableCommandsEncoderV291.READ_INT;
    }
    buffer.readArray(enums, b -> {
      final var name = b.readString();
      final var length = b.readUnsignedVarInt();
      final var enumValues = IntStream.range(0, length)
        .mapToObj(index -> values.get(indexReader.applyAsInt(b)))
        .toArray(String[]::new);
      return new CommandEnumData(false, name, enumValues);
    });
  }

  /**
   * reads the parameter.
   *
   * @param buffer the buffer to read.
   * @param helper the helper to read.
   * @param enums the enums to read.
   * @param softEnums the soft enums to read.
   * @param postFixes the post fixes to read.
   *
   * @return command parameter data.
   */
  @NotNull
  private static CommandParamData readParameter(@NotNull final PacketBuffer buffer, @NotNull final CodecHelper helper,
                                                @NotNull final List<CommandEnumData> enums,
                                                @NotNull final List<CommandEnumData> softEnums,
                                                @NotNull final List<String> postFixes) {
    final var name = buffer.readString();
    final var type = CommandSymbolData.deserialize(buffer.readIntLE());
    final var optional = buffer.readBoolean();
    final var optionsByte = buffer.readByte();
    String postfix = null;
    CommandEnumData enumData = null;
    CommandParam commandParam = null;
    if (type.postfix()) {
      postfix = postFixes.get(type.value());
    } else if (type.commandEnum()) {
      enumData = enums.get(type.value());
    } else if (type.softEnum()) {
      enumData = softEnums.get(type.value());
    } else {
      commandParam = helper.commandParam(type.value());
    }
    final var options = IntStream.range(0, 8)
      .filter(index -> (optionsByte & 1 << index) != 0)
      .mapToObj(index -> CommandParamOption.VALUES[index])
      .collect(Collectors.toCollection(ObjectArrayList::new));
    return new CommandParamData(name, optional, enumData, commandParam, postfix, options);
  }

  /**
   * writes the command.
   *
   * @param buffer the buffer
   * @param helper the helper to read.
   * @param commandData the command data to read.
   * @param enums the enums to read.
   * @param softEnums the soft enums to read.
   * @param postFixes the post fixes to read.
   */
  private static void writeCommand(@NotNull final PacketBuffer buffer, @NotNull final CodecHelper helper,
                                   @NotNull final CommandData commandData,
                                   @NotNull final List<CommandEnumData> enums,
                                   @NotNull final List<CommandEnumData> softEnums,
                                   @NotNull final List<String> postFixes) {
    buffer.writeString(commandData.name());
    buffer.writeString(commandData.description());
    final var flags = commandData.flags().stream()
      .mapToInt(flag -> 1 << flag.ordinal())
      .reduce(0, (a, b) -> a | b);
    buffer.writeByte(flags);
    buffer.writeByte(commandData.permission());
    final var aliases = commandData.aliases();
    buffer.writeIntLE(enums.indexOf(aliases));
    final var overloads = commandData.overloads();
    buffer.writeUnsignedVarInt(overloads.length);
    for (final var overload : overloads) {
      buffer.writeUnsignedVarInt(overload.length);
      for (final var param : overload) {
        AvailableCommandsEncoderV291.writeParameter(buffer, helper, param, enums, softEnums, postFixes);
      }
    }
  }

  /**
   * writes the command enums.
   *
   * @param buffer the buffer to write.
   * @param values the values to write.
   * @param enums the enums to write.
   */
  private static void writeCommandEnums(@NotNull final PacketBuffer buffer, @NotNull final List<String> values,
                                        @NotNull final List<CommandEnumData> enums) {
    final ObjIntConsumer<PacketBuffer> indexWriter;
    final var valuesSize = values.size();
    if (valuesSize < 0x100) {
      indexWriter = AvailableCommandsEncoderV291.WRITE_BYTE;
    } else if (valuesSize < 0x10000) {
      indexWriter = AvailableCommandsEncoderV291.WRITE_SHORT;
    } else {
      indexWriter = AvailableCommandsEncoderV291.WRITE_INT;
    }
    buffer.writeArray(enums, (b, commandEnum) -> {
      b.writeString(commandEnum.name());
      buffer.writeUnsignedVarInt(commandEnum.values().length);
      for (final var value : commandEnum.values()) {
        final var index = values.indexOf(value);
        Preconditions.checkArgument(index > -1, "Invalid enum value detected: %s", value);
        indexWriter.accept(b, index);
      }
    });
  }

  /**
   * writes the parameter.
   *
   * @param buffer the buffer to write.
   * @param helper the helper to write.
   * @param param the param to write.
   * @param enums the enums to write.
   * @param softEnums the soft enums to write.
   * @param postFixes the post fixes to write.
   */
  private static void writeParameter(@NotNull final PacketBuffer buffer, @NotNull final CodecHelper helper,
                                     @NotNull final CommandParamData param, @NotNull final List<CommandEnumData> enums,
                                     @NotNull final List<CommandEnumData> softEnums,
                                     @NotNull final List<String> postFixes) {
    buffer.writeString(param.name());
    final int index;
    var postfix = false;
    var enumData = false;
    var softEnum = false;
    final var postfixString = param.postfix();
    if (postfixString != null) {
      postfix = true;
      index = postFixes.indexOf(postfixString);
    } else {
      final var commandEnumData = param.enumData();
      if (commandEnumData != null) {
        if (commandEnumData.isSoft()) {
          softEnum = true;
          index = softEnums.indexOf(commandEnumData);
        } else {
          enumData = true;
          index = enums.indexOf(commandEnumData);
        }
      } else {
        final var type = param.type();
        Preconditions.checkState(type != null, "No param type specified: %s", param);
        index = type.value(helper);
      }
    }
    final var type = new CommandSymbolData(index, enumData, softEnum, postfix);
    buffer.writeIntLE(type.serialize());
    buffer.writeBoolean(param.optional());
  }

  @Override
  public void decode(@NotNull final AvailableCommands packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var enumValues = new ObjectArrayList<String>();
    final var postFixes = new ObjectArrayList<String>();
    final var enums = new ObjectArrayList<CommandEnumData>();
    final var softEnums = new ObjectArrayList<CommandEnumData>();
    buffer.readArray(enumValues, PacketBuffer::readString);
    buffer.readArray(postFixes, PacketBuffer::readString);
    AvailableCommandsEncoderV291.readCommandEnums(buffer, enumValues, enums);
    buffer.readArray(softEnums, b -> helper.readCommandEnum(b, true));
    buffer.readArray(packet.commands(), b -> AvailableCommandsEncoderV291.readCommand(b, helper, enums, softEnums, postFixes));
  }

  @Override
  public void encode(@NotNull final AvailableCommands packet, @NotNull final CodecHelper helper,
                     @NotNull final PacketBuffer buffer, @NotNull final MinecraftSession session) {
    final var enumValuesSet = new ObjectOpenHashSet<String>();
    final var postfixSet = new ObjectOpenHashSet<String>();
    final var enumsSet = new ObjectOpenHashSet<CommandEnumData>();
    final var softEnumsSet = new ObjectOpenHashSet<CommandEnumData>();
    for (final var data : packet.commands()) {
      final var aliases = data.aliases();
      if (aliases != null) {
        Collections.addAll(enumValuesSet, aliases.values());
        enumsSet.add(aliases);
      }
      for (final var overload : data.overloads()) {
        for (final var parameter : overload) {
          final var commandEnumData = parameter.enumData();
          if (commandEnumData != null) {
            if (commandEnumData.isSoft()) {
              softEnumsSet.add(commandEnumData);
            } else {
              Collections.addAll(enumValuesSet, commandEnumData.values());
              enumsSet.add(commandEnumData);
            }
          }
          final var postfix = parameter.postfix();
          if (postfix != null) {
            postfixSet.add(postfix);
          }
        }
      }
    }
    final var enumValues = new ObjectArrayList<>(enumValuesSet);
    final var postFixes = new ObjectArrayList<>(postfixSet);
    final var enums = new ObjectArrayList<>(enumsSet);
    final var softEnums = new ObjectArrayList<>(softEnumsSet);
    buffer.writeArray(enumValues, PacketBuffer::writeString);
    buffer.writeArray(postFixes, PacketBuffer::writeString);
    AvailableCommandsEncoderV291.writeCommandEnums(buffer, enumValues, enums);
    buffer.writeArray(packet.commands(), (b, command) -> {
      AvailableCommandsEncoderV291.writeCommand(b, helper, command, enums, softEnums, postFixes);
    });
    buffer.writeArray(softEnums, (b, data) -> helper.writeCommandEnum(b, session, data));
  }
}
