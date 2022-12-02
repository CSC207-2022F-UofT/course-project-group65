package screens.viewTournament;

import database.ViewTournament.ViewTournamentFileWriter;
import use_cases.general_classes.InformationRecord;
import use_cases.view_tournament.*;

public class ViewTournamentController {
    final private ViewTournamentIB viewTourIB;

//    public ViewTournamentController(ViewTournamentIB viewTourIB) {
//        this.viewTourIB = viewTourIB;
//    }
    public ViewTournamentController(InformationRecord informationRecord, String currUser) {
        ViewTournamentOB viewTourOB = new ViewTournamentPresenter();
        ViewTournamentGateway gateway = new ViewTournamentFileWriter("brackets.txt");
        this.viewTourIB = new ViewTournamentUC(viewTourOB, gateway, informationRecord, currUser);
    }
    public ViewTournamentOD viewTournament(int tournamentID){
        ViewTournamentID inputData = new ViewTournamentID(tournamentID);
        return viewTourIB.viewBracket(inputData);
    }
}
