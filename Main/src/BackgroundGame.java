// import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
// import javax.imageio.ImageIO;

import javax.imageio.ImageIO;

public class BackgroundGame extends Entity {

    public BufferedImage setupImage(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaledImage(image, gp.screenWidth, gp.screenHeight);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image; 
    }
    
    public BackgroundGame(GamePanel gp) {
        super(gp);
        name = "Background";
        // image = setup("blue_man");
        // right1 = setup("blue_man");

    }

}
