package screens.join_team;

import database.JoinTeam.JoinTeamFileWriter;

import use_cases.general_classes.InformationRecord;
import use_cases.join_team.*;

/**
 * A class for the Controller of the join team use case.
 */
public class JoinTeamController {
    final JoinTeamIB input;

    public JoinTeamController(InformationRecord informationRecord, int bracketID, String username ){
        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        JoinTeamGateway gateway = new JoinTeamFileWriter("brackets.txt");
        this.input = new JoinTeamUC(outputBoundary, gateway, username, bracketID, informationRecord);
    }

    public JoinTeamOD joinTeam(String teamName){
        JoinTeamID inputData = new JoinTeamID(teamName);
        return input.joinTeam(inputData);
    }
}
