package interface_adapters.join_team;

import interface_adapters.data_interface_adapters.join_team_data.JoinTeamFileWriter;

import use_cases.general_classes.InformationRecord;
import use_cases.join_team.*;

/**
 * A class for the Controller of the join team use case.
 */
public class JoinTeamController {
    final JoinTeamIB input;
    /**
     * Creates a new JoinTeamController object
     * @param username The name of the user who joined the team
     * @param informationRecord The class that stores the repositories
     * @param bracketID The current bracket id
     */
    public JoinTeamController(InformationRecord informationRecord, int bracketID, String username ){
        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        JoinTeamGateway gateway = new JoinTeamFileWriter("brackets.txt");
        this.input = new JoinTeamUC(outputBoundary, gateway, username, bracketID, informationRecord);
    }
    /**
     * Joins the team in the bracket
     * @param teamName The name of the team that the user chose
     */
    public JoinTeamOD joinTeam(String teamName){
        JoinTeamID inputData = new JoinTeamID(teamName);
        return input.joinTeam(inputData);
    }
}
