package Pokemon;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class POKE_Scyther extends Pokemon {
    BufferedImage front;
    BufferedImage back;
    BufferedImage icon;
    String type;
    String type2;
    int level;
    String name = "Scyther";
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
        moveSet[0] = new Moves("bug","Furry Cutter", 25, 55, false,0,4);
        moveSet[1] = new Moves("bug","X-Scissor", 30, 80, false,1,1);
        moveSet[2] = new Moves("normal","Slash", 20, 70, false,0,1);
        moveSet[3] = new Moves("flying","Air Slash",15,75,false,0,1);
    }


    public POKE_Scyther(GamePanel gp){
        super(gp);
        setImages();
        setStats();
        setMoveSet();
    }

    @Override
    public void setImages(){
        try{
            front = ImageIO.read(getClass().getResourceAsStream("/pokemon/scytherFront.png"));
            back = ImageIO.read(getClass().getResourceAsStream("/pokemon/scytherBack.png"));
            icon = ImageIO.read(getClass().getResourceAsStream("/pokemon/scytherIcon.png"));
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
        type = "bug";
        type2 = "flying";
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