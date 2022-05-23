// package src;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_KEY extends superobject {

    GamePanel gp;
    public OBJ_KEY(GamePanel gp) {

        this.gp = gp;
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("key.png"));
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace(); 
        }
    }
}
