import java.awt.Rectangle;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }
    public void checkEvent(){
        for(int i = 0; i < 10; i++){
            if(hit(11, 31, "down") == true){
                teleport1(gp.dialogueState);
            }
            if(hit(12, 31, "down") == true){
                teleport2(gp.dialogueState);
            }
            if(hit(11, 35, "up") == true){
                teleport3(gp.dialogueState);
            }
            if(hit(12, 35, "up") == true){
                teleport4(gp.dialogueState);
            }
        }

    }
    public boolean hit(int eventCol, int eventRow, String req){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.contentEquals(req) || req.contentEquals("any")){
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }
    public void teleport1(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize * 11;
        gp.player.worldY = gp.tileSize * 35;
    }
    public void teleport2(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize * 12;
        gp.player.worldY = gp.tileSize * 35;
    }
    public void teleport3(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize * 11;
        gp.player.worldY = gp.tileSize * 31;
    }
    public void teleport4(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport";
        gp.player.worldX = gp.tileSize * 12;
        gp.player.worldY = gp.tileSize * 31;
    }


}
