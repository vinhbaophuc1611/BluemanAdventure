public class AssetSetter{
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setobject(){
        int mapNum = 1;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_DOOR(gp);
        gp.obj[mapNum][i].worldX = 41 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_DOOR(gp);
        gp.obj[mapNum][i].worldX = 53 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 52 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_DOOR(gp);
        gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new OBJ_DOOR(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;
    }

    public void setNPC(){
        int mapNum = 1;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_OLDMAN(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 15;
        gp.npc[mapNum][i].worldY = gp.tileSize * 15;

        gp.npc[mapNum][i] = new NPC_OLDMAN(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 31;
        gp.npc[mapNum][i].worldY = gp.tileSize * 12;

        gp.npc[mapNum][i] = new NPC_OLDMAN(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 31;
        gp.npc[mapNum][i].worldY = gp.tileSize * 37;

        gp.npc[mapNum][i] = new NPC_OLDMAN(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 24;
        gp.npc[mapNum][i].worldY = gp.tileSize * 27;

        gp.npc[mapNum][i] = new NPC_OLDMAN(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 44;
        gp.npc[mapNum][i].worldY = gp.tileSize * 19;
    }

    public void setMonster(){
        int mapNum = 1;
        int i = 0;
        // loadMap = 1;
        gp.monster[mapNum][i] = new MON(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 17;
        gp.monster[mapNum][i].worldY = gp.tileSize * 7;
        i++;

        gp.monster[mapNum][i] = new MON(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 16;
        i++;

        gp.monster[mapNum][i] = new MON(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 44;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.monster[mapNum][i] = new MON(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;
        i++;

        gp.monster[mapNum][i] = new MON(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
        i++;

        // gp.monster[mapNum][i] = new MON2(gp);
        // gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        // gp.monster[mapNum][i].worldY = gp.tileSize * 39;
        // i++;

        mapNum = 0;
        gp.monster[mapNum][i] = new MON2(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 15;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        i++;
        
    }
}