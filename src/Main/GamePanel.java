package Main;

import Entity.Entity;
import Entity.Player;
import jdk.jfr.Event;
import object.SuperObject;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;

public class GamePanel extends JPanel implements Runnable {
    //settings and stuff
    final int originalTileSize = 16; //16x16 tiles AND STUFF
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // its 48 the ACTUAL tile size
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //768 pix
    public final int screenHeight = tileSize * maxScreenRow;//576 pix

    //world settings
    public final int maxWorldCol = 24;
    public final int maxWorldRow = 20;
    //public final int worldWidth = tileSize * maxWorldCol;
    //public final int worldHeight = tileSize * maxWorldRow;

    //fps
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    Thread gameThread;

    // entity + objects
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    //game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    Color color = new Color(171,222,98);
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(color);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(1);
        gameState = playState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer+= currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount =0;
                timer = 0;
            }

        }
    }
    public void update(){

        if (gameState == playState){

            //player
            player.update();
            //npc
            for (int i = 0; i < npc.length; i++){
                if (npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState){

        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); //parent class Jpanel
        Graphics2D g2 = (Graphics2D) g; //change g to Graphics 2d bc it has more functions
        //debug

        long drawStart = 0;
        if (keyH.checkDrawTime == true){
            drawStart = System.nanoTime();
        }




        tileM.draw(g2);

        for (int i = 0; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //NPc
        for (int i = 0; i < npc.length; i++){
            if (npc[i] != null){
                npc[i].draw(g2);
            }
        }

        //Player
        player.draw(g2);
        //UI
        ui.draw(g2);

        if (keyH.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void pauseMusic(){
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }

}
