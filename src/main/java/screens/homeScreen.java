package screens;

import screens.createAccount.createAccountInfo;
import screens.logIn.logInInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homeScreen extends JFrame implements ActionListener{
    private JLabel header;
    private JButton btLogIn;
    private JButton btCreateAccount;
    private JPanel homeScreen;

    public homeScreen() {
        setContentPane(homeScreen);
        setTitle("Home Screen");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btLogIn.addActionListener(this);
        btCreateAccount.addActionListener(this);
    }

    public static void main(String[] args) {
        homeScreen homeScreen = new homeScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCreateAccount) {
            createAccountInfo createAccountScreen = new createAccountInfo();
            this.dispose();
            createAccountScreen.setVisible(true);
        }

        if (e.getSource() == btLogIn) {
            logInInfo logInScreen = new logInInfo();
            this.dispose();
            logInScreen.setVisible(true);
        }
    }
}
