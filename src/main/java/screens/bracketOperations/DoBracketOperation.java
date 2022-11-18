package screens.bracketOperations;

import screens.advanceTeam.AdvanceTeamController;
import screens.changePoints.ChangePointsController;
import screens.declareWinner.DeclareWinnerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DoBracketOperation extends JFrame {
    private JLabel title;
    private JLabel gameNumLabel;
    private JLabel teamsLabel;
    private JLabel advanceTeamLabel;
    private JLabel declareWInnerLabel;
    private JButton declareButton;
    private JLabel chngPtsLabel;
    private JButton advanceButton;
    private JTextField changePtsTF;
    private JButton changePointsButton;
    private JComboBox<String> changePtsBox;
    private JLabel changePtsInstruction;
    private JPanel bracketOpWindow;

    private AdvanceTeamController advanceTeamController;
    private DeclareWinnerController declareWinnerController;
    private ChangePointsController changePointsController;
    public int gameID;

    public DoBracketOperation() {
        super("Bracket Operations");

        showScreen();
        advanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advanceTeamController.create(gameID);
            }
        });
        changePointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int points = 0;
                try {
                    points = Integer.parseInt(changePtsTF.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }

                String teamName = (String) changePtsBox.getSelectedItem();
                if (teamName == null) {
                    JOptionPane.showMessageDialog(null, "Please select a team.");
                }
                changePointsController.create(gameID, points, teamName);
            }
        });
        declareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                declareWinnerController.create(gameID);
            }
        });
    }

    // Overloading the constructor to allow for multiple use cases to be handled

    public DoBracketOperation(AdvanceTeamController controller){
        super("Bracket Operations");
        advanceTeamController = controller;
        showScreen();
        advanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advanceTeamController.create(gameID);
            }
        });
    }

    public DoBracketOperation(DeclareWinnerController controller){
        super("Bracket Operations");
        declareWinnerController = controller;
        showScreen();
        declareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                declareWinnerController.create(gameID);
            }
        });
    }

    public DoBracketOperation(ChangePointsController controller){
        super("Bracket Operations");
        changePointsController = controller;
        showScreen();
        changePointsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int points = 0;
                String teamName = (String) changePtsBox.getSelectedItem();
                if (teamName == null) {
                    JOptionPane.showMessageDialog(null, "Please select a team.");
                }

                try {
                    points = Integer.parseInt(changePtsTF.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }

                changePointsController.create(gameID, points, teamName);
            }
        });
    }


    public void showScreen() {
        gameNumLabel.setText("Game Number: 0");
        teamsLabel.setText("Teams: ");
        advanceTeamLabel.setText("Advance Winning Team");
        chngPtsLabel.setText("Change Points for Team");
        declareButton.setText("Declare Winner");
        declareWInnerLabel.setText("Declare the Winner");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(bracketOpWindow);
        this.setPreferredSize(new Dimension(650, 300));
        this.pack();
        this.setVisible(true);
    }

    // Getter Methods
    // I have to get the game ID somehow from next screen data or something.
    public void setGameForOperation(int gameID) {
        this.gameID = gameID;
    }

    public void setGameNumLabel(String gameID) {
        this.gameNumLabel.setText("Game Number:" + gameID);
    }

    public void setTeamsLabel(ArrayList<String> teams) {
        for (String team : teams) {
            this.teamsLabel.setText(this.teamsLabel.getText() + team + " ");
        }
    }

    public void setChangePointsBox(ArrayList<String> teams) {
        this.changePtsBox.removeAllItems();
        for (String team : teams) {
            this.changePtsBox.addItem(team);
        }
    }

    public static void main(String[] args) {
        DoBracketOperation doBracketOperation = new DoBracketOperation();
    }
}
