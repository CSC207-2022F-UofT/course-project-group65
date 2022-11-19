package screens.logIn;

import screens.createAccount.CreateAccountController;
import screens.homeScreen;
import screens.optionsScreen;
import useCases.LogIn.LogInOD;

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
    private JButton backButton;

    private CreateAccountController createAccountController;
    private LogInController logInController;

    public logInInfo(LogInController logInController, CreateAccountController createAccountController) {
        setContentPane(logIn);
        setTitle("Log In");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.logInController = logInController;
        this.createAccountController = createAccountController;

        btSubmit.addActionListener(this);
        backButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            homeScreen homeScreen = new homeScreen(this.createAccountController, this.logInController);
            this.dispose();
            homeScreen.setVisible(true);
        }
        if (e.getSource() == btSubmit) {
            String username = tfUsername.getText();
            String password = tfPassword.getText();

            try {
                LogInOD outputData = logInController.login(username, password);
                JOptionPane.showMessageDialog(this, "Welcome " + outputData.getUsername());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
            // Go to options screen
//            optionsScreen optionsScreen = new optionsScreen();
//            this.dispose();
//            optionsScreen.setVisible(true);
        }
    }
}
