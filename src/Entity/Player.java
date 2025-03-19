package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public int screenX;
    public int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 9;
        worldY = gp.tileSize * 7;
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
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkdown4.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkleft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkleft2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/playerwalkright2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            }
            else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;

            if (spriteCounter > 8){
                if (spriteNum ==1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 3;
                } else if (spriteNum ==3) {
                    spriteNum = 4;
                }
                else if (spriteNum == 4){
                    spriteNum = 1;
                }

                if(horizon == 1){
                    horizon = 2;
                } else if (horizon == 2){
                    horizon = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up3;
                }
                if(spriteNum == 4){
                    image = up4;
                }

                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down3;
                }
                if(spriteNum == 4){
                    image = down4;
                }

                break;
            case "left":
                if(horizon == 1){
                    image = left1;
                }
                if(horizon == 2){
                    image = left2;
                }
                break;
            case "right":
                if(horizon == 1){
                    image = right1;
                }
                if(horizon == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, 48, 48, null);
    }
}
