package useCases.viewTournament;

import entities.User;

public class ViewTournamentUC implements ViewTournamentIB {
    final ViewTournamentOB outputBound;

    public ViewTournamentUC(ViewTournamentOB outputBound){
        this.outputBound = outputBound;
    }

    public ViewTournamentOD viewBracket(ViewTournamentID input){
        int tournamentID = input.getTournamentID();
        User currUser = input.getCurrUser();

        if (!currUser.getAllTournaments().contains(tournamentID)){
            return outputBound.prepareFailView("You have not joined this tournament yet.");
        }

        String role = currUser.getBracketRole(tournamentID);
        ViewTournamentOD output = new ViewTournamentOD(tournamentID, role);

        return outputBound.prepareSuccessView(output);
    }
}
