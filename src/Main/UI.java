package Main;

import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {
    public Entity npc;
    GamePanel gp;
    Graphics2D g2;
    Font fontGame;
    int charIndex = 0;
    String combinedText = "";
    boolean t1 = true, t2 = false, t3 = false;
    int circle = 0;
    boolean rect = false;


    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNum = 0;
    int counter = 0;
    int battleCounter = 0;
    int reps = 0;
    int radius = 50; // Initial radius
    boolean playMusic = false;
    int rectCount = 0;


    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/pokeFont.ttf");
            fontGame = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(fontGame);
        g2.setColor(Color.white);

        //title state
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        //play state
        if (gp.gameState == gp.playState){
            //do playstate stuff
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //dialogue state
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if (gp.gameState == gp.transitionState){
            drawTransition();
        }
        if (gp.gameState == gp.battleTransitionState){
            drawBattleTransition();

        }
    }

    public void drawTitleScreen(){
        String text;
        int x;
        int y = gp.tileSize*3;
        y += gp.tileSize*2;
        try {
            g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/titleScreen/titleScreen.png")), 0, 0, 768, 576, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,36F));

        text = "NEW  GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 2;
        g2.setColor(Color.gray);
        g2.drawString(text,x+3,y+3);
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
        if (commandNum == 0){
            g2.setColor(Color.gray);
            g2.drawString("   [", x-gp.tileSize+3, y+3);
            g2.setColor(Color.white);
            g2.drawString("   [", x-gp.tileSize, y);
            g2.setColor(Color.gray);
            g2.drawString("]", x+gp.tileSize*6+3, y+3);
            g2.setColor(Color.white);
            g2.drawString("]", x+gp.tileSize*6, y);
        }

        text = "LOAD  GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.gray);
        g2.drawString(text,x+3,y+3);
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
        if (commandNum == 1){
            g2.setColor(Color.gray);
            g2.drawString("   [", x-gp.tileSize+3 , y+3);
            g2.setColor(Color.white);
            g2.drawString("   [", x-gp.tileSize, y);
            g2.setColor(Color.gray);
            g2.drawString("]", x+gp.tileSize*7+3 - 10, y+3);
            g2.setColor(Color.white);
            g2.drawString("]", x+gp.tileSize*7 - 10, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.gray);
        g2.drawString(text,x+3,y+3);
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
        if (commandNum == 2){
            g2.setColor(Color.gray);
            g2.drawString("   [", x-gp.tileSize+3, y+3);
            g2.setColor(Color.white);
            g2.drawString("   [", x-gp.tileSize, y);
            g2.setColor(Color.gray);
            g2.drawString("]", x+gp.tileSize*3+3, y+3);
            g2.setColor(Color.white);
            g2.drawString("]", x+gp.tileSize*3, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        text = "(c) 2025  ZAIF'S  PROJECT  4  inc.";
        x = getXForCenteredText(text);
        y += gp.tileSize*3 - 10;
        g2.setColor(Color.gray);
        g2.drawString(text,x+3,y+3);
        g2.setColor(new Color(240, 240,240));
        g2.drawString(text,x,y);




    }

    public void drawDialogueScreen(){

        //window
        int x = gp.tileSize;
        int y = gp.tileSize * 8;
        int width = gp.screenWidth - (gp.tileSize* 2);
        int height = gp.tileSize*3;

        drawSubWindow(x, y, width, height);
        Color textC = new Color(160, 70, 73);
        g2.setColor(textC);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
        x+= gp.tileSize;
        y+= gp.tileSize;

        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null){

            //currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            char character[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

            if (charIndex < character.length){
                String s = String.valueOf(character[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if (gp.keyH.enterPressed == true){

                charIndex = 0;
                combinedText = "";

                if (gp.gameState == gp.dialogueState){
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        }
        else {
            npc.dialogueIndex = 0;
            if (gp.gameState == gp.dialogueState){
                gp.gameState = gp.playState;
            }
        }



        for (String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+= 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(239, 248, 255, 235);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 100, 100);

        c = new Color(188,211,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(10));
        g2.drawRoundRect(x+3,y+3,width-6,height-8,100,100);

        c = new Color(67,86,122);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x-2,y-2,width+2,height+1,100,100);


    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        g2.setColor(Color.BLACK);
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
        g2.fillRoundRect(2, 40, 404,gp.tileSize + 44, 45,45 );
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
        text = "CONTROLS:";
        g2.drawString(text, 12, gp.tileSize + 24);
        text = "UP/LEFT/DOWN/RIGHT:  W/A/S/D";
        g2.drawString(text, 12, gp.tileSize* 2);
        text = "INTERACT:  ENTER";
        g2.drawString(text, 12, gp.tileSize * 2 + 24);

    }

    public int getXForCenteredText (String text){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth /2 - length/2;
        return x;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;

    }

    public void drawTransition(){
        counter++;
        g2.setColor(new Color(0,0,0, counter *5));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        if (counter == 50){
            counter = 0;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
            gp.gameState = gp.playState;
        }
    }

    public void drawBattleTransition(){
        if (!playMusic){
            playMusic = true;
            gp.stopMusic();
            gp.playMusic(13);
        }

        if (rect){
            rectCount += 2;
            g2.setColor(Color.black);
            g2.fillRect(0,0 - (rectCount*5),gp.screenWidth,gp.screenHeight/2);
            g2.fillRect(0,(0 + gp.screenHeight/2) + (rectCount*5),gp.screenWidth,gp.screenHeight/2);
            if (rectCount == 60){
                circle = 0;
                rectCount = 0;
                reps = 0;
                gp.gameState = gp.playState;
                gp.stopMusic();
                playMusic = false;
                gp.playMusic(1);
                rect = false;
            }

        } else if (reps == 3){
            g2.setColor(Color.black);
            g2.fillOval(gp.screenWidth/2 - (gp.tileSize/2) - ((15 * circle) / 2), gp.screenHeight/2 - (gp.tileSize/2) - ((15 * circle) / 2), radius + (15 * circle),radius + (15 * circle));
            circle++;
            if (circle == 90){
                rect = true;

            }

        } else if (t1){
            battleCounter+=5;
            g2.setColor(new Color(66,66,66, battleCounter * 5));
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
            if (battleCounter == 50){
                battleCounter = 0;
                t1 = false;
                t2 = true;
            }
        } else if (t2){
            g2.setColor(new Color(66,66,66, 250 - (battleCounter * 5)));
            g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
            battleCounter+=5;
            if (battleCounter == 50){
                battleCounter = 0;
                t2 = false;
                t1 = true;
                reps++;

            }
        }

    }
}
