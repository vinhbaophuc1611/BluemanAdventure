// import src.GamePanel;
// import Main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity {
   
    GamePanel gp;
    public int worldX, worldY;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "right";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 30, 30);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    String dialoguesMonster[] = new String[20];
    public int dialoguesIndex = 0;
    public int dialoguesMonsterIndex = 0;
    public BufferedImage image, image2, image3;
    public boolean alive = true;
    public boolean dying = false;
    public int dyingCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    boolean hpBarOn = false;
    public int hpBarCounter = 0;

    //CHARACTER STATUS
    public int type;
    public String name;
    public int speed;
    public int maxLife;
    public int life;   
    public int maxMana;
    public int mana;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentKey;
    public Projectile projectile;

    //ITEMS ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;

    //BACKGROUND
    public BufferedImage backgroundImage;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){}
    public void speak(){
        if(dialogues[dialoguesIndex] == null){
            dialoguesIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialoguesIndex];
        dialoguesIndex++;
        switch(gp.player.direction){
        case "up":
            direction = "down";
            break;
        case "down":
            direction = "up";
            break;
        case "left":
            direction = "right";
            break;
        case "right":
            direction = "left";
            break;
        }
    }
    public void use() {}
    public void checkDrop() {}
    public void dropItem(Entity dropppedItem) {
        for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
            if(gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = dropppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public void monsterSpeak(){
        if(dialoguesMonster[dialoguesMonsterIndex] == null){
            dialoguesMonsterIndex = 0;
        }
        gp.ui.currentMonsterDialogue = dialoguesMonster[dialoguesMonsterIndex];
        dialoguesMonsterIndex++;
        switch(gp.player.direction){
        case "up":
            direction = "down";
            break;
        case "down":
            direction = "up";
            break;
        case "left":
            direction = "right";
            break;
        case "right":
            direction = "left";
            break;
        }
    }
    public void update(){
        setAction();
        collision = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = false;gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }
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
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if(worldX  + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
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
                if(type == 2 && hpBarOn == true){
                    double oneScale = (double)gp.tileSize/maxLife;
                    double hpBarValue = oneScale * life;

                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                    hpBarCounter++;
                    if(hpBarCounter > 60){
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }
                if(invincible == true){
                    hpBarOn = true;
                    hpBarCounter = 0;
                    changeAlpha(g2, 0.4f);
                }
                if(dying == true) {
                    dyingAnimation(g2);
                }
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;
        if(dyingCounter <= i){
            changeAlpha(g2,0f);
        }
        if(dyingCounter > i && dyingCounter <= i * 2){
            changeAlpha(g2,1f);
        }
        if(dyingCounter > i * 2 && dyingCounter <= i * 3){
            changeAlpha(g2,0f);
        }
        if(dyingCounter > i * 3 && dyingCounter <= i * 4){
            changeAlpha(g2,1f);
        }
        if(dyingCounter > i * 4 && dyingCounter <= i * 5){
            changeAlpha(g2,0f);
        }
        if(dyingCounter > i * 5 && dyingCounter <= i * 6){
            changeAlpha(g2,1f);
        }
        if(dyingCounter > i * 6 && dyingCounter <= i * 7){
            changeAlpha(g2,0f);
        }
        if(dyingCounter > i * 7 && dyingCounter <= i * 8){
            changeAlpha(g2,1f);
        }
        if(dyingCounter > i * 8){
            dying = false;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaledImage(image, width, height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image; 
    }
    
}