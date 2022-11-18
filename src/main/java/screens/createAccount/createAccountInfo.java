package screens.createAccount;

import screens.homeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createAccountInfo extends JFrame implements ActionListener{

    private JLabel lbUsername;
    private JLabel lbPassword;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton btSubmit;
    private JPanel createAccount;


    public createAccountInfo() {
        setContentPane(createAccount);
        setTitle("Creating An Account");
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
