package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.create_bracket.CreateBracketController;
import interface_adapters.join_tournament.JoinTournamentController;
import interface_adapters.join_tournament.JoinTournamentInfo;
import interface_adapters.log_in.LogInController;
import interface_adapters.view_tournament.ViewTournamentController;
import interface_adapters.view_tournament.ViewTournamentInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

//            CreateBracketOB createBracketOB = new CreateBracketPresenter();
//            CreateBracketIB interactor = new CreateBracketUC(createBracketOB, nextScreenData.getCurrentUser(),
//                    nextScreenData.getAccounts(), nextScreenData.getBrackets());
//            CreateBracketController createBracketCon = new CreateBracketController(interactor);
            CreateBracketController createBracketCon = new CreateBracketController(nextScreenData.getCurrentUser(),
                    nextScreenData.getInformationRecord());
//            enterBracketInfo enterBracketInfo = new enterBracketInfo(this.createBracketController);
            enterBracketInfo enterBracketInfo = new enterBracketInfo(createBracketCon, nextScreenData);
            this.dispose();
            enterBracketInfo.setCurrentUser(nextScreenData.getCurrentUser());
            enterBracketInfo.setVisible(true);

        } else if (e.getSource() == btJoinExisting) {
            // Join an Existing Bracket
            System.out.println("Join Existing Bracket");
//            ViewTournamentOB viewTournamentOB = new ViewTournamentPresenter();
//            ViewTournamentIB viewTournamentIB = new ViewTournamentUC(viewTournamentOB, nextScreenData.getBrackets(),
//                    nextScreenData.getAccounts(), nextScreenData.getCurrentUser());
//            ViewTournamentController viewTournamentController = new ViewTournamentController(viewTournamentIB);

            ViewTournamentController viewTournamentController = new ViewTournamentController(nextScreenData.getInformationRecord(), nextScreenData.getCurrentUser());
            ViewTournamentInfo viewTournamentInfo = new ViewTournamentInfo(viewTournamentController, nextScreenData);
            ArrayList<Integer> tournaments = nextScreenData.getUsersTournaments(nextScreenData.getCurrentUser());
            viewTournamentInfo.setTournaments(tournaments);
            System.out.println(tournaments);
            this.dispose();
            viewTournamentInfo.setVisible(true);

        } else if (e.getSource() == btJoinNew) {
            // Join a new bracket
            System.out.println("Join New Bracket");
//            JoinTournamentOB joinTournamentOB = new JoinTournamentPresenter();
//            JoinTournamentIB joinTournamentIB = new JoinTournamentUC(joinTournamentOB, nextScreenData.getBrackets(),
//                    nextScreenData.getAccounts(), nextScreenData.getCurrentUser());
//            JoinTournamentController joinTournamentController = new JoinTournamentController(joinTournamentIB);
            JoinTournamentController joinTournamentController = new JoinTournamentController(
                    nextScreenData.getInformationRecord(), nextScreenData.getCurrentUser());
            JoinTournamentInfo joinTournamentInfo = new JoinTournamentInfo(joinTournamentController, nextScreenData);
            this.dispose();
            joinTournamentInfo.setVisible(true);

        } else if (e.getSource() == btLogOut) {
            // Go back to home screen

//            CreateAccountOB createAccountOB = new CreateAccountPresenter();
//            CreateAccountGateway createAccountGateway = new CreateAccountFileWriter("accounts.txt");
//            CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB, nextScreenData.getAccounts(),
//                    nextScreenData.getBrackets(), createAccountGateway);
//            CreateAccountController createAccountController = new CreateAccountController(createAccountIB);
            CreateAccountController createAccountController = new CreateAccountController(nextScreenData.getInformationRecord());

//            LogInOB logInOB = new LogInPresenter();
//            LogInIB logInIB = new LogInUC(logInOB, nextScreenData.getAccounts(),
//                    nextScreenData.getBrackets());
            LogInController logInController = new LogInController(nextScreenData.getInformationRecord());

            homeScreen homeScreen = new homeScreen(createAccountController, logInController, nextScreenData);

//            homeScreen homeScreen = new homeScreen(this.createAccountController, this.logInController);
            this.dispose();
            homeScreen.setVisible(true);
        }
    }
}

