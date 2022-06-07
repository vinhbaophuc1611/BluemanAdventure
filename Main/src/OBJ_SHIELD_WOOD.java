public class OBJ_SHIELD_WOOD extends Entity{

    public OBJ_SHIELD_WOOD(GamePanel gp) {
        super(gp);
        name = "Shield Wood";
        right1 = setup("shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nMade by wood.";
    }
}
