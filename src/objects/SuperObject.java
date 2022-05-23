package objects;

import main.GameModule;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, anim1, anim2;
    public String name;
    public boolean collision = false;
    public int x, y;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public boolean animation;
    int layer = 0;

    public void draw(Graphics2D g2, GameModule gm){

        int screenX = x - gm.player.x + gm.player.screenX;
        int screenY = y - gm.player.y + gm.player.screenY;


        if(animation && gm.player.spriteCounter > 6 ){
            layer++;
            switch(layer){
                case 1, 2, 3 -> {
                    image = anim1;
                }
                case 4, 5, 6 ->{
                    image = anim2;
                }
                case 7 ->{
                    layer = 0;
                }
            }
        }

        if(x + gm.tileSize*5 > gm.player.x - gm.player.screenX &&
                x - gm.tileSize*5 < gm.player.x + gm.player.screenX &&
                y + gm.tileSize*5 > gm.player.y - gm.player.screenY &&
                y - gm.tileSize*5 < gm.player.y + gm.player.screenY){
            g2.drawImage(image,screenX,screenY,image.getWidth()*gm.scale,image.getHeight()*gm.scale,null);
        }
    }
}
