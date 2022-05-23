package entity;

import main.GameModule;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GameModule gm;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    //other_players



    public Player(GameModule gm, KeyHandler keyH){
        this.gm = gm;
        this.keyH = keyH;

        screenX = gm.screenWidth/2-16;
        screenY = gm.screenHeight/2-48;

        solidArea = new Rectangle(8, 40, 16, 24);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 40;
        solidArea.width = 16;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x=370;
        y=50;
        speed = 4;
        direction = "right";
        turn = "right";
        turned_right = true;
    }

    public void getPlayerImage(){
        try{
            elf_right_idle1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/right/elf_m_idle_anim_f0.png")));
            elf_right_idle2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/right/elf_m_idle_anim_f1.png")));
            elf_right_idle3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/right/elf_m_idle_anim_f2.png")));
            elf_right_idle4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/right/elf_m_idle_anim_f3.png")));
            elf_right_run1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/right/elf_m_run_anim_f0.png")));
            elf_right_run2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/right/elf_m_run_anim_f1.png")));
            elf_right_run3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/right/elf_m_run_anim_f2.png")));
            elf_right_run4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/right/elf_m_run_anim_f3.png")));
            elf_left_idle1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/left/elf_m_idle_anim_f0.png")));
            elf_left_idle2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/left/elf_m_idle_anim_f1.png")));
            elf_left_idle3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/left/elf_m_idle_anim_f2.png")));
            elf_left_idle4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/left/elf_m_idle_anim_f3.png")));
            elf_left_run1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/left/elf_m_run_anim_f0.png")));
            elf_left_run2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/left/elf_m_run_anim_f1.png")));
            elf_left_run3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/left/elf_m_run_anim_f2.png")));
            elf_left_run4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/elf/left/elf_m_run_anim_f3.png")));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        collisionOn = false;
        collisionLeft = false;
        collisionDown = false;
        collisionRight = false;
        collisionUp = false;

        int objIndex = gm.colChecker.checkObject(this,true);

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
           if (keyH.rightPressed) {
                direction = "right";
                turn = "right";
                turned_right = true;
                gm.colChecker.checkTile(this);
                if(!collisionRight) {
                    x += speed;
                }
            }
            if (keyH.downPressed) {
                direction = "down";
                gm.colChecker.checkTile(this);
                if(!collisionDown) {
                    y += speed;
                }
            }
            if (keyH.leftPressed) {
                direction = "left";
                turn = "left";
                turned_right = false;
                gm.colChecker.checkTile(this);
                if(!collisionLeft) {
                    x -= speed;
                }
            }
            if (keyH.upPressed) {
                direction = "up";
                gm.colChecker.checkTile(this);
                if(!collisionUp) {
                    y -= speed;
                }
            }

            if (spriteCounter > 6) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            spriteCounter++;
        }else{
            if (spriteCounter > 6) {
                if (spriteNum == 5) {
                    spriteNum = 6;
                } else if(spriteNum == 6){
                    spriteNum = 7;
                } else if(spriteNum ==7){
                    spriteNum = 8;
                } else {
                    spriteNum = 5;
                }
                spriteCounter = 0;
            }
            spriteCounter++;
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (turn) {
            case "left" -> {
                if (spriteNum == 1) {
                    image = elf_left_run1;
                }
                if (spriteNum == 2) {
                    image = elf_left_run2;
                }
                if (spriteNum == 3) {
                    image = elf_left_run3;
                }
                if (spriteNum == 4) {
                    image = elf_left_run4;
                }
                if (spriteNum == 5) {
                    image = elf_left_idle1;
                }
                if (spriteNum == 6) {
                    image = elf_left_idle2;
                }
                if (spriteNum == 7) {
                    image = elf_left_idle3;
                }
                if (spriteNum == 8) {
                    image = elf_left_idle4;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = elf_right_run1;
                }
                if (spriteNum == 2) {
                    image = elf_right_run2;
                }
                if (spriteNum == 3) {
                    image = elf_right_run3;
                }
                if (spriteNum == 4) {
                    image = elf_right_run4;
                }
                if (spriteNum == 5) {
                    image = elf_right_idle1;
                }
                if (spriteNum == 6) {
                    image = elf_right_idle2;
                }
                if (spriteNum == 7) {
                    image = elf_right_idle3;
                }
                if (spriteNum == 8) {
                    image = elf_right_idle4;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize*2, null);
    }

    public void drawOtherPlayer(Graphics2D g2){

        BufferedImage image = null;

        if (!(otherRight)) {
            if (spriteNum == 1) {
                image = elf_left_run1;
            }
            if (spriteNum == 2) {
                image = elf_left_run2;
            }
            if (spriteNum == 3) {
                image = elf_left_run3;
            }
            if (spriteNum == 4) {
                image = elf_left_run4;
            }
            if (spriteNum == 5) {
                image = elf_left_idle1;
            }
            if (spriteNum == 6) {
                image = elf_left_idle2;
            }
            if (spriteNum == 7) {
                image = elf_left_idle3;
            }
            if (spriteNum == 8) {
                image = elf_left_idle4;
            }
        } else if (otherRight) {
            if (spriteNum == 1) {
                image = elf_right_run1;
            }
            if (spriteNum == 2) {
                image = elf_right_run2;
            }
            if (spriteNum == 3) {
                image = elf_right_run3;
            }
            if (spriteNum == 4) {
                image = elf_right_run4;
            }
            if (spriteNum == 5) {
                image = elf_right_idle1;
            }
            if (spriteNum == 6) {
                image = elf_right_idle2;
            }
            if (spriteNum == 7) {
                image = elf_right_idle3;
            }
            if (spriteNum == 8) {
                image = elf_right_idle4;
            }
        }
        //System.out.println((screenX+otherX-x) + " == x || y == " + (screenY+otherY-y));
        g2.drawImage(image, (screenX+otherX)-x, (screenY+otherY)-y, gm.tileSize, gm.tileSize*2, null);

    }

}
