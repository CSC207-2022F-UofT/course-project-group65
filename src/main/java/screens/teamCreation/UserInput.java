package screens.teamCreation;


import useCases.teamCreation.teamCreationIB;
import useCases.teamCreation.teamCreationOB;
import useCases.teamCreation.teamCreationOD;
import useCases.teamCreation.teamCreationUC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInput extends JFrame{
    private JLabel TeamName;
    private JTextField tfTeamName;
    private JButton btnCreate;
    private JPanel inputPanel;

    public UserInput(){
        setContentPane(inputPanel);
        setTitle("Create A Team");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamName = tfTeamName.getText();
                teamCreationOB presenter = new TeamCreationPresenter();
                // the remaining variables are local, how do we access them?
//                teamCreationIB interactor = new teamCreationUC(presenter, username, bracketID, accounts, brackets);
//                TeamCreationController teamCreationController = new TeamCreationController(interactor);
//                teamCreationOD outputData = teamCreationController.createNewTeam(teamName, teamSize);
                // how do we present outputData?
            }
        });
    }
}
