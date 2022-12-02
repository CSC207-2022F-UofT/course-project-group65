package screens.log_in;

import screens.NextScreenData;
import screens.create_account.CreateAccountController;
import frameworks_and_drivers.homeScreen;
import frameworks_and_drivers.optionsScreen;
import use_cases.log_in.LogInOD;

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
    private NextScreenData nextScreenData;

    public logInInfo(LogInController logInController, CreateAccountController createAccountController, NextScreenData nextScreenData) {
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
            homeScreen homeScreen = new homeScreen(this.createAccountController, this.logInController, this.nextScreenData);
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
//                AccountRepo currentAccounts = outputData.getAccountRepo();
//                BracketRepo currentBrackets = outputData.getBracketRepo();

//                NextScreenData nextScreenData = new NextScreenData();
                nextScreenData.setCurrentUser(currentUser);
//                nextScreenData.setAccounts(currentAccounts);
//                nextScreenData.setBrackets(currentBrackets);

//                CreateBracketOB createBracketOB = new CreateBracketPresenter();
//                CreateBracketIB interactor = new CreateBracketUC(createBracketOB, currentUser,
//                        currentAccounts, currentBrackets);
//                CreateBracketController createBracketCon = new CreateBracketController(interactor);

//                optionsScreen optionsScreen = new optionsScreen(nextScreenData, this.createAccountController,
//                        this.logInController, createBracketCon);
                optionsScreen optionsScreen = new optionsScreen(nextScreenData);
                this.dispose();
                optionsScreen.setVisible(true);

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
