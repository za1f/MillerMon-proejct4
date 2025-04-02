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
        dialogues[0][0] = "Hey  there  hot  stuff.";
        dialogues[0][1] = "Oh,  you  want  to  know  about  this  town?";
        dialogues[0][2] = "The  building  with  the  gray  roof  is \n Professor  Hazel's  lab.";
        dialogues[0][3] = "You  should  probably  go  there  to  get \n your  first  Pokémon.";
        dialogues[0][4] = "That  blue  one  is  the  Poké-Mart.";
        dialogues[0][5] = "You  go  there  to  buy  stuff  with  the \n money  you  earn  from  trainer  battles.";
        dialogues[0][6] = "And that  red  one  is  the  Poké-Center.";
        dialogues[0][7] = "You  go  there  to  heal  your  Pokémon \n after  battle.";
        dialogues[0][8] = "Now  get  lost  loser.";

        dialogues[1][0] = "Oh nice i see you've picked *BLANK* as your first pokemon";
        dialogues[1][1] = "Anyways, you should probably begin your journey now";
        dialogues[1][2] = "Head over to the bottom right and enter ROUTE 1";
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
        facePlayer();
        startDialogue(this,dialogueSet);

    }


}
