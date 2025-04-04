package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

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
        if (gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                gp.playSE(4);
                if (gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                gp.playSE(4);
                if (gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.commandNum == 0){
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
                if (gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }



        //play state
        if (gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }


            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
                gp.stopMusic();
                gp.playPauseMusic();

            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }

            //debug

            if(code == KeyEvent.VK_T){
                if (checkDrawTime == false){
                    checkDrawTime = true;
                }
                else if (checkDrawTime == true){
                    checkDrawTime = false;
                }
            }
        }
        //pause state
        else if (gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
                gp.pauseMusic();
                gp.stopPauseMusic();
            }
        }
        //dialogue state
        else if (gp.gameState == gp.dialogueState || gp.gameState == gp.dialogueStateProf){
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;

            }
            //selectign starter
        } else if (gp.gameState == gp.selectionState) {
            if (code == KeyEvent.VK_ENTER){
                enterPressed = true;
                if (gp.ui.selectNum == 1 && enterPressed){


                    gp.ui.textS = "Froakie has been selected!";
                    gp.player.direction = "down";
                } else if (gp.ui.selectNum == 0 && enterPressed){
                    gp.ui.textS = "Torchic has been selected!";
                } else if (gp.ui.selectNum == 2 && enterPressed){
                    gp.ui.textS = "Rowlet has been selected!";
                }
            } else if (code == KeyEvent.VK_A){
                if (gp.ui.selectNum == 1){
                    gp.ui.selectNum = 0;
                } else if (gp.ui.selectNum == 2){
                    gp.ui.selectNum = 1;
                } else if (gp.ui.selectNum == 0){
                    gp.ui.selectNum = 2;
                }

            } else if (code == KeyEvent.VK_D){
                if (gp.ui.selectNum == 1){
                    gp.ui.selectNum = 2;
                } else if (gp.ui.selectNum == 2){
                    gp.ui.selectNum = 0;
                } else if (gp.ui.selectNum == 0){
                    gp.ui.selectNum = 1;
                }
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
