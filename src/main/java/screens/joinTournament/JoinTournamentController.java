package screens.joinTournament;

import database.JoinTournament.JoinTournamentFileWriter;
import useCases.generalClasses.InformationRecord;
import useCases.joinTournament.*;

public class JoinTournamentController {
    final private JoinTournamentIB joinTourIB;

    public JoinTournamentController(InformationRecord informationRecord, String currUser){
        JoinTournamentOB outputBound = new JoinTournamentPresenter();
        JoinTournamentGateway gateway = new JoinTournamentFileWriter("accounts.txt", "brackets.txt");
        this.joinTourIB = new JoinTournamentUC(outputBound, gateway, informationRecord, currUser);
    }

    public JoinTournamentOD joinTournament(String invite){
        JoinTournamentID inputData = new JoinTournamentID(invite);
        return joinTourIB.joinBracket(inputData);
    }
}
