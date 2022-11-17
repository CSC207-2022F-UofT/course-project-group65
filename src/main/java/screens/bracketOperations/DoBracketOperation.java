package screens.bracketOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DoBracketOperation extends JFrame implements ActionListener {
    private JLabel title;
    private JLabel gameNumLabel;
    private JLabel teamsLabel;
    private JButton declareAWinnerButton;
    private JComboBox<String> advanceTeamBox;
    private JLabel advanceTeamLabel;
    private JLabel declareWInnerLabel;
    private JButton declareButton;
    private JLabel chngPtsLabel;
    private JButton advanceButton;
    private JTextField changePtsTF;
    private JButton changePointsButton;
    private JButton declareWinnerButton;
    private JComboBox<String> changePtsBox;
    private JLabel changePtsInstruction;
    private JButton submitAdvanceChoice;
    private JButton submitChangePtsChoice;
    private JTextField changeptsfield;

    public DoBracketOperation() {
        super("Bracket Operations");


        gameNumLabel.setText("Game Number: 0");
        teamsLabel.setText("Teams: ");
        advanceTeamLabel.setText("Advance Winning Team");
        chngPtsLabel.setText("Change Points for Team");
        declareButton.setText("Declare Winner");

        advanceButton.addActionListener(this);
        changePointsButton.addActionListener(this);
        declareWinnerButton.addActionListener(this);
        changePointsButton.addActionListener(this);
        changePtsTF.addActionListener(this);
        changePtsBox.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(title);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
    }

    // Setter methods
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
            this.advanceTeamBox.addItem(team);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == changePointsButton){
            changePtsInstruction.setText("Enter the new points for the team");
            changePtsBox.setVisible(true);
            changeptsfield.setVisible(true);
            submitChangePtsChoice.setVisible(true);
        }
        else if (e.getSource() == advanceButton){
            changePtsInstruction.setText("Select the team to advance");
            changePtsBox.setVisible(true);
            submitChangePtsChoice.setVisible(true);
        }
        else if (e.getSource() == submitChangePtsChoice){
            changePtsBox.setVisible(false);
            changeptsfield.setVisible(false);
            submitChangePtsChoice.setVisible(false);
        }
        else if (e.getSource() == submitAdvanceChoice){
            changePtsBox.setVisible(false);
            submitChangePtsChoice.setVisible(false);
        }

//        if(e.getSource() == submitAdvanceChoice) {
//            try {
//                String team = Objects.requireNonNull(advanceTeamBox.getSelectedItem()).toString();
//                // Call controller
//                // AdvanceTeamController advanceTeamController = new AdvanceTeamController(team);
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, "Please select a team to advance.");
//            }
//        }
//        else if(e.getSource() == submitChangePtsChoice) {
//            try {
//                int pts = Integer.parseInt(changeptsfield.getText());
//                // Call controller
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, "Please enter a valid number of points.");
//            }
//        } else if (e.getSource() == declareAWinnerButton) {
//            // TODO: Implement this
//            // Call controller method to declare a winner
//        }

    }
}
