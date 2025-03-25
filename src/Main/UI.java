package Main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font fontGame;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";

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
}
