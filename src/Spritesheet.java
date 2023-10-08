import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spritesheet {

    public static BufferedImage spritesheet;

    public static BufferedImage player_front, tilewall;

    public Spritesheet() throws IOException {
        spritesheet = ImageIO.read(getClass().getResource("spritesheet.png"));
        player_front = Spritesheet.getSprite(0, 11, 16, 16);
        tilewall = Spritesheet.getSprite(172,185,16,16);
    }


    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }

}
