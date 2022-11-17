package useCases.viewTournament;

import entities.AccountRepo;
import entities.User;

public class ViewTournamentUC implements ViewTournamentIB {
    final ViewTournamentOB outputBound;
    private final AccountRepo accountRepo;
    private User currUser;

    public ViewTournamentUC(ViewTournamentOB outputBound, AccountRepo accountRepo, String currUser){
        this.outputBound = outputBound;
        this.accountRepo = accountRepo;
        this.currUser = accountRepo.getUser(currUser);
    }

    public ViewTournamentOD viewBracket(ViewTournamentID input){
        int tournamentID = input.getTournamentID();

        if (!currUser.getAllTournaments().contains(tournamentID)){
            return outputBound.prepareFailView("You have not joined this tournament yet.");
        }

        String role = currUser.getBracketRole(tournamentID);
        ViewTournamentOD output = new ViewTournamentOD(tournamentID, role);

        return outputBound.prepareSuccessView(output);
    }
}
