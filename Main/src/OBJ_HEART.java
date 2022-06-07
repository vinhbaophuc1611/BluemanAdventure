// import java.io.IOException;
// import javax.imageio.ImageIO;

public class OBJ_HEART extends Entity {

    public OBJ_HEART(GamePanel gp) {

        super(gp);
        name = "Heart";
        image = setup("heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("heart_blank", gp.tileSize, gp.tileSize);
        
    }

}
