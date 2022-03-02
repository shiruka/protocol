package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.PacketHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents add painting packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
public final class AddPainting extends AddHangingEntity {

  //@formatter:off
  private String motive;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}
