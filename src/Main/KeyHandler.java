package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Pokemon.*;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed;

    boolean checkDrawTime = false;


    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //title state
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                gp.playSE(4);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                gp.playSE(4);
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playSE(5);
                    gp.playMusic(10);
                }
                if (gp.ui.commandNum == 1) {
                    String redText = "\u001B[31m****THIS DOES ABSOLUTELY NOTHING****\u001B[0m";
                    System.out.println(redText);
                    gp.ui.commandNum = 0;
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }


        //play state
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_R){
                gp.gameState = gp.pcState;
            }


            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
                gp.stopMusic();
                gp.playPauseMusic();

            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            //debug

            if (code == KeyEvent.VK_T) {
                if (checkDrawTime == false) {
                    checkDrawTime = true;
                } else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
        }
        //pause state
        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
                gp.pauseMusic();
                gp.stopPauseMusic();
            }
            if (code == KeyEvent.VK_W) {
                if (gp.ui.slotRow != 0) {
                    gp.ui.slotRow--;
                }

            }

            if (code == KeyEvent.VK_S) {
                if (gp.ui.slotRow != 5) {
                    gp.ui.slotRow++;
                }

            }

        }
        //dialogue state
        else if (gp.gameState == gp.dialogueState || gp.gameState == gp.dialogueStateProf) {
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;

            }
            //selectign starter
        } else if (gp.gameState == gp.selectionState) {
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;

                if (gp.ui.selectNum == 1 && enterPressed) {
                    gp.ui.choice = "Ahh, i  see  you've  picked  Froakie. \n Good  choice!";
                    Pokemon froakie = new POKE_Froakie(gp);
                    gp.npc[2][0].dialogues[0][0] = "Now  that  you  have  your  starter...";
                    gp.npc[2][0].dialogues[0][1] = "You  should  begin  your  journey.";
                    gp.npc[2][0].dialogues[0][2] = "Head  over  to  Route 1,  located  at \n the  bottom  right  of  the  town";
                    gp.npc[2][0].dialogues[0][3] = "Be  aware  as  there  are  Pokémon that \n lurk...";
                    gp.npc[2][0].dialogues[0][4] = "and trainers to fight.";
                    gp.npc[2][0].dialogues[0][5] = "Good Luck trainer.";
                    gp.player.team[0] = froakie;
                } else if (gp.ui.selectNum == 0 && enterPressed) {
                    gp.ui.choice = "Ahh, i  see  you've  picked  Torchic. \n Good  choice!";
                    Pokemon torchic = new POKE_Torchic(gp);
                    gp.npc[2][0].dialogues[0][0] = "Now  that  you  have  your  starter...";
                    gp.npc[2][0].dialogues[0][1] = "You  should  begin  your  journey.";
                    gp.npc[2][0].dialogues[0][2] = "Head  over  to  Route 1,  located  at \n the  bottom  right  of  the  town";
                    gp.npc[2][0].dialogues[0][3] = "Be  aware  as  there  are  Pokémon that \n lurk...";
                    gp.npc[2][0].dialogues[0][4] = "and trainers to fight.";
                    gp.npc[2][0].dialogues[0][5] = "Good Luck trainer.";
                    gp.player.team[0] = torchic;
                } else if (gp.ui.selectNum == 2 && enterPressed) {
                    gp.ui.choice = "Ahh, i  see  you've  picked  [DEMO]. \n Good  choice!";
                    Pokemon scyther = new POKE_Scyther(gp);
                    Pokemon eevee = new POKE_Eevee(gp);
                    Pokemon gible = new POKE_Gible(gp);
                    Pokemon torchic = new POKE_Torchic(gp);
                    Pokemon froakie = new POKE_Froakie(gp);
                    Pokemon pikachu = new POKE_Pikachu(gp);
                    gp.npc[2][0].dialogues[0][0] = "Now  that  you  have  your  starter...";
                    gp.npc[2][0].dialogues[0][1] = "You  should  begin  your  journey.";
                    gp.npc[2][0].dialogues[0][2] = "Head  over  to  Route 1,  located  at \n the  bottom  right  of  the  town";
                    gp.npc[2][0].dialogues[0][3] = "Be  aware  as  there  are  Pokémon that \n lurk...";
                    gp.npc[2][0].dialogues[0][4] = "and trainers to fight.";
                    gp.npc[2][0].dialogues[0][5] = "Good Luck trainer.";
                    gp.player.team[0] = scyther;
                    gp.player.team[1] = gible;
                    gp.player.team[2] = pikachu;
                    gp.player.team[3] = eevee;
                    gp.player.team[4] = froakie;
                    gp.player.team[5] = torchic;


                }
            } else if (code == KeyEvent.VK_A) {
                if (gp.ui.selectNum == 1) {
                    gp.ui.selectNum = 0;
                } else if (gp.ui.selectNum == 2) {
                    gp.ui.selectNum = 1;
                } else if (gp.ui.selectNum == 0) {
                    gp.ui.selectNum = 2;
                }

            } else if (code == KeyEvent.VK_D) {
                if (gp.ui.selectNum == 1) {
                    gp.ui.selectNum = 2;
                } else if (gp.ui.selectNum == 2) {
                    gp.ui.selectNum = 0;
                } else if (gp.ui.selectNum == 0) {
                    gp.ui.selectNum = 1;
                }
            }
        } else if (gp.gameState == gp.postSelection) {
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        } else if (gp.gameState == gp.battleState) {
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        } else if (gp.gameState == gp.wildBattle) {
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            if (code == KeyEvent.VK_W){
                upPressed = true;
                gp.ui.bCursX = 1;
            }
            if (code == KeyEvent.VK_A){
                leftPressed = true;
                gp.ui.bCursY = 1;
            }
            if (code == KeyEvent.VK_S){
                downPressed = true;
                gp.ui.bCursX = 2;
            }
            if (code == KeyEvent.VK_D){
                rightPressed = true;
                gp.ui.bCursY = 2;
            }
        } else if (gp.gameState == gp.pcState){

            if (code == KeyEvent.VK_R){
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
