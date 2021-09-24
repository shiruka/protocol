package io.github.shiruka.protocol.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import io.github.shiruka.protocol.data.skin.Skin;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a record class that represents client chain data.
 *
 * @param chainData the chain data.
 * @param skinData the skin data.
 */
public final record ClientChainData(
  @NotNull ChainData chainData,
  @NotNull SkinData skinData
) {

  /**
   * the mapper.
   */
  private static final ObjectMapper MAPPER = new ObjectMapper();

  /**
   * the map type.
   */
  private static final TypeReference<Map<String, List<String>>> MAP_TYPE = new TypeReference<>() {
  };

  /**
   * the Mojang's public key.
   */
  private static final PublicKey MOJANG_PUBLIC_KEY;

  /**
   * the Mojang's public key as base 64.
   */
  private static final String MOJANG_PUBLIC_KEY_BASE64 =
    "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAE8ELkixyLcwlZryUQcu1TvPOmI2B7vX83ndnWRUaXm74wFfa5f/lwQNTfrLVHa2PmenpGI6JhIMUJaWZrjmMj90NoKNFSNBuKdm8rYiXsfaz3K36x/1U26HpG0ZxK/V1V";

  static {
    try {
      MOJANG_PUBLIC_KEY = ClientChainData.generateKey(ClientChainData.MOJANG_PUBLIC_KEY_BASE64);
    } catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new IllegalStateException("An error occurs when generating Mojang's public key", e);
    }
  }

  /**
   * creates a chain data.
   *
   * @param chainData the chain data to create.
   * @param skinData the skin data to create.
   *
   * @return chain data.
   */
  @NotNull
  public static ClientChainData from(@NotNull final String chainData, @NotNull final String skinData) {
    return new ClientChainData(
      ChainData.decode(chainData),
      SkinData.decode(skinData));
  }

  /**
   * decodes the token.
   *
   * @param token the token to decode.
   *
   * @return decoded token.
   */
  @NotNull
  private static JsonNode decodeToken(@NotNull final String token) {
    final var base = token.split("\\.");
    Preconditions.checkArgument(base.length >= 2, "Invalid token length!");
    final var json = new String(Base64.getDecoder().decode(base[1]), StandardCharsets.UTF_8);
    try {
      return ClientChainData.MAPPER.readTree(json);
    } catch (final IOException e) {
      throw new IllegalArgumentException("Invalid token JSON", e);
    }
  }

  /**
   * generates a EC key.
   *
   * @param base64 the base 64 to create.
   *
   * @return public EC key.
   *
   * @throws NoSuchAlgorithmException if no {@code Provider} supports a {@code KeyFactorySpi} implementation for
   *   the specified algorithm.
   * @throws InvalidKeySpecException if the given key specification is inappropriate for this key factory to produce a
   *   public key.
   */
  @NotNull
  private static ECPublicKey generateKey(@NotNull final String base64) throws NoSuchAlgorithmException,
    InvalidKeySpecException {
    return (ECPublicKey) KeyFactory.getInstance("EC")
      .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(base64)));
  }

  /**
   * verifies the key.
   *
   * @param key the key to verify.
   * @param object the object to verify.
   *
   * @return {@code true} if the key verified.
   *
   * @throws JOSEException if the JWS object couldn't be verified, or if the elliptic curve of key is not supported.
   */
  private static boolean verify(@NotNull final ECPublicKey key, @NotNull final JWSObject object) throws JOSEException {
    return object.verify(new ECDSAVerifier(key));
  }

  /**
   * verifies the chain.
   *
   * @param chains the chains to verify.
   *
   * @return {@code true} if the chain verified.
   *
   * @throws ParseException if the chains couldn't be parsed to a JWS object.
   * @throws NoSuchAlgorithmException if no {@code Provider} supports a {@code KeyFactorySpi} implementation for
   *   the specified algorithm.
   * @throws InvalidKeySpecException if the given key specification is inappropriate for this key factory to produce a
   *   public key.
   * @throws JOSEException if the JWS object couldn't be verified, or if the elliptic curve of key is not supported.
   */
  private static boolean verifyChain(@NotNull final List<String> chains) throws ParseException, NoSuchAlgorithmException,
    InvalidKeySpecException, JOSEException {
    ECPublicKey lastKey = null;
    var mojangKeyVerified = false;
    final var iterator = chains.iterator();
    while (iterator.hasNext()) {
      final var jws = JWSObject.parse(iterator.next());
      final var x5u = jws.getHeader().getX509CertURL();
      if (x5u == null) {
        return false;
      }
      final var expectedKey = ClientChainData.generateKey(x5u.toString());
      if (lastKey == null) {
        lastKey = expectedKey;
      } else if (!lastKey.equals(expectedKey)) {
        return false;
      }
      if (!ClientChainData.verify(lastKey, jws)) {
        return false;
      }
      if (mojangKeyVerified) {
        return !iterator.hasNext();
      }
      if (lastKey.equals(ClientChainData.MOJANG_PUBLIC_KEY)) {
        mojangKeyVerified = true;
      }
      final var payload = jws.getPayload().toJSONObject();
      final var base64key = payload.get("identityPublicKey");
      if (!(base64key instanceof String)) {
        throw new RuntimeException("No key found");
      }
      lastKey = ClientChainData.generateKey((String) base64key);
    }
    return mojangKeyVerified;
  }

  /**
   * a record class that represents chain data.
   *
   * @param clientUUID the client unique id.
   * @param identityPublicKey the identify public key.
   * @param username the username.
   * @param xboxAuthed the xbox authed.
   * @param xuid the xuid.
   */
  private final record ChainData(
    boolean xboxAuthed,
    @Nullable String username,
    @Nullable UUID clientUUID,
    @Nullable String xuid,
    @Nullable String identityPublicKey
  ) {

    /**
     * decodes the chain data.
     *
     * @param chainData the chain data to decode.
     *
     * @return chain data.
     */
    @NotNull
    private static ChainData decode(@NotNull final String chainData) {
      final Map<String, List<String>> map;
      try {
        map = ClientChainData.MAPPER.readValue(chainData, ClientChainData.MAP_TYPE);
      } catch (final IOException e) {
        throw new IllegalArgumentException("Invalid JSON!", e);
      }
      Preconditions.checkArgument(!map.isEmpty() && map.containsKey("chain") && !map.get("chain").isEmpty(),
        "Something goes wrong when reading the chain data!");
      final var chains = map.get("chain");
      var xboxAuthed = false;
      try {
        xboxAuthed = ClientChainData.verifyChain(chains);
      } catch (final Exception ignored) {
      }
      String username = null;
      UUID clientUUID = null;
      String xuid = null;
      String identityPublicKey = null;
      for (final var chain : chains) {
        final var chainMap = ClientChainData.decodeToken(chain);
        if (chainMap.has("extraData")) {
          final var extra = chainMap.get("extraData");
          if (extra.has("displayName")) {
            username = extra.get("displayName").textValue();
          }
          if (extra.has("identity")) {
            clientUUID = UUID.fromString(extra.get("identity").textValue());
          }
          if (extra.has("XUID")) {
            xuid = extra.get("XUID").textValue();
          }
        }
        if (chainMap.has("identityPublicKey")) {
          identityPublicKey = chainMap.get("identityPublicKey").textValue();
        }
      }
      if (!xboxAuthed) {
        xuid = null;
      }
      return new ChainData(xboxAuthed, username, clientUUID, xuid, identityPublicKey);
    }
  }

  /**
   * a record class that represents skin data.
   *
   * @param clientId the client id.
   * @param currentInputMode the current input mode.
   * @param defaultInputMode the default input mode.
   * @param deviceId the device id.
   * @param deviceModel the device model.
   * @param deviceOS the device os.
   * @param gameVersion the game version.
   * @param guiScale the gui scale.
   * @param languageCode the language code.
   * @param skin the skin.
   * @param serverAddress the server address.
   * @param uiProfile the ui profile.
   */
  private final record SkinData(
    long clientId,
    @Nullable String serverAddress,
    @Nullable String deviceModel,
    int deviceOS,
    @Nullable String deviceId,
    @Nullable String gameVersion,
    int guiScale,
    @Nullable String languageCode,
    int currentInputMode,
    int defaultInputMode,
    int uiProfile,
    @NotNull Skin skin
  ) {

    /**
     * decodes the skin data.
     *
     * @param skinData the skin data to decode.
     *
     * @return skin data.
     */
    @NotNull
    private static SkinData decode(@NotNull final String skinData) {
      final var skinToken = ClientChainData.decodeToken(skinData);
      var clientId = 0L;
      String serverAddress = null;
      String deviceModel = null;
      var deviceOS = 0;
      String deviceId = null;
      String gameVersion = null;
      var guiScale = 0;
      String languageCode = null;
      var currentInputMode = 0;
      var defaultInputMode = 0;
      var uiProfile = 0;
      if (skinToken.has("ClientRandomId")) {
        clientId = skinToken.get("ClientRandomId").longValue();
      }
      if (skinToken.has("ServerAddress")) {
        serverAddress = skinToken.get("ServerAddress").textValue();
      }
      if (skinToken.has("DeviceModel")) {
        deviceModel = skinToken.get("DeviceModel").textValue();
      }
      if (skinToken.has("DeviceOS")) {
        deviceOS = skinToken.get("DeviceOS").intValue();
      }
      if (skinToken.has("DeviceId")) {
        deviceId = skinToken.get("DeviceId").textValue();
      }
      if (skinToken.has("GameVersion")) {
        gameVersion = skinToken.get("GameVersion").textValue();
      }
      if (skinToken.has("GuiScale")) {
        guiScale = skinToken.get("GuiScale").intValue();
      }
      if (skinToken.has("LanguageCode")) {
        languageCode = skinToken.get("LanguageCode").textValue();
      }
      if (skinToken.has("CurrentInputMode")) {
        currentInputMode = skinToken.get("CurrentInputMode").intValue();
      }
      if (skinToken.has("DefaultInputMode")) {
        defaultInputMode = skinToken.get("DefaultInputMode").intValue();
      }
      if (skinToken.has("UIProfile")) {
        uiProfile = skinToken.get("UIProfile").intValue();
      }
      return new SkinData(clientId, serverAddress, deviceModel, deviceOS, deviceId, gameVersion, guiScale, languageCode,
        currentInputMode, defaultInputMode, uiProfile, Skin.from(skinToken));
    }
  }
}
