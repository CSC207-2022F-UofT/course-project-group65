package screens.logIn;

import screens.homeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logInInfo extends JFrame implements ActionListener{
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JPanel logIn;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton btSubmit;

    public logInInfo() {
        setContentPane(logIn);
        setTitle("Log In");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btSubmit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btSubmit) {
            String username = tfUsername.getText();
            String password = tfPassword.getText();
        }
    }
}
