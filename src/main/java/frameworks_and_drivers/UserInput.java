package frameworks_and_drivers;


import interface_adapters.NextScreenData;

import interface_adapters.team_creation.TeamCreationController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInput extends JFrame implements ActionListener {
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
            teamCreationController.createNewTeam(tfTeamName.getText());
            nextScreenData.setCurrentUser(teamCreationController.getCurrUser());

            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
