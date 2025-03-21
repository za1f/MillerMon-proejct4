package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Poke extends SuperObject{
    public OBJ_Poke(){

        name = "Pokeball1";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/pokeball.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}


