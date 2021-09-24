package io.github.shiruka.protocol;

import io.github.shiruka.protocol.data.ClientChainData;
import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.Unknown;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.MinecraftServerSession;
import io.github.shiruka.protocol.server.ServerListener;
import org.jetbrains.annotations.NotNull;

public final class ProtocolTest {

  public static void main(final String[] args) {
    PacketRegistry.registerDefaults();
    new MinecraftServer()
      .maxConnections(1024)
      .defaultPacketHandler(new Handler())
      .motd("Motd")
      .serverListener(new Listener())
      .bind();
  }

  private static final class Handler implements PacketHandler {

    @Override
    public void handle(@NotNull final Login packet) {
      final var now = System.currentTimeMillis();
      final var chainData = ClientChainData.from(
        packet.chainData().toString(),
        packet.skinData().toString());
      System.out.println(System.currentTimeMillis() - now);
    }

    @Override
    public void handle(@NotNull final Unknown packet) {
    }
  }

  private static final class Listener implements ServerListener {

    @Override
    public void onConnect(@NotNull final MinecraftServerSession session) {
    }

    @Override
    public void postPacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftServerSession session) {
    }

    @Override
    public void prePacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftServerSession session) {
    }
  }
}
