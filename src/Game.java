import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int width = 480, height = 480;
    public Player player;

    public World world;

    public Game() throws IOException {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(width, height));

        new Spritesheet();

        player = new Player(32,32);
        world = new World();
    }

    public void tick() {
        player.tick();
    }

    public void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        //Desenha novos graficos
        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0, 135 ,13));
        g.fillRect(0, 0, width, height);

        player.render(g);
        world.render(g);

        bs.show();

    }

    public static void main(String[] args) throws IOException {

        Game game = new Game();
        JFrame frame = new JFrame();

        //dimensões do game
        frame.add(game);
        //Titulo que fica em cima do game
        frame.setTitle("Mini Zelda");
        //empacotar tudo e ficar do tamanho certo
        frame.pack();

        //deixar a janela centralizada
        frame.setLocationRelativeTo(null);
        //Quando fechar finalizar o processo do Java também
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Pra ver a janela
        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {

        while(true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = true;
        }else if(e.getKeyCode() == KeyEvent.VK_S) {
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = false;
        }else if(e.getKeyCode() == KeyEvent.VK_S) {
            player.down = false;
        }
    }
}
