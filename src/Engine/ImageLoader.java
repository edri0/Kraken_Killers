package Engine;

import Utils.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

// Handles loading images safely, works in IDE and JAR
public class ImageLoader {

    // Load image with default transparent color
    public static BufferedImage load(String imageFileName) {
        return load(imageFileName, Config.TRANSPARENT_COLOR);
    }

    // Load image with specified transparent color
    public static BufferedImage load(String imageFileName, Color transparentColor) {
        try (InputStream is = ImageLoader.class.getClassLoader().getResourceAsStream(imageFileName)) {
            if (is == null) {
                throw new RuntimeException("Image file not found in classpath: " + imageFileName);
            }
            BufferedImage img = ImageIO.read(is);
            return ImageUtils.transformColorToTransparency(img, transparentColor);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + imageFileName, e);
        }
    }

    // Load a subimage
    public static BufferedImage loadSubImage(String imageFileName, int x, int y, int width, int height) {
        return loadSubImage(imageFileName, Config.TRANSPARENT_COLOR, x, y, width, height);
    }

    public static BufferedImage loadSubImage(String imageFileName, Color transparentColor, int x, int y, int width, int height) {
        BufferedImage img = load(imageFileName, transparentColor);
        return img.getSubimage(x, y, width, height);
    }
}