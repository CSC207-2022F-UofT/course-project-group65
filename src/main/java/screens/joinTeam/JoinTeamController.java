package screens.joinTeam;

import useCases.joinTeam.JoinTeamIB;
import useCases.joinTeam.JoinTeamID;
import useCases.joinTeam.JoinTeamOD;


public class JoinTeamController {
    final JoinTeamIB input;

    public JoinTeamController(JoinTeamIB input){
        this.input = input;
    }

    public JoinTeamOD joinTeam(String teamName){
        JoinTeamID inputData = new JoinTeamID(teamName);
        return input.joinTeam(inputData);
    }
}
