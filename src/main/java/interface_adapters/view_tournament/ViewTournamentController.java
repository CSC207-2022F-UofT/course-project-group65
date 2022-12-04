package interface_adapters.view_tournament;

import interface_adapters.NextScreenData;
import interface_adapters.data_interface_adapters.view_tournament_data.ViewTournamentFileWriter;
import use_cases.general_classes.InformationRecord;
import use_cases.view_tournament.*;

public class ViewTournamentController {
    final private ViewTournamentIB viewTourIB;
    private ViewTournamentPresenter viewTourPresenter;

    public ViewTournamentController(InformationRecord informationRecord, String currUser) {
        this.viewTourPresenter = new ViewTournamentPresenter();
        ViewTournamentGateway gateway = new ViewTournamentFileWriter("brackets.txt");
        this.viewTourIB = new ViewTournamentUC(this.viewTourPresenter, gateway, informationRecord, currUser);
    }

    public void setPresenterData(NextScreenData nextScreenData) {
        viewTourPresenter.setNextScreenData(nextScreenData);
    }

    public void viewTournament(int tournamentID){
        ViewTournamentID inputData = new ViewTournamentID(tournamentID);
        viewTourIB.viewBracket(inputData);
    }
}
