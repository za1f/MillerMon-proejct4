package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_Girl extends Entity{

    int count = 0;
    public NPC_Girl(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 2;


        getGirlImage();
        setDialogue();
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

    public void setDialogue(){
        dialogues[0] = "Hey  there  hot  stuff.";
        dialogues[1] = "Oh,  you  want  to  know  about  this  town?";
        dialogues[2] = "The  building  with  the  gray  roof  is \n Professor  Hazel's  lab.";
        dialogues[3] = "That  red  one  is  the  PokeCenter.";
        dialogues[4] = "And  that  blue  one  is  the  PokeShop.";
        dialogues[5] = "Now get lost loser.";
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
    public void speak(){
        super.speak();
        if (count == 0){
            gp.stopSE();
            gp.playSE(6);
            count++;
        }else if (count == 1){
            gp.stopSE();
            gp.playSE(6);
            count++;
        } else if (count == 2){
            gp.stopSE();
            gp.playSE(7);
            count++;
        } else if (count == 3 || count == 4){
            gp.stopSE();
            gp.playSE(7);
            count++;
        } else {
            gp.stopSE();
            gp.playSE(6);
            count = 0;
        }
    }


}
