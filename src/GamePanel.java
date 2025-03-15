import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //settings and stuff
    final int originalTileSize = 16; //16x16 tiles AND STUFF
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // its 48 the ACTUAL tile size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 pix
    final int screenHeight = tileSize * maxScreenRow;//576 pix

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

    }
}
