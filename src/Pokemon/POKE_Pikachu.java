package Pokemon;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class POKE_Pikachu extends Pokemon {
    BufferedImage front;
    BufferedImage back;
    BufferedImage icon;
    String type;
    String type2;
    int level;
    String name = "Pikachu";
    public int multiplier = 2;
    public Moves[] moveSet = new Moves[4];
    int hp = 256;

    public int hp(){
        return hp;
    }

    public void setHp(int damage){
        hp = hp - damage;
    }

    public int mult(){
        return multiplier;
    }

    public Moves[] getMoves(){
        return moveSet;
    }

    public void setMoveSet(){
        moveSet[0] = new Moves("bug","Spark", 25, 65, false,0,4);
        moveSet[1] = new Moves("bug","Iron Tail", 30, 100, false,1,1);
        moveSet[2] = new Moves("normal","Thunder", 20, 110, false,0,1);
        moveSet[3] = new Moves("flying","Fake Out",15,40,false,0,1);
    }


    public POKE_Pikachu(GamePanel gp){
        super(gp);
        setImages();
        setStats();
        setMoveSet();
    }

    @Override
    public void setImages(){
        try{
            front = ImageIO.read(getClass().getResourceAsStream("/pokemon/pikachuFront.png"));
            back = ImageIO.read(getClass().getResourceAsStream("/pokemon/pikachuBack.png"));
            icon = ImageIO.read(getClass().getResourceAsStream("/pokemon/pikachuIcon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void heal(){
        hp = 256;
    }
    public void levelUp(){
        level++;
    }

    @Override
    public void setStats(){
        type = "electric";
        type2 = "none";
        level = (int) (Math.random() * (6) + 2);
    }

    public void testInfo(){
        System.out.println("You've found a " + name);
        System.out.println("Level: " + level);
        System.out.println("Types: " + type + "/" + type2);
    }

    public BufferedImage getF(){return front;}
    public BufferedImage getB(){return back;}
    public BufferedImage getI(){return icon;}
    public String name(){return name;}
    public int level(){return level;}


}