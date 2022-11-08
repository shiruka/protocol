package io.github.shiruka.protocol.data;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Base64;
import org.jetbrains.annotations.NotNull;

/**
 * a record class that represents image data.
 *
 * @param width the width.
 * @param height the height.
 * @param image the image.
 */
public record ImageData(int width, int height, byte[] image) {
  /**
   * the empty image data.
   */
  public static final ImageData EMPTY = new ImageData(0, 0, new byte[0]);

  /**
   * creates an image data from token.
   *
   * @param token the token to create.
   * @param name the name to create.
   *
   * @return image data.
   */
  @NotNull
  public static ImageData from(
    @NotNull final JsonNode token,
    @NotNull final String name
  ) {
    if (!token.has(name + "Data")) {
      return ImageData.EMPTY;
    }
    final var skinImage = Base64
      .getDecoder()
      .decode(token.get(name + "Data").textValue());
    if (token.has(name + "ImageHeight") && token.has(name + "ImageWidth")) {
      final var width = token.get(name + "ImageWidth").intValue();
      final var height = token.get(name + "ImageHeight").intValue();
      return new ImageData(width, height, skinImage);
    } else {
      return switch (skinImage.length / 4) {
        case 2048 -> new ImageData(64, 32, skinImage);
        case 4096 -> new ImageData(64, 64, skinImage);
        case 8192 -> new ImageData(128, 64, skinImage);
        case 16384 -> new ImageData(128, 128, skinImage);
        default -> ImageData.EMPTY;
      };
    }
  }
}
