package io.github.shiruka.protocol.data.command;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a record class that represents command parameter data.
 *
 * @param name the name.
 * @param optional the optional.
 * @param enumData the enum data.
 * @param type the type.
 * @param postfix the postfix.
 * @param options the options.
 */
public record CommandParamData(
  @NotNull String name,
  boolean optional,
  @Nullable CommandEnumData enumData,
  @Nullable CommandParam type,
  @Nullable String postfix,
  @NotNull List<CommandParamOption> options
) {

}
