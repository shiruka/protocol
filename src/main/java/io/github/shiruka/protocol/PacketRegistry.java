package io.github.shiruka.protocol;

import io.github.shiruka.protocol.packets.AddBehaviorTree;
import io.github.shiruka.protocol.packets.AddEntity;
import io.github.shiruka.protocol.packets.AddItemEntity;
import io.github.shiruka.protocol.packets.AddPlayer;
import io.github.shiruka.protocol.packets.AdventureSettings;
import io.github.shiruka.protocol.packets.ClientToServerHandshake;
import io.github.shiruka.protocol.packets.Disconnect;
import io.github.shiruka.protocol.packets.Login;
import io.github.shiruka.protocol.packets.MoveEntityAbsolute;
import io.github.shiruka.protocol.packets.PlayStatus;
import io.github.shiruka.protocol.packets.PlayerAuthInput;
import io.github.shiruka.protocol.packets.RemoveEntity;
import io.github.shiruka.protocol.packets.ResourcePackClientResponse;
import io.github.shiruka.protocol.packets.ResourcePackInfo;
import io.github.shiruka.protocol.packets.ResourcePackStack;
import io.github.shiruka.protocol.packets.ServerToClientHandshake;
import io.github.shiruka.protocol.packets.SetTime;
import io.github.shiruka.protocol.packets.StartGame;
import io.github.shiruka.protocol.packets.TakeItemEntity;
import io.github.shiruka.protocol.packets.Text;
import io.github.shiruka.protocol.packets.Unknown;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Objects;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;

/**
 * a class that represents packet registries.
 */
public final class PacketRegistry {

  /**
   * the packets.
   */
  private static final Int2ObjectMap<Supplier<? extends MinecraftPacket>> PACKETS = new Int2ObjectOpenHashMap<>();

  /**
   * ctor.
   */
  private PacketRegistry() {
  }

  /**
   * gets a Minecraft packet by id.
   *
   * @param id the id to get.
   *
   * @return Minecraft packet, if not found unknown packet instance.
   */
  @NotNull
  public static MinecraftPacket get(final int id) {
    return Objects.requireNonNull(PacketRegistry.PACKETS.get(id), "Packet %s not found!"
      .formatted(id)).get();
  }

  /**
   * registers the packet.
   *
   * @param id the id to register.
   * @param packet the packet to register.
   */
  public static void register(final int id, @NotNull final Supplier<? extends MinecraftPacket> packet) {
    PacketRegistry.PACKETS.put(id, packet);
  }

  /**
   * register default packets.
   */
  public static void registerDefaults() {
    Constants.register();
    PacketRegistry.PACKETS.defaultReturnValue(Unknown::new);
    PacketRegistry.register(Ids.LOGIN, Login::new);
    PacketRegistry.register(Ids.PLAY_STATUS, PlayStatus::new);
    PacketRegistry.register(Ids.SERVER_TO_CLIENT_HANDSHAKE, ServerToClientHandshake::new);
    PacketRegistry.register(Ids.CLIENT_TO_SERVER_HANDSHAKE, ClientToServerHandshake::new);
    PacketRegistry.register(Ids.DISCONNECT, Disconnect::new);
    PacketRegistry.register(Ids.RESOURCE_PACK_INFO, ResourcePackInfo::new);
    PacketRegistry.register(Ids.RESOURCE_PACK_STACK, ResourcePackStack::new);
    PacketRegistry.register(Ids.RESOURCE_PACK_CLIENT_RESPONSE, ResourcePackClientResponse::new);
    PacketRegistry.register(Ids.TEXT, Text::new);
    PacketRegistry.register(Ids.SET_TIME, SetTime::new);
    PacketRegistry.register(Ids.START_GAME, StartGame::new);
    PacketRegistry.register(Ids.ADD_PLAYER, AddPlayer::new);
    PacketRegistry.register(Ids.ADD_ENTITY, AddEntity::new);
    PacketRegistry.register(Ids.REMOVE_ENTITY, RemoveEntity::new);
    PacketRegistry.register(Ids.ADD_ITEM_ENTITY, AddItemEntity::new);
    // 16 missing
    PacketRegistry.register(Ids.TAKE_ITEM_ENTITY, TakeItemEntity::new);
    PacketRegistry.register(Ids.MOVE_ENTITY_ABSOLUTE, MoveEntityAbsolute::new);
    // 19...54 missing
    PacketRegistry.register(Ids.ADVENTURE_SETTINGS, AdventureSettings::new);
    // 56...88 missing
    PacketRegistry.register(Ids.ADD_BEHAVIOR_TREE, AddBehaviorTree::new);
    // 90...143 missing
    PacketRegistry.register(Ids.PLAYER_AUTH_INPUT, PlayerAuthInput::new);
  }
}
