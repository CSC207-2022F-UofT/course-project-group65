package screens;

import screens.createAccount.CreateAccountController;
import screens.createAccount.CreateAccountPresenter;
import screens.createBracket.CreateBracketController;
import screens.createBracket.CreateBracketPresenter;
import screens.createBracket.enterBracketInfo;
import screens.joinTournament.JoinTournamentController;
import screens.joinTournament.JoinTournamentInfo;
import screens.joinTournament.JoinTournamentPresenter;
import screens.logIn.LogInController;
import screens.logIn.LogInPresenter;
import screens.logIn.logInInfo;
import screens.viewTournament.ViewTournamentController;
import screens.viewTournament.ViewTournamentInfo;
import screens.viewTournament.ViewTournamentPresenter;
import useCases.CreateAccount.CreateAccountIB;
import useCases.CreateAccount.CreateAccountOB;
import useCases.CreateAccount.CreateAccountUC;
import useCases.LogIn.LogInIB;
import useCases.LogIn.LogInOB;
import useCases.LogIn.LogInUC;
import useCases.createBracket.CreateBracketIB;
import useCases.createBracket.CreateBracketOB;
import useCases.createBracket.CreateBracketUC;
import useCases.joinTournament.JoinTournamentIB;
import useCases.joinTournament.JoinTournamentOB;
import useCases.joinTournament.JoinTournamentUC;
import useCases.viewTournament.ViewTournamentIB;
import useCases.viewTournament.ViewTournamentOB;
import useCases.viewTournament.ViewTournamentUC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class optionsScreen extends JFrame implements ActionListener {
    private JLabel lbHeader;
    private JButton btCreateBracket;
    private JButton btJoinNew;
    private JButton btJoinExisting;
    private JButton btLogOut;
    private JPanel optionsScreen;
    private NextScreenData nextScreenData;
//    private CreateAccountController createAccountController;
//    private LogInController logInController;
//    private CreateBracketController createBracketController;

    public optionsScreen(NextScreenData nextScreenData) {
        setContentPane(optionsScreen);
        setTitle("Options Screen");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.nextScreenData = nextScreenData;
//        this.createAccountController = createAccountController;
//        this.logInController = logInController;
//        this.createBracketController = createBracketController;

        btCreateBracket.addActionListener(this);
        btJoinNew.addActionListener(this);
        btJoinExisting.addActionListener(this);
        btLogOut.addActionListener(this);

    }

//    public optionsScreen(NextScreenData nextScreenData, CreateAccountController createAccountController, LogInController logInController,
//                         CreateBracketController createBracketController) {
//        setContentPane(optionsScreen);
//        setTitle("Options Screen");
//        setSize(450, 300);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
//
//        this.nextScreenData = nextScreenData;
//        this.createAccountController = createAccountController;
//        this.logInController = logInController;
//        this.createBracketController = createBracketController;
//
//        btCreateBracket.addActionListener(this);
//        btJoinNew.addActionListener(this);
//        btJoinExisting.addActionListener(this);
//        btLogOut.addActionListener(this);
//
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCreateBracket) {
            // Create a new bracket

            CreateBracketOB createBracketOB = new CreateBracketPresenter();
            CreateBracketIB interactor = new CreateBracketUC(createBracketOB, nextScreenData.getCurrentUser(),
                    nextScreenData.getAccounts(), nextScreenData.getBrackets());
            CreateBracketController createBracketCon = new CreateBracketController(interactor);



//            enterBracketInfo enterBracketInfo = new enterBracketInfo(this.createBracketController);
            enterBracketInfo enterBracketInfo = new enterBracketInfo(createBracketCon);
            this.dispose();
            enterBracketInfo.setCurrentUser(nextScreenData.getCurrentUser());
            enterBracketInfo.setVisible(true);

        } else if (e.getSource() == btJoinExisting) {
            // Join an Existing Bracket
            System.out.println("Join Existing Bracket");
            ViewTournamentOB viewTournamentOB = new ViewTournamentPresenter();
            ViewTournamentIB viewTournamentIB = new ViewTournamentUC(viewTournamentOB, nextScreenData.getBrackets(),
                    nextScreenData.getAccounts(), nextScreenData.getCurrentUser());
            ViewTournamentController viewTournamentController = new ViewTournamentController(viewTournamentIB);
            ViewTournamentInfo viewTournamentInfo = new ViewTournamentInfo(viewTournamentController);
            this.dispose();
            viewTournamentInfo.setVisible(true);

        } else if (e.getSource() == btJoinNew) {
            // Join a new bracket
            System.out.println("Join New Bracket");
            JoinTournamentOB joinTournamentOB = new JoinTournamentPresenter();
            JoinTournamentIB joinTournamentIB = new JoinTournamentUC(joinTournamentOB, nextScreenData.getBrackets(),
                    nextScreenData.getAccounts(), nextScreenData.getCurrentUser());
            JoinTournamentController joinTournamentController = new JoinTournamentController(joinTournamentIB);
            JoinTournamentInfo joinTournamentInfo = new JoinTournamentInfo(joinTournamentController);
            this.dispose();
            joinTournamentInfo.setVisible(true);

        } else if (e.getSource() == btLogOut) {
            // Go back to home screen

            CreateAccountOB createAccountOB = new CreateAccountPresenter();
            CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB, nextScreenData.getAccounts(),
                    nextScreenData.getBrackets());
            CreateAccountController createAccountController = new CreateAccountController(createAccountIB);

            LogInOB logInOB = new LogInPresenter();
            LogInIB logInIB = new LogInUC(logInOB, nextScreenData.getAccounts(),
                    nextScreenData.getBrackets());
            LogInController logInController = new LogInController(logInIB);

            homeScreen homeScreen = new homeScreen(createAccountController, logInController);

//            homeScreen homeScreen = new homeScreen(this.createAccountController, this.logInController);
            this.dispose();
            homeScreen.setVisible(true);
        }
    }
}

