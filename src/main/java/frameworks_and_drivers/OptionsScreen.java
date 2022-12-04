package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.create_bracket.CreateBracketController;
import interface_adapters.join_tournament.JoinTournamentController;
import interface_adapters.log_in.LogInController;
import interface_adapters.view_tournament.ViewTournamentController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OptionsScreen extends JFrame implements ActionListener {
    private JLabel lbHeader;
    private JButton btCreateBracket;
    private JButton btJoinNew;
    private JButton btJoinExisting;
    private JButton btLogOut;
    private JPanel optionsScreen;
    private final NextScreenData nextScreenData;

    public OptionsScreen(NextScreenData nextScreenData) {
        setContentPane(optionsScreen);
        setTitle("Options Screen");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        this.nextScreenData = nextScreenData;
        btCreateBracket.addActionListener(this);
        btJoinNew.addActionListener(this);
        btJoinExisting.addActionListener(this);
        btLogOut.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCreateBracket) {
            // Create a new bracket

            CreateBracketController createBracketCon = new CreateBracketController(nextScreenData.getCurrentUser(),
                    nextScreenData.getInformationRecord());
            EnterBracketInfo enterBracketInfo = new EnterBracketInfo(createBracketCon, nextScreenData);
            this.dispose();
            enterBracketInfo.setCurrentUser(nextScreenData.getCurrentUser());
            enterBracketInfo.setVisible(true);

        } else if (e.getSource() == btJoinExisting) {
            // Join an Existing Bracket
            System.out.println("Join Existing Bracket");

            ViewTournamentController viewTournamentController = new ViewTournamentController(nextScreenData.getInformationRecord(), nextScreenData.getCurrentUser());
            ViewTournamentInfo viewTournamentInfo = new ViewTournamentInfo(viewTournamentController, nextScreenData);
            ArrayList<Integer> tournaments = nextScreenData.getUsersTournaments(nextScreenData.getCurrentUser());
            viewTournamentInfo.setTournaments(tournaments);
            this.dispose();
            viewTournamentInfo.setVisible(true);

        } else if (e.getSource() == btJoinNew) {
            // Join a new bracket
            System.out.println("Join New Bracket");

            JoinTournamentController joinTournamentController = new JoinTournamentController(
                    nextScreenData.getInformationRecord(), nextScreenData.getCurrentUser());
            JoinTournamentInfo joinTournamentInfo = new JoinTournamentInfo(joinTournamentController, nextScreenData);
            this.dispose();
            joinTournamentInfo.setVisible(true);

        } else if (e.getSource() == btLogOut) {
            // Go back to home screen

            CreateAccountController createAccountController = new CreateAccountController(nextScreenData.getInformationRecord());


            LogInController logInController = new LogInController(nextScreenData.getInformationRecord());

            HomeScreen homeScreen = new HomeScreen(createAccountController, logInController, nextScreenData);

            this.dispose();
            homeScreen.setVisible(true);
        }
    }
}

