package screens.joinTeam;

import database.JoinTeam.JoinTeamFileWriter;

import entities.AccountRepo;
import entities.BracketRepo;
import useCases.joinTeam.*;

/**
 * A class for the Controller of the join team use case.
 */
public class JoinTeamController {
    final JoinTeamIB input;

    public JoinTeamController(BracketRepo bracketRepo, AccountRepo accountRepo, int bracketID, String username ){
        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        JoinTeamGateway gateway = new JoinTeamFileWriter("brackets.txt");
        this.input = new JoinTeamUC(outputBoundary, gateway, username, bracketID, accountRepo,bracketRepo);
    }

    public JoinTeamOD joinTeam(String teamName){
        JoinTeamID inputData = new JoinTeamID(teamName);
        return input.joinTeam(inputData);
    }
}
