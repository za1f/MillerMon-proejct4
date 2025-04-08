package Main;

import Entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.BiFunction;
import Pokemon.*;

public class UI {
    int row1 = 0;
    int timer = 0;
    ArrayList<Pokemon> extras = new ArrayList<>();
    BufferedImage pokeball;
    int mTimer = 0;
    boolean damageDone = true;
    boolean damageRec = true;
    public BufferedImage rec;
    public int ranM = (int) (Math.random() * 4);
    int oTimer = 0;
    boolean nextTurn = false;
    public BufferedImage physical;
    public int cTimer = 0;
    public boolean moveUsed = true;
    public String nameMove;
    public boolean attackMenu = false;
    public BufferedImage select;
    public int bCursX = 0;
    public int bCursY = 0;
    public int battleChoice = 0;
    int wildOffset = 0;
    Pokemon outPoke;
    public Entity npc;
    GamePanel gp;
    Graphics2D g2;
    Font fontGame;
    int charIndex = 0;
    String combinedText = "";
    boolean t1 = true, t2 = false, t3 = false;
    int circle = 0;
    boolean rect = false;
    int colorN = 0;
    String choice = "";
    int teamNum = 0;


    public int slotCol = 0;
    public int slotRow = 0;

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
    boolean firstChoice = true;
    int startTran = 0;
    BufferedImage startT;
    BufferedImage startR;
    BufferedImage startF;
    public int selectNum = 1;
    BufferedImage arrow;
    public String textS = "Choose  a  starter...";
    int startAnim = 0;


