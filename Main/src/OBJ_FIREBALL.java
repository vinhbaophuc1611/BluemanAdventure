public class OBJ_FIREBALL extends Projectile{
    GamePanel gp;
    public OBJ_FIREBALL(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "FireBall";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("fire_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("fire_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("fire_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("fire_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("fire_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("fire_left_1", gp.tileSize, gp.tileSize);
        right1 = setup("fire_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("fire_right_2", gp.tileSize, gp.tileSize);
    }
}
