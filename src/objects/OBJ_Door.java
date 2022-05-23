package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
    public OBJ_Door(){
        name = "Doors";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/doors_all.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
