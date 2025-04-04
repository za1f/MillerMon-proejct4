package Main;

import Entity.NPC_Girl;
import Entity.NPC_Prof;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Poke;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }


    public void setObject() {
        int mapNum = 0;
    }

    public void setNPC() {
        int mapNum = 0;

        //gp.npc[# of map they appear][which npc]
        gp.npc[0][0] = new NPC_Girl(gp);
        gp.npc[0][0].worldX = gp.tileSize * 2;
        gp.npc[0][0].worldY = gp.tileSize * 2;
        gp.npc[2][0] = new NPC_Prof(gp);
        gp.npc[2][0].worldX = gp.tileSize * 12;
        gp.npc[2][0].worldY = gp.tileSize * 5 + 24;

    }
}
