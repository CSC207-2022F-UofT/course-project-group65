package screens.teamCreation;


import screens.ExtendedView;
import screens.NextScreenData;

import use_cases.team_creation.teamCreationOD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInput extends JFrame implements ActionListener{
    private JLabel TeamName;
    private JTextField tfTeamName;
    private JButton btnCreate;
    private JPanel inputPanel;
    private final TeamCreationController teamCreationController;
//    public bracketView view;
    private final NextScreenData nextScreenData;
    public ExtendedView extendedView;

    public UserInput(TeamCreationController controller, ExtendedView viewChange, NextScreenData nextScreenData) {

        this.teamCreationController = controller;
        this.extendedView = viewChange;
        this.nextScreenData = nextScreenData;

        this.setContentPane(inputPanel);
        this.setTitle("Create A Team");
        this.setSize(450,300);
        this.setVisible(true);
        btnCreate.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {

        try {
            if(tfTeamName.getText() == null) {
                throw new Exception("Please enter a team name");
            }
            teamCreationOD outputData = teamCreationController.createNewTeam(tfTeamName.getText());

            nextScreenData.setCurrentUser(outputData.getUsername());
            nextScreenData.bundleData();
            extendedView.replaceTeam(outputData.getNewTeam(), outputData.getOldTeam(), outputData.getGameToTeams());
            extendedView.updateTeamMembers(outputData.getTeamToPlayers());

            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
