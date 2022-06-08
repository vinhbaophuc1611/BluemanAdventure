// import javax.swing.*;
// import java.awt.*;

// public class ImageExample {

//     private JFrame frame;
//     private ImageIcon backgroundIcon;
//     private JLabel label;

//     public ImageExample() {

//         backgroundIcon = new ImageIcon(this.getClass().getResource("blue_man.png"));

//         // ImageIcon backgroundIcon = new ImageIcon("blue_man.png");
//         Image backgroundImage = backgroundIcon.getImage();
//         Image scaledImage = backgroundImage.getScaledInstance(768, 576, java.awt.Image.SCALE_SMOOTH);
//         backgroundIcon = new ImageIcon(scaledImage);

//         label = new JLabel(backgroundIcon);
//         label.setSize(768, 576);

//         frame = new JFrame();
//         frame.add(label);
//         frame.setSize(780, 610);
//         frame.setLayout(null);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLocationRelativeTo(null);
//         frame.setVisible(true);

//     }

//     // private void scaledImage() {

        

//     // }

//     public static void main(String[] args) {

//         new ImageExample();

//         // ImageIcon backgroundIcon = new ImageIcon("blue_man.png");
//         // Image backgroundImage = backgroundIcon.getImage();
//         // Image scaledImage = backgroundImage.getScaledInstance(650, 450, java.awt.Image.SCALE_SMOOTH);
//         // backgroundIcon = new ImageIcon(scaledImage);

//         // JOptionPane.showMessageDialog(null, "nothing", "Background", 
//         //     JOptionPane.INFORMATION_MESSAGE, backgroundIcon);

//     }
    
// }
