// import java.awt.image.BufferedImage;
// import java.awt.*;
// import java.awt.Rectangle;
// import java.awt.Graphics2D;

// public class superobject {

//     public int worldX, worldY;
//     public Rectangle solidArea = new Rectangle(0, 0, 30, 30);
//     public int solidAreaDefaultX = 0;
//     public int solidAreaDefaultY = 0;
//     UtilityTool uTool = new UtilityTool();
    
//     public void draw(Graphics2D g2, GamePanel gp) {
        
//         int screenX = worldX - gp.player.worldX + gp.player.screenX;
//         int screenY = worldY - gp.player.worldY + gp.player.screenY;

//             if(worldX  + gp.tileSize > gp.player.worldX - gp.player.screenX && 
//                 worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//                 worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                 worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
//                 g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

//             }

//     }
// }
