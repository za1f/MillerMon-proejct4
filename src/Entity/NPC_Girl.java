package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_Girl extends Entity{
    public NPC_Girl(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 3;

        getGirlImage();
    }

    public void getGirlImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkup2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkup3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkup4.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkdown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkdown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkdown3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkdown4.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkleft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkleft2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/girlwalkright2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction(){

        actionLockCounter++;

        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1; //pick a random number from 1-100;

            if (i <= 25){
                direction = "up";
            }
            if (i > 25 && i <= 50){
                direction = "down";
            }
            if (i > 50 && i <= 75){
                direction = "left";
            }
            if (i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

}
