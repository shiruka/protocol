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
import io.github.shiruka.protocol.packets.MovePlayer;
import io.github.shiruka.protocol.packets.PlayStatus;
import io.github.shiruka.protocol.packets.PlayerAuthInput;
import io.github.shiruka.protocol.packets.RemoveEntity;
import io.github.shiruka.protocol.packets.ResourcePackClientResponse;
import io.github.shiruka.protocol.packets.ResourcePackInfo;
import io.github.shiruka.protocol.packets.ResourcePackStack;
import io.github.shiruka.protocol.packets.RiderJump;
import io.github.shiruka.protocol.packets.ServerToClientHandshake;
import io.github.shiruka.protocol.packets.SetTime;
import io.github.shiruka.protocol.packets.StartGame;
import io.github.shiruka.protocol.packets.TakeItemEntity;
import io.github.shiruka.protocol.packets.Text;

/**
 * an interface that contains Minecraft packet ids.
 */
public interface Ids {

  /**
   * the id of the {@link AddBehaviorTree} packet.
   */
  int ADD_BEHAVIOR_TREE = 89;

  /**
   * the id of the {@link AddEntity} packet.
   */
  int ADD_ENTITY = 13;

  /**
   * the id of the {@link AddItemEntity} packet.
   */
  int ADD_ITEM_ENTITY = 15;

  /**
   * the id of the {@link AddPlayer} packet.
   */
  int ADD_PLAYER = 12;

  /**
   * the id of the {@link AdventureSettings} packet.
   */
  int ADVENTURE_SETTINGS = 55;

  /**
   * the id of the {@link ClientToServerHandshake} packet.
   */
  int CLIENT_TO_SERVER_HANDSHAKE = 4;

  /**
   * the id of the {@link Disconnect} packet.
   */
  int DISCONNECT = 5;

  /**
   * the id of the {@link Login} packet.
   */
  int LOGIN = 1;

  /**
   * the id of the {@link MoveEntityAbsolute} packet.
   */
  int MOVE_ENTITY_ABSOLUTE = 18;

  /**
   * the id of the {@link MovePlayer} packet.
   */
  int MOVE_PLAYER = 19;

  /**
   * the id of the {@link PlayerAuthInput} packet.
   */
  int PLAYER_AUTH_INPUT = 144;

  /**
   * the id of the {@link PlayStatus} packet.
   */
  int PLAY_STATUS = 2;

  /**
   * the id of the {@link RemoveEntity} packet.
   */
  int REMOVE_ENTITY = 14;

  /**
   * the id of the {@link ResourcePackClientResponse} packet.
   */
  int RESOURCE_PACK_CLIENT_RESPONSE = 8;

  /**
   * the id of the {@link ResourcePackInfo} packet.
   */
  int RESOURCE_PACK_INFO = 6;

  /**
   * the id of the {@link ResourcePackStack} packet.
   */
  int RESOURCE_PACK_STACK = 7;

  /**
   * the id of the {@link RiderJump} packet.
   */
  int RIDER_JUMP = 20;

  /**
   * the id of the {@link ServerToClientHandshake} packet.
   */
  int SERVER_TO_CLIENT_HANDSHAKE = 3;

  /**
   * the id of the {@link SetTime} packet.
   */
  int SET_TIME = 10;

  /**
   * the id of the {@link StartGame} packet.
   */
  int START_GAME = 11;

  /**
   * the id of the {@link TakeItemEntity} packet.
   */
  int TAKE_ITEM_ENTITY = 17;

  /**
   * the id of the {@link Text} packet.
   */
  int TEXT = 9;

  /**
   * the id of the {@link UpdateBlock} packet.
   */
  int UPDATE_BLOCK = 21;
}
