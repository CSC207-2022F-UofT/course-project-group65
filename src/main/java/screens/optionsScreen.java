package screens;

import screens.joinTournament.JoinTournamentController;
import screens.joinTournament.JoinTournamentInfo;
import screens.logIn.logInInfo;
import screens.viewTournament.ViewTournamentInfo;
import useCases.joinTournament.JoinTournamentIB;

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
            // TODO
        }

        else if (e.getSource() == btJoinExisting){
            // TODO
        }

        else if (e.getSource() == btJoinNew){
            // TODO
        }

        else if (e.getSource() == btLogOut){
            // Go back to home screen
            homeScreen homeScreen = new homeScreen();
            this.dispose();
            homeScreen.setVisible(true);
        }
    }
}
