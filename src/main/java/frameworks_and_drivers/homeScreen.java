package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.create_account.createAccountInfo;
import interface_adapters.log_in.LogInController;
import interface_adapters.log_in.logInInfo;

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
    private NextScreenData nextScreenData;

    public homeScreen(CreateAccountController createAccountController, LogInController logInController, NextScreenData nextScreenData) {
        setContentPane(homeScreen);
        setTitle("Home Screen");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.createAccountController = createAccountController;
        this.logInController = logInController;
        this.nextScreenData = nextScreenData;

        btLogIn.addActionListener(this);
        btCreateAccount.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCreateAccount) {
            createAccountInfo createAccountScreen = new createAccountInfo(this.createAccountController, this.logInController, this.nextScreenData);
            this.dispose();
            createAccountScreen.setVisible(true);
        }

        if (e.getSource() == btLogIn) {
            logInInfo logInScreen = new logInInfo(this.logInController, this.createAccountController, this.nextScreenData);
            this.dispose();
            logInScreen.setVisible(true);
        }
    }
}
