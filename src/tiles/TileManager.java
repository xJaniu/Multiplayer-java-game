package tiles;

import main.GameModule;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GameModule gm;
    public Tile[] tile;
    Tile[] tile_extend;
    public int mapTileNum[][];
    int mapTileNumExtend[][];

    public TileManager(GameModule gm){
        this.gm = gm;
        tile = new Tile[25];
        tile_extend = new Tile[25];
        mapTileNum = new int[gm.maxWorldCol][gm.maxWorldRow];
        mapTileNumExtend = new int[gm.maxWorldCol][gm.maxWorldRow];

        getTileImage();
//        getTileImageExtend();
        loadMap("/resources/maps/map01.txt");
//        loadExtends("/resources.maps/map01_extend.txt");
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/ground/floor_1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/ground/floor_2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/ground/floor_3.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/ground/floor_4.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/ground/floor_5.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/ground/floor_6.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/resources/ground/floor_7.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/resources/ground/floor_8.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/resources/extends/wall_side_mid_left.png"));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/resources/extends/wall_corner_top_right.png"));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/resources/extends/wall_mid.png"));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/resources/extends/wall_side_mid_right.png"));
            tile[11].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/resources/extends/blank.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/resources/extends/wall_left.png"));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/resources/extends/wall_right.png"));
            tile[14].collision = true;

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

//    public void getTileImageExtend(){
//        try{
//            tile_extend[0] = new Tile();
//            tile_extend[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/blank.png"));
//
//            tile_extend[1] = new Tile();
//            tile_extend[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_left.png"));
//
//            tile_extend[2] = new Tile();
//            tile_extend[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_mid.png"));
//
//            tile_extend[3] = new Tile();
//            tile_extend[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_right.png"));
//
//            tile_extend[4] = new Tile();
//            tile_extend[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_corner_bottom_left.png"));
//
//            tile_extend[5] = new Tile();
//            tile_extend[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_corner_top_left.png"));
//
//            tile_extend[6] = new Tile();
//            tile_extend[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_corner_top_right.png"));
//
//            tile_extend[7] = new Tile();
//            tile_extend[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_corner_bottom_right.png"));
//
//            tile_extend[8] = new Tile();
//            tile_extend[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_side_mid_left.png"));
//
//            tile_extend[9] = new Tile();
//            tile_extend[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_side_mid_right.png"));
//
//            tile_extend[10] = new Tile();
//            tile_extend[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_inner_corner_mid_left.png"));
//
//            tile_extend[11] = new Tile();
//            tile_extend[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/extends/wall_inner_corner_mid_rigth.png"));
//
//        }catch(IOException ex){
//            ex.printStackTrace();
//        }
//    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gm.maxWorldCol && row < gm.maxWorldRow){
                String line = br.readLine();
                while(col < gm.maxWorldCol){
                    String numbers[] = line.split("\\s+");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gm.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception ex){
            System.out.println("Load map exception");
        }
    }

//    public void loadExtends(String filePath){
//        try{
//            InputStream is = getClass().getResourceAsStream(filePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            int col = 0;
//            int row = 0;
//
//            while(col < gm.maxWorldCol && row < gm.maxWorldRow){
//                String line = br.readLine();
//                while(col < gm.maxWorldCol){
//                    String numbers[] = line.split(" ");
//                    int num = Integer.parseInt(numbers[col]);
//
//                    mapTileNumExtend[col][row] = num;
//                    col++;
//                }
//                if(col == gm.maxWorldCol){
//                    col = 0;
//                    row++;
//                }
//            }
//            br.close();
//        }catch(Exception ex){
//            System.out.println("Load map exception");
//        }
//    }

    public void draw(Graphics2D g2){
        int worldCol=0;
        int worldRow=0;
        while(worldCol<gm.maxWorldCol && worldRow <gm.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gm.tileSize;
            int worldY = worldRow * gm.tileSize;
            int screenX = worldX - gm.player.x + gm.player.screenX;
            int screenY = worldY - gm.player.y + gm.player.screenY;

            if(worldX + gm.tileSize*5 > gm.player.x - gm.player.screenX &&
                    worldX - gm.tileSize*5 < gm.player.x + gm.player.screenX &&
                    worldY + gm.tileSize*5 > gm.player.y - gm.player.screenY &&
                    worldY - gm.tileSize*5 < gm.player.y + gm.player.screenY){
                g2.drawImage(tile[tileNum].image,screenX,screenY,gm.tileSize,gm.tileSize,null);
            }



            worldCol++;
            if(worldCol == gm.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }

//    public void drawExtends(Graphics2D g2){
//        int worldCol=0;
//        int worldRow=0;
//        while(worldCol<gm.maxWorldCol && worldRow <gm.maxWorldRow){
//            int tileNum = mapTileNumExtend[worldCol][worldRow];
//
//            int worldX = worldCol * gm.tileSize;
//            int worldY = worldRow * gm.tileSize;
//            int screenX = worldX - gm.player.x + gm.player.screenX;
//            int screenY = worldY - gm.player.y + gm.player.screenY;
//
//            g2.drawImage(tile_extend[tileNum].image,screenX,screenY,gm.tileSize,gm.tileSize,null);
//            worldCol++;
//            if(worldCol == gm.maxWorldCol){
//                worldCol=0;
//                worldRow++;
//            }
//        }
//    }

}
