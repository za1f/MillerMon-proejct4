package Pokemon;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class POKE_Eevee extends Pokemon {
    BufferedImage front;
    BufferedImage back;
    BufferedImage icon;
    String type;
    int level;
    String type2;
    String name = "Eevee";
    public int multiplier = 1;
    public Moves[] moveSet = new Moves[4];
    int hp = 256;

    public int hp(){
        return hp;
    }
    public void heal(){
        hp = 256;
    }

    public void levelUp(){
        level++;
    }

    public void setHp(int damage){
        hp = hp - damage;
    }

    public int mult(){
        return multiplier;
    }

    public POKE_Eevee(GamePanel gp){
        super(gp);
        setImages();
        setStats();
        setMoveSet();
    }

    public void setMoveSet(){
        moveSet[0] = new Moves("normal","Covet", 25, 60, false,0,1);
        moveSet[1] = new Moves("dark","Bite", 25, 60, false,0,1);
        moveSet[2] = new Moves("normal","Growl", 40, 0, true,0,1);
        moveSet[3] = new Moves("normal","Tackle",35,40,false,0,1);
    }

    @Override
    public void setImages(){
        try{
            front = ImageIO.read(getClass().getResourceAsStream("/pokemon/eeveeFront.png"));
            back = ImageIO.read(getClass().getResourceAsStream("/pokemon/eeveeBack.png"));
            icon = ImageIO.read(getClass().getResourceAsStream("/pokemon/eeveeIcon.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setStats(){
        type = "normal";
        type2 = "none";
        level = (int) (Math.random() * (6) + 2);
    }

    public Moves[] getMoves(){
        return moveSet;
    }

    public void testInfo(){
        System.out.println("You've found an " + name);
        System.out.println("Level: " + level);
        System.out.println("Types: " + type + "/" + type2);
    }

    public BufferedImage getF(){return front;}
    public BufferedImage getB(){return back;}
    public BufferedImage getI(){return icon;}
    public String name(){return name;}
    public int level(){return level;}
}
