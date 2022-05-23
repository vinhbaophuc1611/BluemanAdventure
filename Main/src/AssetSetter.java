public class AssetSetter{
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setobject(){
        gp.obj[0] = new OBJ_KEY(gp);
        gp.obj[0].worldX = 3 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_DOOR(gp);
        gp.obj[1].worldX = 5 * gp.tileSize;
        gp.obj[1].worldY = 7 * gp.tileSize;

        // gp.obj[2] = new OBJ_DOOR(gp);
        // gp.obj[2].worldX = 8 * gp.tileSize;
        // gp.obj[2].worldY = 8 * gp.tileSize;

        // gp.obj[3] = new OBJ_KEY(gp);
        // gp.obj[3].worldX = 12 * gp.tileSize;
        // gp.obj[3].worldY = 21 * gp.tileSize;

        // gp.obj[4] = new OBJ_DOOR(gp);
        // gp.obj[4].worldX = 23 * gp.tileSize;
        // gp.obj[4].worldY = 36 * gp.tileSize;

        // gp.obj[5] = new OBJ_KEY(gp);
        // gp.obj[5].worldX = 38 * gp.tileSize;
        // gp.obj[5].worldY = 13 * gp.tileSize;

    } 

    public void setnpc(){

        gp.npc[0] = new NPC_OLDMAN(gp);
        gp.npc[0].worldX = 10 * gp.tileSize;
        gp.npc[0].worldY = 4 * gp.tileSize;

    }
}