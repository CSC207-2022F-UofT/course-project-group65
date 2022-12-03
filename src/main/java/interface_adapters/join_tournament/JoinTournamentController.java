package interface_adapters.join_tournament;

import interface_adapters.data_interface_adapters.join_tournament_data.JoinTournamentFileWriter;
import use_cases.general_classes.InformationRecord;
import use_cases.join_tournament.*;

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
