package io.github.shiruka.protocol;

import io.github.shiruka.protocol.codec.Codec;
import io.github.shiruka.protocol.codec.CodecHelper;
import io.github.shiruka.protocol.data.ClientChainData;
import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.Unknown;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.MinecraftServerSession;
import io.github.shiruka.protocol.server.ServerListener;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public final class ProtocolTest {

  public static void main(final String[] args) {
    PacketRegistry.registerDefaults();
    final var codec = Codec.newBuilder()
      .protocolVersion(475)
      .minecraftVersion("1.18.2")
      .helper(CodecHelper.EMPTY)
      .build();
    new MinecraftServer(codec)
      .maxConnections(1024)
      .defaultPacketHandler(new Handler())
      .motd("Motd")
      .serverListener(new Listener())
      .bind();
  }

  @NotNull
  private static String enumWithComments(@NotNull final Enum<?>[] values) {
    final var builder = new StringBuilder();
    for (final var value : values) {
      final var name = value.name();
      final var comment = name.toLowerCase(Locale.ROOT).replace("_", " ");
      builder
        .append("/**").append("\n")
        .append(" * the ").append(comment).append(".").append("\n")
        .append(" */").append("\n")
        .append(name).append(",").append("\n");
    }
    return builder.toString();
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
