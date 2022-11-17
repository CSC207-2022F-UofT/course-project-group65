package screens.joinTeam;

import entities.User;
import useCases.joinTeam.joinTeamIB;
import useCases.joinTeam.joinTeamID;
import useCases.joinTeam.joinTeamOD;


public class joinTeamController {
    final joinTeamIB input;

    public joinTeamController(joinTeamIB input){
        this.input = input;
    }

    public joinTeamOD joinTeam(User user, String teamName){
        joinTeamID inputData = new joinTeamID(user, teamName);
        return input.joinTeam(inputData);
    }
}
