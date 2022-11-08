package io.github.shiruka.protocol.data.command;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a record class that represents command data.
 *
 * @param name the name.
 * @param description the description.
 * @param flags the flags.
 * @param permission the permission.
 * @param aliases the aliases.
 * @param overloads the overloads.
 */
public record CommandData(
  @NotNull String name,
  @NotNull String description,
  @NotNull List<Flag> flags,
  int permission,
  @Nullable CommandEnumData aliases,
  @NotNull CommandParamData[][] overloads
) {

  @Override
  public String toString() {
    final var overloads = new StringBuilder("[\r\n");
    for (final var overload : this.overloads) {
      overloads.append("    [\r\n");
      for (final var parameter : overload) {
        overloads.append("       ").append(parameter).append("\r\n");
      }
      overloads.append("    ]\r\n");
    }
    overloads.append("]\r\n");
    final var builder = new StringBuilder("CommandData(\r\n");
    final var objects = Arrays.asList(
      "name=" + this.name,
      "description=" + this.description,
      "flags=" + Arrays.toString(this.flags.toArray()),
      "permission=" + this.permission,
      "aliases=" + this.aliases,
      "overloads=" + overloads
    );
    for (final var object : objects) {
      builder
        .append("    ")
        .append(Objects.toString(object).replaceAll("\r\n", "\r\n    "))
        .append("\r\n");
    }
    return builder.append(")").toString();
  }

  /**
   * an enum class that contains flags.
   */
  public enum Flag {
    /**
     * the usage.
     */
    USAGE,
    /**
     * the visibility.
     */
    VISIBILITY,
    /**
     * the sync.
     */
    SYNC,
    /**
     * the execute.
     */
    EXECUTE,
    /**
     * the type.
     */
    TYPE,
    /**
     * the cheat.
     */
    CHEAT,
    /**
     * the unknown 6.
     */
    UNKNOWN_6;

    /**
     * the values.
     */
    public static final Flag[] VALUES = Flag.values();
  }
}
