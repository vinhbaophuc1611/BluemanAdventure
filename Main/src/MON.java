import java.util.Random;

public class MON extends Entity {

    public MON(GamePanel gp) {
        super(gp);

        name = "Monster";
        speed = 1;
        maxLife = 4;
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

        up1 = setup("greenslime_down_1");
        up2 = setup("greenslime_down_2");
        down1 = setup("greenslime_down_1");
        down2 = setup("greenslime_down_2");
        left1 = setup("greenslime_down_1");
        left2 = setup("greenslime_down_2");
        right1 = setup("greenslime_down_1");
        right2 = setup("greenslime_down_2");

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
    
}