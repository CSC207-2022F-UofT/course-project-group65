package screens;

import screens.createBracket.enterBracketInfo;
import screens.joinTournament.JoinTournamentInfo;
import screens.logIn.logInInfo;
import screens.viewTournament.ViewTournamentInfo;

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

    public optionsScreen() {
        setContentPane(optionsScreen);
        setTitle("Options Screen");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btCreateBracket.addActionListener(this);
        btJoinNew.addActionListener(this);
        btJoinExisting.addActionListener(this);
        btLogOut.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == btCreateBracket) {
//            // Create a new bracket
//            enterBracketInfo enterBracketInfo = new enterBracketInfo();
//            this.dispose();
//            enterBracketInfo.setVisible(true);
//
//        } else if (e.getSource() == btJoinExisting) {
//            // Join an Existing Bracket
//            ViewTournamentInfo viewTournamentInfo = new ViewTournamentInfo();
//            this.dispose();
//            viewTournamentInfo.setVisible(true);
//
//        } else if (e.getSource() == btJoinNew) {
//            // Join a new bracket
//            JoinTournamentInfo joinTournamentInfo = new JoinTournamentInfo();
//            this.dispose();
//            joinTournamentInfo.setVisible(true);
//
//        } else if (e.getSource() == btLogOut) {
//            // Go back to home screen
//            homeScreen homeScreen = new homeScreen();
//            this.dispose();
//            homeScreen.setVisible(true);
//            }
        }
    }
