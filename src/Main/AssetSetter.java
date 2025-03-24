package Main;

import Entity.NPC_Girl;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Poke;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }


    public void setObject(){

    }
    public void setNPC(){
        gp.npc[0] = new NPC_Girl(gp);
        gp.npc[0].worldX = gp.tileSize * 10;
        gp.npc[0].worldY = gp.tileSize * 7;
    }

}
