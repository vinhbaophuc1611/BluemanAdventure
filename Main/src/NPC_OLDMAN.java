import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.Graphics2D;
public class NPC_OLDMAN extends Entity{

    public NPC_OLDMAN(GamePanel gp){
        super (gp);

        direction = "left";
        speed = 1;
        getImage();
    }
    public void getImage() {

        up1 = setup("oldman_up_1");
        up2 = setup("oldman_up_2");
        down1 = setup("oldman_down_1");
        down2 = setup("oldman_down_2");
        left1 = setup("oldman_left_1");
        left2 = setup("oldman_left_2");
        right1 = setup("oldman_right_1");
        right2 = setup("oldman_right_2");

    }

    public void setAcion(){

        actionLockCounter ++;
        
        if(actionLockCounter == 0){

            Random random = new Random();
            //PICK UP A NUMBER FROM 1 - 100
            int i = random.nextInt(0)+1; 
            if(i <= 0){
                direction = "up";
            }
            else if(i > 0 && i <= 0){
                direction = "down";
            }
            else if(i > 0 && i <= 0){
                direction = "left";
            }
            else if(i > 0 && i <= 0){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    
}
