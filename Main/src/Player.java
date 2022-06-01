// import java.awt.event.KeyListener;
// import java.awt.*;
import javax.imageio.ImageIO;
// import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Player extends Entity {
    
    // GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    int standCount = 0;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super (gp);
        // this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);    
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        setDefaultValues();
        getPlayerImage();
        setItems();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 42;
        worldY = gp.tileSize * 43;
        speed = 4;
        direction = "right"; 
        //PLAYER STATUS.
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        currentWeapon = new OBJ_WEAPON_NORMAL(gp);
        currentShield = new OBJ_SHIELD_WOOD(gp);
        currentKey = new OBJ_KEY(gp);
    }
    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(currentKey);
    }
    public int getAttack(){
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentWeapon.defenseValue;
    }
    public void getPlayerImage() {

        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");

    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image; 
    }

    public void update() {
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true) {
                direction = "up";
            }
            else if(keyH.downPressed == true) {
                direction = "down";
            }
            else if(keyH.leftPressed == true) {
                direction = "left";
            }
            else if(keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collision = false;
            gp.cChecker.checkTile(this);
            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collision == false) {
                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed;break;
                    case "right": worldX += speed; break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            // String text;
            // if(inventory.size() != maxInventorySize){
            //     inventory.add(currentKey);
            //     text = "Got a " + gp.obj[i].name + "!";
            // }else{
            //     text = "You cannot carry anymore";
            // }
            // gp.ui.showMessage(text);
            // gp.obj[i] = null;
            String objectName = gp.obj[i].name;
            // if(inventory.size() != maxInventorySize){
            //     inventory.add(currentKey);
            //     objectName = "Got a " + gp.obj[i].name + "!";
            //     switch(objectName){
            //         case "Key":
            //             hasKey++;
            //             gp.obj[i] = null;
            //             // gp.ui.showMessage("objectName");
            //             break;
            //         case "Door":
            //             if(hasKey > 0){
            //                 gp.obj[i] = null;
            //                 hasKey--;
            //                 gp.ui.showMessage("You opened the door!");
            //             }
            //             else {
            //                 gp.ui.showMessage("You need a key to open!");
            //             }
            //             System.out.println("Key: " + hasKey);
            //             break;    
            //         }
            // }else{
            //     objectName = "You cannot carry anymore!";
            // }
            // gp.ui.showMessage(objectName);
            // gp.obj[i] = null;
            switch(objectName){
                case "Key":
                hasKey++;
                inventory.add(currentKey);
                gp.obj[i] = null;
                gp.ui.showMessage("You got a key!");
                break;
                case "Door":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        inventory.remove(currentKey);
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key to open!");
                    }
                    System.out.println("Key: " + hasKey);
                    break;
            }
        }
    }
    public void interactMonster(int i){
        if(i != 999){
            if(gp.keyH.monsterPressed == true){
                gp.gameState = gp.dialogueMonsterState;
                gp.monster[i].monsterSpeak();
            }
            gp.keyH.monsterPressed = false;
        }
    }
    public void interactNPC(int i){
        if(i != 999){
            //System.out.println("You are hitting an NPC!");
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            gp.keyH.enterPressed = false;
        }
    }
    
    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }

}