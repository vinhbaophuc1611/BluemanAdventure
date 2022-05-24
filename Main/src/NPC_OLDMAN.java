// import java.awt.*;
// import javax.imageio.ImageIO;
// import java.awt.image.BufferedImage;
// import java.io.IOException;
import java.util.Random;
// import java.awt.Rectangle;
// import java.awt.Graphics2D;
public class NPC_OLDMAN extends Entity{

    public NPC_OLDMAN(GamePanel gp){
        super (gp);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
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

    public void setDialogue(){

        dialogues[0] = "Hello, world!";
        dialogues[1] = "Arcade Fire's 'The Suburbs' won the Album \nof the Year award in the 2011 Grammys.";
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

    public void speak(){

        super.speak();
    }
    
}
