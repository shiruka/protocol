package io.github.shiruka.protocol.data.skin;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.shiruka.protocol.data.ImageData;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * a record class that represents skins.
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(fluent = true)
@ToString(doNotUseGetters = true)
public final class Skin {

  /**
   * the animation data.
   */
  @Nullable
  private String animationData;

  /**
   * the animations.
   */
  @NotNull
  private List<Animation> animations = new ArrayList<>();

  /**
   * the arm size.
   */
  @Nullable
  private String armSize;

  /**
   * the cape data.
   */
  @Nullable
  private ImageData capeData;

  /**
   * the cape id.
   */
  @Nullable
  private String capeId;

  /**
   * the cape on classic.
   */
  private boolean capeOnClassic;

  /**
   * the geometry data.
   */
  @Nullable
  private String geometryData;

  /**
   * the persona.
   */
  private boolean persona;

  /**
   * the persona pieces.
   */
  @NotNull
  private List<PersonaPiece> personaPieces = new ArrayList<>();

  /**
   * the play fab id.
   */
  @Nullable
  private String playFabId;

  /**
   * the premium.
   */
  private boolean premium;

  /**
   * the skin color.
   */
  @Nullable
  private String skinColor;

  /**
   * the skin data.
   */
  @Nullable
  private ImageData skinData;

  /**
   * the skin id.
   */
  @Nullable
  private String skinId;

  /**
   * the skin resource patch.
   */
  @Nullable
  private String skinResourcePatch;

  /**
   * the tint colors.
   */
  @NotNull
  private List<PersonaPiece.Tint> tintColors = new ArrayList<>();

  /**
   * creates a skin from skin token.
   *
   * @param token the token to create.
   *
   * @return skin.
   */
  @NotNull
  public static Skin from(@NotNull final JsonNode token) {
    final var newSkin = new Skin();
    if (token.has("SkinId")) {
      newSkin.skinId(token.get("SkinId").textValue());
    }
    if (token.has("PlayFabId")) {
      newSkin.playFabId(token.get("PlayFabId").textValue());
    }
    if (token.has("SkinResourcePatch")) {
      newSkin.skinResourcePatch(new String(Base64.getDecoder().decode(token.get("SkinResourcePatch").textValue()), StandardCharsets.UTF_8));
    }
    newSkin.skinData(ImageData.from(token, "Skin"));
    if (token.has("AnimatedImageData")) {
      final var array = token.get("AnimatedImageData");
      final var animations = new ArrayList<Animation>();
      for (final var element : array) {
        animations.add(Animation.from(element));
      }
      newSkin.animations(animations);
    }
    if (token.has("PersonaSkin")) {
      newSkin.persona(token.get("PersonaSkin").booleanValue());
      if (token.has("PersonaPieces")) {
        for (final var piece : token.get("PersonaPieces")) {
          final var personaPiece = new PersonaPiece(
            piece.get("PieceId").textValue(),
            piece.get("PieceType").textValue(),
            piece.get("PackId").textValue(),
            piece.get("IsDefault").booleanValue(),
            piece.get("ProductId").textValue()
          );
          newSkin.addPersonaPieces(personaPiece);
        }
      }
      if (token.has("PieceTintColors")) {
        for (final var node : token.get("PieceTintColors")) {
          final var colors = new ArrayList<String>();
          for (final var color : node.get("Colors")) {
            colors.add(color.textValue());
          }
          newSkin.addTintColors(new PersonaPiece.Tint(
            node.get("PieceType").textValue(),
            colors
          ));
        }
      }
    }
    newSkin.capeData(ImageData.from(token, "Cape"));
    if (token.has("SkinGeometryData")) {
      newSkin.geometryData(new String(Base64.getDecoder().decode(token.get("SkinGeometryData").textValue()), StandardCharsets.UTF_8));
    }
    if (token.has("SkinAnimationData")) {
      newSkin.animationData(new String(Base64.getDecoder().decode(token.get("SkinAnimationData").textValue()), StandardCharsets.UTF_8));
    }
    if (token.has("PremiumSkin")) {
      newSkin.premium(token.get("PremiumSkin").booleanValue());
    }
    if (token.has("CapeOnClassicSkin")) {
      newSkin.capeOnClassic(token.get("CapeOnClassicSkin").booleanValue());
    }
    if (token.has("CapeId")) {
      newSkin.capeId(token.get("CapeId").textValue());
    }
    if (token.has("SkinColor")) {
      newSkin.skinColor(token.get("SkinColor").textValue());
    }
    if (token.has("ArmSize")) {
      newSkin.armSize(token.get("ArmSize").textValue());
    }
    return newSkin;
  }

  /**
   * adds the persona piece.
   *
   * @param personaPiece the persona piece to add.
   */
  public void addPersonaPieces(@NotNull final PersonaPiece personaPiece) {
    this.personaPieces.add(personaPiece);
  }

  /**
   * adds the tint color.
   *
   * @param tintColor the tint color to add.
   */
  public void addTintColors(@NotNull final PersonaPiece.Tint tintColor) {
    this.tintColors.add(tintColor);
  }

  /**
   * a class that represents skin animations.
   */
  public final record Animation(
    @NotNull ImageData imageData,
    int type,
    float frames,
    int expression
  ) {

    /**
     * creates an skin animation from animation token.
     *
     * @param token the token to create.
     *
     * @return skin.
     */
    @NotNull
    public static Animation from(@NotNull final JsonNode token) {
      final var frames = token.get("Frames").floatValue();
      final var type = token.get("Type").intValue();
      final var data = Base64.getDecoder().decode(token.get("Image").textValue());
      final var width = token.get("ImageWidth").intValue();
      final var height = token.get("ImageHeight").intValue();
      var expression = 0;
      if (token.hasNonNull("ExpressionType")) {
        expression = token.get("ExpressionType").intValue();
      }
      return new Animation(new ImageData(width, height, data), type, frames, expression);
    }
  }
}
