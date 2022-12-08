package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.log_in.LogInController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The home screen of the application.
 */
public class HomeScreen extends JFrame implements ActionListener{
    private JLabel header;
    private JButton btLogIn;
    private JButton btCreateAccount;
    private JPanel homeScreen;
    private final CreateAccountController createAccountController;
    private final LogInController logInController;
    private final NextScreenData nextScreenData;

    public HomeScreen(CreateAccountController createAccountController, LogInController logInController, NextScreenData nextScreenData) {
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
        header.setVisible(true);
    }

    /**
     * Handles the button clicks on the home screen. Activates the appropriate controller for logging in or creating an
     * account.
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCreateAccount) {
            CreateAccountInfo createAccountScreen = new CreateAccountInfo(this.createAccountController, this.logInController, this.nextScreenData);
            this.dispose();
            createAccountScreen.setVisible(true);
        }

        if (e.getSource() == btLogIn) {
            LogInInfo logInScreen = new LogInInfo(this.logInController, this.createAccountController, this.nextScreenData);
            this.dispose();
            logInScreen.setVisible(true);
        }
    }
}
