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
        int mapNum = 0;
    }
    public void setNPC(){
        int mapNum = 0;
        gp.npc[mapNum][0] = new NPC_Girl(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 10;
        gp.npc[mapNum][0].worldY = gp.tileSize * 7;
    }

}
