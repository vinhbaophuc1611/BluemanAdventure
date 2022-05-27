import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_HEART extends superobject {

    GamePanel gp;
    public OBJ_HEART(GamePanel gp) {

        this.gp = gp;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("heart_blank.png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaledImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaledImage(image3, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace(); 
        }
    }

}
