// package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.awt.*;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font maruMFont, purisa;
    BufferedImage keyImage;
    BufferedImage heart_half, heart_full, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNum = 0;
    double playTime ;
    DecimalFormat DF = new DecimalFormat("#0");

    public UI(GamePanel gp){
        this.gp = gp;
        try{
            InputStream is = getClass().getResourceAsStream("x12y16pxMaruMonica.ttf");
            maruMFont = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("Purisa Bold.ttf");
            purisa = Font.createFont(Font.TRUETYPE_FONT, is); 
        }catch(FontFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        OBJ_KEY key = new OBJ_KEY(gp);
        keyImage = key.image;

        //CREATE HUD
        Entity Heart = new OBJ_HEART(gp);
        heart_full = Heart.image;
        heart_half = Heart.image2;
        heart_blank = Heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;
        // g2.setFont(maruMFont);
        g2.setFont(purisa);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.hasKey, 74, 65);

        //TIME
        playTime += (double)1/60;
        g2.drawString("Time: "+DF.format(playTime), gp.tileSize * 12, 65);

        //DISPLAY MESSAGE
        if(messageOn == true){
            
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

            messageCounter++;
            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }

        //TITLE SCREEN
        if(gp.gameState == gp.titleState){
            drawTitlesScreen();
        }

        //PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();

        }
        //PAUSE STATE
        if(gp.gameState == gp.gamePause){
            drawPlayerLife();
            drawPauseScreen();
        }
        //DISPLAY MESSAGE
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterState();
        }
    }

    public void drawPlayerLife(){
        int x = gp.tileSize / 2;
        int y = gp.tileSize * 10;
        int i = 0;
        //DRAW MAX HEART
        while(i < gp.player.maxLife / 2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize / 2;
        y = gp.tileSize * 10;
        i = 0;
        //DRAW CURRENT LIFE
        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitlesScreen(){

        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String text = "Blueman Adventure";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        //SHADOW COLOR
        g2.setColor(new Color(153, 153, 153));
        g2.drawString(text, x + 5, y + 5);
        
        //MAIN COLOR
        g2.setColor(Color.red);
        g2.drawString(text, x, y);

        //BLUE BOY IMAGE
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize ;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen(){
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){

        //DRAW ON WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSub(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawCharacterState(){

        //CREATE A FRAME
        final int frameX = gp.tileSize / 2;
        final int frameY = gp.tileSize; 
        final int frameWidth = gp.tileSize * 7;
        final int frameHeight = gp.tileSize * 10;
        drawSub(frameX, frameY, frameWidth, frameHeight);

        //TEST
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 36;

        //NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        //RESET TEXTY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.right1, tailX - gp.tileSize, textY - 14, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentSheild.right1, tailX - gp.tileSize, textY - 14, null);
        textY += gp.tileSize;
    }
    
    public void drawSub(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}