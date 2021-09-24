package io.github.shiruka.protocol;

import io.github.shiruka.protocol.server.MinecraftServer;
import java.net.InetSocketAddress;

public final class ProtocolTest {

  public static void main(final String[] args) throws Exception {
    new MinecraftServer(new InetSocketAddress("127.0.0.1", 19132))
      .bind();
    while (true) {
      Thread.sleep(5L);
    }
  }
}
