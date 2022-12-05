package interface_adapters.join_tournament;

import interface_adapters.NextScreenData;
import interface_adapters.data_interface_adapters.join_tournament_data.JoinTournamentFileWriter;
import use_cases.general_classes.InformationRecord;
import use_cases.join_tournament.*;

public class JoinTournamentController {
    final private JoinTournamentIB joinTourIB;
    private final JoinTournamentPresenter presenter;

    public JoinTournamentController(InformationRecord informationRecord, String currUser){
        presenter = new JoinTournamentPresenter();
        JoinTournamentGateway gateway = new JoinTournamentFileWriter("accounts.txt", "brackets.txt");
        this.joinTourIB = new JoinTournamentUC(presenter, gateway, informationRecord, currUser);
    }

    public void setPresenterData(NextScreenData nextScreenData){
        this.presenter.setNextScreenData(nextScreenData);
    }

    public JoinTournamentOD joinTournament(String invite){
        JoinTournamentID inputData = new JoinTournamentID(invite);
        return joinTourIB.joinBracket(inputData);
    }
}
