

public class OBJ_WEAPON_NORMAL extends Entity{
    
    public OBJ_WEAPON_NORMAL(GamePanel gp){
        super(gp);
        name = "Normal Sword";
        right1 = setup("sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
}
