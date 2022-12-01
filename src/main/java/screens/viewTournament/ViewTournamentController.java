package screens.viewTournament;

import database.ViewTournament.ViewTournamentFileWriter;
import useCases.viewTournament.*;

public class ViewTournamentController {
    final private ViewTournamentIB viewTourIB;

//    public ViewTournamentController(ViewTournamentIB viewTourIB) {
//        this.viewTourIB = viewTourIB;
//    }
    public ViewTournamentController(Object bracketRepo, Object accountRepo, String currUser) {
        ViewTournamentOB viewTourOB = new ViewTournamentPresenter();
        ViewTournamentGateway gateway = new ViewTournamentFileWriter("brackets.txt");
        this.viewTourIB = new ViewTournamentUC(viewTourOB, gateway, bracketRepo, accountRepo, currUser);
    }
    public ViewTournamentOD viewTournament(int tournamentID){
        ViewTournamentID inputData = new ViewTournamentID(tournamentID);
        return viewTourIB.viewBracket(inputData);
    }
}
