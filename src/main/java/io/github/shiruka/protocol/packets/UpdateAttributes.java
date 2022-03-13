package io.github.shiruka.protocol.packets;

import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.AttributeData;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents update attributes packets.
 */
@Setter
@Getter
@ToString
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public final class UpdateAttributes extends MinecraftPacket.Base {

  //@formatter:off
  private @Builder.Default List<AttributeData> attributes = new ObjectArrayList<>();
  private long runtimeEntityId;
  private long tick;
  //@formatter:on

  @Override
  public void handle(@NotNull final PacketHandler handler) {
    handler.handle(this);
  }
}