package screens;

import javax.swing.*;
import java.awt.*;

public class bracketView extends JFrame {
//    The main panel for the bracket view screen
    private JTabbedPane mainView;
//    Panel Bar and General Info
    private JLabel currentUser;
    private JLabel bracketName;
    private JPanel bracketView;
    private JLabel currentTournament;
    private JPanel teamView;
    private JPanel observerControls;
    private JPanel tournamentControls;
    private JButton logOut;

//    Bracket View
    private JButton game2Button;
    private JButton game3Button;
    private JButton game1Button;
    private JLabel game3Label;
    private JLabel game1Label;
    private JLabel game2Label;
//    Team View
    private JLabel team1Name;
    private JList team1List;
    private JButton joinTeam1;
    private JLabel team2Name;
    private JButton joinTeam2;
    private JList team2List;
    private JLabel team3Name;
    private JList team3List;
    private JButton joinTeam3;
    private JList team4List;
    private JButton joinTeam4;
    private JLabel team4Name;
//    Observer Assignments
    private JComboBox observerGame1;
    private JButton assignGame1;
    private JComboBox observerGame2;
    private JButton assignGame2;
    private JComboBox observerGame3;
    private JButton assignGame3;
    private JComboBox observerGame4;
    private JButton assignGame4;
//    Overseer Controls
    private JButton startTournamentButton;
    private JButton startNextRoundButton;
    private JButton declareWinnerEndTournamentButton;

    public bracketView() {
        super("Bracket View");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainView);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
    }
}
