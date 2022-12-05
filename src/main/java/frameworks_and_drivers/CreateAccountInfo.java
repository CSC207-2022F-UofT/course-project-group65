package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.log_in.LogInController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountInfo extends JFrame implements ActionListener{
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton btSubmit;
    private JPanel createAccount;
    private JButton backBtn;
    private final CreateAccountController createAccountController;
    private final LogInController logInController;
    private final NextScreenData nextScreenData;


    public CreateAccountInfo(CreateAccountController createAccountController, LogInController logInController, NextScreenData nextScreenData) {
        setContentPane(createAccount);
        setTitle("Creating An Account");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.createAccountController = createAccountController;
        this.logInController = logInController;
        this.nextScreenData = nextScreenData;

        btSubmit.addActionListener(this);
        backBtn.addActionListener(this);
        lbUsername.setVisible(true);
        lbPassword.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            HomeScreen homeScreen = new HomeScreen(createAccountController, logInController, this.nextScreenData);
            homeScreen.setVisible(true);
            this.dispose();
        }

        if (e.getSource() == btSubmit) {
            String username = tfUsername.getText();
            String password = tfPassword.getText();


            try {
                createAccountController.create(username, password);
                JOptionPane.showMessageDialog(this, "Successfully created account!");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
            HomeScreen homeScreen = new HomeScreen(this.createAccountController, this.logInController, this.nextScreenData);
            this.dispose();
            homeScreen.setVisible(true);

        }
    }
}
