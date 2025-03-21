package object;
//pokeball
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){

        name = "PokeballCHEAT";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/pokeball.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
