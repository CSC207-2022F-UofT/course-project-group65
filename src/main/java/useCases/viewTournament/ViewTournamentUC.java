package useCases.viewTournament;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.User;

public class ViewTournamentUC implements ViewTournamentIB {
    final ViewTournamentOB outputBound;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;
    private User currUser;

    /**
     * Construct a ViewTournamentUC interactor instance with the given BracketRepo and AccountRepo.
     *
     * @param outputBound The output boundary to use
     * @param bracketRepo The BracketRepo to use
     * @param accountRepo The AccountRepo to use
     * @param currUser    The username of the user who is joining the tournament
     */
    public ViewTournamentUC(ViewTournamentOB outputBound, BracketRepo bracketRepo,
                            AccountRepo accountRepo, String currUser){
        this.outputBound = outputBound;
        this.bracketRepo =bracketRepo;
        this.accountRepo = accountRepo;
        this.currUser = accountRepo.getUser(currUser);
    }

    /**
     * Allows the user to access a tournament that they have already joined
     *
     * @param input the inputData to use
     * @return the output data
     */
    public ViewTournamentOD viewBracket(ViewTournamentID input){
        int tournamentID = input.getTournamentID();

        if (!currUser.getAllTournaments().contains(tournamentID)){
            return outputBound.prepareFailView("You have not joined this tournament yet.");
        }

        String role = currUser.getBracketRole(tournamentID);
        ViewTournamentOD output = new ViewTournamentOD(currUser.getUsername(), accountRepo,
                bracketRepo, tournamentID, role);

        return outputBound.prepareSuccessView(output);
    }
}
