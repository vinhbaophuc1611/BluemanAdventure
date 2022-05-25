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
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";

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
        //PLAY STATE
        if(gp.gameState == gp.playState){

        }
        //PAUSE STATE
        if(gp.gameState == gp.gamePause){
            drawPauseScreen();
        }
        //DISPLAY MESSAGE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
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

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
        

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
}