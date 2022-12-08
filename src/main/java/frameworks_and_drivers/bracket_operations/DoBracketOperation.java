package frameworks_and_drivers.bracket_operations;

import interface_adapters.advance_team.AdvanceTeamController;
import interface_adapters.advance_team.AdvanceTeamFailed;
import interface_adapters.change_points.ChangePointsController;
import interface_adapters.change_points.ChangePointsFailed;
import interface_adapters.declare_winner.DeclareWinnerController;
import interface_adapters.declare_winner.DeclareWinnerFailed;
import interface_adapters.view_interfaces.bracket_operation_interface.ChangePointsBOView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the screen file for the bracket operations use cases - so ChangePoints, AdvanceTeam, and DeclareWinner.
 */
public class DoBracketOperation extends JFrame implements ActionListener, ChangePointsBOView {
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
    private JLabel changePtsInstruction;
    private JPanel bracketOpWindow;
    private JTextField teamPointsBox;

    private final AdvanceTeamController advanceTeamController;
    private final DeclareWinnerController declareWinnerController;
    private final ChangePointsController changePointsController;
    private int gameID;

    public DoBracketOperation(AdvanceTeamController advanceTeamController,
                              DeclareWinnerController declareWinnerController,
                              ChangePointsController changePointsController) {
        super("Bracket Operations");
        this.advanceTeamController = advanceTeamController;
        this.declareWinnerController = declareWinnerController;
        this.changePointsController = changePointsController;

        gameNumLabel.setText("Game Number: 0");
        teamsLabel.setText("Teams: ");
        advanceTeamLabel.setText("Advance Winning Team");
        chngPtsLabel.setText("Change Points for Team");
        declareButton.setText("Declare Winner");
        declareWInnerLabel.setText("Declare the Winner");

        advanceButton.addActionListener(this);
        changePointsButton.addActionListener(this);
        declareButton.addActionListener(this);
        changePtsTF.addActionListener(this);

        title.setVisible(true);
        changePtsInstruction.setVisible(true);


        this.setContentPane(bracketOpWindow);
        this.setPreferredSize(new Dimension(650, 300));
        this.pack();
        this.setVisible(true);

    }


    // Setters for the labels on the screen.
    public void setGameForOperation(int gameID) {
        this.gameID = gameID;
    }

    public void setGameNumLabel(String gameID) {
        this.gameNumLabel.setText("Game Number: " + gameID);
    }

    public void setTeamsLabel(String label) {
        this.teamsLabel.setText(label);
    }
    public void changeTeamsLabel(String team1 , String team2, int team1Score, int team2Score) {
        this.teamsLabel.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }

    /**
     * This method is responsible for taking the user input upon the user clicking a button - activating the change
     * points use case or the advance team use case or the declare winner use case.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == advanceButton) {
                advanceTeamController.create(gameID);
            } else if (e.getSource() == declareButton) {
                declareWinnerController.create(gameID);
            } else if (e.getSource() == changePointsButton){
                int points = Integer.parseInt(changePtsTF.getText().trim());
                String teamName = teamPointsBox.getText().trim();
                if (teamName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select a team.");
                }
                changePointsController.setView(this);
                changePointsController.create(gameID, points, teamName);
            }

        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
        } catch (AdvanceTeamFailed | DeclareWinnerFailed | ChangePointsFailed exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

}
