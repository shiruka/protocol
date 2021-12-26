package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.MinecraftPacket;
import io.github.shiruka.protocol.PacketHandler;
import io.github.shiruka.protocol.data.AdventureSetting;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.command.CommandPermission;
import java.util.EnumSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents adventure settings packets.
 */
@Getter
@Setter
@ToString
@Accessors(fluent = true)
public final class AdventureSettings extends MinecraftPacket {

  /**
   * the settings.
   */
  private final Set<AdventureSetting> settings = EnumSet.noneOf(AdventureSetting.class);

  /**
   * the command permission.
   */
  private CommandPermission commandPermission = CommandPermission.NORMAL;

  /**
   * the player permission.
   */
  private PlayerPermission playerPermission = PlayerPermission.VISITOR;

  /**
   * the unique entity id.
   */
  private long uniqueEntityId;

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
