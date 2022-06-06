// package src;
// import java.io.IOException;
// import javax.imageio.ImageIO;

public class OBJ_KEY extends Entity {

    public OBJ_KEY(GamePanel gp) {
        super(gp);
        // type = type_key;
        name = "Key";
        right1 = setup("OBJ_KEY", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens a door.";
    }
}
