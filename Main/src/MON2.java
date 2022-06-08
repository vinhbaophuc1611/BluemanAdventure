import java.util.Random;

public class MON2 extends Entity {

    public MON2(GamePanel gp) {
        super(gp);
        type = 4;
        name = "Monster";
        speed = 1;
        maxLife = 10;
        life = maxLife;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
        setMonsterDialog();
    }
    public void getImage(){
        up1 = setup("mon2_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("mon2_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("mon2_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("mon2_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("mon2_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("mon2_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("mon2_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("mon2_right_2", gp.tileSize, gp.tileSize);
    }
    public void setMonsterDialog(){
        dialoguesMonster[0] = "Hi";
        dialoguesMonster[1] = "How are you?";
    }
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            //PICK UP A NUMBER FROM 1 - 100 or 0 - 99
            int i = random.nextInt(100) + 1; 
            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void monsterSpeak(){
        super.monsterSpeak();
    }
    public void checkDrop() {
        //CAST A DIE
        int i = new Random().nextInt(50) + 1;
        //SET THE MONSTER DROP
        if(i < 50) {
            dropItem(new OBJ_KEY(gp));
        }
    }
}