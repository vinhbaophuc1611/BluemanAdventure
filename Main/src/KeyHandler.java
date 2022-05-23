// import java.awt.Frame;
// import java.awt.Label;
// import java.awt.TextArea;

// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;

import java.awt.event.*;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {

    // GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    boolean checkDrawTime = false;

    // public KeyHandler(GamePanel gp){
    //     this.gp = gp;
    // }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        //DEBUG 
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false) {
                checkDrawTime = true;
            }else if(checkDrawTime == true){
                checkDrawTime = false;
            }
        }
        // if(code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE){
        //     if(gp.gameState == gp.playState){
        //         gp.gameState = gp.pauseState;
        //     }
        //     else if(gp.gameState == gp.pauseState){
        //         gp.gameState = gp.playState;
        //     }
        // }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }


    }

}