import java.awt.*;

public class Blocks extends Rectangle{

    public Blocks(int x, int y) {
        super(x,y, 32,32);
    }

    public void render(Graphics g) {
        g.drawImage(Spritesheet.tilewall,x,y,32,32, null);
    }
}
