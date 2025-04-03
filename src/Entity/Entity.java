package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int horizon = 1;

    public Rectangle solidArea = new Rectangle(0,0,40,40);
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;
    public boolean wildEncounter = false;

    public int actionLockCounter = 0;

    public String[][] dialogues = new String[20][20];
    public int dialogueSet = 0;
    public int dialogueIndex = 0;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){

    }
    public void speak(){

    }

    public void facePlayer() {
        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void startDialogue(Entity entity, int setNum){
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }

    public void update(){
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObj(this, false);
        gp.cChecker.checkPlayer(this);

        if (collisionOn == false){
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

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

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
            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
        }
    }
}
