import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
// import java.util.*;

// import java.util.concurrent.TimeUnit;

public class TFAnswer {

    JFrame window;
    Container con;

    JPanel trueChoice, falseChoice, controlPanel, questionPanel;
    JLabel statusLabel, questionLabel;

    JButton enterTrue, enterFalse;

    Font normalFont = new Font("Arial", Font.BOLD, 26);

    String questions = "1+1 = 2";

    public TFAnswer() {

        // WINDOW SETTINGS
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.white);
        window.setLayout(null);

        con = window.getContentPane();

        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // STATUS LABEL
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setBounds(150, 450, 500, 30);
        window.add(statusLabel);

        // QUESTION SETTINGS
        questionPanel = new JPanel();
        questionPanel.setBounds(150, 250, 500, 50);
        questionLabel = new JLabel(questions);
        
        questionLabel.setForeground(Color.black);
        questionLabel.setFont(normalFont);
        questionPanel.add(questionLabel);
        con.add(questionPanel);

        // TRUE CHOICE SETUP
        trueChoice = new JPanel();
        trueChoice.setBounds(150, 450, 250, 30);
        trueChoice.setBackground(Color.black);
        trueChoice.setLayout(new GridLayout(1, 2));

        enterTrue = new JButton("TRUE");
        enterTrue.setBackground(Color.white);

        enterTrue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("It's correct.");
            }
        });
        trueChoice.add(enterTrue);
        con.add(trueChoice);

        // FALSE CHOICE SETUP
        falseChoice = new JPanel();
        falseChoice.setBounds(400, 450, 250, 30);
        falseChoice.setBackground(Color.black);
        falseChoice.setLayout(new GridLayout(1, 2));

        enterFalse = new JButton("FALSE");
        enterFalse.setBackground(Color.white);

        enterFalse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("It's wrong.");
            }
        });
        falseChoice.add(enterFalse);
        con.add(falseChoice);

        window.setVisible(true);

        prepareGUI();

    }

    public void prepareGUI() {
        // window = new JFrame("TFAnswer");
        // window.setSize(400, 300);
        // window.setLayout(new GridLayout(3, 1));
        // window.addWindowListener(new WindowAdapter() {
        //     public void windowClosing(WindowEvent windowEvent) {
        //         System.exit(0);
        //     }
        // });
        // questions = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        // window.add(questions);
        window.add(controlPanel);
        window.add(statusLabel);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        new TFAnswer();
    }

    public void testAnswer() {

    }

    // private JFrame mainFrame;
    // private JLabel headerLabel;
    // private JLabel statusLabel;
    // private JPanel controlPanel;
 
    // public TFAnswer() {
    //     prepareGUI();
    // }
 
    // public static void main(String[] args) {
    //     TFAnswer TFAnswer = new TFAnswer();
    //     TFAnswer.showButtonDemo();
    // }
 
    // private void prepareGUI() {
    //     mainFrame = new JFrame("TFAnswer");
    //     mainFrame.setSize(400, 300);
    //     mainFrame.setLayout(new GridLayout(3, 1));
    //     mainFrame.addWindowListener(new WindowAdapter() {
    //         public void windowClosing(WindowEvent windowEvent) {
    //             System.exit(0);
    //         }
    //     });
    //     headerLabel = new JLabel("", JLabel.CENTER);
    //     statusLabel = new JLabel("", JLabel.CENTER);
    //     statusLabel.setSize(350, 100);
    //     controlPanel = new JPanel();
    //     controlPanel.setLayout(new FlowLayout());
    //     mainFrame.add(headerLabel);
    //     mainFrame.add(controlPanel);
    //     mainFrame.add(statusLabel);
    //     mainFrame.setVisible(true);
    // }
 
    // private void showButtonDemo() {
    //     headerLabel.setText("DO I HANDSOME?");
    //     JButton okButton = new JButton("True");
    //     JButton javaButton = new JButton("Submit");
    //     JButton cancelButton = new JButton("False");
    //     cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);
    //     okButton.addActionListener(new ActionListener() {
    //         public void actionPerformed(ActionEvent e) {
    //             statusLabel.setText("It's correct.");
    //         }
    //     });
    //     javaButton.addActionListener(new ActionListener() {
    //         public void actionPerformed(ActionEvent e) {
    //             statusLabel.setText("Submit Button clicked.");
    //         }
    //     });
    //     cancelButton.addActionListener(new ActionListener() {
    //         public void actionPerformed(ActionEvent e) {
    //             statusLabel.setText("It's wrong.");
    //         }
    //     });
    //     controlPanel.add(okButton);
    //     controlPanel.add(javaButton);
    //     controlPanel.add(cancelButton);
    //     mainFrame.setVisible(true);
    // }
    
}
