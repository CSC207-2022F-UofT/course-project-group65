package interface_adapters.join_team;

import interface_adapters.data_interface_adapters.join_team_data.JoinTeamFileWriter;

import interface_adapters.view_interfaces.main_view_interfaces.JoinTeamViewInterface;
import use_cases.general_classes.InformationRecord;
import use_cases.join_team.*;

import java.util.ArrayList;

/**
 * A class for the Controller of the join team use case.
 */
public class JoinTeamController {
    final JoinTeamIB input;
    public ArrayList<String> membersNames;
    JoinTeamPresenter presenter;
    /**
     * Creates a new JoinTeamController object
     * @param username The name of the user who joined the team
     * @param informationRecord The class that stores the repositories
     * @param bracketID The current bracket id
     */
    public JoinTeamController(InformationRecord informationRecord, int bracketID, String username ){
        this.presenter = new JoinTeamPresenter();
        JoinTeamGateway gateway = new JoinTeamFileWriter("brackets.txt");
        this.input = new JoinTeamUC(this.presenter, gateway, username, bracketID, informationRecord);
    }

    public void setPresenterView(JoinTeamViewInterface view){
        presenter.setView(view);
    }

    /**
     * Joins the team in the bracket
     *
     * @param teamName The name of the team that the user chose
     */
    public void joinTeam(String teamName){
        JoinTeamID inputData = new JoinTeamID(teamName);
        JoinTeamOD outputData = input.joinTeam(inputData);
        this.membersNames = outputData.getMembersNames();
    }
}
