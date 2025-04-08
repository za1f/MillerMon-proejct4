package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NPC_Prof extends Entity{

    public boolean firstTalk = true;

    public NPC_Prof(GamePanel gp){
        super(gp);

        direction = "up";
        speed = 0;

        getProfImage();
        setDialogue();
    }

    public void getProfImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/profup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/profup1.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/npc/profup1.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/npc/profup1.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/profdown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/profdown1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/npc/profdown1.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/npc/profdown1.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/profleft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/profleft1.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/profright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/profright1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void speak(){
        facePlayer();
        startDialogue(this,dialogueSet);

    }

    @Override
    public void startDialogue(Entity entity, int setNum){
            gp.gameState = gp.dialogueStateProf;
            gp.ui.npc = entity;
            dialogueSet = setNum;
    }

    public void setDialogue(){
        dialogues[0][0] = "Hello  young  one!";
        dialogues[0][1] = "Oh,  you're  asking  who  i  am?";
        dialogues[0][2] = "I'm  Professor  Miller!  I'm  here  in  place \n of  Professor  Hazel.";
        dialogues[0][3] = "Where'd  she  go?  I  have  no  clue.";
        dialogues[0][4] = "But  i'm  guessing  you're  here  to  get \n your  first  Pokémon?";
        dialogues[0][5] = "Alrighty  then,  choose  wisely!";

    }



}
