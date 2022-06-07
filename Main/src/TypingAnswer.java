import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TypingAnswer {
    
    JFrame window;
    Container con;
    JPanel questionPanel, inputPanel, answerPanel;
    JLabel questionLabel, answerLabel;
    JTextField textField;
    JButton enterB;

    InputHandler ihandler = new InputHandler();

    Font normalFont = new Font("Arial", Font.BOLD, 26);

    String questions = "1+1 = ?";

    public static void main(String[] args) {
        new TypingAnswer();
    }

    public TypingAnswer() {

        // WINDOW SETTING
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.white);
        window.setLayout(null);
        con = window.getContentPane();

        // QUESTION FRAME SETTINGS
        questionPanel = new JPanel();
        questionPanel.setBounds(150, 250, 500, 50);
        questionLabel = new JLabel(questions);
        
        questionLabel.setForeground(Color.black);
        questionLabel.setFont(normalFont);
        questionPanel.add(questionLabel);
        con.add(questionPanel);

        // ANSWER SETTINGS
        answerPanel = new JPanel();
        answerPanel.setBounds(150,300, 500, 50);
        answerLabel = new JLabel("Enter your answer: ");

        answerLabel.setForeground(Color.black);
        answerLabel.setFont(normalFont);
        answerPanel.add(answerLabel);
        con.add(answerPanel);

        // INPUT SETTING
        inputPanel = new JPanel();
        inputPanel.setBounds(150, 450, 500, 30);
        inputPanel.setBackground(Color.black);
        inputPanel.setLayout(new GridLayout(1, 2));

        textField = new JTextField();
        inputPanel.add(textField);

        // BUTTON SETTING
        enterB = new JButton("Answer");
        enterB.setBackground(Color.white);
        enterB.addActionListener(ihandler);
        inputPanel.add(enterB);
        con.add(inputPanel);

        window.setVisible(true);

    }

    public class InputHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String text = textField.getText();
            if(text.equalsIgnoreCase("2") || text.equalsIgnoreCase("two")) {
                questionLabel.setText(questions);
                answerLabel.setText("Your answer is correct");
            } else {
                questionLabel.setText(questions);
                answerLabel.setText("Your answer is wrong");
            }

        }

    }

}
