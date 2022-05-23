package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Potion extends SuperObject{
    public OBJ_Potion(String potion){
        name = "Potion";
        animation = false;
        collision = true;

        solidArea.x = 12;
        solidArea.y = 12;
        solidArea.width=24;
        solidArea.height=24;

        try{

            switch(potion){
                case "speed" -> {
                    image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/potions/flask_big_blue.png")));
                }
                case "strenght" ->{
                    image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/potions/flask_big_yellow.png")));
                }
                case "health" -> {
                    image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/potions/flask_big_red.png")));
                }
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
