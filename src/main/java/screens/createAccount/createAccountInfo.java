package screens.createAccount;

import entities.AccountRepo;
import screens.homeScreen;
import screens.logIn.logInInfo;
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
    private CreateAccountController createAccountController;


    public createAccountInfo(CreateAccountController createAccountController) {
        setContentPane(createAccount);
        setTitle("Creating An Account");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.createAccountController = createAccountController;

        btSubmit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btSubmit) {
            String username = tfUsername.getText();
            String password = tfPassword.getText();

            CreateAccountOD outputData = createAccountController.create(username, password);

            // Go back to home screen
            homeScreen homeScreen = new homeScreen();
            this.dispose();
            homeScreen.setVisible(true);
        }
    }
}
