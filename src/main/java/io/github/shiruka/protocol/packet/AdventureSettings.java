package io.github.shiruka.protocol.packet;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.AdventureSetting;
import io.github.shiruka.protocol.data.PlayerPermission;
import io.github.shiruka.protocol.data.command.CommandPermission;
import java.util.EnumSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents adventure settings packets.
 */
@Getter
@Setter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class AdventureSettings extends MinecraftPacket.Base {

  //@formatter:off
  @Builder.Default CommandPermission commandPermission = CommandPermission.NORMAL;
  @Builder.Default PlayerPermission playerPermission = PlayerPermission.VISITOR;
  @Builder.Default Set<AdventureSetting> settings = EnumSet.noneOf(AdventureSetting.class);
  long uniqueEntityId;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
