package Main;

import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font PKMNRBYGSC40;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        PKMNRBYGSC40 = new Font("PKMN RBYGSC", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(PKMNRBYGSC40);
        g2.setColor(Color.white);
        if (gp.gameState == gp.playState){
            //do playstate stuff
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

    }

    public void drawPauseScreen(){
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
