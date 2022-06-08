// import java.io.IOException;
// import javax.imageio.ImageIO;

public class OBJ_DOOR extends Entity {  
    public OBJ_DOOR(GamePanel gp) {
        super(gp);
        type = 3;
        name = "Door";
        right1 = setup("OBJ_DOOR", gp.tileSize, gp.tileSize);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}