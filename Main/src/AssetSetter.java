public class AssetSetter{
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setobject(){
        gp.obj[0] = new OBJ_KEY(gp);
        gp.obj[0].worldX = 38 * gp.tileSize;
        gp.obj[0].worldY = 45 * gp.tileSize;

        gp.obj[1] = new OBJ_DOOR(gp);
        gp.obj[1].worldX = 38 * gp.tileSize;
        gp.obj[1].worldY = 15 * gp.tileSize;

        gp.obj[2] = new OBJ_DOOR(gp);
        gp.obj[2].worldX = 9 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new OBJ_KEY(gp);
        gp.obj[3].worldX = 47 * gp.tileSize;
        gp.obj[3].worldY = 25 * gp.tileSize;

        // gp.obj[4] = new OBJ_DOOR(gp);
        // gp.obj[4].worldX = 23 * gp.tileSize;
        // gp.obj[4].worldY = 36 * gp.tileSize;

        // gp.obj[5] = new OBJ_KEY(gp);
        // gp.obj[5].worldX = 38 * gp.tileSize;
        // gp.obj[5].worldY = 13 * gp.tileSize;

    } 

    public void setNPC(){

        gp.npc[0] = new NPC_OLDMAN(gp);
        gp.npc[0].worldX = gp.tileSize * 30;
        gp.npc[0].worldY = gp.tileSize * 17;

        gp.npc[1] = new NPC_OLDMAN(gp);
        gp.npc[1].worldX = gp.tileSize * 31;
        gp.npc[1].worldY = gp.tileSize * 21;

        gp.npc[2] = new NPC_OLDMAN(gp);
        gp.npc[2].worldX = gp.tileSize * 17;
        gp.npc[2].worldY = gp.tileSize * 43;

        gp.npc[3] = new NPC_OLDMAN(gp);
        gp.npc[3].worldX = gp.tileSize * 33;
        gp.npc[3].worldY = gp.tileSize * 43;

    }

    public void setMonster(){

        gp.monster[0] = new MON(gp);
        gp.monster[0].worldX = gp.tileSize * 30;
        gp.monster[0].worldY = gp.tileSize * 18;

        gp.monster[1] = new MON(gp);
        gp.monster[1].worldX = gp.tileSize * 30;
        gp.monster[1].worldY = gp.tileSize * 19;
    }
}