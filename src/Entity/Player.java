package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkup2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkup3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkup4.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkdown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkdown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkdown3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkedown4.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkuleft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkleft2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkright2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPressed == true){
            direction = "up";
            y -= speed;
        }
        else if (keyH.downPressed == true){
            direction = "down";
            y += speed;
        }
        if (keyH.leftPressed == true){
            direction = "left";
            x -= speed;
        }
        else if (keyH.rightPressed == true){
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, x, y, 48,48, null);





    }
}
