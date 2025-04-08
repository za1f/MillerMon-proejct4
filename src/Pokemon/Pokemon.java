package Pokemon;

import Main.GamePanel;

import java.awt.image.BufferedImage;

public class Pokemon {
    GamePanel gp;

    public Pokemon(GamePanel gp){
        this.gp = gp;
    }

    public void setImages(){}
    public void setStats(){}
    public void testInfo(){}
    public BufferedImage getF(){return null;}
    public BufferedImage getB(){return null;}
    public BufferedImage getI(){return null;}
    public int mult(){return 0;}
    public String name(){return null;}
    public int level(){return 0;}
    public Moves[] moveSet = null;
    public Moves[] getMoves(){return null;}
    public int hp(){return 0;}
    public void setHp(int damage){

    }
    public void heal(){
    }

    public void levelUp(){
    }
}
