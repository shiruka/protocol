package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
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

  //@formatter:off
  private final Set<AdventureSetting> settings = EnumSet.noneOf(AdventureSetting.class);
  private CommandPermission commandPermission = CommandPermission.NORMAL;
  private PlayerPermission playerPermission = PlayerPermission.VISITOR;
  private long uniqueEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
