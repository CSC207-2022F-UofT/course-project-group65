package screens;

import screens.endTourn.EndTournController;
import screens.startTourn.StartTournController;
import useCases.endTourn.EndTournIB;
import useCases.endTourn.EndTournID;
import useCases.startTourn.StartTournID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bracketView extends JFrame implements ActionListener {
//    The main panel for the bracket view screen
    private JPanel mainPanel;
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
    private JList<String> team1List;
    private JButton joinTeam1;
    private JLabel team2Name;
    private JButton joinTeam2;
    private JList<String> team2List;
    private JLabel team3Name;
    private JList<String> team3List;
    private JButton joinTeam3;
    private JList<String> team4List;
    private JButton joinTeam4;
    private JLabel team4Name;
//    Observer Assignments
    private JComboBox<String> observerGame1;
    private JButton assignGame1;
    private JComboBox<String> observerGame2;
    private JButton assignGame2;
    private JComboBox<String> observerGame3;
    private JButton assignGame3;
//    Overseer Controls
    private JButton startTournamentButton;
    private JButton declareWinnerEndTournamentButton;
//    Controllers for all Buttons (Go below)

    public bracketView() {
        super("Bracket View");
//        Assign all controllers for this view

//        Default Text for New Bracket View
//        Info Bar
        currentUser.setText("Logged in: ");
        bracketName.setText("Bracket Name");
        currentTournament.setText("ID: 0");
//        Bracket View
        game1Label.setText("[] 0 - 0 []");
        game2Label.setText("[BlankTeam 1] 0 - 0 [BlankTeam 2]");
        game3Label.setText("[BlankTeam 3] 0 - 0 [BlankTeam 4]");
//        Team View
        team1Name.setText("BlankTeam 1");
        team1List.setListData(new String[]{});
        team2Name.setText("BlankTeam 2");
        team2List.setListData(new String[]{});
        team3Name.setText("BlankTeam 3");
        team3List.setListData(new String[]{});
        team4Name.setText("BlankTeam 4");
        team4List.setListData(new String[]{});
//        Observer Assignments (No Default Text)
//        Overseer Controls (No Default Text)

//        Action Listeners for all Buttons
//        Bracket View
        game1Button.addActionListener(this);
        game2Button.addActionListener(this);
        game3Button.addActionListener(this);
//        Team View
        joinTeam1.addActionListener(this);
        joinTeam2.addActionListener(this);
        joinTeam3.addActionListener(this);
        joinTeam4.addActionListener(this);
//        Observer Assignments
        assignGame1.addActionListener(this);
        assignGame2.addActionListener(this);
        assignGame3.addActionListener(this);
//        Overseer Controls
        startTournamentButton.addActionListener(this);
        declareWinnerEndTournamentButton.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
    }

//    Setter Methods to update the view, or used when creating a new instance of
//    a tournament that is currently in progress
//    Info Bar
    public void setCurrentUser(String username) {
        currentUser.setText("Logged in: " + username);
    }
    public void setBracketName(String bracketName) {
        this.bracketName.setText(bracketName);
    }
    public void setCurrentTournament(int tournamentID) {
        currentTournament.setText("ID: " + tournamentID);
    }
//    Bracket View
    public void setGame1Label(String team1 , String team2, int team1Score, int team2Score) {
        this.game1Label.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }
    public void setGame2Label(String team1 , String team2, int team1Score, int team2Score) {
        this.game2Label.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }
    public void setGame3Label(String team1 , String team2, int team1Score, int team2Score) {
        this.game3Label.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }
//    Team View
    public void setTeam1Name(String team1Name) {
        this.team1Name.setText(team1Name);
    }
    public void setTeam1PlayerList(String[] team1List) {
        this.team1List.setListData(team1List);
    }
    public void setTeam2Name(String team2Name) {
        this.team2Name.setText(team2Name);
    }
    public void setTeam2PlayerList(String[] team2List) {
        this.team2List.setListData(team2List);
    }
    public void setTeam3Name(String team3Name) {
        this.team3Name.setText(team3Name);
    }
    public void setTeam3PlayerList(String[] team3List) {
        this.team3List.setListData(team3List);
    }
    public void setTeam4Name(String team4Name) {
        this.team4Name.setText(team4Name);
    }
    public void setTeam4PlayerList(String[] team4List) {
        this.team4List.setListData(team4List);
    }
//    Observer Assignments
    public void setObserverListGame1(String[] observers) {
        this.observerGame1.setModel(new DefaultComboBoxModel<>(observers));
    }
    public void setObserverListGame2(String[] observers) {
        this.observerGame2.setModel(new DefaultComboBoxModel<>(observers));
    }
    public void setObserverListGame3(String[] observers) {
        this.observerGame3.setModel(new DefaultComboBoxModel<>(observers));
    }


//    Action Listeners for all Buttons (Controllers are connected here)
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
//        Replace print statements with controller calls
        if (e.getSource() == game1Button) {
            System.out.println("Game 1 Button Clicked");
//            Create new screen for game 1
//            In that screen have buttons linked to change points and advance teams
//            TODO
        } else if (e.getSource() == game2Button) {
            System.out.println("Game 2 Button Clicked");
            //            TODO
        } else if (e.getSource() == game3Button) {
            System.out.println("Game 3 Button Clicked");
            //            TODO
        } else if (e.getSource() == joinTeam1) {
            System.out.println("Join Team 1 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
        } else if (e.getSource() == joinTeam2) {
            System.out.println("Join Team 2 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
        } else if (e.getSource() == joinTeam3) {
            System.out.println("Join Team 3 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
        } else if (e.getSource() == joinTeam4) {
            System.out.println("Join Team 4 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
        } else if (e.getSource() == assignGame1) {
            System.out.println("Assign Game 1 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
        } else if (e.getSource() == assignGame2) {
            System.out.println("Assign Game 2 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
        } else if (e.getSource() == assignGame3) {
            System.out.println("Assign Game 3 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
        } else if (e.getSource() == startTournamentButton) {
            System.out.println("Start Tournament Button Clicked");

            //            TODO
//            Calls corresponding UC through controller
//
//            StartTournID userInput = new StartTournID(brackets, userName, accounts, bracketId);
//            StartTournController startTournController = new StartTournController(userInput);
//            startTournController.startTourn()
        } else if (e.getSource() == declareWinnerEndTournamentButton) {
            System.out.println("Declare Winner End Tournament Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
            // End Tournament below
            // for now I assumed I have all the infos about the inputs for ID.
//
//            EndTournID userInput = new EndTournID(brackets, userName, accounts, bracketId);
//            EndTournController endTournController = new EndTournController(userInput);
        }
    }
}
