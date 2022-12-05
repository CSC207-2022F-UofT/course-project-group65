package frameworks_and_drivers;


import interface_adapters.NextScreenData;

import interface_adapters.team_creation.TeamCreationController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the screen that takes in user input after the user clicks create in the team view page
 * It takes in a team name as a string from the text field and initializes the controller for team
 * creation use case once the create button is clicked.
 */
public class UserInput extends JFrame implements ActionListener {
    private JLabel TeamName;
    /** This is the text field that takes in a team name from the user  */
    private JTextField tfTeamName;
    /** This is the button that the user clicks when they finish */
    private JButton btnCreate;
    private JPanel inputPanel;
    /** This is the controller that is connected to the create button */
    private final TeamCreationController teamCreationController;
    /** This contains all the data that was passed on from previous screens */
    private final NextScreenData nextScreenData;
    /** This is the view model that gets updated after teamCreation gets run */
    public ExtendedView extendedView;

    /**
     * This creates an instance of UserInput screen and makes the screen visible to the user
     * @param controller teamCreation controller that passes input to the interactor
     * @param viewChange this is the current view model that will get updated
     * @param nextScreenData this contains all the data that was passed on from pervious screens
     */
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

    /**
     * This reacts to the button and calls the creatNewTeam method to run the teamCreation use case after taking in
     * a valid team name.
     * @param evt the event to be processed
     */
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
