package tiles;

import main.GameModule;
import objects.OBJ_Door;
import objects.OBJ_Potion;

public class AssetSetter {
    GameModule gm;

    public AssetSetter(GameModule gm){
        this.gm = gm;
    }

    public void setObject(){
        gm.obj[0] = new OBJ_Door();
        gm.obj[0].x = 3*gm.tileSize;
        gm.obj[0].y = 20*gm.tileSize-6;
        gm.obj[0].collision = true;

        gm.obj[1] = new OBJ_Potion("speed");
        gm.obj[1].x = 6*gm.tileSize;
        gm.obj[1].y = 6*gm.tileSize;
        gm.obj[1].collision=true;

        gm.obj[2] = new OBJ_Potion("health");
        gm.obj[2].x = 7*gm.tileSize;
        gm.obj[2].y = 6*gm.tileSize;
        gm.obj[2].collision=true;
        gm.obj[3] = new OBJ_Potion("strenght");
        gm.obj[3].x = 8*gm.tileSize;
        gm.obj[3].y = 6*gm.tileSize;
        gm.obj[3].collision=true;
    }
}
