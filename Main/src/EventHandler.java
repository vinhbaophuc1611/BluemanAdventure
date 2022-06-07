// import java.awt.Rectangle;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];
    int map = 0;
    public EventHandler(GamePanel gp){
        this.gp = gp;
        // int previousEventX, previousEventY;
        // boolean canToucheEvent = true;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x = 0;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y = 0;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }
    public void checkEvent(){
        for(int i = 0; i < 20; i++){
            if(hit(1, 13, 7, "left") == true){
                teleport1(0, 8, 10, gp.dialogueState);
            }
            if(hit(0, 8, 10, "left") == true){
                teleport2(1, 13, 7, gp.dialogueState);
            }
        }
        if(hit(1, 45, 31, "any") == true){
            damagePit(1, 45, 31, gp.dialogueState);
        }
    }
    public boolean hit(int map, int col, int row, String req){
        boolean hit = false;
        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
    
            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if(gp.player.direction.contentEquals(req) || req.contentEquals("any")){
                    hit = true;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }return hit;
    }
    public void damagePit(int map, int col, int row, int gameState){
        gp.gameState = gameState;
        gp.currentMap = map;
        gp.ui.currentDialogue = "You fall into a pit";
        gp.player.life -= 1;
        eventRect[map][col][row].eventDone = true;
    }
    public void teleport1(int map, int col, int row, int gameState){
        gp.gameState = gameState;
        gp.currentMap = map;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
    }
    public void teleport2(int map, int col, int row, int gameState){
        gp.gameState = gameState;
        gp.currentMap = map;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
    }
}