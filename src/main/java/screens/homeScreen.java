package screens;

import screens.createAccount.CreateAccountController;
import screens.createAccount.createAccountInfo;
import screens.logIn.LogInController;
import screens.logIn.logInInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class homeScreen extends JFrame implements ActionListener{
    private JLabel header;
    private JButton btLogIn;
    private JButton btCreateAccount;
    private JPanel homeScreen;
    private CreateAccountController createAccountController;
    private LogInController logInController;

    public homeScreen(CreateAccountController createAccountController, LogInController logInController) {
        setContentPane(homeScreen);
        setTitle("Home Screen");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.createAccountController = createAccountController;
        this.logInController = logInController;

        btLogIn.addActionListener(this);
        btCreateAccount.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCreateAccount) {
            createAccountInfo createAccountScreen = new createAccountInfo(this.createAccountController, this.logInController);
            this.dispose();
            createAccountScreen.setVisible(true);
        }

        if (e.getSource() == btLogIn) {
            logInInfo logInScreen = new logInInfo(this.logInController, this.createAccountController);
            this.dispose();
            logInScreen.setVisible(true);
        }
    }
}
