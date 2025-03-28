package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;

    public int screenX;
    public int screenY;
    int standCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 12;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        if (gp.currentMap == 1){
            worldX = gp.tileSize * 7;
            worldY = gp.tileSize * 13;
        } else if (gp.currentMap == 0){
            worldX = gp.tileSize * 9;
            worldY = gp.tileSize * 7;
        }

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

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check obj collision
            int objIndex = gp.cChecker.checkObj(this, true);
            pickUpPoke(objIndex);
            //Check npc collision

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            //check event
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE!!!
            if (collisionOn == false && keyH.enterPressed == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            gp.keyH.enterPressed = false;

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
        else {
            standCounter++;
            if (standCounter == 20){
                spriteNum = 1;
                standCounter = 0;
            }

        }
    }

    public void pickUpPoke (int i){
        if (i != 999){

        }
    }

    public void interactNPC(int i){
        if (i != 999){
            if (gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
            gp.keyH.enterPressed = false;
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
