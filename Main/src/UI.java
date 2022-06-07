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
    // Font arial_40, arial_80B;
    Font maruMFont, purisa;
    BufferedImage keyImage;
    BufferedImage heart_half, heart_full, heart_blank;
    BufferedImage backgroundImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";
    public String currentMonsterDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;

    int subState = 0;

    double playTime ;
    DecimalFormat DF = new DecimalFormat("#0");
    private Graphics2D g2;

    public UI(GamePanel gp){
        this.gp = gp;
        // arial_40 = new Font("Cambria", Font.PLAIN, 40);
        // arial_80B = new Font("Arial", Font.BOLD, 80);
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

        //BACKGROUND
        Entity background = new Background(gp);
        backgroundImage = background.backgroundImage;

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
        //MONSTER STATE
        if(gp.gameState == gp.dialogueMonsterState){
            drawDialogueMonsterScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterState();
            drawInventory();
        }
        //OPTION STATE
        if(gp.gameState == gp.optionState){
            drawOptionScreen();
        }
        //GAME OVER STATE
        if(gp.gameState == gp.optionState){
            drawGameOverScreen();
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

        //BACKGROUND_IMAGE
        g2.drawImage(backgroundImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

        // g2.setColor(new Color(255, 255, 255));
        // g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String text = "";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 2;
        //SHADOW COLOR
        g2.setColor(new Color(153, 153, 153));
        // g2.drawString(text, x + 5, y + 5);
        
        //MAIN COLOR
        g2.setColor(Color.red);
        g2.drawString(text, x, y);

        //BLUE BOY IMAGE
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize * 2;
        // g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

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
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 42F));
        g2.drawString(text, x - gp.tileSize, y);
    }
    public void drawDialogueMonsterScreen(){
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSub(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentMonsterDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
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
        //CREATE FRAME
        final int frameX = gp.tileSize - 40;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 7;
        final int frameHeight = gp.tileSize * 10;
        drawSub(frameX, frameY, frameWidth, frameHeight);
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));
        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight =  50;
        //NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight; 
        g2.drawString("Life", textX, textY);
        textY += lineHeight; 
        g2.drawString("Strength", textX, textY);
        textY += lineHeight; 
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight + 20; 
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;
        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        //RESET TEXTY
        textY = frameY + gp.tileSize;
        String value;
        //LEVEL
        value = String.valueOf(gp.player.level);
        textX = getXforText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        //LIFE
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        //STRENGTH
        value = String.valueOf(gp.player.strength);
        textX = getXforText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        //DEXTERITY
        value = String.valueOf(gp.player.dexterity);
        textX = getXforText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        //WEAPON
        g2.drawImage(gp.player.currentWeapon.right1, tailX - gp.tileSize, textY - 16, null);
        textY += lineHeight;
        g2.drawImage(gp.player.currentShield.right1, tailX - gp.tileSize, textY - 16, null);
        textY += lineHeight;
    }
    public void drawInventory(){
        //FRAME
        int frameX = (int) (gp.tileSize * 9.8);
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSub(frameX, frameY, frameWidth, frameHeight);
        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart; 
        int slotSize = gp.tileSize + 3;
        //DRAW PLAYER'S ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++){
            g2.drawImage(gp.player.inventory.get(i).right1, slotX, slotY, null);
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        //CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        //DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        //DRAW DESCRIPTIONS
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;
        drawSub(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
        //DRAW SCRIPT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(25F));
        int itemIndex = getItemToSLot();
        if(itemIndex < gp.player.inventory.size()){
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){ 
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public void drawGameOverScreen() {

        //GAME OVER COLOR SCREEN
        g2.setColor(new Color(0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x, y;
        String text = "Game Over";
        g2.setFont(g2.getFont().deriveFont(25F));

        //SHADOW
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;

        //MAIN
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //RETRY
        g2.setFont(g2.getFont().deriveFont(25F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        //BACK TO TITLE SCREEN
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }
    public void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(25F));

        //SUB WINDOW
        int frameX = gp.tileSize*6, frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8, frameHeight = gp.tileSize*10;
        drawSub(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0: optionTop(frameX, frameY); break;
            case 1: break;
            case 2: break;
        }

        gp.keyH.enterPressed = false;

    }

    public void optionTop(int frameX, int frameY) {
        int textX, textY;

        //TITLE
        String text = "Option";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULL SCREEN SWITCH (ON/OFF)
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                if(gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                }
                else if(gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                }
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
        }

        //SAVE GAME
        textY += gp.tileSize;
        g2.drawString("Save game", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX-25, textY);
        }

        //SE
        // textY += gp.tileSize;
        // g2.drawString("SE", textX, textY);
        // if(commandNum == 2) {
        //     g2.drawString(">", textX-25, textY);
        // }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX-25, textY);
        }

        //END GAME
        textY += gp.tileSize;
        g2.drawString("End game", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX-25, textY);
        }

        //BACK
        textY += gp.tileSize;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX-25, textY);
        }

        //FULL SCREEN CHECK BOX
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);

        //SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);

        gp.config.saveConfig();

    }

    public int getItemToSLot(){
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
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
    public int getXforText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}