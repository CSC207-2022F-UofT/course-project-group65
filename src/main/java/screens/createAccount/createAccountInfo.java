package screens.createAccount;

import screens.homeScreen;
import screens.logIn.LogInController;
import useCases.CreateAccount.*;

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
    private JButton backBtn;
    private CreateAccountController createAccountController;
    private LogInController logInController;


    public createAccountInfo(CreateAccountController createAccountController, LogInController logInController) {
        setContentPane(createAccount);
        setTitle("Creating An Account");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.createAccountController = createAccountController;
        this.logInController = logInController;

        btSubmit.addActionListener(this);
        backBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            homeScreen homeScreen = new homeScreen(createAccountController, logInController);
            homeScreen.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == btSubmit) {
            String username = tfUsername.getText();
            String password = tfPassword.getText();


            try {
                CreateAccountOD outputData = createAccountController.create(username, password);
                JOptionPane.showMessageDialog(this, "Successfully created account!");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
            homeScreen homeScreen = new homeScreen(this.createAccountController, this.logInController);
            this.dispose();
            homeScreen.setVisible(true);


            // Go back to home screen
//            homeScreen homeScreen = new homeScreen();
//            this.dispose();
//            homeScreen.setVisible(true);
        }
    }
}
