import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_DOOR extends superobject {

    GamePanel gp;
    public OBJ_DOOR(GamePanel gp) {

        this.gp = gp;
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("door.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace(); 
        }
        collision = true;
    }
}