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
        up1 = setup("npc_oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("npc_oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("npc_oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("npc_oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("npc_oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("npc_oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("npc_oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("npc_oldman_right_2", gp.tileSize, gp.tileSize);
    }
    public void setDialogue(){
        dialogues[0] = "Hello, world!";
        dialogues[1] = "Arcade Fire's 'The Suburbs' won \nthe Album of the Year \naward in the 2011 Grammys.";
        dialogues[2] = "The cover of The Beatles album \n'Abbey' featured a Volkswagen \nBeetle in the background.";
        dialogues[3] = "The country song 'A Boy Named \nSue' was written by Shel Silverstein.";
    }

    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 90){
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