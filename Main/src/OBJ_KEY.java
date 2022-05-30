// package src;
// import java.io.IOException;
// import javax.imageio.ImageIO;

public class OBJ_KEY extends Entity {

    public OBJ_KEY(GamePanel gp) {
        super(gp);
        name = "Key";
        right1 = setup("key");
        description = "[" + name + "]\nIt opens a door.";
    }
}
