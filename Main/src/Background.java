import java.io.IOException;
import javax.imageio.ImageIO;
// import java.awt.image.BufferedImage;

public class Background extends Entity {

    public Background(GamePanel gp) {
        super(gp);

        UtilityTool uTool = new UtilityTool();

        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("BLUEMAN.png"));
            backgroundImage = uTool.scaledImage(backgroundImage, gp.screenWidth, gp.screenHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // name = "Background";
        // right1 = setup("BluemanAdventure");
    }
}