package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.GameRuleValue;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents game rules changed packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public class GameRulesChanged extends MinecraftPacket.Base {

  //@formatter:off
  private List<GameRuleValue> gameRules;
  //@formatter:on

  @Override
  public final void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
