package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public int otherX;
    public int otherY;
    public boolean otherRight;

    public BufferedImage elf_right_idle1, elf_right_idle2, elf_right_idle3, elf_right_idle4, elf_right_run1, elf_right_run2, elf_right_run3, elf_right_run4, elf_right_hit;
    public BufferedImage elf_left_idle1, elf_left_idle2, elf_left_idle3, elf_left_idle4, elf_left_run1, elf_left_run2, elf_left_run3, elf_left_run4, elf_left_hit;
    public String direction, turn;
    public boolean turned_right;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;
    public boolean collisionUp = false;
    public boolean collisionDown = false;
    public boolean collisionLeft = false;
    public boolean collisionRight = false;


}
