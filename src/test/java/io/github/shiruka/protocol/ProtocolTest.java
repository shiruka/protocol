package io.github.shiruka.protocol;

import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.UnknownPacket;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.MinecraftServerSession;
import io.github.shiruka.protocol.server.ServerListener;
import org.jetbrains.annotations.NotNull;

public final class ProtocolTest {

  public static void main(final String[] args) throws Exception {
    PacketRegistry.registerDefaults();
    new MinecraftServer()
      .maxConnections(1024)
      .defaultPacketHandler(new Handler())
      .motd("Motd")
      .serverListener(new Listener())
      .bind();
    while (true) {
      Thread.sleep(5L);
    }
  }

  private static final class Handler implements PacketHandler {

    @Override
    public void handle(@NotNull final Login login) {
      System.out.println("Login packet received!");
    }

    @Override
    public void handle(@NotNull final UnknownPacket login) {
      System.out.println("Login packet received!");
    }
  }

  private static final class Listener implements ServerListener {

    @Override
    public void onConnect(@NotNull final MinecraftServerSession session) {
      System.out.printf("A session %s has been created!%n", session.address());
      System.out.println();
    }

    @Override
    public void postPacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftServerSession session) {
      System.out.printf("Packet %s received from %s!%n", packet.packetId(), session.address());
      System.out.println();
    }

    @Override
    public void prePacket(@NotNull final MinecraftPacket packet, @NotNull final MinecraftServerSession session) {
      System.out.printf("Packet %s receiving from %s!%n", packet.packetId(), session.address());
      System.out.println();
    }
  }
}
