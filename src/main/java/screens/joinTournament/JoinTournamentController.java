package screens.joinTournament;

import useCases.joinTournament.JoinTournamentIB;
import useCases.joinTournament.JoinTournamentID;
import useCases.joinTournament.JoinTournamentOD;

public class JoinTournamentController {
    final private JoinTournamentIB joinTourIB;

    public JoinTournamentController(JoinTournamentIB joinTourIB) {
        this.joinTourIB = joinTourIB;
    }

    public JoinTournamentOD joinTournament(String invite){
        JoinTournamentID inputData = new JoinTournamentID(invite);
        return joinTourIB.joinBracket(inputData);
    }
}
