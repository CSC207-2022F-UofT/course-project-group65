package screens.joinTournament;

import database.JoinTournament.JoinTournamentFileWriter;
import useCases.joinTournament.*;

public class JoinTournamentController {
    final private JoinTournamentIB joinTourIB;

    public JoinTournamentController(Object bracketRepo, Object accountRepo, String currUser){
        JoinTournamentOB outputBound = new JoinTournamentPresenter();
        JoinTournamentGateway gateway = new JoinTournamentFileWriter("accounts.txt", "brackets.txt");
        this.joinTourIB = new JoinTournamentUC(outputBound, gateway, bracketRepo, accountRepo, currUser);
    }

    public JoinTournamentOD joinTournament(String invite){
        JoinTournamentID inputData = new JoinTournamentID(invite);
        return joinTourIB.joinBracket(inputData);
    }
}