    public UI(GamePanel gp) {
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


    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(fontGame);
        g2.setColor(Color.white);

        //title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //play state
        if (gp.gameState == gp.playState) {
            //do playstate stuff
        }
        if (gp.gameState == gp.pauseState) {
            viewPokemonTeam();
        }
        //dialogue state
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }
        if (gp.gameState == gp.battleTransitionState) {
            drawBattleTransition();
        }
        if (gp.gameState == gp.choiceState) {
            trans2();
        }
        if (gp.gameState == gp.dialogueStateProf) {
            profDialogue();
        }
        if (gp.gameState == gp.choiceStateTrans) {
            choiceTrans();
        }
        if (gp.gameState == gp.selectionState) {
            starters();
        }
        if (gp.gameState == gp.endStateStart) {
            drawTransitionEnd();
        }
        if (gp.gameState == gp.battleState) {
            wildBattle();

        }
        if (gp.gameState == gp.postSelection) {
            postStarterText();
        }
        if (gp.gameState == gp.wildBattle) {
            battle();
        }
        if (gp.gameState == gp.pcState){
            pc();
        }
    }

    public void drawTitleScreen() {
        String text;
        int x;
        int y = gp.tileSize * 3;
        y += gp.tileSize * 2;
        try {
            g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/titleScreen/titleScreen.png")), 0, 0, 768, 576, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));

        text = "NEW  GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 2;
        g2.setColor(Color.gray);
        g2.drawString(text, x + 3, y + 3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(Color.gray);
            g2.drawString("   [", x - gp.tileSize + 3, y + 3);
            g2.setColor(Color.white);
            g2.drawString("   [", x - gp.tileSize, y);
            g2.setColor(Color.gray);
            g2.drawString("]", x + gp.tileSize * 6 + 3, y + 3);
            g2.setColor(Color.white);
            g2.drawString("]", x + gp.tileSize * 6, y);
        }

        text = "LOAD  GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.gray);
        g2.drawString(text, x + 3, y + 3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(Color.gray);
            g2.drawString("   [", x - gp.tileSize + 3, y + 3);
            g2.setColor(Color.white);
            g2.drawString("   [", x - gp.tileSize, y);
            g2.setColor(Color.gray);
            g2.drawString("]", x + gp.tileSize * 7 + 3 - 10, y + 3);
            g2.setColor(Color.white);
            g2.drawString("]", x + gp.tileSize * 7 - 10, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.setColor(Color.gray);
        g2.drawString(text, x + 3, y + 3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.setColor(Color.gray);
            g2.drawString("   [", x - gp.tileSize + 3, y + 3);
            g2.setColor(Color.white);
            g2.drawString("   [", x - gp.tileSize, y);
            g2.setColor(Color.gray);
            g2.drawString("]", x + gp.tileSize * 3 + 3, y + 3);
            g2.setColor(Color.white);
            g2.drawString("]", x + gp.tileSize * 3, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        text = "(c) 2025  ZAIF'S  PROJECT  4  inc.";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3 - 10;
        g2.setColor(Color.gray);
        g2.drawString(text, x + 3, y + 3);
        g2.setColor(new Color(240, 240, 240));
        g2.drawString(text, x, y);


    }

    public void drawDialogueScreen() {

        //window
        int x = gp.tileSize;
        int y = gp.tileSize * 8;
        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);
        Color textC = new Color(160, 70, 73);
        g2.setColor(textC);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
        x += gp.tileSize;
        y += gp.tileSize;

        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {

            //currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            char character[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

            if (charIndex < character.length) {
                String s = String.valueOf(character[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if (gp.keyH.enterPressed == true) {

                charIndex = 0;
                combinedText = "";

                if (gp.gameState == gp.dialogueState) {
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        } else {
            npc.dialogueIndex = 0;
            if (gp.gameState == gp.dialogueState) {
                gp.gameState = gp.playState;
            }
        }


        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(239, 248, 255, 235);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 100, 100);

        c = new Color(188, 211, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(10));
        g2.drawRoundRect(x + 3, y + 3, width - 6, height - 8, 100, 100);

        c = new Color(67, 86, 122);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x - 2, y - 2, width + 2, height + 1, 100, 100);
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        g2.setColor(Color.BLACK);
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
        g2.fillRoundRect(2, 40, 404, gp.tileSize + 44, 45, 45);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
        text = "CONTROLS:";
        g2.drawString(text, 12, gp.tileSize + 24);
        text = "UP/LEFT/DOWN/RIGHT:  W/A/S/D";
        g2.drawString(text, 12, gp.tileSize * 2);
        text = "INTERACT:  ENTER";
        g2.drawString(text, 12, gp.tileSize * 2 + 24);

    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

    }

    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (counter == 50) {
            counter = 0;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
            gp.gameState = gp.playState;
        }
    }

    public void drawBattleTransition() {
        if (!playMusic) {
            playMusic = true;
            gp.stopMusic();
            gp.playMusic(13);
        }

        if (rect) {
            rectCount += 2;
            g2.setColor(Color.white);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setColor(Color.black);
            g2.fillRect(0, 0 - (rectCount * 5), gp.screenWidth, gp.screenHeight / 2);
            g2.fillRect(0, (0 + gp.screenHeight / 2) + (rectCount * 5), gp.screenWidth, gp.screenHeight / 2);
            if (rectCount == 60) {
                circle = 0;
                rectCount = 0;
                reps = 0;
                gp.gameState = gp.battleState;
                playMusic = false;
                rect = false;
            }

        } else if (reps == 3) {
            g2.setColor(Color.black);
            g2.fillOval(gp.screenWidth / 2 - (gp.tileSize / 2) - ((15 * circle) / 2), gp.screenHeight / 2 - (gp.tileSize / 2) - ((15 * circle) / 2), radius + (15 * circle), radius + (15 * circle));
            circle++;
            if (circle == 90) {
                rect = true;

            }

        } else if (t1) {
            battleCounter += 5;
            g2.setColor(new Color(66, 66, 66, battleCounter * 5));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            if (battleCounter == 50) {
                battleCounter = 0;
                t1 = false;
                t2 = true;
            }
        } else if (t2) {
            g2.setColor(new Color(66, 66, 66, 250 - (battleCounter * 5)));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            battleCounter += 5;
            if (battleCounter == 50) {
                battleCounter = 0;
                t2 = false;
                t1 = true;
                reps++;

            }
        }

    }

    public void profDialogue() {
        //window
        int x = gp.tileSize;
        int y = gp.tileSize * 8;
        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);
        Color textC = new Color(160, 70, 73);
        g2.setColor(textC);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
        x += gp.tileSize;
        y += gp.tileSize;

        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {

            //currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            char character[] = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

            if (charIndex < character.length) {
                String s = String.valueOf(character[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if (gp.keyH.enterPressed == true) {

                charIndex = 0;
                combinedText = "";

                if (gp.gameState == gp.dialogueStateProf) {
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        } else {
            npc.dialogueIndex = 0;
            if (gp.gameState == gp.dialogueStateProf && firstChoice) {
                gp.gameState = gp.choiceStateTrans;
                firstChoice = false;
            } else {
                gp.gameState = gp.playState;
            }
        }


        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void trans2() {
        g2.setColor(new Color(0 + (colorN * 5), 0 + (colorN * 5), 0 + (colorN * 5)));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        colorN += 1;
        if (colorN == 51) {
            gp.gameState = gp.selectionState;
            colorN = 0;
        }

    }

    public void choiceTrans() {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, 0 + (startTran * 5), gp.screenHeight / 8);
        g2.fillRect(gp.screenWidth - (startTran * 5), gp.screenHeight / 8, 0 + (startTran * 5), gp.screenHeight / 8);
        g2.fillRect(0, gp.screenHeight / 4, 0 + (startTran * 5), gp.screenHeight / 8);
        g2.fillRect(gp.screenWidth - (startTran * 5), (gp.screenHeight / 8) * 3, 0 + (startTran * 5), gp.screenHeight / 8);
        g2.fillRect(0, gp.screenHeight / 2, 0 + (startTran * 5), gp.screenHeight / 8);
        g2.fillRect(gp.screenWidth - (startTran * 5), (gp.screenHeight / 8) * 5, 0 + (startTran * 5), gp.screenHeight / 8);
        g2.fillRect(0, (gp.screenHeight / 4) * 3, 0 + (startTran * 5), gp.screenHeight / 8);
        g2.fillRect(gp.screenWidth - (startTran * 5), (gp.screenHeight / 8) * 7, 0 + (startTran * 5), gp.screenHeight / 8);
        startTran += 2;
        if (startTran == 200) {
            gp.gameState = gp.choiceState;
            startTran = 0;
        }
    }

    public void starters() {
        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        try {
            startT = ImageIO.read(getClass().getResourceAsStream("/pokemon/torchicSTART.png"));
            startF = ImageIO.read(getClass().getResourceAsStream("/pokemon/froakieSTART.png"));
            startR = ImageIO.read(getClass().getResourceAsStream("/pokemon/rowletSTART.png"));
            arrow = ImageIO.read(getClass().getResourceAsStream("/menuImages/selectArrow.png"));
            select = ImageIO.read(getClass().getResourceAsStream("/menuImages/selectSide.png"));
            physical = ImageIO.read(getClass().getResourceAsStream("/menuImages/attackPART.png"));
            rec = ImageIO.read(getClass().getResourceAsStream("/menuImages/attackREC.png"));
            pokeball = ImageIO.read(getClass().getResourceAsStream("/objects/pokeball.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        int scaleT = 2;
        int scaleF = 2;
        int scaleR = 2;
        g2.drawImage(startT, 95, (gp.screenHeight / 2 - 48) - 95, 59 * scaleT, 96 * scaleT, null);
        g2.drawImage(startF, 295, (gp.screenHeight / 2) - 100, 64 * scaleF, 74 * scaleF, null);
        g2.drawImage(startR, 495, (gp.screenHeight / 2) - 100, 92 * scaleR, 72 * scaleR, null);

        starterText();
        if (selectNum == 1) {
            g2.drawImage(arrow, 345, (gp.screenHeight / 2) - 150, 48, 48, null);
        } else if (selectNum == 2) {
            g2.drawImage(arrow, 590, (gp.screenHeight / 2) - 160, 48, 48, null);
        } else if (selectNum == 0) {
            g2.drawImage(arrow, 115, (gp.screenHeight / 2) - 180, 48, 48, null);
        }


    }


    public void starterText() {
        int x = gp.tileSize;
        int y = gp.tileSize * 8;
        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);
        Color textC = new Color(160, 70, 73);
        g2.setColor(textC);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
        x += gp.tileSize;
        y += gp.tileSize;

        if (textS != null) {

            char character[] = textS.toCharArray();

            if (charIndex < character.length) {
                String s = String.valueOf(character[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if (gp.keyH.enterPressed == true) {
                textS = null;
                charIndex = 0;
                combinedText = "";


                if (gp.gameState == gp.selectionState) {
                    gp.keyH.enterPressed = false;
                }
            }
        } else {
            if (gp.gameState == gp.selectionState) {
                gp.gameState = gp.postSelection;
            }
        }


        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawTransitionEnd() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
        }
    }

    public void battleText() {

        int x = 0;
        int y = gp.screenHeight - (gp.tileSize * 3 + 24);
        ;
        int width = (gp.screenWidth / 2);
        int height = gp.tileSize * 3 + 24;

        String Btext = "Go  get  'em, " + outPoke.name() + ".";
        drawBattleWindow(x, y, width, height);
        Color textC = new Color(0, 9, 14);
        g2.setColor(textC);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
        x += gp.tileSize;
        y += gp.tileSize;
        if (Btext != null) {

            char character[] = Btext.toCharArray();

            if (charIndex < character.length) {
                String s = String.valueOf(character[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if (gp.keyH.enterPressed == true) {
                combinedText = "";
                charIndex = 0;
                gp.gameState = gp.wildBattle;
                if (gp.gameState == gp.battleState) {
                    gp.keyH.enterPressed = false;
                }
            }
        }


        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void wildBattle() {
        int y = gp.screenHeight - (gp.tileSize * 3 + 24);
        g2.setColor(Color.white);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.drawImage(gp.player.randPoke.getF(), gp.screenWidth - (12 * wildOffset), 48, gp.tileSize * 4, gp.tileSize * 4, null);
        if (gp.player.team[0] != null && gp.player.team[0].hp() > 0){
            outPoke = gp.player.team[0];
        } else if (gp.player.team[1] != null && gp.player.team[1].hp() > 0){
            outPoke = gp.player.team[1];
        } else if (gp.player.team[2] != null && gp.player.team[2].hp() > 0){
            outPoke = gp.player.team[2];
        } else if (gp.player.team[3] != null && gp.player.team[3].hp() > 0){
            outPoke = gp.player.team[3];
        } else if (gp.player.team[4] != null && gp.player.team[4].hp() > 0){
            outPoke = gp.player.team[4];
        } else if (gp.player.team[5] != null && gp.player.team[5].hp() > 0){
            outPoke = gp.player.team[5];
        } else {
            System.out.println("You are stupid");
            System.exit(0);
        }


        //(12 * wildOffset) = (gp.tileSize * 5)
        if (wildOffset < 20) {
            wildOffset++;
        }
        drawBattleWindow(0, y, gp.screenWidth, gp.tileSize * 3 + 24);
        battleText();
    }

    public void viewPokemonTeam() {
        String name = null;
        //frame
        int frameX = 20;
        int frameY = 20;
        int fW = gp.screenWidth - 40;
        int fH = gp.screenHeight - 40;
        drawSubWindow(frameX, frameY, fW, fH);
        //slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;

        int SX = slotX;
        int SW = fW - 40;
        int SH = fH / 6 - 10;

        g2.setColor(new Color(136, 162, 191));
        g2.fillRoundRect(SX, slotY, SW, SH, 45, 45);
        g2.setColor(new Color(97, 122, 143));
        g2.fillRoundRect(SX, slotY + ((fH / 6 - 10)), SW, SH, 45, 45);
        g2.setColor(new Color(136, 162, 191));
        g2.fillRoundRect(SX, slotY + ((fH / 6 - 10) * 2), SW, SH, 45, 45);
        g2.setColor(new Color(97, 122, 143));
        g2.fillRoundRect(SX, slotY + ((fH / 6 - 10) * 3), SW, SH, 45, 45);
        g2.setColor(new Color(136, 162, 191));
        g2.fillRoundRect(SX, slotY + ((fH / 6 - 10) * 4), SW, SH, 45, 45);
        g2.setColor(new Color(97, 122, 143));
        g2.fillRoundRect(SX, slotY + ((fH / 6 - 10) * 5), SW, SH, 45, 45);

        if (gp.player.team[0] != null) {
            g2.setColor(new Color(207, 222, 255));
            g2.fillRoundRect(SX, slotY, SW, SH, 45, 45);
            g2.drawImage(gp.player.team[0].getI(), SX + 20, slotY + 20, gp.tileSize, gp.tileSize, null);
            g2.setColor(new Color(49, 57, 73));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            name = gp.player.team[0].name();
            g2.drawString(name, 240, 80);
            String level = "lvl: " + gp.player.team[0].level() + "";
            g2.setColor(Color.white);
            g2.drawString(level, 140, 80);
        }
        if (gp.player.team[1] != null) {
            g2.setColor(new Color(139, 147, 170));
            g2.fillRoundRect(SX, slotY + ((fH / 6 - 10)), SW, SH, 45, 45);
            g2.drawImage(gp.player.team[1].getI(), SX + 20, slotY + ((fH / 6 - 10)) + 20, gp.tileSize, gp.tileSize, null);
            g2.setColor(new Color(49, 57, 73));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            name = gp.player.team[1].name();
            g2.drawString(name, 240, 160);
            String level = "lvl: " + gp.player.team[1].level() + "";
            g2.setColor(Color.white);
            g2.drawString(level, 140, 160);
        }
        if (gp.player.team[2] != null) {
            g2.setColor(new Color(207, 222, 255));
            g2.fillRoundRect(SX, slotY + ((fH / 6 - 10) * 2), SW, SH, 45, 45);
            g2.drawImage(gp.player.team[2].getI(), SX + 20, slotY + ((fH / 6 - 10) * 2) + 20, gp.tileSize, gp.tileSize, null);
            g2.setColor(new Color(49, 57, 73));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            name = gp.player.team[2].name();
            g2.drawString(name, 240, 240);
            String level = "lvl: " + gp.player.team[2].level() + "";
            g2.setColor(Color.white);
            g2.drawString(level, 140, 240);

        }
        if (gp.player.team[3] != null) {
            g2.setColor(new Color(139, 147, 170));
            g2.fillRoundRect(SX, slotY + ((fH / 6 - 10) * 3), SW, SH, 45, 45);
            g2.drawImage(gp.player.team[3].getI(), SX + 20, slotY + ((fH / 6 - 10) * 3), gp.tileSize, gp.tileSize, null);
            g2.setColor(new Color(49, 57, 73));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            name = gp.player.team[3].name();
            g2.drawString(name, 240, 320);
            String level = "lvl: " + gp.player.team[3].level() + "";
            g2.setColor(Color.white);
            g2.drawString(level, 140, 320);
        }
        if (gp.player.team[4] != null) {
            g2.setColor(new Color(207, 222, 255));
            g2.fillRoundRect(SX, slotY + ((fH / 6 - 10) * 4), SW, SH, 45, 45);
            g2.drawImage(gp.player.team[4].getI(), SX + 20, slotY + ((fH / 6 - 10) * 4), gp.tileSize, gp.tileSize, null);
            g2.setColor(new Color(49, 57, 73));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            name = gp.player.team[4].name();
            g2.drawString(name, 240, 400);
            String level = "lvl: " + gp.player.team[4].level() + "";
            g2.setColor(Color.white);
            g2.drawString(level, 140, 400);
        }
        if (gp.player.team[5] != null) {
            g2.setColor(new Color(139, 147, 170));
            g2.fillRoundRect(SX, slotY + ((fH / 6 - 10) * 5), SW, SH, 45, 45);
            g2.drawImage(gp.player.team[5].getI(), SX + 20, slotY + ((fH / 6 - 10) * 5), gp.tileSize, gp.tileSize, null);
            g2.setColor(new Color(49, 57, 73));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            name = gp.player.team[5].name();
            g2.drawString(name, 240, 480);
            String level = "lvl: " + gp.player.team[5].level() + "";
            g2.setColor(Color.white);
            g2.drawString(level, 140, 480);
        }

        //cursor
        int CX = slotX;
        int CY = slotY + ((fH / 6 - 10) * slotRow);
        int CW = fW - 40;
        int CH = fH / 6 - 10;
        //draw it
        g2.setColor(new Color(0, 0, 0));

        g2.drawRoundRect(CX, CY, CW, CH, 45, 45);
    }

    public void postStarterText() {
        int x = gp.tileSize;
        int y = gp.tileSize * 8;
        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = gp.tileSize * 3;


        drawSubWindow(x, y, width, height);
        Color textC = new Color(160, 70, 73);
        g2.setColor(textC);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
        x += gp.tileSize;
        y += gp.tileSize;

        if (choice != null) {

            char character[] = choice.toCharArray();

            if (charIndex < character.length) {
                String s = String.valueOf(character[charIndex]);
                combinedText = combinedText + s;
                currentDialogue = combinedText;
                charIndex++;
            }

            if (gp.keyH.enterPressed == true) {
                choice = null;
                charIndex = 0;
                combinedText = "";


                if (gp.gameState == gp.postSelection) {
                    gp.keyH.enterPressed = false;
                }
            }
        } else {
            if (gp.gameState == gp.postSelection) {
                gp.gameState = gp.playState;
            }
        }


        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawBattleWindow(int x, int y, int width, int height) {
        Color c = new Color(236, 236, 236, 235);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 0, 0);

        c = new Color(124, 124, 124);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(10));
        g2.drawRoundRect(x + 3, y + 3, width - 6, height - 8, 0, 0);

        c = new Color(51, 51, 51);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x - 2, y - 2, width + 2, height + 1, 0, 0);
    }

    boolean start = true;

    public void battle() {
        if (outPoke.hp() > 0 ){
            int x = 0;
            int y = gp.screenHeight - (gp.tileSize * 3 + 24);
            int ty = gp.screenHeight - (gp.tileSize * 3 + 48);
            g2.setColor(Color.white);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.drawImage(gp.player.randPoke.getF(), gp.screenWidth - ((gp.tileSize * 5)), 48, gp.tileSize * 4, gp.tileSize * 4, null);
            drawBattleWindow(0, y, gp.screenWidth, gp.tileSize * 3 + 24);
            drawBattleWindow(x, y, gp.screenWidth / 2, gp.tileSize * 3 + 24);

            drawBattleWindow(gp.screenWidth / 2, y, gp.screenWidth / 4, (gp.tileSize * 3 + 24) / 2); // top left
            drawBattleWindow(gp.screenWidth / 2, y + ((gp.tileSize * 3 + 24) / 2), gp.screenWidth / 4, (gp.tileSize * 3 + 24) / 2); // bottom left
            drawBattleWindow(gp.screenWidth / 2 + (gp.screenWidth / 4), y, gp.screenWidth / 4, (gp.tileSize * 3 + 24) / 2); // top right
            drawBattleWindow(gp.screenWidth / 2 + (gp.screenWidth / 4), y + ((gp.tileSize * 3 + 24) / 2), gp.screenWidth / 4, (gp.tileSize * 3 + 24) / 2); // bottom right

            //text
            if (start) {                                 //288
                g2.drawImage(outPoke.getB(), 24 - ((gp.tileSize * 6) - (16 * startAnim)), gp.screenHeight - (gp.tileSize * 3 + 24) - (gp.tileSize * 5), gp.tileSize * 5, gp.tileSize * 5, null);
                startAnim++;
                if (startAnim == 19) {
                    start = false;
                    startAnim = 0;
                }
            } else {
                Color c = new Color(124, 124, 124);
                g2.setColor(c);
                g2.fillRect(gp.tileSize*9 + 24, gp.tileSize * 6, gp.tileSize*6,gp.tileSize+ gp.tileSize); //health bar
                c = new Color(236, 236, 236, 235);
                g2.setColor(c);
                g2.fillRect(gp.tileSize*9 + 28, gp.tileSize * 6 + 4, gp.tileSize*6 - 8,gp.tileSize - 8 + gp.tileSize); //health bar
                g2.setColor(new Color(0, 9, 14));
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
                g2.drawString(outPoke.name(),gp.tileSize*9 + 28 + 60, gp.tileSize * 6 + 4 + 16 );
                g2.drawString("lvl: " + outPoke.level() ,gp.tileSize*9 + 28 + 2, gp.tileSize * 6 + 4 + 16 );
                //hp bar
                g2.setColor(new Color(197, 105, 105));
                g2.fillRoundRect(gp.tileSize*9 + 28 + 16, gp.tileSize * 6 + 4 + 32, 256,10, 10,10);
                g2.setColor(new Color(139, 184, 118));
                g2.fillRoundRect(gp.tileSize*9 + 28 + 16, gp.tileSize * 6 + 4 + 32, outPoke.hp(),10, 10,10);

                //second bar
                c = new Color(124, 124, 124);
                g2.setColor(c);
                g2.fillRect(gp.tileSize*9 + 24 - (gp.tileSize* 8), gp.tileSize * 6 - (gp.tileSize* 5), gp.tileSize*6,gp.tileSize+ gp.tileSize); //health bar
                c = new Color(236, 236, 236, 235);
                g2.setColor(c);
                g2.fillRect(gp.tileSize*9 + 28 - (gp.tileSize* 8), gp.tileSize * 6 + 4 - (gp.tileSize* 5), gp.tileSize*6 - 8,gp.tileSize - 8 + gp.tileSize); //health bar
                g2.setColor(new Color(0, 9, 14));
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
                g2.drawString(gp.player.randPoke.name(), gp.tileSize*9 + 28 - (gp.tileSize* 8) + 58,gp.tileSize * 6 + 4 - (gp.tileSize* 5) + 16);
                g2.drawString("lvl: " + gp.player.randPoke.level(),gp.tileSize*9 + 28 - (gp.tileSize* 8) + 4,gp.tileSize * 6 + 4 - (gp.tileSize* 5) + 16  );

                //hp bar
                g2.setColor(new Color(197, 105, 105));
                g2.fillRoundRect(gp.tileSize*9 + 28 - (gp.tileSize* 8) + 16, gp.tileSize * 6 + 4 - (gp.tileSize* 5) + 32, 256,10, 10,10);
                g2.setColor(new Color(139, 184, 118));
                g2.fillRoundRect(gp.tileSize*9 + 28 - (gp.tileSize* 8) + 16, gp.tileSize * 6 + 4 - (gp.tileSize* 5) + 32, gp.player.randPoke.hp(),10, 10,10);





                g2.drawImage(outPoke.getB(), 24, gp.screenHeight - (gp.tileSize * 3 + 24) - (gp.tileSize * 5), gp.tileSize * 5, gp.tileSize * 5, null);

                if (bCursX == 1 && bCursY == 1) {
                    battleChoice = 1;
                    g2.drawImage(select, gp.screenWidth / 2 + 16, y + (((gp.tileSize * 3 + 24) / 2) / 4) + 4, 32, 32, null);
                }
                if (bCursX == 1 && bCursY == 2) {
                    battleChoice = 2;
                    g2.drawImage(select, gp.screenWidth / 2 + 16 + (((gp.screenWidth / 4)) + 16), y + (((gp.tileSize * 3 + 24) / 2) / 4) + 4, 32, 32, null);
                }
                if (bCursX == 2 && bCursY == 1) {
                    battleChoice = 3;
                    g2.drawImage(select, gp.screenWidth / 2 + 16, y + (((gp.tileSize * 3 + 24) / 2) / 4) + 4 + (gp.tileSize * 2 - 12), 32, 32, null);
                }
                if (bCursX == 2 && bCursY == 2) {
                    battleChoice = 4;
                    g2.drawImage(select, gp.screenWidth / 2 + 16 + (((gp.screenWidth / 4)) + 16), y + (((gp.tileSize * 3 + 24) / 2) / 4) + 4 + (gp.tileSize * 2 - 12), 32, 32, null);
                }

                if (gp.keyH.spacePressed && !attackMenu) {
                    if (battleChoice == 4) {

                        gp.gameState = gp.playState;
                        gp.stopMusic();
                        gp.playMusic(1);
                        battleChoice = 0;
                        bCursX = 0;
                        bCursY = 0;
                        wildOffset = 0;
                        start = true;

                    }else
                    if (battleChoice == 2){
                        Pokemon curr = gp.player.randPoke;
                        gp.stopMusic();
                        gp.playMusic(1);
                        extras.add(curr);
                        gp.gameState = gp.playState;
                        battleChoice = 0;
                        bCursX = 0;
                        bCursY = 0;
                        wildOffset = 0;
                        start = true;

                    }else if (battleChoice == 1) {
                        attackMenu = true;
                        battleChoice = 0;
                        bCursX = 0;
                        bCursY = 0;
                    }
                    gp.keyH.spacePressed = false;
                }
                if (attackMenu){
                    if (outPoke.getMoves()[3].getName() == null){
                        nameMove = "";
                    } else {
                        nameMove = outPoke.getMoves()[3].getName();
                    }

                    g2.setColor(new Color(0, 9, 14));
                    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
                    g2.drawString(outPoke.getMoves()[0].getName(), gp.screenWidth / 2 + (gp.screenWidth/16) - 8, y + (((gp.tileSize * 3 + 24) / 2) / 8) * 5);
                    g2.drawString(outPoke.getMoves()[1].getName(), gp.screenWidth / 2 + (gp.screenWidth/32) + 24 , (y + ((gp.tileSize * 3 + 24) / 2)) + (((gp.tileSize * 3 + 24) / 2) / 8) * 5);
                    g2.drawString(outPoke.getMoves()[2].getName() , gp.screenWidth / 2 + ((gp.screenWidth/4)) + 16 + (gp.screenWidth/16), y + (((gp.tileSize * 3 + 24) / 2) / 8) * 5);
                    g2.drawString(nameMove, gp.screenWidth / 2 + ((gp.screenWidth/4)) + 16 + (gp.screenWidth/16), y + ((gp.tileSize * 3 + 24) / 2) + (((gp.tileSize * 3 + 24) / 2) / 8) * 5);
                    if (gp.keyH.spacePressed == true){
                        if (bCursX == 1 && bCursY == 1 ){
                            if (gp.keyH.spacePressed){
                                g2.setColor(new Color(0, 9, 14));
                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                x += gp.tileSize / 2;
                                ty += gp.tileSize + 24;
                                cTimer++;
                                if (cTimer < 90){
                                    String attack = outPoke.name().toUpperCase() + "  used  \n " + outPoke.getMoves()[0].name.toUpperCase() + "!";
                                    g2.drawImage(physical,gp.screenWidth/3 + (cTimer* 3) + (2 * cTimer),((gp.screenHeight / 4) * 3) - (cTimer * 3) - (130), 48 + (cTimer),48 + (cTimer),null);
                                    for (String line : attack.split("\n")) {
                                        g2.drawString(line, x, ty);
                                        ty += 40;
                                    }
                                } else {
                                    if (cTimer == 90){
                                        if (damageDone){
                                            gp.player.randPoke.setHp(outPoke.getMoves()[0].damage);
                                            damageDone = false;
                                        }
                                    }
                                    nextTurn = true;
                                }
                                if (nextTurn){
                                    if (gp.player.randPoke.hp() <= 0){
                                        if (mTimer < 90){
                                            String attack = "The opposing " + gp.player.randPoke.name() + " \n has fainted.";
                                            for (String line : attack.split("\n")) {
                                                g2.setColor(new Color(0, 9, 14));
                                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                                g2.drawString(line, x, ty);
                                                ty += 40;
                                            }
                                            mTimer++;
                                        } else {
                                            outPoke.levelUp();
                                            gp.gameState = gp.playState;
                                            oTimer = 0;
                                            cTimer = 0;
                                            nextTurn = false;
                                            gp.keyH.enterPressed = false;
                                            gp.keyH.spacePressed = false;
                                            attackMenu = false;
                                            damageDone = true;
                                            ranM = (int) (Math.random() * 4);
                                            damageRec = true;
                                            gp.stopMusic();
                                            battleChoice = 0;
                                            bCursX = 0;
                                            bCursY = 0;
                                            wildOffset = 0;
                                            start = true;
                                            mTimer = 0;
                                        }

                                    } else {
                                        if (oTimer < 65){

                                            String attack = "The opposing " + gp.player.randPoke.name().toUpperCase() + " \n  used " + gp.player.randPoke.getMoves()[ranM].name.toUpperCase() + "!";
                                            g2.drawImage(rec,(gp.screenWidth - 200) - (oTimer* 3) - (3 * oTimer),0 + (oTimer * 3) + (130), 48 + (oTimer),48 + (oTimer),null);
                                            for (String line : attack.split("\n")) {
                                                g2.setColor(new Color(0, 9, 14));
                                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                                g2.drawString(line, x, ty);
                                                ty += 40;
                                            }
                                            oTimer++;
                                        } else {
                                            if (oTimer == 65){
                                                if (damageRec){
                                                    outPoke.setHp(gp.player.randPoke.getMoves()[ranM].damage);
                                                    damageRec = false;
                                                }
                                            }
                                            oTimer = 0;
                                            cTimer = 0;
                                            nextTurn = false;
                                            gp.keyH.enterPressed = false;
                                            gp.keyH.spacePressed = false;
                                            attackMenu = false;
                                            damageDone = true;
                                            ranM = (int) (Math.random() * 4);
                                            damageRec = true;
                                        }
                                    }

                                }
                            }
                        }  if ((bCursX == 1 && bCursY == 2)){
                            if (gp.keyH.spacePressed){
                                g2.setColor(new Color(0, 9, 14));
                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                x += gp.tileSize / 2;
                                ty += gp.tileSize + 24;
                                cTimer++;
                                if (cTimer < 90){
                                    String attack = outPoke.name().toUpperCase() + "  used  \n " + outPoke.getMoves()[2].name.toUpperCase() + "!";
                                    g2.drawImage(physical,gp.screenWidth/3 + (cTimer* 3) + (2 * cTimer),((gp.screenHeight / 4) * 3) - (cTimer * 3) - (130), 48 + (cTimer),48 + (cTimer),null);
                                    for (String line : attack.split("\n")) {
                                        g2.drawString(line, x, ty);
                                        ty += 40;
                                    }
                                } else {
                                    if (cTimer == 90){
                                        if (damageDone){
                                            gp.player.randPoke.setHp(outPoke.getMoves()[2].damage);
                                            damageDone = false;
                                        }
                                    }
                                    nextTurn = true;
                                }
                                if (nextTurn){
                                    if (gp.player.randPoke.hp() <= 0){
                                        if (mTimer < 90){
                                            String attack = "The opposing " + gp.player.randPoke.name() + " \n has fainted.";
                                            for (String line : attack.split("\n")) {
                                                g2.setColor(new Color(0, 9, 14));
                                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                                g2.drawString(line, x, ty);
                                                ty += 40;
                                            }
                                            mTimer++;
                                        } else {
                                            outPoke.levelUp();
                                            gp.gameState = gp.playState;
                                            oTimer = 0;
                                            cTimer = 0;
                                            nextTurn = false;
                                            gp.keyH.enterPressed = false;
                                            gp.keyH.spacePressed = false;
                                            attackMenu = false;
                                            damageDone = true;
                                            ranM = (int) (Math.random() * 4);
                                            damageRec = true;
                                            gp.stopMusic();
                                            battleChoice = 0;
                                            bCursX = 0;
                                            bCursY = 0;
                                            wildOffset = 0;
                                            start = true;
                                            mTimer = 0;

                                        }

                                    } else {
                                        if (oTimer < 65){

                                            String attack = "The opposing " + gp.player.randPoke.name().toUpperCase() + " \n  used " + gp.player.randPoke.getMoves()[ranM].name.toUpperCase() + "!";
                                            g2.drawImage(rec,(gp.screenWidth - 200) - (oTimer* 3) - (3 * oTimer),0 + (oTimer * 3) + (130), 48 + (oTimer),48 + (oTimer),null);
                                            for (String line : attack.split("\n")) {
                                                g2.setColor(new Color(0, 9, 14));
                                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                                g2.drawString(line, x, ty);
                                                ty += 40;
                                            }
                                            oTimer++;
                                        } else {
                                            if (oTimer == 65){
                                                if (damageRec){
                                                    outPoke.setHp(gp.player.randPoke.getMoves()[ranM].damage);
                                                    damageRec = false;
                                                }
                                            }
                                            oTimer = 0;
                                            cTimer = 0;
                                            nextTurn = false;
                                            gp.keyH.enterPressed = false;
                                            gp.keyH.spacePressed = false;
                                            attackMenu = false;
                                            damageDone = true;
                                            ranM = (int) (Math.random() * 4);
                                            damageRec = true;
                                        }
                                    }

                                }
                            }
                        }  if (bCursX == 2 && bCursY == 1){
                            if (gp.keyH.spacePressed){
                                g2.setColor(new Color(0, 9, 14));
                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                x += gp.tileSize / 2;
                                ty += gp.tileSize + 24;
                                cTimer++;
                                if (cTimer < 90){
                                    String attack = outPoke.name().toUpperCase() + "  used  \n " + outPoke.getMoves()[1].name.toUpperCase() + "!";
                                    g2.drawImage(physical,gp.screenWidth/3 + (cTimer* 3) + (2 * cTimer),((gp.screenHeight / 4) * 3) - (cTimer * 3) - (130), 48 + (cTimer),48 + (cTimer),null);
                                    for (String line : attack.split("\n")) {
                                        g2.drawString(line, x, ty);
                                        ty += 40;
                                    }
                                } else {
                                    if (cTimer == 90){
                                        if (damageDone){
                                            gp.player.randPoke.setHp(outPoke.getMoves()[1].damage);
                                            damageDone = false;
                                        }
                                    }
                                    nextTurn = true;
                                }
                                if (nextTurn){
                                    if (gp.player.randPoke.hp() <= 0){
                                        if (mTimer < 90){
                                            String attack = "The opposing " + gp.player.randPoke.name() + " \n has fainted.";
                                            for (String line : attack.split("\n")) {
                                                g2.setColor(new Color(0, 9, 14));
                                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                                g2.drawString(line, x, ty);
                                                ty += 40;
                                            }
                                            mTimer++;
                                        } else {
                                            outPoke.levelUp();
                                            gp.gameState = gp.playState;
                                            oTimer = 0;
                                            cTimer = 0;
                                            nextTurn = false;
                                            gp.keyH.enterPressed = false;
                                            gp.keyH.spacePressed = false;
                                            attackMenu = false;
                                            damageDone = true;
                                            ranM = (int) (Math.random() * 4);
                                            damageRec = true;
                                            gp.stopMusic();
                                            battleChoice = 0;
                                            bCursX = 0;
                                            bCursY = 0;
                                            wildOffset = 0;
                                            start = true;
                                            mTimer = 0;

                                        }

                                    } else {
                                        if (oTimer < 65){

                                            String attack = "The opposing " + gp.player.randPoke.name().toUpperCase() + " \n  used " + gp.player.randPoke.getMoves()[ranM].name.toUpperCase() + "!";
                                            g2.drawImage(rec,(gp.screenWidth - 200) - (oTimer* 3) - (3 * oTimer),0 + (oTimer * 3) + (130), 48 + (oTimer),48 + (oTimer),null);
                                            for (String line : attack.split("\n")) {
                                                g2.setColor(new Color(0, 9, 14));
                                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                                g2.drawString(line, x, ty);
                                                ty += 40;
                                            }
                                            oTimer++;
                                        } else {
                                            if (oTimer == 65){
                                                if (damageRec){
                                                    outPoke.setHp(gp.player.randPoke.getMoves()[ranM].damage);
                                                    damageRec = false;
                                                }
                                            }
                                            oTimer = 0;
                                            cTimer = 0;
                                            nextTurn = false;
                                            gp.keyH.enterPressed = false;
                                            gp.keyH.spacePressed = false;
                                            attackMenu = false;
                                            damageDone = true;
                                            ranM = (int) (Math.random() * 4);
                                            damageRec = true;
                                        }
                                    }

                                }

                            }
                        }  if (bCursX == 2 && bCursY == 2 && outPoke.getMoves()[3] != null){
                            if (gp.keyH.spacePressed){
                                g2.setColor(new Color(0, 9, 14));
                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                x += gp.tileSize / 2;
                                ty += gp.tileSize + 24;
                                cTimer++;
                                if (cTimer < 90){
                                    String attack = outPoke.name().toUpperCase() + "  used  \n " + outPoke.getMoves()[3].name.toUpperCase() + "!";
                                    g2.drawImage(physical,gp.screenWidth/3 + (cTimer* 3) + (2 * cTimer),((gp.screenHeight / 4) * 3) - (cTimer * 3) - (130), 48 + (cTimer),48 + (cTimer),null);
                                    for (String line : attack.split("\n")) {
                                        g2.drawString(line, x, ty);
                                        ty += 40;
                                    }
                                } else {
                                    if (cTimer == 90){
                                        if (damageDone){
                                            gp.player.randPoke.setHp(outPoke.getMoves()[3].damage);
                                            damageDone = false;
                                        }
                                    }
                                    nextTurn = true;
                                }
                                if (nextTurn){
                                    if (gp.player.randPoke.hp() <= 0){
                                        if (mTimer < 90){
                                            String attack = "The opposing " + gp.player.randPoke.name() + " \n has fainted.";
                                            for (String line : attack.split("\n")) {
                                                g2.setColor(new Color(0, 9, 14));
                                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                                g2.drawString(line, x, ty);
                                                ty += 40;
                                            }
                                            mTimer++;
                                        } else {
                                            outPoke.levelUp();
                                            gp.stopMusic();
                                            gp.playMusic(1);
                                            gp.gameState = gp.playState;
                                            oTimer = 0;
                                            cTimer = 0;
                                            nextTurn = false;
                                            gp.keyH.enterPressed = false;
                                            gp.keyH.spacePressed = false;
                                            attackMenu = false;
                                            damageDone = true;
                                            ranM = (int) (Math.random() * 4);
                                            damageRec = true;
                                            battleChoice = 0;
                                            bCursX = 0;
                                            bCursY = 0;
                                            wildOffset = 0;
                                            start = true;
                                            mTimer = 0;

                                        }

                                    } else {
                                        if (oTimer < 65){

                                            String attack = "The opposing " + gp.player.randPoke.name().toUpperCase() + " \n  used " + gp.player.randPoke.getMoves()[ranM].name.toUpperCase() + "!";
                                            g2.drawImage(rec,(gp.screenWidth - 200) - (oTimer* 3) - (3 * oTimer),0 + (oTimer * 3) + (130), 48 + (oTimer),48 + (oTimer),null);
                                            for (String line : attack.split("\n")) {
                                                g2.setColor(new Color(0, 9, 14));
                                                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                                                g2.drawString(line, x, ty);
                                                ty += 40;
                                            }
                                            oTimer++;
                                        } else {
                                            if (oTimer == 65){
                                                if (damageRec){
                                                    outPoke.setHp(gp.player.randPoke.getMoves()[ranM].damage);
                                                    damageRec = false;
                                                }
                                            }
                                            oTimer = 0;
                                            cTimer = 0;
                                            nextTurn = false;
                                            gp.keyH.enterPressed = false;
                                            gp.keyH.spacePressed = false;
                                            attackMenu = false;
                                            damageDone = true;
                                            ranM = (int) (Math.random() * 4);
                                            damageRec = true;
                                        }
                                    }

                                }

                            }
                        }


                    }

                } else {
                    g2.setColor(new Color(0, 9, 14));
                    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                    x += gp.tileSize / 2;
                    ty += gp.tileSize + 24;


                    String consider = "What  will  " + outPoke.name().toUpperCase() + "  do \n next?";
                    for (String line : consider.split("\n")) {
                        g2.drawString(line, x, ty);
                        ty += 40;
                    }
                    g2.setColor(new Color(0, 9, 14));
                    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 21));
                    g2.drawString("FIGHT", gp.screenWidth / 2 + (gp.screenWidth/16), y + (((gp.tileSize * 3 + 24) / 2) / 8) * 5);
                    g2.drawString("POKé", gp.screenWidth / 2 + (gp.screenWidth/32) + 24 , (y + ((gp.tileSize * 3 + 24) / 2)) + (((gp.tileSize * 3 + 24) / 2) / 8) * 5);
                    g2.drawString("CATCH", gp.screenWidth / 2 + ((gp.screenWidth/4)) + 8 + (gp.screenWidth/16), y + (((gp.tileSize * 3 + 24) / 2) / 8) * 5);
                    g2.drawString("RUN", gp.screenWidth / 2 + ((gp.screenWidth/4)) + 16 + (gp.screenWidth/16), y + ((gp.tileSize * 3 + 24) / 2) + (((gp.tileSize * 3 + 24) / 2) / 8) * 5);
                }

            }

        } else {
            //stuff
            if (gp.player.team[0] != null && gp.player.team[0].hp() > 0){
                outPoke = gp.player.team[0];
            } else if (gp.player.team[1] != null && gp.player.team[1].hp() > 0){
                gp.playSE(14);
                outPoke = gp.player.team[1];
            } else if (gp.player.team[2] != null && gp.player.team[2].hp() > 0){
                gp.playSE(14);
                outPoke = gp.player.team[2];
            } else if (gp.player.team[3] != null && gp.player.team[3].hp() > 0){
                gp.playSE(14);
                outPoke = gp.player.team[3];
            } else if (gp.player.team[4] != null && gp.player.team[4].hp() > 0){
                gp.playSE(14);
                outPoke = gp.player.team[4];
            } else if (gp.player.team[5] != null && gp.player.team[5].hp() > 0){
                gp.playSE(14);
                outPoke = gp.player.team[5];
            } else {
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.out.println("You are stupid");
                System.exit(0);
            }
        }
        }

        public void pc(){
        drawBattleWindow(24,24,gp.screenWidth - 48, gp.screenHeight - 48);
        int x = gp.tileSize;
        int y = gp.tileSize;
        int w = gp.tileSize;
        int h = gp.tileSize;
        for (Pokemon pcP : extras){
            g2.drawImage(pcP.getI(), x,y,w,h,null);
            x += gp.tileSize + 12;
            if (x > (gp.tileSize + 12 )*11){
                x = gp.tileSize;
                y += gp.tileSize + 12;
            }
        }

        }
    }

