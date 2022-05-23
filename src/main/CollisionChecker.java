package main;

import entity.Entity;

public class CollisionChecker {

    GameModule gm;

    public CollisionChecker(GameModule gm){
        this.gm = gm;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gm.tileSize;
        int entityRightCol = entityRightWorldX/gm.tileSize;
        int entityTopRow = entityTopWorldY/gm.tileSize;
        int entityBottomRow = entityBottomWorldY/gm.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gm.tileSize;
                tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gm.tileM.tile[tileNum1].collision == true || gm.tileM.tile[tileNum2].collision == true){
                      entity.collisionUp = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gm.tileSize;
                tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gm.tileM.tile[tileNum1].collision == true || gm.tileM.tile[tileNum2].collision == true){
                    entity.collisionDown = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gm.tileSize;
                tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gm.tileM.tile[tileNum1].collision == true || gm.tileM.tile[tileNum2].collision == true){
                    entity.collisionLeft = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gm.tileSize;
                tileNum1 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gm.tileM.tile[tileNum1].collision == true || gm.tileM.tile[tileNum2].collision == true){
                    entity.collisionRight = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i=0; i<gm.obj.length;i++){
            if(gm.obj[i] != null){
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;

                gm.obj[i].solidArea.x = gm.obj[i].x + gm.obj[i].solidArea.x;
                gm.obj[i].solidArea.y = gm.obj[i].y + gm.obj[i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gm.obj[i].solidArea)){
                            if(gm.obj[i].collision == true){
                                entity.collisionUp = true;
                            }
                            if(player==true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gm.obj[i].solidArea)){
                            if(gm.obj[i].collision == true){
                                entity.collisionDown = true;
                            }
                            if(player==true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gm.obj[i].solidArea)){
                            if(gm.obj[i].collision == true){
                                entity.collisionLeft = true;
                            }
                            if(player==true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gm.obj[i].solidArea)){
                            if(gm.obj[i].collision == true){
                                entity.collisionRight = true;
                            }
                            if(player==true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gm.obj[i].solidArea.x = gm.obj[i].solidAreaDefaultX;
                gm.obj[i].solidArea.y = gm.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

}
