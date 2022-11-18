package screens;

import screens.createBracket.enterBracketInfo;
import screens.joinTournament.JoinTournamentInfo;
import screens.logIn.logInInfo;
import screens.viewTournament.ViewTournamentInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.ActivationInstantiator;

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
        if (e.getSource() == btCreateBracket){
            // enterbracketinfo
            enterBracketInfo enterBracketInfo = new enterBracketInfo();
            this.dispose();
            enterBracketInfo.setVisible(true);
        }

        if (e.getSource() == btJoinExisting){
            // Join an Existing Bracket
            ViewTournamentInfo viewTournamentInfo = new ViewTournamentInfo();
            this.dispose();
            viewTournamentInfo.setVisible(true);
        }

        if (e.getSource() == btJoinNew){
            // Join a new bracket
            JoinTournamentInfo joinTournamentInfo = new JoinTournamentInfo();
            this.dispose();
            joinTournamentInfo.setVisible(true);
        }

        if (e.getSource() == btLogOut){
            // Go back to home screen
            homeScreen homeScreen = new homeScreen();
            this.dispose();
            homeScreen.setVisible(true);
        }
    }
}
