package screens.viewTournament;

import useCases.viewTournament.ViewTournamentIB;
import useCases.viewTournament.ViewTournamentID;
import useCases.viewTournament.ViewTournamentOD;

public class ViewTournamentController {
        final private ViewTournamentIB viewTourIB;

        public ViewTournamentController(ViewTournamentIB viewTourIB) {
            this.viewTourIB = viewTourIB;
        }

        public ViewTournamentOD viewTournament(int tournamentID){
            ViewTournamentID inputData = new ViewTournamentID(tournamentID);
            return viewTourIB.viewBracket(inputData);
        }
}
