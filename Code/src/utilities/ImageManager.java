package utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    public final static String path = "Code/images/";
    public final static String ext = ".png";

    public static BufferedImage loadImage(String file) throws IOException {
        BufferedImage img;
        img = ImageIO.read(new File(path + file + ext));
        return img;
    }
}