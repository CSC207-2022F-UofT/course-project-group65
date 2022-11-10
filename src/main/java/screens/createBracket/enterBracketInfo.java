package screens.createBracket;

import javax.swing.*;
import java.awt.*;

public class enterBracketInfo extends JFrame {
    private JComboBox comboBox1;
    private JPanel mainPanel;
    private JLabel windowName;
    private JButton createButton;
    private JTextField textField1;
    private JComboBox comboBox3;
    private JComboBox comboBox2;
    private JComboBox comboBox4;


    public enterBracketInfo() {
        super("Create Bracket");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        enterBracketInfo frame = new enterBracketInfo();
        frame.setVisible(true);
    }

}


