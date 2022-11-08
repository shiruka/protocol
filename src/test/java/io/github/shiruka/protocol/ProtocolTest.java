package io.github.shiruka.protocol;

import io.github.shiruka.protocol.codec.Codecs;
import io.github.shiruka.protocol.codec.v557.CodecV557;
import io.github.shiruka.protocol.common.MinecraftPacket;
import io.github.shiruka.protocol.common.PacketHandler;
import io.github.shiruka.protocol.data.ClientChainData;
import io.github.shiruka.protocol.data.PacketCompressionAlgorithm;
import io.github.shiruka.protocol.packet.Login;
import io.github.shiruka.protocol.packet.NetworkSettings;
import io.github.shiruka.protocol.packet.PlayStatus;
import io.github.shiruka.protocol.packet.RequestNetworkSettings;
import io.github.shiruka.protocol.packet.Unknown;
import io.github.shiruka.protocol.server.MinecraftServer;
import io.github.shiruka.protocol.server.ServerListener;
import io.github.shiruka.protocol.server.channels.MinecraftChildChannel;
import java.util.List;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public final class ProtocolTest {

  public static void main(final String[] args) {
    System.out.println("Server is starting...");
    new MinecraftServer()
      .codec(CodecV557.INSTANCE)
      .maxConnections(1024)
      .defaultPacketHandler(Handler::new)
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
        .append("/**")
        .append("\n")
        .append(" * the ")
        .append(comment)
        .append(".")
        .append("\n")
        .append(" */")
        .append("\n")
        .append(name)
        .append(",")
        .append("\n");
    }
    return builder.toString();
  }

  private record Handler(@NotNull MinecraftChildChannel session)
    implements PacketHandler {
    @Override
    public void handle(@NotNull final Login packet) {
      final var now = System.currentTimeMillis();
      final var version = packet.protocolVersion();
      final var codec = Codecs.findByProtocolVersion(version);
      if (codec == null) {
        final var status = PlayStatus
          .newBuilder()
          .status(
            version < Codecs.latestProtocolVersion()
              ? PlayStatus.Status.LOGIN_FAILED_CLIENT_OLD
              : PlayStatus.Status.LOGIN_FAILED_SERVER_OLD
          )
          .build();
        // @todo #1:15m Packets cannot send.
        this.session.writeAndFlush(List.of(status));
        return;
      }
      final var chainData = ClientChainData.from(
        packet.chainData().toString(),
        packet.skinData().toString()
      );
      System.out.println(System.currentTimeMillis() - now);
    }

    @Override
    public void handle(@NotNull final Unknown packet) {}

    @Override
    public void handle(@NotNull final RequestNetworkSettings packet) {
      final var version = packet.protocolVersion();
      final var codec = Codecs.findByProtocolVersion(version);
      if (codec == null) {
        final var status = PlayStatus
          .newBuilder()
          .status(
            version < Codecs.latestProtocolVersion()
              ? PlayStatus.Status.LOGIN_FAILED_CLIENT_OLD
              : PlayStatus.Status.LOGIN_FAILED_SERVER_OLD
          )
          .build();
        // @todo #1:15m Packets cannot send.
        this.session.writeAndFlush(List.of(status));
        return;
      }
      this.session.writeAndFlush(
          NetworkSettings
            .newBuilder()
            .compressionAlgorithm(PacketCompressionAlgorithm.ZLIB)
            .compressionThreshold(1)
            .build()
        );
    }
  }

  private static final class Listener implements ServerListener {

    @Override
    public void onConnect(@NotNull final MinecraftChildChannel session) {
      System.out.printf("onConnect(%s)%n", session);
    }

    @Override
    public void onDisconnect(@NotNull final MinecraftChildChannel session) {
      System.out.printf("onDisconnect(%s)%n", session);
    }

    @Override
    public void onStart() {
      System.out.println("onStart()");
    }

    @Override
    public void postPacket(
      @NotNull final MinecraftPacket packet,
      @NotNull final MinecraftChildChannel session
    ) {
      System.out.printf(
        "postPacket(%s,%s)%n",
        packet.getClass().getSimpleName(),
        session
      );
    }

    @Override
    public void prePacket(
      @NotNull final MinecraftPacket packet,
      @NotNull final MinecraftChildChannel session
    ) {
      System.out.printf(
        "prePacket(%s,%s)%n",
        packet.getClass().getSimpleName(),
        session
      );
    }
  }
}
