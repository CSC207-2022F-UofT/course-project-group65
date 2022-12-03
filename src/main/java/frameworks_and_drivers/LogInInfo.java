package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.log_in.LogInController;
import use_cases.log_in.LogInOD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInInfo extends JFrame implements ActionListener{
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JPanel logIn;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton btSubmit;
    private JButton backButton;

    private final CreateAccountController createAccountController;
    private final LogInController logInController;
    private final NextScreenData nextScreenData;

    public LogInInfo(LogInController logInController, CreateAccountController createAccountController, NextScreenData nextScreenData) {
        setContentPane(logIn);
        setTitle("Log In");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.logInController = logInController;
        this.createAccountController = createAccountController;
        this.nextScreenData = nextScreenData;

        btSubmit.addActionListener(this);
        backButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            HomeScreen homeScreen = new HomeScreen(this.createAccountController, this.logInController, this.nextScreenData);
            this.dispose();
            homeScreen.setVisible(true);
        }
        if (e.getSource() == btSubmit) {
            String username = tfUsername.getText();
            String password = tfPassword.getText();

            try {
                LogInOD outputData = logInController.login(username, password);
                JOptionPane.showMessageDialog(this, "Welcome " + outputData.getUsername());

                String currentUser = outputData.getUsername();
                nextScreenData.setCurrentUser(currentUser);
                OptionsScreen optionsScreen = new OptionsScreen(nextScreenData);
                this.dispose();
                optionsScreen.setVisible(true);

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        }
    }
}
